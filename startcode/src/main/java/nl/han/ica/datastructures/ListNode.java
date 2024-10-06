package nl.han.ica.datastructures;

public class ListNode<T> {
    private T value;
    private ListNode<T> next;

    public ListNode() {}

    public ListNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public ListNode<T> getNext() {
        return next;
    }

    public void setNext(ListNode<T> next) {
        this.next = next;
    }
}
