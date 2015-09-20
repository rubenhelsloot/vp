package assignment2;

public class Set<E extends Data<E>> implements SetInterface<E> {
	
	private List<E> setList;
	
	Set() {
		setList = new List<E>();
	}

	@Override
	public Set<E> init() {
		setList.init();
		return this;
	}

	@Override
	public Set<E> addElement(E id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<E> removeElement(E id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E getRandom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getRemove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(E I) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Set<E> clone() {
		Set<E> clone = new Set<E>();
		clone.list = list.clone();
		return clone;		
	}


	@Override
	public Set<E> union(Set<E> S) throws ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<E> difference(Set<E> S) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<E> intersection(Set<E> S) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<E> symmetricDifference(Set<E> S) throws ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

}
