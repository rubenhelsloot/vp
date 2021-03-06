package assignment1;

class Set implements SetInterface {

	private static int MAX_SET_SIZE = 20;
	private static String tooLargeSet = "The set contains more than " + MAX_SET_SIZE + " elements.";
	private Identifier[] set;
	int setLength;

	Set() {
		set = new Identifier[MAX_SET_SIZE];
		setLength = 0;
	}

	Set(Set origin) {
		set = new Identifier[MAX_SET_SIZE];
		setLength = origin.getSize();
		Set copy = origin.copy();
		for (int i = 0; i < setLength; i++) {
			set[i] = new Identifier(copy.getRandom());
			copy.removeElement(set[i]);
		}
	}

	private Set copy() {
		Set copy = new Set();

		for (int i = 0; i < setLength; i++) {
			try {
				copy.addElement(new Identifier(set[i]));
			} catch (ArrayIndexOutOfBoundsException error) {
				System.out.println(tooLargeSet);
			}
		}
		return copy;
	}

	public Set init() {
		setLength = 0;
		return this;
	}

	public Set addElement(Identifier id) {

		for (int i = 0; i < setLength; i++) {
			if (set[i].equals(id)) {
				return this;
			}
		}
		set[setLength] = id;
		setLength++;
		return this;
	}

	public Set removeElement(Identifier id) {

		if (isEmpty()) {
			return this;
		}

		for (int i = 0; i < setLength; i++) {
			if (set[i].equals(id)) {
				set[i] = set[setLength - 1];
				i--;
				setLength--;
			}
		}
		return this;
	}

	public int getSize() {
		return setLength;
	}

	public boolean isEmpty() {
		return setLength == 0;
	}

	public Identifier getRandom() {
		return set[setLength - 1];
	}

	public Identifier getRemove() {
		Identifier ident = set[setLength - 1];
		removeElement(ident);
		return ident;
	}

	public boolean contains(Identifier ident) {
		for (int i = 0; i < getSize(); i++) {
			if (set[i].equals(ident)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Set union(Set S) throws ArrayIndexOutOfBoundsException {
		Set union = new Set(this);
		Set inputSet = new Set(S);

		while (inputSet.getSize() > 0) {
			Identifier currentIdentifier = inputSet.getRemove();
			if (!contains(currentIdentifier)) {
				union.addElement(currentIdentifier);
			}
		}
		return union;
	}

	public Set difference(Set S) {
		Set inputSet = new Set(S);
		Set difference = new Set(this); 

		while (inputSet.getSize() > 0) {
			Identifier currentIdentifier = inputSet.getRemove();
			if (difference.contains(currentIdentifier)) {
				difference.removeElement(currentIdentifier);
			}
		}
		return difference;
	}

	public Set intersection(Set S) {
		return difference(difference(S));
	}

	public Set symmetricDifference(Set S) throws ArrayIndexOutOfBoundsException {
		return difference(S).union(S.difference(this));
	}

}