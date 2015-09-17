package assignment2;

/* ADT for the class NaturalNumber
 * 
 * @author Ruben Helsloot 
 * @author Sherida van den Bent
 * 
 * @elements characters of type char
 * @structure linear
 * @domain all elements are positive numbers or zero
 * 
 * @constructor
 *	NaturalNumber();
 *	    <dl>
 *		<dt><b>PRE-condition</b><dd> -
 *		<dt><b>POST-condition</b><dd> The content of the NaturalNumber is zero.
 *	    </dl>
 *	NaturalNumber(NaturalNumber n);
 *	    <dl>
 *		<dt><b>PRE-condition</b><dd> 
 *		<dt><b>POST-condition</b><dd> The content of the NaturalNumber a clone of the NaturalNumber origin.
 *	    </dl>
 *	<br>
 */

public interface NaturalNumberInterface extends Data {
	
	/*
	 * Initializes the NaturalNumber and sets its contents to char c.
	 * 
	 * @precondition The char c is numerical
	 * 
	 * @postcondition A NaturalNumber now contains c
	 */
	
	void init(char c);
	
	/*
	 * Adds the char c to the NaturalNumber. 
	 * 
	 * @precondition The char c is numerical
	 * 
	 * @postcondition c is appended to the back of the NaturalNumber
	 */
	
	void add(char c);
	
	/* 
	 * Returns the value of the NaturalNumber converted to int.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The int valued as the value of the NaturalNumber
	 */
	
	int get();
	
	/* 
	 * Returns the amount of elements in the NaturalNumber.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The amount of elements in the NaturalNumber has been returned.
	 */
	
	int getSize();
	
	/*
	 * Returns whether two NaturalNumbers are equal.
	 * 
	 * @precondition -
	 * 
	 * @postcondition Returns true if the NaturalNumbers are equal. Returns false
	 * if the NaturalNumbers are not equal.
	 */
	
	boolean isEqual(NaturalNumber n);
	
	/*
	 * @precondition -
	 * 
	 * @postcondition Returns a clone of the Identifier.
	 */
		
	NaturalNumber clone();
}