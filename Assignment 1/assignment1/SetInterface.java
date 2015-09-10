package assignment1;

/* ADT for the class Identifier
 * 
 * @author Ruben Helsloot 
 * @author Sherida van den Bent
 * 
 * @elements objects of type Identifier.
 * @structure linear
 * @domain A collection of between zero and twenty unique Identifiers.
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
 *			<dt><b>POST-condition</b><dd>The content of the new Set is a clone of origin.
 *		</dl> */

public interface SetInterface {

	/*
	 * Initializes the Set-object to be an empty set.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The empty set has been initialized.
	 */

	Set init();

	/*
	 * Adds an Identifier to the set.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The set contains an Identifier with the same content as
	 * id.
	 * 
	 * @throws If the set contains more than the maximum amount of Identifiers.
	 */

	Set addElement(Identifier id) throws ArrayIndexOutOfBoundsException;

	/*
	 * Removes an Identifier from the set.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The set does not contain an Identifier with the same
	 * content as id.
	 */

	Set removeElement(Identifier id);

	/*
	 * Returns the length of the set.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The length of the set is returned.
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
	 * Returns an element of the Set and removes it from the Set.
	 * 
	 * @precondition The Set is nonempty.
	 * 
	 * @postcondition A copy of a random element in the Set-PRE is returned and
	 * the element itself is removed from the Set.
	 */

	Identifier getRandom();

	/*
	 * Returns a set containing the union of the own set and the given Set S.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The new Set contains all elements that are in either the
	 * own Set or the given Set S.
	 * 
	 * @throws If the new Set contains more than the maximum amount of elements.
	 */

	Set union(Set S) throws ArrayIndexOutOfBoundsException;

	/*
	 * Returns a set containing the difference of the own Set and the given Set S.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The new set contains all elements that are in the own Set,
	 * but not the given Set S.
	 */

	Set difference(Set S);

	/*
	 * Returns a set containing the intersection of the own Set and the given
	 * Set S.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The new set contains all elements that are in both the own
	 * Set and the given Set S.
	 */

	Set intersection(Set S);

	/*
	 * Returns a set containing the symmetric difference of the own Set and the
	 * given Set S.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The new set contains all elements that are in either the
	 * own Set or the given Set S.
	 * 
	 * @throws If the new set contains more than the maximum amount of elements.
	 * 
	 */

	Set symmetricDifference(Set S) throws ArrayIndexOutOfBoundsException;

}
