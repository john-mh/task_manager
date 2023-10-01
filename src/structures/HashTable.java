package src.structures;

import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K, V> implements Iterable<V> {

    @SuppressWarnings("unchecked")
    private final LinkedList<Entry<K, V>>[] table = new LinkedList[16];
    
    /**
     * Iniciliza la tabla hash
     */
    public HashTable() {

    }

    /**
     * Agrega el elemento en la posición index de la lista enlazada correspondiente
     * @param key
     * @param item
     */
    public void add(K key, V item) {

        int index = calculateIndex(key);
    }


    /**
     * Elimina el elemento en la posición index de la lista enlazada correspondiente
     * @param key
     */
    public void remove(K key) {

        int index = calculateIndex(key);
    }

    /**
     * 
     * @param key
     * @return
     */
    public V get(K key) {
        
        int index = calculateIndex(key);
        
        return null; 
    }

    /**
     * Calcula el índice en función de la clave
     * @param key
     * @return
     */
    private int calculateIndex(K key) {
        
        return key.hashCode() % table.length;
    }

    /**
     * 
     */
    @Override
    public Iterator<V> iterator() {
        return null;
    }


}
