package assignment3;

import java.util.Scanner;
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
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(DESCENDING)) {
				descending = true;
				options++;
			} else if (args[i].equals(CASE_INSENSITIVITY)) {
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

		while (iterator.hasNext()) {
			while (iterator.hasNext()) {
				next = iterator.next();
				if (current.compareTo(next) == 0) {
					counter++;
				} else
					break;
			}

			if (counter % 2 == 1) {
				print(current);
			}

			if (iterator.hasNext()) {
				current = next.clone();
				counter = 1;
			} else {
				if (current.compareTo(next) != 0) {
					print(next);
				}
			}
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

			if (caseInsensitive)
				id = id.toLowerCase();

			if (isIdentifier(id))
				tree.insert(toIdentifier(id));
		}
	}

	private Scanner[] readScanner(String[] args, int options) throws APException {
		Scanner[] scanners = new Scanner[args.length - options];
		for (int i = 0; i < (args.length - options); i++) {
			File file = new File(args[i + options]);

			try {
				scanners[i] = new Scanner(file);
				scanners[i].useDelimiter(DELIMITER);
			} catch (FileNotFoundException e) {
				throw new APException("File " + file + " not found");
			}
		}

		if (options >= args.length)
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
				} else
					throw new APException("Invalid filescanner, file could not be scanned");
			}

			Iterator<Identifier> iterator = descending ? tree.descendingIterator() : tree.ascendingIterator();
			print(iterator);
		} catch (APException e) {
			System.out.println(e.toString());
		}
	}

	public static void main(String[] args) {
		new Main().start(args);
	}

}
