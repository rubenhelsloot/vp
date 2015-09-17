package assignment2;

public interface KeyValueInterface<K extends Data, V extends Clonable> extends Data {
	
	/*
	 * @precondition - 
	 * 
	 * @postcondition Returns K key 
	 */
	
	K getKey();
	
	/*
	 * @precondition - 
	 * 
	 * @postcondition Returns V value
	 */
	
	V getValue();
	
	/*
	 * @precondition - 
	 */
}
