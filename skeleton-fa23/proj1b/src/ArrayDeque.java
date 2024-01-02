import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int first;
    private int last;
    private final int REFACTOR = 2;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        first = items.length/2;
        last = first+1;
        size = 0;
    }
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int mid = items.length / 2;
        for (int i = 0; i < mid; i++) {
            a[i] = items[i];
            int pos = capacity - i - 1;
            int posItem = items.length - i - 1;
            a[pos] = items[posItem];
        }
        first = capacity - items.length/2 - 1;
        last = items.length / 2;
        items = a;
    }
    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * REFACTOR);
        }
        items[first] = x;
        first = --first % items.length;
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * REFACTOR);
        }
        items[last] = x;

        last = ++last % items.length;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if (first < last && size != items.length) {
            returnList.addAll(Arrays.asList(items).subList(first + 1, last));
            return returnList;
        }

        int firstNum = 0;
        for (int i = first + 1; i < items.length; i++) {
            returnList.add(items[i]);
            firstNum++;
        }

        int lastNum = size - firstNum;
        returnList.addAll(Arrays.asList(items).subList(0, lastNum));

        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
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
        if (size == items.length && index >= 0 && index < items.length) {
            return items[index];
        }

        if (first < index && index < last) {
            return items[index];
        }

        if (first < index && index < items.length) {
            return items[index];
        }

        if (index >= 0 && index < last) {
            return items[index];
        }

        return null;

    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
