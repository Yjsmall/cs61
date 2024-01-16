package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> previous;
        public Node() {
            next = this;
            previous = this;
        }

        public Node(T val, Node<T> pre, Node<T> nxt) {
            value = val;
            next = nxt;
            previous = pre;
        }
    }

    /**
     * sentinel.next = first;
     * sentinel.previous = last;
     */
    private Node<T> sentinel;

    private int size;
    public LinkedListDeque(){
        sentinel = new Node<>();
        size = 0;
    }
    @Override
    public void addFirst(T x) {
        Node<T> node;
        if (size == 0) {
            node = new Node<>(x, sentinel, sentinel);
            sentinel.previous = node;
        } else {
            node = new Node<>(x, sentinel, sentinel.next);
            sentinel.next.previous = node;
        }
        sentinel.next = node;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node<T> node;
        if (size == 0) {
            node = new Node<>(x, sentinel, sentinel);
            sentinel.next = node;
        } else {
            node = new Node<>(x, sentinel.previous, sentinel);
            sentinel.previous.next = node;
        }
        sentinel.previous = node;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node<T> cur = this.sentinel.next;
        int size = size();
        while (size > 0) {
            returnList.add(cur.value);
            cur = cur.next;
            size--;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {return null;}
        Node<T> node = sentinel.next;
        if (size == 1) {
            sentinel.next = sentinel;
            sentinel.previous = sentinel;
        }

        sentinel.next = sentinel.next.next;
        size--;
        return node.value;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {return null;}
        Node<T> node = sentinel.previous;
        if (size == 1) {
            sentinel.next = sentinel;
            sentinel.previous = sentinel;
        }

        sentinel.previous = sentinel.previous.previous;
        size--;
        return node.value;
    }

    @Override
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }

        if (index < 0 || index >= size) {
            return null;
        }

        Node<T> cur = sentinel.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.value;
    }

    private T getRecursive(int index, Node<T> node) {
        if (isEmpty()) {
            return null;
        }

        if (index < 0 || index >= size) {
            return null;
        }

        if (index == 0) {
            return node.value;
        } else {
            return getRecursive(--index, node.next);
        }

    }

    @Override
    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }
}
