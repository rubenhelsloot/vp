 package assignment3;

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
 *		<dt><b>POST-condition</b><dd>The new Identifier contains the char c.
 *	    </dl>
 */

public interface IdentifierInterface extends Data<Identifier> {

	/*
	 * Initializes the Identifier-object to an empty Identifier.b
	 * 
	 * @precondition -
	 * 
	 * @postcondition The identifier has been initialized as an empty Identifier.
	 */

	Identifier init();

	/*
	 * Adds a character to the Identifier
	 * 
	 * @precondition c is a letter or a number in the format of a-Z or 0-9.
	 * 
	 * @postcondition The character has been added to the back of the Identifier.
	 */

	Identifier add(char c);

	/*
	 * Returns the string representation of the Identifier.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The string representation of the Identifier has been returned.
	 */

	String stringify();

	/*
	 * Returns the length of the Identifier.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The length of the Identifier has been returned.
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
	boolean equals(Identifier id);
}