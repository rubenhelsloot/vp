package assignment2;

public class Table<K extends Data<K>, V extends Clonable<V>> implements TableInterface<K, V> {

	private List<KeyValuePair<K, V>> entry;

	Table() {
		entry = new List<KeyValuePair<K, V>>();
	}

	@Override
	public Table<K, V> init() {
		entry.init();
		return this;
	}

	private boolean find(K key) {
		return entry.find(new KeyValuePair<K, V>(key, null));
	}

	@Override
	public Table<K, V> update(K key, V value) {
		remove(key);
		KeyValuePair<K, V> newest = new KeyValuePair<K, V>(key, value);
		entry.insert(newest);
		return this;
	}

	@Override
	public Table<K, V> remove(K key) {
		if (find(key)) {
			entry.remove();
		}
		return this;
	}

	@Override
	public V getValue(K key) throws APException {
		if (find(key)) {
			return entry.retrieve().getValue();
		} else {
			throw new APException(
					"The value associated with key " + key.toString() + " was failed to retrieve as the key was not found.");
		}
	}

	@Override
	public int getSize() {
		return entry.size();
	}

	@Override
	public boolean isEmpty() {
		return entry.isEmpty();
	}

	@Override
	public Table<K, V> clone() {
		Table<K,V> cloneTable = new Table<K,V>();
		cloneTable.entry = entry.clone();
		return cloneTable;
	}
}
