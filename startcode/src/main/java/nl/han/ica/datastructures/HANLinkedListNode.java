package nl.han.ica.datastructures;

public class HANLinkedListNode<T> {
    private T value;
    private HANLinkedListNode<T> next;

    public HANLinkedListNode() {}

    public HANLinkedListNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public HANLinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(HANLinkedListNode<T> next) {
        this.next = next;
    }
}
