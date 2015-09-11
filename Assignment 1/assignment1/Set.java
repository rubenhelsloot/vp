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
				System.out.println("The element you are trying to add is already in the Set.");
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
				set[i] = set[setLength - 1];
				i--;
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
	public Set union(Set S) throws ArrayIndexOutOfBoundsException {
		Set union = new Set(this);
		Set inputSet = new Set(S);
		
		while(inputSet.getSize() > 0) {
			Identifier currentIdentifier = inputSet.getRemove();
			if(!contains(currentIdentifier)) {
				addElement(currentIdentifier); 
			}
		}
		return union;
	}

	@Override
	public Set difference(Set S) {
		Set inputSet = new Set(S);
		Set difference = new Set();
		
		while(inputSet.getSize() > 0) {
			Identifier currentIdentifier = inputSet.getRemove();
			if(difference.contains(currentIdentifier)) {
				difference.removeElement(currentIdentifier);
			}
		}
		return difference;
	}

	@Override
	public Set intersection(Set S) {
		Set inputSet = new Set(S);
		Set intersection = new Set();
//		intersection.init();
		
		while(inputSet.getSize() > 0) {
			Identifier currentIdentifier = inputSet.getRemove();
			if(this.contains(currentIdentifier)) {
				intersection.addElement(currentIdentifier);
			}
		}
		return intersection;
	}

	@Override
	public Set symmetricDifference(Set S) throws ArrayIndexOutOfBoundsException { // hoeft toch niet?
		Set difference1 = new Set(this.difference(S));
		Set difference2 = new Set(S.difference(this));
		Set symmDiffSet = new Set(difference1.union(difference2));
		return symmDiffSet;
	}

}