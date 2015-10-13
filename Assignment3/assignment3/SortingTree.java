package assignment3;

import java.util.Iterator;
import java.util.ArrayList;

public class SortingTree<E extends Data<E>> implements SortingTreeInterface<E> {

	TreeNode<E> root;
	ArrayList<E> arrList;
	
	SortingTree() {
		arrList = new ArrayList<E>();
		root = new TreeNode<E>();
	}

	@Override
	public SortingTree<E> init() {
		return new SortingTree<E>();
	}

	@Override
	public SortingTree<E> insert(E d) {
		root = insert(root, d);
		return this;
	}

	private TreeNode<E> insert(TreeNode<E> root, E d) {
		if (root == null || root.data == null) {
			return new TreeNode<E>(d);
		}

		if (d.compareTo(root.data) < 0) {
			root.left = insert(root.left, d);
		} else {
			root.right = insert(root.right, d);
		}
		return root;
	}

	@Override
	public SortingTree<E> remove(E d) throws APException {
		root = remove(root, d);
		return this;
	}

	private TreeNode<E> remove(TreeNode<E> root, E d) throws APException {
		if (root == null)
			throw new APException("Data was not in the tree");

		if (d.compareTo(root.data) < 0) {
			root.left = remove(root.left, d);
		} else if (d.compareTo(root.data) > 0) {
			root.right = remove(root.right, d);
		} else {
			// 1 child
			if (root.left == null) {
				root = root.right;
			} else if (root.right == null) {
				root = root.left;
			}
			// 2 children
			root.data = smallest(root.right);
			root.right = remove(root.right, root.data);
		}
		return root;
	}

	E smallest(TreeNode<E> root) {
		return root.left == null ? root.data : smallest(root.left);
	}

	@Override
	public boolean contains(E id) {
		return contains(root, id);
	}

	private boolean contains(TreeNode<E> root, E d) {
		if (root == null)
			return false;

		if (d.compareTo(root.data) < 0) {
			return contains(root.left, d);
		} else if (d == root.data) {
			return true;
		} else {
			return contains(root.right, d);
		}
	}

	public int size() {
		return getSize(root);
	}

	private int getSize(TreeNode<E> root) {
		if (root == null) {
			return 0;
		} else {
			return 1 + getSize(root.left) + getSize(root.right);
		}
	}

	public int height() {
		return height(root);
	}

	private int height(TreeNode<E> root) {
		if (root == null) {
			return -1;
		} else {
			return 1 + Math.max(height(root.left), height(root.right));
		}
	}

//	@Override
//	public boolean hasNext() {
//		return (root.left != null || root.right != null);
//	}
//
//	@Override
//	public E next() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Iterator<E> ascendingIterator() {
		return sortUpwardList().iterator();
	}
	
	private ArrayList<E> sortUpwardList() {
		inOrder(root);
		return arrList;
	}

	private void inOrder(TreeNode<E> root) {
		if (root == null) {
			return;
		}
		inOrder(root.left);
		arrList.add(root.data);
		inOrder(root.right);		
	}

	@Override
	public Iterator<E> descendingIterator() {
		return sortDownwardList().iterator();
	}
	
	private ArrayList<E> sortDownwardList() {
		downOrder(root);
		return arrList;
	}
	
	private void downOrder(TreeNode<E> root) {
		if (root == null) {
			return;
		}
		
		downOrder(root.right);
		arrList.add(root.data);
		downOrder(root.left);		
	}

	public SortingTree<E> clone() {
		SortingTree<E> tree = new SortingTree<E>();
		tree.root = copy(root);
		return tree;
	}

	private TreeNode<E> copy(TreeNode<E> root) {
		if (root == null) {
			return null;
		} else {
			return new TreeNode<E>(root.data, copy(root.left), copy(root.right));
		}
	}

	private class TreeNode<E extends Data<E>> implements Clonable<TreeNode<E>> {

		private E data;
		private TreeNode<E> left;
		private TreeNode<E> right;

		TreeNode() {
			this(null);
		}

		TreeNode(E data) {
			this(data, null, null);
		}

		TreeNode(E data, TreeNode<E> left, TreeNode<E> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		@Override
		public TreeNode<E> clone() {
			return new TreeNode<E>(data, right, left);
		}

		@Override
		public String toString() {
			return "Node{ left: " + left + ", right: " + right + ", data: " + data + "}";
		}
	}
}
