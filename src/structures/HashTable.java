package structures;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class that implements the hash table
 * @param <K>
 * @param <V>
 */
public class HashTable<K, V> implements Iterable<V> {

    @SuppressWarnings("unchecked")
    private final LinkedList<Entry<K, V>>[] table = new LinkedList[16];

    /**
     * This is the hash table constructor
     */
    public HashTable() {

        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
    }


    /**
     * Searches for a key based on a given value.
     * If scours all linked lists and records looking for a match on the value
     * @param value
     * @return
     */
    public K key(V value) {
        for (LinkedList<Entry<K, V>> list : table) {
            for (Entry<K, V> entry : list) {
                if (entry.value().equals(value)) {
                    return entry.key();
                }
            }
        }
        return null;
    }

    /**
     * Returns a list containing all values stored in the hash table
     * @return
     */
    public LinkedList<V> values() {

        LinkedList<V> values = new LinkedList<>();

        for (LinkedList<Entry<K, V>> list : table) {
            for (Entry<K, V> entry : list) {
                values.add(entry.value());
            }
        }

        return values;
    }

    /**
     * Adds a key-value pair to the hash table.
     * It calculates the table index based on the key and adds the new record to the corresponding linked list.
     * @param key
     * @param item
     */
    public void add(K key, V item) {

        int index = calculateIndex(key);

        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : table[index]) {
            if (entry.key().equals(key)) {

                table[index].remove(entry);
                break;
            }
        }

        table[index].add(new Entry<>(key, item));
    }




    /**
     * Removes an item from the hash table based on its key.
     * @param key
     */
    public void remove(K key) {

        int index = calculateIndex(key);
        LinkedList<Entry<K, V>> list = table[index];
        Entry<K, V> entryToRemove = null;

        for (Entry<K, V> entry : list) {
            if (entry.key().equals(key)) {
                entryToRemove = entry;
                break;
            }
        }
        if (entryToRemove != null) {
            list.remove(entryToRemove);
        }
    }

    /**
     * Gets the value associated with a specific key
     * @param key
     * @return
     */
    public V get(K key) {

        int index = calculateIndex(key);
        LinkedList<Entry<K, V>> list = table[index];

        for (Entry<K, V> entry : list) {
            if (entry.key().equals(key)) {
                return entry.value();
            }
        }
        return null;
    }



    /**
     * Calculates the index based on the key
     * @param key
     * @return
     */
    private int calculateIndex(K key) {

        int index = key.hashCode() % table.length;

        if (index < 0) {
            index += table.length;
        }

        return index;
    }

    /**
     * Returns the total number of elements stored in the hash table.
     * @return
     */
    public int size() {

        int size = 0;

        for (LinkedList<Entry<K, V>> list : table) {
            size += list.size();
        }

        return size;
    }

    /**
     * Obtains the value at position zero of the hash table
     * @return
     */
    public V peek() {

        if (table[0].isEmpty()) {
            return null;
        }

        assert table[0].peek() != null;
        return table[0].peek().value();
    }

    /**
     * Checks if the hash table is empty
     * @return
     */
    public boolean isEmpty() {

        for (LinkedList<Entry<K, V>> list : table) {
            if (!list.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Allows to traverse the records in all the linked lists of the hash table sequentially.
     * @return
     */
    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {

            private int currentList = 0;
            private Iterator<Entry<K, V>> currentIterator = table[0].iterator();

            @Override
            public boolean hasNext() {
                if (currentIterator.hasNext()) {
                    return true;
                }
                while (currentList < table.length - 1) {
                    currentList++;
                    currentIterator = table[currentList].iterator();

                    if (currentIterator.hasNext()) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public V next() {
                return currentIterator.next().value();
            }
        };
    }


}
