package assignment2;

class Identifier implements IdentifierInterface {
	
	StringBuffer sb;

	Identifier() {
		sb = new StringBuffer();
	}

	@Override
	public Identifier init() {
		sb.delete(0, getSize());
		return this;
	}

	@Override
	public Identifier add(char c) {
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
	public boolean equals(Identifier id) {
		if(compareTo(id) == 0) return true;
		return false;
	}

	public int compareTo(Identifier d) {
		return sb.toString().compareTo(d.sb.toString());
	}

	@Override
	public Identifier clone() {
		Identifier idenClone = new Identifier();
		idenClone.init();
		
		for(int i = 0; i < sb.length(); i++) {
			idenClone.add(sb.charAt(i));
		}
		
		return idenClone;
	}

}