package assignment3;

import java.util.Iterator;

/* ADT for the class SortingTree
 * 
 * @author Ruben Helsloot 
 * @author Sherida van den Bent
 * 
 * @elements objects of type E
 * @structure binary tree
 * @domain left < 0, right >= 0
 * 
 * @constructor
 *	SortingTree();
 *	    <dl>
 *		<dt><b>PRE-condition</b><dd>
 *		<dt><b>POST-condition</b><dd>The new SortingTree contains no data, left and right are null.
 *	    </dl>
 *	<br>
 *	SortingTree(E data);
 *	    <dl>
 *		<dt><b>PRE-condition</b><dd>
 *		<dt><b>POST-condition</b><dd>The new SortingTree data of type E, but left and right are null.
 *	    </dl>
 *	<br>
 *	SortingTree(E data, SortingTree left, SortingTree right);
 *	    <dl>
 *		<dt><b>PRE-condition</b><dd>
 *		<dt><b>POST-condition</b><dd>The new SortingTree data of type E, left and right reference the given SortingTrees respectively.
 *	    </dl>
 *	<br>
 */
public interface SortingTreeInterface<E extends Data<E>> extends Iterator<E>, Clonable<SortingTreeInterface<E>> {

	/*
	 * Initializes the SortingTree-object to be an empty SortingTree.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The empty SortingTree has been initialized.
	 */

	SortingTree<E> init();

	/*
	 * Adds an object of type E to the SortingTree.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The SortingTree-POST contains an object of type E with the
	 * same content as id.
	 */

	SortingTree<E> insert(E id);

	/*
	 * Checks if an object of type E with the same content as id is contained in
	 * the SortingTree.
	 * 
	 * @precondition -
	 * 
	 * @postcondition TRUE: The SortingTree contains an object of type E with
	 * the same content as id FALSE: The SortingTree does not contain an object
	 * of type E with the same content as id
	 * 
	 */

	boolean contains(E id);
	
	/*
	 * Returns size of the tree
	 * 
	 * @precondition -
	 * 
	 * @postcondition The number of nodes in the SortingTree is returned
	 */
	
	int size();
	
	/*
	 * Returns the depth of the tree
	 * 
	 * @precondition -
	 * 
	 * @postcondition The deepest layer of the SortingTree is returned
	 */
	
	int height();

	/*
	 * Return the nodes of the SortingTree, in an ascending order.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The data stored in the binary search tree was iterated in
	 * monotonically non-decreasing order and was added in this order to an
	 * object of the type Iterator<E>. This object of the type Iterator<E> was
	 * subsequently returned.
	 * 
	 */

	Iterator<E> sortUpward(E id);

	/*
	 * Checks if an object of type E with the same content as id is contained in
	 * the SortingTree.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The data stored in the binary search tree was iterated in
	 * monotonically non-increasing order and was added in this order to an
	 * object of the type Iterator<E>. This object of the type Iterator<E> was
	 * subsequently returned.
	 * 
	 */

	Iterator<E> sortDownward(E id);
}
