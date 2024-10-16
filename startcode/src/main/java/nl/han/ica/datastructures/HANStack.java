package nl.han.ica.datastructures;

import java.util.EmptyStackException;

public class HANStack<T> implements IHANStack<T> {
    private final IHANLinkedList<T> items;

    public HANStack() {
        this.items = new HANLinkedList<>();
    }

    @Override
    public void push(T value) {
        items.addFirst(value);
    }

    @Override
    public T pop() throws EmptyStackException {
        if (items.getSize() < 1) {
            throw new EmptyStackException();
        }
        T first = items.getFirst();
        items.removeFirst();
        return first;
    }

    @Override
    public T peek() throws EmptyStackException {
        if (items.getSize() < 1) {
            throw new EmptyStackException();
        }
        return items.getFirst();
    }
}
