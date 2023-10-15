package structures;

/**
 * Used to store a generic key-value pair.
 * @param key
 * @param value
 * @param <K>
 * @param <V>
 */
public record Entry<K, V>(K key, V value) {
}
