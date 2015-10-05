package assignment3;

public class Identifier implements IdentifierInterface {
	
	StringBuffer sb;
	
	Identifier() {
		init();
	}

	@Override
	public Identifier init() {
		sb = new StringBuffer();
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
		return sb.equals(id.sb);
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

	@Override
	public int compareTo(Identifier o) {
		return sb.toString().compareTo(o.sb.toString());
	}

}
