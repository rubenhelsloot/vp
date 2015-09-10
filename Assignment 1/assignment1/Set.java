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

	@Override
	public Set init() {
		setLength = 0;
		return this;
	}

	@Override
	public Set addElement(Identifier id) throws ArrayIndexOutOfBoundsException {
		for (int i = 0; i < setLength; i++) {
			if (set[i].equals(id)) {
				return this;
			}
		}
		set[setLength] = id;
		setLength++;
		return this;
	}

	@Override
	public Set removeElement(Identifier id) {
		if (isEmpty()) {
			return this;
		}

		for (int i = 0; i < setLength; i++) {
			if (set[i].equals(id)) {
				for (int j = i; j < setLength; j++) {
					set[j] = set[j + 1];
				}
				setLength--;
			}
		}
		return this;
	}

	@Override
	public int getSize() {
		return setLength;
	}

	@Override
	public boolean isEmpty() {
		return setLength == 0;
	}

	@Override
	public Identifier getRandom() {
		return set[setLength - 1];
	}

	@Override
	public Identifier getRemove() {
		Identifier ident = set[setLength - 1];
		removeElement(ident);
		return ident;
	}

	@Override
	public Set union(Set S) throws ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set difference(Set S) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set intersection(Set S) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set symmetricDifference(Set S) throws ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

}