package assignment1;

class Identifier implements IdentifierInterface {
	
	static final int MAX_ID_SIZE = 20;
	StringBuffer sb;

	Identifier() {
		sb = new StringBuffer();
	}

	Identifier(Identifier origin) {
		sb = origin.get().sb;
	}

	public Identifier init(char c) {
		sb.delete(0, getSize());
		sb.append(c);
		return this;
	}

	public Identifier add(char c) {
		sb.append(c);
		return this;
	}

	public Identifier get() {
		return this;
	}

	public Identifier remove(int index) {
		sb.deleteCharAt(sb.length() - 1);
		return this;
	}

	public int getSize() {
		return sb.length();
	}

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

}