package nl.han.ica.datastructures;

import java.util.NoSuchElementException;

public class HANLinkedList<T> implements IHANLinkedList<T> {
    private final ListNode<T> head;
    private int size = 0;

    public HANLinkedList() {
        this(new ListNode<>());
    }

    /**
     * Constructor for unit test mocking.
     */
    HANLinkedList(ListNode<T> head) {
        this.head = head;
    }

    @Override
    public void addFirst(T value) {
        ListNode<T> newNode = new ListNode<>(value);
        newNode.setNext(head.getNext());
        head.setNext(newNode);
        size++;
    }

    @Override
    public void clear() {
        head.setNext(null);
        size = 0;
    }

    @Override
    public void insert(int index, T value) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ListNode<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        ListNode<T> nodeToInsert = new ListNode<>(value);
        nodeToInsert.setNext(currentNode.getNext());
        currentNode.setNext(nodeToInsert);
        size++;
    }

    @Override
    public void delete(int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        ListNode<T> currentNode = head;
        for (int i = 0; i < pos; i++) {
            currentNode = currentNode.getNext();
        }
        ListNode<T> nodeToDelete = currentNode.getNext();
        currentNode.setNext(nodeToDelete.getNext());
        size--;
    }

    @Override
    public T get(int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        ListNode<T> currentNode = head;
        for (int i = 0; i <= pos; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getValue();
    }

    @Override
    public void removeFirst() throws NoSuchElementException {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        head.setNext(head.getNext().getNext());
        size--;
    }

    @Override
    public T getFirst() throws NoSuchElementException {
        if (size < 1) {
            throw new NoSuchElementException();
        }
        return head.getNext().getValue();
    }

    @Override
    public int getSize() {
        return size;
    }
}
