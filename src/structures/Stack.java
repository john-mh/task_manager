package src.structures;

import java.util.EmptyStackException;
import java.util.Vector;

public class Stack<E> extends Vector<E> {

    public Stack() {}

    public void push(E item) {
        addElement(item);
    }
    public E pop() {
        E item;
        int len = size();

        item = peek();
        removeElementAt(len - 1);

        return item;
    }


    public E peek() {
        int len = size();
        if (len == 0) {
            throw new EmptyStackException();
        }
        return elementAt(len - 1);
    }

    public boolean isEmpty() { return size() == 0; }

}
