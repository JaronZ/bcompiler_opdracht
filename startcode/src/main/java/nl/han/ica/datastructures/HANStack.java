package nl.han.ica.datastructures;

import java.util.EmptyStackException;

public class HANStack<T> implements IHANStack<T> {
    private final IHANLinkedList<T> items;

    public HANStack() {
        this(new HANLinkedList<>());
    }

    /**
     * Constructor for unit test mocking.
     */
    HANStack(IHANLinkedList<T> items) {
        this.items = items;
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
