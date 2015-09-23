 package assignment2;

/* ADT for the class Identifier
 * 
 * @author Ruben Helsloot 
 * @author Sherida van den Bent
 * 
 * @elements characters of type char
 * @structure linear
 * @domain all series of nonempty alphanumeric characters starting with a letter
 * 
 * @constructor
 *	Identifier();
 *	    <dl>
 *		<dt><b>PRE-condition</b><dd>The char c is a letter.
 *		<dt><b>POST-condition</b><dd>The new Identifier<E> contains the char c.
 *	    </dl>
 *	<br>
 *	Identifier(Identifier<E> origin);
 *	    <dl>
 *		<dt><b>PRE-condition</b><dd>-
 *		<dt><b>POST-condition</b><dd>The content of the new Identifier<E> is a clone of the Identifier<E> origin
 *	    </dl>
 */

public interface IdentifierInterface<E extends Data<E>> extends Clonable<Identifier<E>> {

	/*
	 * Initializes the Identifier-object to an identifier with one character.
	 * 
	 * @precondition c is a letter in the format of a-Z.
	 * 
	 * @postcondition The identifier has been initialized with a character c.
	 */

	Identifier<E> init(char c);

	/*
	 * Adds a character to the Identifier
	 * 
	 * @precondition c is a letter or a number in the format of a-Z or 0-9.
	 * 
	 * @postcondition The character has been added to the back of the Identifier.
	 */

	Identifier<E> add(char c);

	/*
	 * Returns the Identifier.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The Identifier<E> has been returned.
	 */

	StringBuffer get();

	/*
	 * Returns the length of the Identifier.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The length of the Identifier<E> has been returned.
	 */
	int getSize();

	/*
	 * Returns whether two Identifiers are equal.
	 * 
	 * @precondition -
	 * 
	 * @postcondition Returns true if the identifiers are equal. Returns false
	 * if the identifiers are not equal.
	 */
	boolean equals(E id);
	
}
