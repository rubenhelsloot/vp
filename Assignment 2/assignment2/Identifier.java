package assignment2;

class Identifier implements IdentifierInterface {
	
	static final int MAX_ID_SIZE = 20;
	int idLength;
	StringBuffer sb;

	Identifier() {
		idLength = 1;
		sb = new StringBuffer();
		sb.append('1');
	}

	Identifier(Identifier origin) {
		idLength = origin.getSize();
		sb = origin.get().sb;
	}

	@Override
	public Identifier init(char c) {
		sb.delete(0, MAX_ID_SIZE);
		sb.append(c);
		idLength = 1;
		return this;
	}

	@Override
	public Identifier add(char c) {
		sb.append(c);
		idLength ++;
		return this;
	}

	@Override
	public Identifier get() {
		return this;
	}

	@Override
	public Identifier remove(int index) {
		sb.deleteCharAt(idLength - 1);
		idLength --;
		return this;
	}

	@Override
	public int getSize() {
		return idLength;
	}

	@Override
	public boolean equals(Identifier id) {
		if (id.getSize() != idLength) {
			return false;
		}
		
		for(int i = 0; i < idLength; i++) {
			if(sb.charAt(i) != id.sb.charAt(i)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public int compareTo(Object o) {
		String oString = o.toString();
		int oLength = oString.length();
		if(idLength == oLength){
			return 0;
		} else if(idLength > oLength){
			return 1;
		} else return -1;
	}

	@Override
	public Identifier clone() {
		Identifier idenClone = new Identifier(this);
		return idenClone;
	}

}