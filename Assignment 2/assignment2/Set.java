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
		if (!this.contains(id)){
			setList.insert(id);
		}
		return this;
	}

	@Override
	public Set<E> removeElement(E id) {
		if(setList.find(id)) setList.remove();
		return this;
	}

	@Override
	public int getSize() {
		return setList.size();
	}

	@Override
	public boolean isEmpty() {
		return setList.isEmpty();
	}

	@Override
	public E getRandom() {
		long random = Math.round(Math.random() * setList.size());
		setList.goToFirst();
		
		for(long i = 1; i < random; i++) {
			setList.goToNext();
		}
		
		return setList.retrieve();
	}

	@Override
	public E getRemove() {
		E result = getRandom();
		setList.remove();
		return result;
	}

	@Override
	public boolean contains(E I) {
		return (setList.find(I));
	}
	
	public Set<E> clone() {
		Set<E> clone = new Set<E>();
		clone.setList = setList.clone();
		return clone;		
	}


	@Override
	public Set<E> union(Set<E> S) {
		Set<E> union = this.clone();
		
		if(union.isEmpty()) return S;
		if(S.isEmpty()) return union;
		
		do {
			E el = S.getRemove();
			if(!union.setList.find(el)) {
				union.addElement(el);
			}
		} while(!S.isEmpty());
		
		return union;
	}

	@Override
	public Set<E> difference(Set<E> S) {
		Set<E> difference = new Set<E>();
		difference.init();
		
		if(!setList.goToFirst()) return this;
		if(S.isEmpty()) return this;
		
		do {
			E el = setList.retrieve();
			if(!S.setList.find(el)) {
				difference.addElement(el);
			}
		} while(setList.goToNext());
		
		return difference;
	}

	@Override
	public Set<E> intersection(Set<E> S) {
		return difference(difference(S));
	}

	@Override
	public Set<E> symmetricDifference(Set<E> S) {
		return difference(S).union(S.difference(this));
	}

}
