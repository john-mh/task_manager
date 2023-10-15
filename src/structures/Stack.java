package structures;

import java.util.EmptyStackException;
import java.util.Vector;

/**
 * Stack implementation using a Vector.
 */
public class Stack<E> extends Vector<E> {

    /**
     * Creates an empty Stack.
     */
    public Stack() {}

    /**
     * Pushes an item onto the top of the stack.
     *
     * @param item  The item to push onto the stack.
     */
    public void push(E item) {
        addElement(item);
    }

    /**
     * Pops and returns the item on the top of the stack.
     *
     * @return The item from the top of the stack.
     */
    public E pop() {
        E item;
        int len = size();

        item = peek();
        removeElementAt(len - 1);

        return item;
    }

    /**
     * Returns the item at the top of the stack without removing it.
     *
     * @return The item at the top of the stack.
     * @throws EmptyStackException If the stack is empty.
     */
    public E peek() {
        int len = size();
        if (len == 0) {
            throw new EmptyStackException();
        }
        return elementAt(len - 1);
    }

    /**
     * Returns true if the stack contains no elements.
     *
     * @return true if the stack is empty, false otherwise.
     */
    public boolean isEmpty() { return size() == 0; }

}
