package assignment1;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

	public static final int MAX_INPUT_SIZE = 10;
	public static final String DEFAULT_DELIMITER = "", QUESTION_ONE = "Give the first set: ",
			QUESTION_TWO = "Give the second set: ";
	public static final char OPEN = '{', CLOSE = '}', SPACE = ' ';

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
			nextChar(in); // Move over the whitespace
		}
	}

	private void errorMessage(String s, Scanner in) {
		System.out.println(s);
		in.nextLine();
	}

	private boolean firstElement(Scanner in) {
		if (!hasNextCharIsSpecial(in, OPEN)) {
			if (hasNextCharIsNewLine(in)) {
				in.nextLine();
				return false;
			}

			errorMessage("The input should start with the opening tag, " + OPEN + ".", in);
			return false;
		}
		return true;
	}

	private boolean parseInput(Set answer, Scanner in, Identifier id) {
		if (!hasNextCharIsLetter(in)) {
			if (!hasNextCharIsAlphaNumerical(in)) {
				errorMessage("Every identifier should only contain alphanumerical characters.", in);
				return false;
			}

			errorMessage("Every identifier should start with a letter.", in);
			return false;
		}

		id.init(nextChar(in));
		while (hasNextCharIsAlphaNumerical(in)) {
			id = id.add(nextChar(in));
		}

		if (hasNextCharIsNewLine(in)) {
			errorMessage("The input should end with the closing tag, " + CLOSE + ".", in);
			return false;
		}

		answer.addElement(id);

		if (answer.getSize() > MAX_INPUT_SIZE) {
			errorMessage("The input contains more than the maximum of " + MAX_INPUT_SIZE + " elements.", in);
			return false;
		}
		removeWhiteSpace(in);
		return true;
	}

	private boolean readInput(Set answer, Scanner in) {
		removeWhiteSpace(in);

		if (!firstElement(in)) {
			return false;
		} else {
			nextChar(in);
		}

		removeWhiteSpace(in);

		while (!hasNextCharIsSpecial(in, CLOSE)) {
			Identifier id = new Identifier();
			if (!parseInput(answer, in, id)) {
				return false;
			}
		}

		nextChar(in);
		removeWhiteSpace(in);
		if (hasNextCharIsNewLine(in)) {
			in.nextLine();
			return true;
		}

		errorMessage("There are characters outside of the domain, please let " + CLOSE
				+ " be the last element in your input.", in);
		return false;
	}

	private boolean question(Scanner in, String question, Set answer) {
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
		Scanner in = new Scanner(System.in);
		in.useDelimiter(DEFAULT_DELIMITER);
		return question(in, QUESTION_ONE, first) && question(in, QUESTION_TWO, second);
	}

	private void print(Set answer, String prelude) {
		System.out.print(prelude + ": {");
		while (answer.getSize() > 0) {
			Identifier id = answer.getRemove();
			System.out.print(id.get().sb);

			// For the last element, don't print the space at the end
			if (answer.getSize() >= 1) {
				System.out.print(" ");
			}
			answer.removeElement(id);
		}
		System.out.print("}\n");
	}

	void start() {
		Set first, second;
		first = new Set();
		second = new Set();

		first.init();
		second.init();

		while (input(first, second)) {
			try {
				print(first.union(second), "Union");
				print(first.difference(second), "Difference");
				print(first.intersection(second), "Intersection");
				print(first.symmetricDifference(second), "Symmetric difference");
			} catch (Exception e) {
				System.out.println("The program was unable to construct a valid result for you and provided"
						+ " the following error message");
				System.out.println(e);
			}
		}
	}

	public static void main(String[] args) {
		new assignment1.Main().start();
	}
}