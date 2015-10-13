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
 */

public interface NaturalNumberInterface extends Data<NaturalNumber> {
	
	/*
	 * Initializes the NaturalNumber and sets its contents to an empty stringbuffer.
	 * 
	 * @precondition -
	 * 
	 * @postcondition A NaturalNumber is now empty
	 */
	
	NaturalNumber init();
	
	/*
	 * Adds the char c to the NaturalNumber. 
	 * 
	 * @precondition The char c is numerical
	 * 
	 * @postcondition c is appended to the back of the NaturalNumber
	 */
	
	NaturalNumber add(char c);
	
	/*
	 * Returns a NaturalNumber containing the NaturalNumber plus a given integer.
	 * 
	 * @precondition The NaturalNumber is nonempty
	 * 
	 * @postcondition The NaturalNumber result as the sum of NaturalNumber-PRE and int i
	 */
	
	NaturalNumber raise(int i);
	
	/*
	 *  Returns whether a NaturalNumber is equal to the given NaturalNumber.
	 *  
	 *  @precondition - 
	 *  
	 *  @postcondition FALSE: The NaturalNumbers are not equal. TRUE: The NaturalNumbers are equal.
	 */
	
	boolean isEqual(NaturalNumber Number);
	
	/* 
	 * Returns the value of the NaturalNumber converted to int.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The int valued as the value of the NaturalNumber
	 */
	
	int get();
	
	/*
	 * Returns a string version of the data inside the NaturalNumber.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The stringification of the NaturalNumber has been returned.
	 */
	
	String stringify();
	
	/* 
	 * Returns the amount of elements in the NaturalNumber.
	 * 
	 * @precondition -
	 * 
	 * @postcondition The amount of elements in the NaturalNumber has been returned.
	 */
	
	int getSize();
}