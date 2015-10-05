package assignment2;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

	public static final String DEFAULT_DELIMITER = "";
	public static final char SET_OPEN = '{', SET_CLOSE = '}', INNER_OPEN = '(', INNER_CLOSE = ')', QUESTION = '?',
			COMMENT = '/', ASSIGN = '=', SPACE = ' ', UNION = '+', DIFFERENCE = '-', INTERSECTION = '*', SYM_DIFF = '|',
			SET_DELIMITER = ',', RANGE = '.';
	public String operators = "" + UNION + DIFFERENCE + INTERSECTION + SYM_DIFF;
	Table<Identifier, Set<NaturalNumber>> table = new Table<Identifier, Set<NaturalNumber>>();

	Main() {
		table.init();
	}

	private char nextChar(Scanner in) {
		return in.next().charAt(0);
	}

	private boolean hasNextChar(Scanner in) {
		return in.hasNext();
	}

	private boolean hasNextCharIsSpecial(Scanner in, char c) {
		return in.hasNext(Pattern.quote(c + ""));
	}

	private boolean hasNextCharIsDigit(Scanner in) {
		return in.hasNext("[0-9]");
	}

	private boolean hasNextCharIsLetter(Scanner in) {
		return in.hasNext("[a-zA-Z]");
	}

	private boolean hasNextCharIsAlphaNumerical(Scanner in) {
		return (hasNextCharIsDigit(in) || hasNextCharIsLetter(in));
	}

	private boolean hasNextCharIsNewLine(Scanner in) {
		return hasNextCharIsSpecial(in, '\n') || hasNextCharIsSpecial(in, '\r') || in.hasNextLine();
	}

	private void removeWhiteSpace(Scanner in) throws APException {
		while (hasNextCharIsSpecial(in, SPACE)) {
			nextChar(in);
		}
	}

	private void printSet(Set<NaturalNumber> S) {
		StringBuffer result = new StringBuffer();

		while (S.getSize() > 0) {
			NaturalNumber N = S.getRemove();
			result.append(N.data.toString());

			if (S.getSize() > 0) {
				result.append(SPACE);
			}
		}

		System.out.println(result.toString());
	}

	private char readOperator(Scanner in) throws APException {
		String result = nextChar(in) + "";
		if (operators.contains(result)) {
			return result.charAt(0);
		} else {
			throw new APException("The perceived input '" + result + "' is not a valid operator");
		}
	}

	private Identifier readIdentifier(Scanner in) throws APException {

		removeWhiteSpace(in);
		Identifier result = new Identifier();

		if (hasNextCharIsLetter(in)) {
			result.add(nextChar(in));
		} else {
			throw new APException("Identifiers should start with a letter");
		}

		while (hasNextCharIsAlphaNumerical(in)) {
			result.add(nextChar(in));
		}

		return result;
	}

	private Identifier readIdentifierString(String input) throws APException {
		Scanner in = new Scanner(input);
		in.useDelimiter("");
		Identifier result = new Identifier();

		result = readIdentifier(in);
		removeWhiteSpace(in);
		
		if (hasNextChar(in)) {
			if (hasNextCharIsAlphaNumerical(in)) {
				throw new APException("Identifiers should not contain spaces");
			} else
				throw new APException(
						"Identifiers should only contain alphanumerical characters, the received character was '"
								+ nextChar(in) + "'");
		}

		return result;
	}

	private NaturalNumber readNaturalNumber(Scanner in) throws APException {
		removeWhiteSpace(in);
		NaturalNumber result = new NaturalNumber();

		if (hasNextCharIsSpecial(in, '0')) {
			result.init().add(nextChar(in));
			if (hasNextCharIsDigit(in)) {
				throw new APException("Nonzero numbers should never start with a zero.");
			} else {
				return result;
			}
		}

		result.init();
		while (hasNextCharIsDigit(in)) {
			result.add(nextChar(in));
		}

		if (hasNextCharIsLetter(in)) {
			throw new APException("Letters are not allowed inside a natural number");
		}

		if (result.getSize() > 0) {
			return result;
		} else {
			return null;
		}
	}

	private Set<NaturalNumber> readRange(Set<NaturalNumber> result, Scanner in) throws APException {
		NaturalNumber first = readNaturalNumber(in);
		result.addElement(first);

		if (hasNextCharIsSpecial(in, RANGE)) {
			int counter = 0;
			while (hasNextCharIsSpecial(in, RANGE)) {
				nextChar(in);
				counter++;
			}
			if (counter != 3) {
				throw new APException("Three '" + RANGE + "' were expected, but a different number was received");
			}

			if (hasNextCharIsDigit(in)) {
				NaturalNumber second = readNaturalNumber(in);
				
				for(int i = first.get() + 1; i <= second.get(); i++) {
					String string = Integer.toString(i);
					NaturalNumber Number = new NaturalNumber();
					for (int j = 0; j < string.length(); j++) {
						Number.add(string.charAt(j));
					}
					
					result.addElement(Number);
				}

			} else
				throw new APException(
						"A number was expected to end the range, but '" + nextChar(in) + "' was received");
		}
		return result;
	}

	private Set<NaturalNumber> readNumbers(Scanner in) throws APException {
		Set<NaturalNumber> result = new Set<NaturalNumber>();

		if (!hasNextCharIsDigit(in)) {
			return result;
		}

		while (hasNextChar(in)) {
			if (hasNextCharIsDigit(in)) {
				result = readRange(result, in);
			} else
				throw new APException(
						"A '" + SET_DELIMITER + "' is needed to separate elements of a set, but it was not found");
			if (hasNextCharIsSpecial(in, SET_DELIMITER)) {
				nextChar(in);
				removeWhiteSpace(in);
			} else if (hasNextCharIsSpecial(in, SET_CLOSE)) {
				return result;
			} else if (hasNextChar(in)) {
				throw new APException(
						"A '" + SET_CLOSE + "' was expected to end the set, but '" + nextChar(in) + "' was received");
			}
		}
		throw new APException("A '" + SET_CLOSE + "' was expected to end the set, but nothing was received");
	}

	private Set<NaturalNumber> readSet(Scanner in) throws APException {
		Set<NaturalNumber> result = new Set<NaturalNumber>();

		if (hasNextCharIsSpecial(in, SET_OPEN)) {
			nextChar(in);
			removeWhiteSpace(in);

			if (hasNextChar(in)) {
				result = readNumbers(in);
			} else
				throw new APException("There was no input following the set opening sign, '" + SET_OPEN + "'");

			nextChar(in);

		} else if (hasNextCharIsLetter(in)) {
			Identifier key = readIdentifier(in);
			result = table.getValue(key).clone();

		} else {
			throw new APException("An expression should either start with a set or the name of a saved set");
		}

		removeWhiteSpace(in);
		if (!hasNextCharIsSpecial(in, INTERSECTION)) {
			return result;
		} else {
			return readPartialExpression(result, in);
		}
	}

	private Set<NaturalNumber> readPartialExpression(Set<NaturalNumber> set1, Scanner in) throws APException {
		Set<NaturalNumber> result = new Set<NaturalNumber>();

		removeWhiteSpace(in);
		char operator = readOperator(in);

		removeWhiteSpace(in);
		Set<NaturalNumber> set2 = readPartial(in);

		return parseExpression(set1, operator, set2);
	}

	private Set<NaturalNumber> readPartial(Scanner in) throws APException {
		Set<NaturalNumber> result = new Set<NaturalNumber>();
		result.init();

		if (hasNextCharIsSpecial(in, INNER_OPEN)) {
			nextChar(in);
			removeWhiteSpace(in);

			while (!hasNextCharIsSpecial(in, INNER_CLOSE)) {
				if (!hasNextChar(in)) {
					throw new APException("No closing sign '" + INNER_CLOSE + "' was found");
				}

				result = readExpression(in);
			}
			nextChar(in);
		} else {
			result = readSet(in);
		}

		return result;
	}

	private Set<NaturalNumber> readExpression(Scanner in) throws APException {
		removeWhiteSpace(in);
		Set<NaturalNumber> set1 = readPartial(in);
		removeWhiteSpace(in);

		if (hasNextChar(in) && !hasNextCharIsSpecial(in, INNER_CLOSE)) {
			char operator = readOperator(in);
			removeWhiteSpace(in);

			Set<NaturalNumber> set2 = readPartial(in);

			return parseExpression(set1, operator, set2);
		} else {
			return set1;
		}
	}

	private Set<NaturalNumber> parseExpression(Set<NaturalNumber> set1, char operator, Set<NaturalNumber> set2)
			throws APException {
		switch (operator) {
		case UNION:
			return set1.union(set2);
		case DIFFERENCE:
			return set1.difference(set2);
		case INTERSECTION:
			return set1.intersection(set2);
		case SYM_DIFF:
			return set1.symmetricDifference(set2);
		default:
			throw new APException("The operator '" + operator + "' was invalid");
		}
	}

	private void saveAssignment(Scanner in) throws APException {
		Identifier key = readIdentifierString(in.useDelimiter(ASSIGN + "").next());
		removeWhiteSpace(in.useDelimiter(""));

		if (!hasNextCharIsSpecial(in, ASSIGN)) {
			throw new APException("A value should be assigned here");
		} else {
			nextChar(in);
			removeWhiteSpace(in);

			if (!hasNextChar(in))
				throw new APException("There was no input following the '" + ASSIGN + "' sign");

			Set<NaturalNumber> value = readExpression(in);
			removeWhiteSpace(in);

			while (hasNextChar(in)) {
				value = readPartialExpression(value, in);
				removeWhiteSpace(in);
			}

			table.update(key, value);
		}
	}

	private void printStatement(Scanner in) throws APException {
		removeWhiteSpace(in);

		if (!hasNextChar(in))
			throw new APException("There was no input following the '" + QUESTION + "' sign");

		Set<NaturalNumber> value = readExpression(in);

		while (hasNextChar(in)) {
			value = readPartialExpression(value, in);
			removeWhiteSpace(in);
		}

		printSet(value);
	}

	private void readInput(String input) throws APException {
		Scanner parser = new Scanner(input);
		parser.useDelimiter(DEFAULT_DELIMITER);

		if (parser.hasNext()) {
			removeWhiteSpace(parser);

			if (!parser.hasNext())
				throw new APException("Line should not be empty");
			if (hasNextCharIsSpecial(parser, COMMENT)) {
				return;
			} else if (hasNextCharIsSpecial(parser, QUESTION)) {
				nextChar(parser);
				printStatement(parser);
			} else if (hasNextCharIsLetter(parser)) {
				saveAssignment(parser);
			} else
				throw new APException("Start of line is invalid");
		}
		
		parser.close();
	}

	private void start() {
		Scanner in = new Scanner(System.in);
		while (hasNextCharIsNewLine(in)) {
			try {
				readInput(in.nextLine());
			} catch (APException e) {
				System.out.println(e.toString());
			}
		}
		in.close();
	}

	public static void main(String[] args) {
		new Main().start();
	}

}
