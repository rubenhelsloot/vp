package assignment2;

class Identifier<E extends Data<E>> implements IdentifierInterface<E> {
	
	StringBuffer sb;

	Identifier() {
		sb = new StringBuffer();
	}

	@Override
	public Identifier<E> init(char c) {
		sb.delete(0, getSize());
		sb.append(c);
		return this;
	}

	@Override
	public Identifier<E> add(char c) {
		sb.append(c);
		return this;
	}

	@Override
	public StringBuffer get() {
		return this.sb;
	}

	@Override
	public int getSize() {
		return sb.length();
	}

	@Override
	public boolean equals(Identifier<E> id) {
		if(compareTo(id) == 0) return true;
		return false;
	}

	public int compareTo(Identifier<E> d) {
		return sb.toString().compareTo(d.sb.toString());
	}

	@Override
	public Identifier<E> clone() {
		Identifier<E> idenClone = new Identifier<E>();
		idenClone.init(sb.charAt(0));
		
		for(int i = 1; i < sb.length(); i++) {
			idenClone.add(sb.charAt(i));
		}
		
		return idenClone;
	}

}