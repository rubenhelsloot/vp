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
	public boolean contains(Identifier I) {
		for(int i = 0; i < this.getSize(); i++) {
			if(this.set[i].equals(I)) {
				return true;
			}
		} 
		return false;
	}

	@Override
	public Set union(Set S) throws ArrayIndexOutOfBoundsException { //set will never get too big?
		Set ownSet = new Set(this);
		Set inputSet = new Set(S);
		
		while(inputSet.getSize() > 0) {
			Identifier currentIdentifier = inputSet.getRemove();
			if(!ownSet.contains(currentIdentifier)) {
				ownSet.addElement(currentIdentifier); 
			}
		}
		return ownSet;
	}

	@Override
	public Set difference(Set S) {
		Set differenceSet = new Set(this);
		Set inputSet = new Set(S);
		
		while(inputSet.getSize() > 0) {
			Identifier currentIdentifier = inputSet.getRemove();
			if(differenceSet.contains(currentIdentifier)) {
				differenceSet.removeElement(currentIdentifier);
			}
		}
		return differenceSet;
	}

	@Override
	public Set intersection(Set S) {
		Set inputSet = new Set(S);
		Set intersectionSet = new Set();
		
		while(inputSet.getSize() > 0) {
			Identifier currentIdentifier = inputSet.getRemove();
			if(this.contains(currentIdentifier)) {
				intersectionSet.addElement(currentIdentifier);
			}
		}
		return intersectionSet;
	}

	@Override
	public Set symmetricDifference(Set S) throws ArrayIndexOutOfBoundsException { // hoeft toch niet?
		Set differenceSet1 = new Set(this.difference(S));
		Set differenceSet2 = new Set(S.difference(this));
		Set symmDiffSet = new Set(differenceSet1.union(differenceSet2));
		return symmDiffSet;
	}

}