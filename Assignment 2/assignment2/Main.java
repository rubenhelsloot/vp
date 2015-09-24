package assignment2;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

	public static final String DEFAULT_DELIMITER = "";
	public static final char OPEN = '{', CLOSE = '}', INNER_OPEN = '(', INNER_CLOSE = ')', QUESTION = '?',
			COMMENT = '/', ASSIGN = '=', SPACE = ' ', UNION = '+', DIFFERENCE = '-', INTERSECTION = '*', SYM_DIFF = '|';
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

	private void errorMessage(String s, Scanner in) {
		System.out.println(s);
		in.nextLine();
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
		
		if(hasNextCharIsSpecial(in, '0')) {
			result.init(nextChar(in));
			if(hasNextCharIsDigit(in)) {
				throw new APException("Nonzero numbers should never start with a zero.");
			} else {
				return result;
			}
		}
		
		result.init(nextChar(in));
		while(hasNextCharIsDigit(in)) {
			result.add(nextChar(in));
		}
		
		return result;
	}

	private void saveStatement(Scanner in) throws APException {
		Identifier key = readIdentifier(in);
		removeWhiteSpace(in);
		
		if(!hasNextCharIsSpecial(in, ASSIGN)) {
			throw new APException("A value should be assigned here");
		} else {
			nextChar(in);
			removeWhiteSpace(in);
		}
		
		if()
	}
	
	private void printStatement(Scanner in) {
		removeWhiteSpace(in);
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
