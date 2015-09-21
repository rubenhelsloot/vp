package assignment2;

class Identifier implements IdentifierInterface {
	
	static final int MAX_ID_SIZE = 20;
	StringBuffer sb;

	Identifier() {
		sb = new StringBuffer();
		sb.append('1');
	}

	@Override
	public Identifier init(char c) {
		sb.delete(0, MAX_ID_SIZE);
		sb.append(c);
		return this;
	}

	@Override
	public Identifier add(char c) {
		sb.append(c);
		return this;
	}

	@Override
	public Identifier get() {
		return this;
	}

	@Override
	public Identifier remove(int index) {
		sb.deleteCharAt(sb.length() - 1);
		return this;
	}

	@Override
	public int getSize() {
		return sb.length();
	}

	@Override
	public boolean equals(Identifier id) {
		if (id.getSize() != sb.length()) {
			return false;
		}
		
		for(int i = 0; i < sb.length(); i++) {
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
		if(sb.length() == oLength){
			return 0;
		} else if(sb.length() > oLength){
			return 1;
		} else return -1;
	}

	@Override
	public Identifier clone() {
		Identifier idenClone = new Identifier();
		idenClone.init(sb.charAt(0));
		
		for(int i = 1; i < sb.length(); i++) {
			idenClone.add(sb.charAt(i));
		}
		
		return idenClone;
	}

}