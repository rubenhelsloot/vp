package assignment2;

class Identifier<E extends Data<E>> implements IdentifierInterface<E> {
	
	static final int MAX_ID_SIZE = 20;
	StringBuffer sb;

	Identifier() {
		sb = new StringBuffer();
		sb.append('1');
	}

	@Override
	public void init(char c) {
		sb.delete(0, MAX_ID_SIZE);
		sb.append(c);
	}

	@Override
	public void add(char c) {
		sb.append(c);
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
	public boolean equals(E id) {
		Identifier<E> ident = (Identifier<E>) id;
		if (ident.getSize() != sb.length()) {
			return false;
		}
		
		for(int i = 0; i < sb.length(); i++) {
			if(sb.charAt(i) != ident.sb.charAt(i)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public int compareTo(E d) {
		Identifier<E> id = (Identifier<E>) d;
		return sb.toString().compareTo(id.sb.toString());
	}

	@Override
	public E clone() {
		Identifier<E> idenClone = new Identifier<E>();
		idenClone.init(sb.charAt(0));
		
		for(int i = 1; i < sb.length(); i++) {
			idenClone.add(sb.charAt(i));
		}
		
		return ((E) idenClone);
	}

}