package assignment2;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	
	public static final String DEFAULT_DELIMITER = "";
	public static final char OPEN = '{', CLOSE = '}', INNER_OPEN = '(', INNER_CLOSE = ')',
			QUESTION = '?', COMMENT = '/', ASSIGN = '=', SPACE = ' ',
			UNION = '+', DIFFERENCE = '-', INTERSECTION = '*', SYM_DIFF = '|';
	Scanner in;
	
	Main() {
		in = new Scanner(System.in);
		in.useDelimiter(DEFAULT_DELIMITER);
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
	
	void readInput(Scanner in) {
		
	}
	
	void start() {
		readInput(in);
	}

	public static void main(String[] args) {
		while(true) {
			new Main().start();
		}
	}

}
