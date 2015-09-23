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
		NClone.init(data.charAt(0));
		
		for(int i = 1; i < data.length(); i++) {
			NClone.add(data.charAt(i));
		}
		return NClone;
	}

	@Override
	public int compareTo(NaturalNumber o) {
		return data.toString().compareTo(o.data.toString());
	}

	@Override
	public NaturalNumber init(char c) {
		data = new StringBuffer(c);
		return this;
	}

	@Override
	public NaturalNumber add(char c) {
		data.delete(0, MAX_NUMBER_SIZE);
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
