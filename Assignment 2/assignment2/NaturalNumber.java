package assignment2;

public class NaturalNumber implements NaturalNumberInterface {

	StringBuffer data;
	static final int MAX_NUMBER_SIZE = 20;

	NaturalNumber() {
		data = new StringBuffer();
	}

	@Override
	public NaturalNumber clone() {
		NaturalNumber NClone = new NaturalNumber();

		NClone.init();

		for (int i = 0; i < data.length(); i++) {
			NClone.add(data.charAt(i));
		}
		return NClone;
	}

	@Override
	public int compareTo(NaturalNumber o) {
		if (getSize() < o.getSize()) {
			return -1;
		} else if (getSize() > o.getSize()) {
			return 1;
		}

		for (int i = 0; i < getSize(); i++) {
			if (data.charAt(i) < o.data.charAt(i)) {
				return -1;
			} else if (data.charAt(i) > o.data.charAt(i)) {
				return 1;
			}
		}

		return 0;
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

	public NaturalNumber raise(int i) {
		return raise(this, i);
	}

	private NaturalNumber raise(NaturalNumber N, int i) {
		if (N.getSize() == 0) {
			N.init();
			N.data.append(Character.forDigit(i, 10));
			return N;
		}

		char c = N.data.charAt(N.getSize() - 1);
		int old = Character.getNumericValue(c);
		int newI = old + i;
		
		if (newI > 9) {
			N.data.deleteCharAt(N.getSize() - 1);
			N = raise(N, newI / 10);
			int j = newI % 10; 
			N.add(Character.forDigit(j, 10));
		} else {
			char newC = Character.forDigit(newI, 10);
			N.data.setCharAt(N.getSize() - 1, newC);
		}
		
		return N;
	}

	@Override
	public boolean isEqual(NaturalNumber Number) {
		return stringify().equals(Number.stringify());
	}

	@Override
	public int get() {
		return Integer.parseInt(stringify());
	}
	
	@Override
	public String stringify() {
		return data.toString();
	}

	@Override
	public int getSize() {
		return data.length();
	}

}
