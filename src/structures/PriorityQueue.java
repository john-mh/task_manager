package src.structures;

import java.util.ArrayList;

public class PriorityQueue<T extends Comparable<T>> {

    protected final ArrayList heap;


    public PriorityQueue() {
        heap = new Heap<>();
    }

    public T peek() {
        return heap.get(0);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void add(T item) {
        heap.add(item);
        siftUp(heap.size() - 1);
    }

    public T poll() {
        T min = heap.get(0);
        swap(0, heap.size()-1);
        heap.remove(heap.size()-1);
        siftDown(0);
        return min;
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private void siftDown(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int min = index;

        if (left < heap.size() && heap.get(left).compareTo(heap.get(min)) < 0) {
            min = left;
        }

        if (right < heap.size() && heap.get(right).compareTo(heap.get(min)) < 0) {
            min = right;
        }

        if (min != index) {
            swap(index, min);
            siftDown(min);
        }
    }

    private void siftUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && heap.get(parent).compareTo(heap.get(index)) > 0) {
            swap(parent, index);
            index = parent;
            parent = (index - 1) / 2;
        }
    }
}
