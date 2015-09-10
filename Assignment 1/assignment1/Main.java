package assignment1;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

	public static final int MAX_INPUT_SIZE = 10;
	public static String DEFAULT_DELIMITER = " ", QUESTION_ONE = "Give the first set: ",
			QUESTION_TWO = "Give the second set: ";
	public static char OPEN = '{', CLOSE = '}', SPACE = ' ', BETWEENER = ' ';

	private Scanner in;

	Main() {
		in = new Scanner(System.in);
		in.useDelimiter(DEFAULT_DELIMITER);
	}

	private char nextChar(Scanner in) {
		return in.next().charAt(0);
	}

	private boolean nextCharIsSpecial(Scanner in, char c) {
		return in.hasNext(c + "");
	}

	private boolean nextCharIsDigit(Scanner in) {
		return in.hasNext("[0-9]");
	}

	private boolean nextCharIsLetter(Scanner in) {
		return in.hasNext("[a-zA-Z]");
	}

	private boolean nextCharIsAlphaNumerical(Scanner in) {
		return (nextCharIsDigit(in) || nextCharIsLetter(in));
	}

	private boolean nextCharIsSpace(Scanner in) {
		return in.hasNext(String.valueOf(SPACE));
	}

	private boolean nextCharIsNewLine(Scanner in) {
		return nextCharIsSpecial(in, '\n') || nextCharIsSpecial(in, '\r');
	}

	private void removeWhiteSpace(Scanner in) {
		while (nextCharIsSpace(in)) {
			in.next(); // Pass over the whitespace.
		}
	}

	private boolean firstElement(Scanner in) {
		if (!nextCharIsSpecial(in, OPEN)) {
			if (nextCharIsNewLine(in)) {
				System.out.println("New line");
				in.nextLine();
				return false;
			}
			System.out.println("The input should start with the opening tag, " + OPEN + ".");
			return false;
		}
		return true;
	}

//	private boolean parseInput(Set answer, Scanner in) {
//		if (!nextCharIsLetter(in)) {
//			System.out.println("The identifier should start with a letter.");
//			in.nextLine();
//			return false;
//		}
//
//		Identifier id = new Identifier();
//		id.init(nextChar(in));
//		while (nextCharIsAlphaNumerical(in)) {
//			id = id.add(nextChar(in));
//		}
//
//		if (nextCharIsNewLine(in)) {
//			System.out.println("The input should end with the closing tag, " + CLOSE + ".");
//			in.nextLine();
//			return false;
//		}
//
//		if (nextCharIsSpecial(in, CLOSE) || nextCharIsSpecial(in, DEFAULT_DELIMITER.charAt(0))) {
//			System.out.println("The input can only consists of numbers and letters, a closing tag " + CLOSE
//					+ " and an opening tag " + OPEN + ".");
//			in.nextLine();
//			return false;
//		}
//
//		answer.addElement(id);
//
//		if (answer.getSize() > MAX_INPUT_SIZE) {
//			System.out.println("The input contains more than the maximum of " + MAX_INPUT_SIZE + " elements.");
//			in.nextLine();
//			return false;
//		}
//		removeWhiteSpace(in);
//
//		return true;
//	}

	private boolean readInput(Set answer, Scanner in) {
		removeWhiteSpace(in);

		if (firstElement(in)) {
			System.out.println("Is first");
			return false;
		}

		in.next();
		removeWhiteSpace(in);

		while (!nextCharIsSpecial(in, CLOSE)) {
			if (!nextCharIsLetter(in)) {
				System.out.println("The identifier should start with a letter.");
				in.nextLine();
				return false;
			}

			Identifier id = new Identifier();
			id.init(nextChar(in));
			while (nextCharIsAlphaNumerical(in)) {
				id = id.add(nextChar(in));
			}

			if (nextCharIsNewLine(in)) {
				System.out.println("The input should end with the closing tag, " + CLOSE + ".");
				in.nextLine();
				return false;
			}

			if (nextCharIsSpecial(in, CLOSE) || nextCharIsSpecial(in, DEFAULT_DELIMITER.charAt(0))) {
				System.out.println("The input can only consists of numbers and letters, a closing tag " + CLOSE
						+ " and an opening tag " + OPEN + ".");
				in.nextLine();
				return false;
			}

			answer.addElement(id);

			if (answer.getSize() > MAX_INPUT_SIZE) {
				System.out.println("The input contains more than the maximum of " + MAX_INPUT_SIZE + " elements.");
				in.nextLine();
				return false;
			}
			removeWhiteSpace(in);
		}
		
		in.next();
		removeWhiteSpace(in);
		if(nextCharIsNewLine(in)) {
			in.nextLine();
			return true;
		}
		
		System.out.println("There are characters outside of the domain, please let " + CLOSE + " be the last element in your input.");
		in.nextLine();
		return false;
	}

	private boolean question(String question, Set answer) {
		do {
			answer.init();
			System.out.print(question);
			if (!in.hasNext()) {
				in.close();
				return false;
			}
		} while (!readInput(answer, in));

		return true;
	}

	private boolean input(Set first, Set second) {
		return question(QUESTION_ONE, first) && question(QUESTION_TWO, second);
	}

	void start() {
		Set first, second;
		first = new Set();
		second = new Set();

		first.init();
		second.init();
		
		while (input(first, second)) {
			System.out.println("HEWWWWROO");
		}
	}

	public static void main(String[] args) {
		new assignment1.Main().start();
	}
}
