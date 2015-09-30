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

	private boolean hasNextCharEquals(Scanner in, char c) {
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
		return hasNextCharEquals(in, '\n') || hasNextCharEquals(in, '\r');
	}

	private void removeWhiteSpace(Scanner in) {
		while (hasNextCharEquals(in, SPACE)) {
			nextChar(in); // Move over the whitespace
		}
	}

	private boolean firstElement(Scanner in) throws APException {
		if (!hasNextCharEquals(in, OPEN)) {
			if (hasNextCharIsNewLine(in)) {
				in.nextLine();
				return false;
			}

			throw new APException("The input should start with the opening tag, " + OPEN + ".");
		}
		return true;
	}

	private boolean parseInput(Set answer, Scanner in, Identifier id) throws APException {
		if (!hasNextCharIsLetter(in)) {
			if (!hasNextCharIsAlphaNumerical(in)) {
				throw new APException("Every identifier should only contain alphanumerical characters.");
			} else 
				throw new APException("Every identifier should start with a letter.");
		}

		id.init(nextChar(in));
		while (hasNextCharIsAlphaNumerical(in)) {
			id = id.add(nextChar(in));
		}

		if (hasNextCharIsNewLine(in)) {
			throw new APException("The input should end with the closing tag, " + CLOSE + ".");
		}

		answer.addElement(id);

		if (answer.getSize() > MAX_INPUT_SIZE) {
			throw new APException("The input contains more than the maximum of " + MAX_INPUT_SIZE + " elements.");
		}
		removeWhiteSpace(in);
		return true;
	}

	private boolean readInput(Set answer, Scanner in) throws APException {
		removeWhiteSpace(in);

		if (!firstElement(in)) {
			return false;
		} else {
			nextChar(in);
		}

		removeWhiteSpace(in);

		while (!hasNextCharEquals(in, CLOSE)) {
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

		throw new APException("There are characters outside of the domain, please let " + CLOSE
				+ " be the last element in your input.");
	}

	private boolean question(Scanner in, String question, Set answer) throws APException {
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

	private boolean input(Set first, Set second) throws APException {
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

		while (true) {
			try {
				input(first, second);
				print(first.union(second), "Union");
				print(first.difference(second), "Difference");
				print(first.intersection(second), "Intersection");
				print(first.symmetricDifference(second), "Symmetric difference");
			} catch (APException e) {
				System.out.println("The program returned an error: " + e);
			}
		}
	}

	public static void main(String[] args) {
		new assignment1.Main().start();
	}
}