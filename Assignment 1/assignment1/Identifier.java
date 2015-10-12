package assignment1;

class Identifier implements IdentifierInterface {
	
	static final int MAX_ID_SIZE = 20;
	StringBuffer sb;

	Identifier() {
		sb = new StringBuffer();
		sb.append('1');
	}

	Identifier(Identifier origin) {
		sb = origin.sb;
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

	public String get() {
		return sb.toString();
	}

	public Identifier remove() {
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
		
		return (sb.toString().equals(id.get()));
	}

}