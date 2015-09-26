package assignment3;

import Data;
import SortingTree;

/* ADT for the class SortingTree
 * 
 * @author Ruben Helsloot 
 * @author Sherida van den Bent
 * 
 * @elements objects of type E
 * @structure ?
 * @domain ?
 * 
 * @constructor
 *	SortingTree();
 *	    <dl>
 *		<dt><b>PRE-condition</b><dd>
 *		<dt><b>POST-condition</b><dd>The new SortingTree contains an empty tree.
 *	    </dl>
 *	<br>
 * SortingTree(SortingTree Origin);
 *	    <dl>
 *		<dt><b>PRE-condition</b><dd>
 *		<dt><b>POST-condition</b><dd>The content of the new SortingTree is a clone of the SortingTree origin
 *	    </dl>
 */
public interface SortingTreeInterface<E extends Data<E>> {
	
	/*
	 * Initializes the SortingTree-object to be an empty SortingTree.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The empty SortingTree has been initialized.
	 */

	SortingTree init();
	
	/*
	 * Adds an object of type E to the SortingTree.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The SortingTree-POST contains an object of type E with the same content as
	 * id.
	 */

	SortingTree insert(E id);
	
	/*
	 * Remove the object with the same content as id from the SortingTree.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The SortingTree-POST does not contain an object with the same content as
	 * id.
	 */

	SortingTree remove(E id);
	
	/*
	 * Checks if an object of type E with the same content as id is contained in the SortingTree.
	 * 
	 * @precondition -
	 * 
	 * @postcondition TRUE: The SortingTree contains an object of type E with the same content as id FALSE: The SortingTree does not contain an object of type E with the same content as id
	 * 
	 */

	boolean contains(E id);
	
	/*
	 * Return the nodes of the SortingTree, in an ascending order.
	 * 
	 * @precondition -
	 * 
	 * @postcondition 
	 * 
	 */

	???  sortUpward(E id);
	
	/*
	 * Checks if an object of type E with the same content as id is contained in the SortingTree.
	 * 
	 * @precondition -
	 * 
	 * @postcondition
	 * 
	 */

	??? sortDownward(E id);
}