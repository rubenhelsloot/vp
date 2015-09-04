package assignment1;

/* ADT for the class Identifier
 * 
 * @author Ruben Helsloot 
 * @author Sherida van den Bent
 * 
 * @elements
 * @structure linear
 * @domain
 * 
 * @constructor
 *	Set ();
 *		<dl>
 *			<dt><b>PRE-condition</b><dd> -
 *			<dt><b>POST-condition</b><dd>The new object contains an empty Set.
 *		</dl>
 *		<br>
 *	Set (Set origin);
 *		<dl>
 *			<dt><b>PRE-condition</b><dd> -
 *			<dt><b>POST-condition</b><dd>The content of the new Set is a clone of the Set origin.
 *		</dl> */

public interface SetInterface {

	/*
	 * Initializes the Set-object to be an empty set.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The empty set has been initialized.
	 */

	void init();

	/*
	 * Adds an Identifier to the set.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The set contains an Identifier with the same content as
	 * id.
	 * 
	 * @throws If the set contains more than the maximum amount of elements.
	 */

	void addElement(Identifier id) throws ArrayIndexOutOfBoundsException;

	/*
	 * Removes an Identifier from the set.
	 * 
	 * @precondition The set is nonempty.
	 * 
	 * @postcondition The set does not contain an Identifier with the same
	 * content as id.
	 */

	void removeElement(Identifier id) throws ArrayIndexOutOfBoundsException;

	/*
	 * Returns the length of the set.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The length length of the set has been returned.
	 */
	int getSize();

	/*
	 * Returns whether the set contains elements or not.
	 * 
	 * @precondition -
	 * 
	 * @postcondition TRUE: The set is empty. FALSE: The set is nonempty.
	 */
	boolean isEmpty();

	/*
	 * Returns a set containing the union of the own set and the given set.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The new set contains all elements that are in either the
	 * own set or the given set, but does not contain duplicate elements.
	 * 
	 * @throws If the new set contains more than the maximum amount of elements.
	 */

	Set union(Set S) throws ArrayIndexOutOfBoundsException;

	/*
	 * Returns a set containing the difference of the own set and the given set.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The new set contains all elements that are in the own set,
	 * but not the given set.
	 */

	Set difference(Set S);

	/*
	 * Returns a set containing the intersection of the own set and the given
	 * set.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The new set contains all elements that are in both the own
	 * set and the given set.
	 */

	Set intersection(Set S);

	/*
	 * Returns a set containing the symmetric difference of the own set and the
	 * given set.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The new set contains all elements that are in either the
	 * own set or the given set, but not in both.
	 * 
	 * @throws If the new set contains more than the maximum amount of elements.
	 * 
	 */

	Set symmetricDifference(Set S) throws ArrayIndexOutOfBoundsException;

}
