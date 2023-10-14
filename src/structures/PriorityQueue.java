package structures;

public class PriorityQueue<T extends Comparable<T>> {

    protected final Heap<T> heap;

    public PriorityQueue() {
        heap = new Heap<>();
    }

    /**
     * Returns the element at the top of the heap without removing it.
     *
     * @return The element at the top of the heap
     */
    public T peek() {
        return heap.peek();
    }

    /**
     * Returns the number of elements currently in the heap.
     *
     * @return The heap size
     */
    public int size() {
        return heap.size();
    }

    /**
     * Returns whether the heap is empty.
     *
     * @return True if the heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Inserts an item into the priority queue.
     *
     * @param item The item to insert.
     */
    public void add(T item) {
        heap.add(item);
    }

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return The head of the queue, or null if the queue is empty.
     */
    public T poll() {
        return heap.poll();
    }
}
