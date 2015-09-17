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
 *		<dt><b>POST-condition</b><dd>The new Identifier contains the char c.
 *	    </dl>
 *	<br>
 *	Identifier(Identifier origin);
 *	    <dl>
 *		<dt><b>PRE-condition</b><dd>-
 *		<dt><b>POST-condition</b><dd>The content of the new Identifier is a clone of the Identifier origin
 *	    </dl>
 */

public interface IdentifierInterface extends Data {

	/*
	 * Initializes the Identifier-object to an identifier with one character.
	 * 
	 * @precondition c is a letter in the format of a-Z.
	 * 
	 * @postcondition The identifier has been initialized with a character c.
	 */

	Identifier init(char c);

	/*
	 * Adds a character to the Identifier
	 * 
	 * @precondition c is a letter or a number in the format of a-Z or 0-9.
	 * 
	 * @postcondition The character has been added to the back of the Identifier.
	 */

	Identifier add(char c);

	/*
	 * Returns the Identifier.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The Identifier has been returned.
	 */

	Identifier get();

	/*
	 * Removes a specific character from the Identifier.
	 * 
	 * @precondition index is a number that is larger than 0 and smaller than
	 * the length of the Identifier.
	 * 
	 * @postcondition The character at the specified index has been removed from
	 * the Identifier.
	 */

	Identifier remove(int index);

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
	
	/*
	 * @precondition -
	 * 
	 * @postcondition Returns a clone of the Identifier.
	 */
	
	Identifier clone();
}
