package assignment1;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

	public static final int MAX_INPUT_SIZE = 10;
	public static final String DEFAULT_DELIMITER = "", QUESTION_ONE = "Give the first set: ",
			QUESTION_TWO = "Give the second set: ";
	public static final char OPEN = '{', CLOSE = '}', SPACE = ' ', BETWEENER = ' ';

	private Scanner in;

	Main() {
		in = new Scanner(System.in);
		in.useDelimiter(DEFAULT_DELIMITER);
	}

	private char nextChar(Scanner in) {
		return in.next().charAt(0);
	}

//	private boolean hasNextCharIsSpecial(Scanner in, char c) {
//		String s = Character.toString(c);
//		System.out.println(s.equals(in.next()));
//		return in.hasNext(s);
//	}
	
	private boolean hasNextCharIsSpecial(Scanner in, char c) {
		return in.hasNext(Pattern.quote(c+""));
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

	private boolean hasNextCharIsSpace(Scanner in) {
		return in.hasNext(String.valueOf(SPACE));
	}

	private boolean hasNextCharIsNewLine(Scanner in) {
		return hasNextCharIsSpecial(in, '\n') || hasNextCharIsSpecial(in, '\r');
	}

	private void removeWhiteSpace(Scanner in) {
		while (hasNextCharIsSpace(in)) {
			in.next(); // Pass over the whitespace.
		}
	}

	private boolean firstElement(Scanner in) {
		if (!hasNextCharIsSpecial(in, OPEN)) {
			System.out.println("We are now here.");
			if (hasNextCharIsNewLine(in)) {
				System.out.println("New line");
				in.nextLine();
				return false;
			}
			System.out.println("The input should start with the opening tag, " + OPEN + ".");
			return false;
		}
		return true;
	}

	// private boolean parseInput(Set answer, Scanner in) {
	// if (!hasNextCharIsLetter(in)) {
	// System.out.println("The identifier should start with a letter.");
	// in.nextLine();
	// return false;
	// }
	//
	// Identifier id = new Identifier();
	// id.init(nextChar(in));
	// while (hasNextCharIsAlphaNumerical(in)) {
	// id = id.add(nextChar(in));
	// }
	//
	// if (hasNextCharIsNewLine(in)) {
	// System.out.println("The input should end with the closing tag, " + CLOSE
	// + ".");
	// in.nextLine();
	// return false;
	// }
	//
	// if (hasNextCharIsSpecial(in, CLOSE) || hasNextCharIsSpecial(in,
	// DEFAULT_DELIMITER.charAt(0))) {
	// System.out.println("The input can only consists of numbers and letters, a
	// closing tag " + CLOSE
	// + " and an opening tag " + OPEN + ".");
	// in.nextLine();
	// return false;
	// }
	//
	// answer.addElement(id);
	//
	// if (answer.getSize() > MAX_INPUT_SIZE) {
	// System.out.println("The input contains more than the maximum of " +
	// MAX_INPUT_SIZE + " elements.");
	// in.nextLine();
	// return false;
	// }
	// removeWhiteSpace(in);
	//
	// return true;
	// }

	private boolean readInput(Set answer, Scanner in) {
		removeWhiteSpace(in);

		if (!firstElement(in)) {
			return false;
		} else {
			in.next();
		}
		
		removeWhiteSpace(in);

		while (!hasNextCharIsSpecial(in, CLOSE)) {
			if (!hasNextCharIsLetter(in)) {
				System.out.println("The identifier should start with a letter.");
				in.nextLine();
				return false;
			}
			
			Identifier id = new Identifier();
			id.init(nextChar(in));
			while (hasNextCharIsAlphaNumerical(in)) {
				id = id.add(nextChar(in));
				System.out.println(id.sb);
			}

			if (hasNextCharIsNewLine(in)) {
				System.out.println("The input should end with the closing tag, " + CLOSE + ".");
				in.nextLine();
				return false;
			}
			
//			if (hasNextCharIsSpecial(in, CLOSE) || hasNextCharIsSpecial(in, SPACE)) {
//				System.out.println("The truth? YOU CAN\'T HANDLE THE TRUTH! ");
//				System.out.println("The input can only consists of numbers and letters, a closing tag " + CLOSE
//						+ " and an opening tag " + OPEN + ".");
//				in.nextLine();
//				return false;
//			}
			
			System.out.println(id.sb);
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
		if (hasNextCharIsNewLine(in)) {
			in.nextLine();
			return true;
		}

		System.out.println("There are characters outside of the domain, please let " + CLOSE
				+ " be the last element in your input.");
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