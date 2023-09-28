package src.structures;

import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {

    protected ArrayList<T> heap;

    public Heap() {
        heap = new ArrayList<>();
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

    public void sort() {
        int size = heap.size();

        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }

        for (int i = size - 1; i >= 0; i--) {
            swap(0, i);
            siftDown(0, i);
        }
    }

    // minheap
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

    // maxheap
    private void siftDown(int index, int size) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largest = index;

        if (left < size && heap.get(left).compareTo(heap.get(largest)) > 0) {
            largest = left;
        }

        if (right < size && heap.get(right).compareTo(heap.get(largest)) > 0) {
            largest = right;
        }

        if (largest != index) {
            swap(index, largest);
            siftDown(largest, size);
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
