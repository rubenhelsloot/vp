package assignment2;

public class List<E extends Data<E>> implements ListInterface<E> {

	public Node first, last, current;

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

		if (isEmpty())
			return size;

		size = getSize(first);

		return size;
	}

	private int getSize(Node node) {
		if (node == null) {
			return 0;
		}
		return 1 + getSize(node.next);
	}

	@Override
	public List<E> insert(E d) {
		if (current == null) {
			// Insert in empty list
			Node n = new Node((E) d.clone());
			first = last = current = n;
		} else if (last.data.compareTo(d) < 0) {
			// Insert at the end of the list
			Node n = new Node((E) d.clone(), last, null);
			current = last = n;
			current.prior.next = current;
		} else if (first.data.compareTo(d) > 0) {
			// Insert at the beginning of the list
			Node n = new Node((E) d.clone(), null, first);
			current = first = n;
			current.next.prior = current;
		} else {
			// insert after current, where current.next exists
			find(d);
			Node n = new Node((E) d.clone(), current.prior, current);
			current = n;
			current.next.prior = current.prior.next = n;
		}
		return this;
	}

	@Override
	public E retrieve() {
		return current.data;
	}

	@Override
	public List<E> remove() {
		// switch statement?
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
		boolean result = false;

		if (goToFirst()) {
			result = recursiveFind(d);
		}

		return result;
	}

	private boolean recursiveFind(E d) {

		if (current.data.compareTo(d) < 0) {
			if (goToNext()) {
				return recursiveFind(d);
			} else {
				return false;
			}
		} else if (current.data.compareTo(d) == 0) {
			return true;
		} else {
			//current.data is bigger than d and was never equal to d, and therefore d is not in the list 
			return false;
		}
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

	public class Node {

		E data;
		public Node prior, next;

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
