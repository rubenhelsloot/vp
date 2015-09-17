package assignment2;

/* ADT for the class TableInterface
 * 
 * @author Ruben Helsloot 
 * @author Sherida van den Bent
 * 
 * @elements Key, value pairs of types K, V respectively
 * @structure Ordered list of key, value pairs sorted by keys
 * @domain Any collection of key, value pairs with unique keys
 * 
 * @constructor
 *	Table ();
 *		<dl>
 *			<dt><b>PRE-condition</b><dd> -
 *			<dt><b>POST-condition</b><dd>The new object contains an empty Table.
 *		</dl>
 *		<br>
 */

public interface TableInterface<K extends Data, V extends Clonable> extends Clonable {

	/*
	 * @precondition -
	 * 
	 * @postcondition An empty table is returned
	 */

	Table<K, V> init();

	/*
	 * @precondition -
	 * 
	 * @postcondition The Key, Value pair K key, V value has been added to
	 * Table-POST, if key was already in Table-PRE, the corresponding value-PRE
	 * has been updated to the given V value.
	 */

	Table<K, V> update(K key, V value);

	/*
	 * @precondition -
	 * 
	 * @postcondition If the value K key was in Table-PRE, it is not in
	 * Table-POST. If K key was not in Table-PRE, Table-POST is the same as
	 * Table-PRE.
	 */

	Table<K, V> remove(K key);

	/*
	 * @precondition K key is in Table-PRE
	 * 
	 * @postcondition Returns the corresponding V value for the given K key
	 * 
	 * @throws if K key is not in Table-PRE
	 */

	V getValue(K key) throws APException;

	/*
	 * @precondition -
	 * 
	 * @postcondition Returns the number of rows in the Table.
	 */

	int getSize();
	
	/*
	 * @precondition -
	 * 
	 * @postcondition Returns true if the table is empty, returns false if the
	 * table is nonempty.
	 */

	boolean isEmpty();

}
