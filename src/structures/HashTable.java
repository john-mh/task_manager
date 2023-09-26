package src.structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

public class HashTable<K, V> implements Iterable<V> {

    @SuppressWarnings("unchecked")
    private final LinkedList<Entry<K, V>>[] table = new LinkedList[16];

    public HashTable() {
    }

    public void add(V item) {}
    public void add(K key, V item) {}
    public void remove(V item) {}
    public V get(K key) { return null; }

    @Override
    public Iterator<V> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super V> action) {
        Iterable.super.forEach(action);
    }
}
