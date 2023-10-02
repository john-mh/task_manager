package src.structures;

public class PriorityQueue<T extends Comparable<T>> {

    protected final Heap<T> heap;

    public PriorityQueue() {
        heap = new Heap<>();
    }

    public T peek() {
        return heap.peek();
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void add(T item) {
        heap.add(item);
    }

    public T poll() {
        return heap.poll();
    }
}
