package assignment3;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	String DESCENDING = "-d", CASE_INSENSITIVITY = "-i", DELIMITER = "[^a-zA-Z0-9]+";
	boolean caseInsensitive, descending;
	SortingTree<Identifier> tree;

	Main() {
		caseInsensitive = descending = false;
		tree = new SortingTree<Identifier>();
	}

	private boolean hasNext(Scanner in) {
		return in.hasNext();
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

	private int options(String[] args) throws APException {
		int options = 0;

		if (args.length == options) {
			throw new APException("No arguments were received");
		}
		for (int i = 0; i < Math.min(args.length, 2); i++) {
			if (args[i].equals(DESCENDING)) {
				descending = true;
				options++;
			}
			if (args[i].equals(CASE_INSENSITIVITY)) {
				caseInsensitive = true;
				options++;
			}
		}
		return options;
	}

	private void print(Iterator<Identifier> iterator) throws APException {
		Identifier current = new Identifier();
		Identifier next = new Identifier();
		int counter = 1;
		
		if (iterator.hasNext()) {
			current = iterator.next();	
		} else 
			throw new APException("The tree is empty, no output found");
		
		while(iterator.hasNext()) {
			if(iterator.hasNext()) {
				next = iterator.next();
				if(current.compareTo(next) == 0) {
					counter++;
				}
			}
			
			if (counter % 2 == 1) {
				print(current);
			}
			
			current = next.clone();
			counter = 1;
			
		}
	}
	
	private void print(Identifier id) {
		System.out.println(id.stringify());
	}

	private boolean isIdentifier(String input) {
		Scanner in = new Scanner(input);
		in.useDelimiter("");

		if (!hasNextCharIsLetter(in)) {
			return false;
		}

		while (hasNext(in)) {
			if (!hasNextCharIsAlphaNumerical(in)) {
				return false;
			}
			nextChar(in);
		}

		return true;
	}

	private Identifier toIdentifier(String input) {
		Scanner in = new Scanner(input);
		in.useDelimiter("");
		Identifier result = new Identifier();

		while (hasNextCharIsAlphaNumerical(in)) {
			result.add(nextChar(in));
		}

		in.close();
		return result;
	}

	private void processInput(Scanner in) {
		while (hasNext(in)) {
			String id = in.next();

			if (caseInsensitive) {
				id = id.toLowerCase();
			}

			if (isIdentifier(id)) {
				tree.insert(toIdentifier(id));
			}
		}
	}

	private Scanner[] readScanner(String[] args, int start) throws APException {
		Scanner[] scanners = new Scanner[args.length];
		boolean files = false;
		for (int i = start; i < args.length; i++) {
			File file = new File(args[i]);
			files = true;

			try {
				scanners[i] = new Scanner(file);
				scanners[i].useDelimiter(DELIMITER);
			} catch (FileNotFoundException e) {
				System.out.println("File " + file + " not found");
				throw new APException("Incorrect input");
			}
		}

		if (!files)
			throw new APException("No input files were received, try again");

		return scanners;
	}

	private void start(String[] args) {
		try {
			int options = options(args);
			tree.init();

			Scanner[] scannerArray = readScanner(args, options);
			for (Scanner fileScanner : scannerArray) {
				if (fileScanner != null) {
					processInput(fileScanner);
				}
			}

			Iterator<Identifier> iterator = !descending ? tree.ascendingIterator() : tree.descendingIterator();
			print(iterator);
		} catch (APException e) {
			System.out.println(e.toString());
		}
		
//		for(String s : args) {
//			System.out.println(s);
//		}
	}

	public static void main(String[] args) {
		new Main().start(args);
	}

}
