package assignment1;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

	public static final int MAX_INPUT_SIZE = 20;
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
			in.next(); // Pass over the whitespace.
		}
	}

	private boolean firstElement(Scanner in) {
		if (!hasNextCharIsSpecial(in, OPEN)) {
			if (hasNextCharIsNewLine(in)) {
				in.nextLine();
				return false;
			}
			
			System.out.println("The input should start with the opening tag, " + OPEN + ".");
			in.nextLine();
			return false;
		}
		return true;
	}

	private boolean parseInput(Set answer, Scanner in, Identifier id) {
		if (!hasNextCharIsLetter(in)) {
			if(!hasNextCharIsAlphaNumerical(in)) {
				System.out.println("Every identifier should only contain alphanumerical characters.");
				in.nextLine();
				return false;
			}
			
			System.out.println("Every identifier should start with a letter.");
			in.nextLine();
			return false;
		}
		
		id.init(nextChar(in));
		while (hasNextCharIsAlphaNumerical(in)) {
			id = id.add(nextChar(in));
		}

		if (hasNextCharIsNewLine(in)) {
			System.out.println("The input should end with the closing tag, " + CLOSE + ".");
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
		return true;
	 }

	private boolean readInput(Set answer, Scanner in) {
		removeWhiteSpace(in);

		if (!firstElement(in)) {
			return false;
		} else {
			in.next();
		}

		removeWhiteSpace(in);
		
		while (!hasNextCharIsSpecial(in, CLOSE)) {
			Identifier id = new Identifier();
			if(!parseInput(answer, in, id)) {
				return false;
			}
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
	
	private void print(Set answer, String prelude){
		System.out.print(prelude + ": {");
		while(answer.getSize() > 0){
			Identifier id = answer.getRemove();
			System.out.print(id.get().sb);
			
			//For the last element, don't print the space at the end
			if(answer.getSize() >= 1){
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
			print(first.union(second), "Union");
			print(first.difference(second), "Difference");
			print(first.intersection(second), "Intersection");
			print(first.symmetricDifference(second), "Symmetric difference");
		}
	}

	public static void main(String[] args) {
		new assignment1.Main().start();
	}
}