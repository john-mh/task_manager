package src.structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class HashTable<K, V> implements Iterable<V> {

    @SuppressWarnings("unchecked")
    private final LinkedList<Entry<K, V>>[] table = new LinkedList[16];
    
    /**
     * Inicializa la tabla hash
     */
    public HashTable() {
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
    }



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
     * Agrega el elemento en la posición index de la lista enlazada correspondiente
     * @param key
     * @param item
     */
    public void add(K key, V item) {
        int index = Objects.hash(key);

        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        table[index].add(new Entry<>(key, item));
    }


    /**
     * Elimina el elemento en la posición index de la lista enlazada correspondiente
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
     * 
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
     * Calcula el índice en función de la clave
     * @param key
     * @return
     */
    private int calculateIndex(K key) {
        int index = key.hashCode() % table.length;

        if (index < 0) {
            index += table.length; // Asegura que el índice sea no negativo
        }

        return index;
    }

    /**
     *
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
