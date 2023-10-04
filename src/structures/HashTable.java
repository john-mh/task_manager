package src.structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

public class HashTable<K, V> implements Iterable<V> {

    @SuppressWarnings("unchecked")
    private final LinkedList<Entry<K, V>>[] table = new LinkedList[16];
    
    /**
     * Iniciliza la tabla hash
     */
    public HashTable() {

        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }

    }

    /**
     * Generar un UUID aleatorio como clave única
     * @param item
     */
    public void add(V item) {
         
        String key = UUID.randomUUID().toString();
        add(key, item);
    }

    /**
     * Agrega el elemento en la posición index de la lista enlazada correspondiente
     * @param key
     * @param item
     */
    public void add(K key, V item) {

        int index = hash(key);

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
        return null;
    }


}
