package assignment2;

/* ADT for the class Set
 * 
 * @author Ruben Helsloot 
 * @author Sherida van den Bent
 * 
 * @elements objects of type E.
 * @structure linear
 * @domain A collection of between zero and twenty unique objects of type E.
 * 
 * @constructor
 *	Set ();
 *		<dl>
 *			<dt><b>PRE-condition</b><dd> -
 *			<dt><b>POST-condition</b><dd>The new object contains an empty Set.
 *		</dl>
 *		<br>
 */

public interface SetInterface<E extends Data> extends Clonable {

	/*
	 * Initializes the Set-object to be an empty set.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The empty set has been initialized.
	 */

	Set<E> init();

	/*
	 * Adds an object of type E to the set.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The set contains an object of type E with the same content as
	 * id.
	 * 
	 * @throws If the set contains more than the maximum amount of objects of type E.
	 */

	Set<E> addElement(E id) throws ArrayIndexOutOfBoundsException;

	/*
	 * Removes an object of type E from the set.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The set does not contain an Identifier with the same
	 * content as id.
	 */

	Set<E> removeElement(E id);

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
	 * Returns an element of the Set.
	 * 
	 * @precondition The Set is nonempty.
	 * 
	 * @postcondition A copy of a random element in the Set-PRE is returned.
	 */

	E getRandom();
	
	/*
	 * Returns an element of the Set and removes it from the Set.
	 * 
	 * @precondition The Set is nonempty.
	 * 
	 * @postcondition A copy of a random element in the Set-PRE is returned and
	 * the element itself is removed from the Set.
	 */

	E getRemove();
	
	/*
	 * Checks if the Set contains given Identifier.
	 * 
	 * @precondition
	 * 
	 * @postcondition TRUE: the Set contains the given Identifier FALSE: The set does not
	 * contain the given Identifier
	 */

	boolean contains(E I);
	
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

	Set<E> union(Set S) throws ArrayIndexOutOfBoundsException;

	/*
	 * Returns a set containing the difference of the own Set and the given Set S.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The new set contains all elements that are in the own Set,
	 * but not the given Set S.
	 */

	Set<E> difference(Set S);

	/*
	 * Returns a set containing the intersection of the own Set and the given
	 * Set S.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The new set contains all elements that are in both the own
	 * Set and the given Set S.
	 */

	Set<E> intersection(Set S);

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

	Set<E> symmetricDifference(Set S) throws ArrayIndexOutOfBoundsException;

}