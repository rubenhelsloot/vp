package assignment2;

public interface TableInterface<K extends Data, V extends Clonable> extends Clonable {
	
	/*
	 * @precondition
	 * 
	 * @postcondition
	 */
	
	Table<K,V> init();
	
	/*
	 * @precondition
	 * 
	 * @postcondition
	 */
	
	Table<K,V> update(K key, V value);
	
	/*
	 * @precondition
	 * 
	 * @postcondition
	 */
	
	Table<K,V> remove(K key);
	
	/*
	 * @precondition
	 * 
	 * @postcondition
	 */
	
	V getValue(K key) throws APException;
	
	/*
	 * @precondition
	 * 
	 * @postcondition
	 */
		
	Table<K,V> clone();
}
