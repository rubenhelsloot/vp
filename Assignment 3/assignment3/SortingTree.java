package assignment3;

import java.util.Iterator;

public class SortingTree<E extends Data<E>> implements SortingTreeInterface<E> {

	E data;
	SortingTree<E> left;
	SortingTree<E> right;

	SortingTree(E data, SortingTree<E> l, SortingTree<E> r) {
		this.data = data;
		left = l;
		right = r;
	}

	SortingTree(E data) {
		this(data, null, null);
	}

	SortingTree() {
		this(null, null, null);
	}

	@Override
	public SortingTree<E> init() {
		return new SortingTree<E>();
	}

	@Override
	public SortingTree<E> insert(E id) {
		if (data == null) {
			data = id.clone();
		} else if (data.compareTo(id) < 0) {
			if (left == null) {
				left = new SortingTree<E>();
			}
			left.insert(id.clone());
		} else {
			if (right == null) {
				right = new SortingTree<E>();
			}
			right.insert(id.clone());
		}

		return this;
	}

	@Override
	public boolean contains(E id) {
		if (data == null) {
			return false;
		} else if(data.compareTo(id) == 0) {
			return true;
		} else if (data.compareTo(id) > 0) {
			return left.contains(id);
		} else {
			return right.contains(id);
		}
	}

	public int size() {
		return getSize(this);
	}

	private int getSize(SortingTree<E> root) {
		if (root == null) {
			return 0;
		} else {
			return 1 + getSize(root.left) + getSize(root.right);
		}
	}

	public int height() {
		return height(this);
	}

	private int height(SortingTree<E> root) {
		if (root == null) {
			return -1;
		} else {
			return 1 + Math.max(height(root.left), height(root.right));
		}
	}

	@Override
	public boolean hasNext() {
		return (left != null || right != null);
	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> sortUpward(E id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> sortDownward(E id) {
		// TODO Auto-generated method stub
		return null;
	}

	public SortingTree<E> clone() {
		return copy(this);
	}

	private SortingTree<E> copy(SortingTree<E> root) {
		if (root == null) {
			return null;
		} else {
			return new SortingTree<E>(root.data, copy(root.left), copy(root.right));
		}
	}
}
