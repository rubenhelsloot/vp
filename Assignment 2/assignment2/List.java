package assignment2;

public class List<E extends Data<E>> implements ListInterface<E> {

	private Node first, last, current;

	List() {
		init();
	}

	@Override
	public boolean isEmpty() {
		return (first == null);
	}

	@Override
	public List<E> init() {
		first = last = current = null;
		return this;
	}

	@Override
	public int size() {
		int size = 0;

		if (goToFirst()) {
			do {
				size++;
			} while (goToNext());
		}

		return size;
	}

	@Override
	public List<E> insert(E d) {
		if (current == null) {
			// Insert in empty list
			Node n = new Node((E) d.clone());
			first = last = current = n;
		} else if (current == first) {
			// Insert at the beginning of the list
			Node n = new Node((E) d.clone(), null, first);
			first = current = n;
			current.next.prior = current;
		} else if (current == last) {
			// Insert at the end of the list
			Node n = new Node((E) d.clone(), last, null);
			last = current = n;
			current.prior.next = current;
		} else {
			// insert before current
			Node n = new Node((E) d.clone(), current.prior, current);
			current = n;
			current.prior.next = current;
			current.prior = current;
		}
		return this;
	}

	@Override
	public E retrieve() {
		return current.data;
	}

	@Override
	public List<E> remove() {
		if (first == last) {
			init();
		} else if (current == first) {
			current.next.prior = null;
			first = current = current.next;
		} else if (current == last) {
			current.prior.next = null;
			last = current = current.prior;
		} else {
			current.next.prior = current.prior;
			current = current.prior.next = current.next;
		}

		return this;
	}

	@Override
	public boolean find(E d) {
		if(!goToFirst()) return false;
		
		do {
			if(d.compareTo(current.data) == 0) return true;
			
			// if d < current.data, d can only be in previous nodes
			if(d.compareTo(current.data) == -1) {
				if(current.prior != null) {
					current = current.prior;
				} else {
					return false;
				}
			}
		} while(goToNext());
		
		// here only if d > last.data, so d is not in List 
		return false;
	}

	@Override
	public boolean goToFirst() {
		current = first;
		return (current != null);
	}

	@Override
	public boolean goToLast() {
		current = last;
		return (current != null);
	}

	@Override
	public boolean goToNext() {
		if (isEmpty() || current.next == null) {
			return false;
		} else {
			current = current.next;
			return true;
		}
	}

	@Override
	public boolean goToPrevious() {
		if (isEmpty() || current.prior == null) {
			return false;
		} else {
			current = current.prior;
			return true;
		}
	}

	@Override
	public List<E> clone() {
		List<E> copy = new List<E>();

		if (goToFirst()) {
			do {
				copy.insert(current.data.clone());
			} while (goToNext());
		}
		return copy;
	}

	private class Node {

		E data;
		Node prior, next;

		public Node(E d) {
			this(d, null, null);
		}

		public Node(E data, Node prior, Node next) {
			this.data = data == null ? null : data;
			this.prior = prior;
			this.next = next;
		}
	}
}
