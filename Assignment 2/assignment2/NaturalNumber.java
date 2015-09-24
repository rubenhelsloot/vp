package assignment2;

public class NaturalNumber implements NaturalNumberInterface  {
	
	StringBuffer data;
	static final int MAX_NUMBER_SIZE = 20;
	
	NaturalNumber() {
		data = new StringBuffer();
		data.append('1');
	}

	@Override
	public NaturalNumber clone() {
		NaturalNumber NClone = new NaturalNumber();
		
		NClone.init();
		
		for(int i = 0; i < data.length(); i++) {
			NClone.add(data.charAt(i));
		}
		return NClone;
	}

	@Override
	public int compareTo(NaturalNumber o) {
		return data.toString().compareTo(o.data.toString());
	}

	@Override
	public NaturalNumber init() {
		data = new StringBuffer();
		return this;
	}

	@Override
	public NaturalNumber add(char c) {
		data.append(c);
		return this;
	}

	@Override
	public int get() {
		return Integer.parseInt(data.toString());
	}

	@Override
	public int getSize() {
		return data.length();
	}

}
