package structures;

import java.util.ArrayList;
import java.util.function.BiPredicate;

public class Heap<T extends Comparable<T>> {

    protected ArrayList<T> heap;
    protected final BiPredicate<T, T> minHeap = (a, b) -> a.compareTo(b) < 0;
    protected final BiPredicate<T, T> maxHeap = (a, b) -> a.compareTo(b) > 0;

    public Heap() {
        heap = new ArrayList<>();
    }

    /**
     * Returns the element at the top of the heap without removing it.
     *
     * @return The element at the top of the heap
     */
    public T peek() {
        return heap.get(0);
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
     * Adds an element to the heap.
     * <p>
     * The element is appended to the end of the heap array and then the {@code siftUp()}
     * method is called to bubble it up to the appropriate position based on the
     * heap ordering property. The {@code siftUp()} method swaps the element with its parent
     * recursively until finding the correct position for the new element.
     * </p>
     *
     * @param item The element to add to the heap.
     */
    public void add(T item) {
        heap.add(item);
        siftUp(heap.size() - 1);
    }

    /**
     * Removes and returns the root element of the heap.
     * <p>
     * The root element is removed by swapping it with the last element in the
     * heap array. Then, the {@code siftDown()} method is called to sink the new root element
     * to the proper position based on the heap ordering property by swapping it
     * with the smallest child recursively.
     * </p>
     *
     * @return The root element that was removed.
     */
    public T poll() {
        T min = heap.get(0);
        swap(0, heap.size()-1);
        heap.remove(heap.size()-1);
        siftDown(minHeap, 0, heap.size());
        return min;
    }

    /**
     * Swaps two elements in the heap array by index.
     * <p>
     * This helper method is used by {@code siftUp()} and {@code siftDown()} to swap elements
     * and restore heap ordering. The swap is done by storing temp reference
     * to one element and then swapping the array references.
     * </p>
     *
     * @param i The index of the first element
     * @param j The index of the second element
     */
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Sorts the elements in the heap using heapsort.
     * <p>
     * The sort is performed in-place by first converting the heap into a
     * max-heap by calling {@code siftDown()} repeatedly from mid-point down.
     * Then the maximum element is swapped to the end and the heap is
     * sifted down again, recursively reducing the heap size after each swap.
     * This results in the elements being popped from the heap in descending
     * order, achieving the sort.
     * </p>
     */
    public void sort() {
        int size = heap.size();

        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(minHeap, i, size);
        }

        for (int i = size - 1; i >= 0; i--) {
            swap(0, i);
            siftDown(maxHeap, 0, i);
        }
    }

    /**
     * Sift down operation to maintain heap order property.
     * <p>
     * This method checks if the element at the given index violates the heap
     * order property with either of its children, and swaps the element with
     * the appropriate child if necessary to restore the heap order.
     * The operation is repeated recursively on the affected child index if
     * a swap occurred.
     * </p>
     * <p>
     * The order property is determined by the given comparator function, which
     * can be either a min-heap or max-heap comparison. The size parameter indicates
     * the effective heap size to consider for comparing children.
     * </p>
     *
     * @param comparator {@code BiPredicate} comparator function to determine heap order
     * @param index Index of node to sift down
     * @param size Effective heap size to consider
     */
    private void siftDown(BiPredicate<T,T> comparator, int index, int size) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int value = index;

        if (left < size && comparator.test(heap.get(left), heap.get(value))) {
            value = left;
        }

        if (right < size && comparator.test(heap.get(right), heap.get(value))) {
            value = right;
        }

        if (value != index) {
            swap(index, value);
            siftDown(comparator, value, size);
        }
    }

    /**
     * Sift up operation to maintain heap order property.
     * <p>
     * This method checks if the element at the given index violates the heap
     * order property with its parent, and swaps the element with its parent
     * if necessary to restore the heap order. The operation bubbles the element
     * up recursively until the heap order is satisfied.
     * </p>
     *
     * @param index Index of node to sift up
     */
    private void siftUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && maxHeap.test(heap.get(index), heap.get(parent))) {
            swap(parent, index);
            index = parent;
            parent = (index - 1) / 2;
        }
    }
}
