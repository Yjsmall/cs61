import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node<T> {
        T value;
        Node next;
        Node previous;
        public Node() {
        }

        public Node(T val, Node nxt, Node pre) {
            value = val;
            next = nxt;
            previous = pre;
        }
    }
    private Node sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel = new Node<>(null, null, null);
        size = 0;
    }
    @Override
    public void addFirst(T x) {
        Node<T> node = new Node<>(x, sentinel, sentinel.previous);
        size++;
    }

    @Override
    public void addLast(T x) {

    }

    @Override
    public List<T> toList() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
