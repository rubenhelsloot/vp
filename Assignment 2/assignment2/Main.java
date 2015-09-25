package assignment2;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

	public static final String DEFAULT_DELIMITER = "";
	public static final char SET_OPEN = '{', SET_CLOSE = '}', INNER_OPEN = '(', INNER_CLOSE = ')', QUESTION = '?',
			COMMENT = '/', ASSIGN = '=', SPACE = ' ', UNION = '+', DIFFERENCE = '-', INTERSECTION = '*', SYM_DIFF = '|',
			SET_DELIMITER = ',';
	Table<Identifier, Set<NaturalNumber>> table = new Table<Identifier, Set<NaturalNumber>>();

	Main() {
		table.init();
	}

	private char nextChar(Scanner in) {
		return in.next().charAt(0);
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
		return hasNextCharIsSpecial(in, '\n') || hasNextCharIsSpecial(in, '\r');
	}

	private void removeWhiteSpace(Scanner in) {
		while (hasNextCharIsSpecial(in, SPACE)) {
			in.next(); // Move over the whitespace
		}
	}

	private void printSet(Set<NaturalNumber> S) {
		StringBuffer result = new StringBuffer().append(SET_OPEN);
		
				
		while(S.getSize() > 0) {
			NaturalNumber N = S.getRemove();
			result.append(N.data.toString());
			
			if(S.getSize() > 0) {
				result.append(SET_DELIMITER).append(SPACE);
			}
		}
		
		result.append(SET_CLOSE);
		System.out.println(result.toString());
	}

	private Identifier readIdentifier(Scanner in) throws APException {
		removeWhiteSpace(in);
		Identifier result = new Identifier();
		result.init();

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

		return result;
	}

	private Set<NaturalNumber> readSet(Scanner in) throws APException {
		Set<NaturalNumber> result = new Set<NaturalNumber>();
		result.init();

		if (hasNextCharIsSpecial(in, SET_OPEN)) {
			nextChar(in);
			removeWhiteSpace(in);

			// Might need to be if statement, debug by counting number of loops
			while (!hasNextCharIsSpecial(in, SET_CLOSE)) {
				removeWhiteSpace(in);
				result.addElement(readNaturalNumber(in));
				removeWhiteSpace(in);

				while (hasNextCharIsSpecial(in, SET_DELIMITER)) {
					nextChar(in);
					removeWhiteSpace(in);
					result.addElement(readNaturalNumber(in));
					removeWhiteSpace(in);
				}
			}
			nextChar(in);

		} else if (hasNextCharIsLetter(in)) {
			Identifier key = readIdentifier(in);
			result = table.getValue(key).clone();

		} else {
			throw new APException("An expression should either start with a set or the name of a saved set");
		}
		
		removeWhiteSpace(in);
		if(!hasNextCharIsSpecial(in, INTERSECTION)) {
			return result;
		} else {
			return readPriorityPartial(result, in);
		}
	}
	
	private Set<NaturalNumber> readPriorityPartial(Set<NaturalNumber> set1, Scanner in) throws APException {
		Set<NaturalNumber> result = new Set<NaturalNumber>();
		result.init();
		
		char operator = nextChar(in);

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
				result = readExpression(in);
			}
			nextChar(in);
		} else {
			result = readSet(in);
		}

		return result;
	}

	private Set<NaturalNumber> readExpression(Scanner in) throws APException {
		// Set<NaturalNumber> result = new Set<NaturalNumber>();
		// result.init();
		removeWhiteSpace(in);

		Set<NaturalNumber> set1 = readPartial(in);
		removeWhiteSpace(in);
		
		if (in.hasNext()) {
			char operator = nextChar(in);
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
			throw new APException("The operator was invalid " + operator);
		}
	}

	private void saveStatement(Scanner in) throws APException {
		Identifier key = readIdentifier(in);
		removeWhiteSpace(in);

		if (!hasNextCharIsSpecial(in, ASSIGN)) {
			throw new APException("A value should be assigned here");
		} else {
			nextChar(in);
			removeWhiteSpace(in);
			Set<NaturalNumber> value = readExpression(in);
			table.update(key, value);
		}
	}

	private void printStatement(Scanner in) throws APException {
		removeWhiteSpace(in);
		
		Set<NaturalNumber> value = readExpression(in);
		printSet(value);
	}

	private void readInput(String input) throws APException {
		System.out.println(input);
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
				saveStatement(parser);
			} else
				throw new APException("Start of line is invalid");
		}
	}

	private void start() {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			try {
				readInput(in.nextLine());
			} catch (APException e) {
				System.out.println(e);
			}
		}
		in.close();
	}

	public static void main(String[] args) {
		new Main().start();
	}

}
