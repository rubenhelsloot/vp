package assignment2;

public class KeyValuePair<K extends Data<K>, V extends Clonable<V>> implements Data<KeyValuePair<K,V>> {
	
	private K key;
	private V value;
	
	KeyValuePair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
	
	@Override
	public KeyValuePair<K,V> clone() {
		return new KeyValuePair<K,V>((K) key.clone(), (V) value.clone());
	}

	@Override
	public int compareTo(KeyValuePair<K, V> o) {
		return key.compareTo(o.getKey());
	}
}
