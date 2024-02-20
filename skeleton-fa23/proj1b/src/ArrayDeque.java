import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private final int REFACTOR = 2;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = items.length / 2;
        nextLast = nextFirst + 1;
        size = 0;
    }

    /**
     * 将first元素至数组末端元素转移到新数组的尾部
     * 然后再把剩下元素放置新数组的头部
     *
     * @param capacity
     */
    private void resize(int capacity) {
        if (capacity < 8) {
            return;
        }
        T[] arr = (T[]) new Object[capacity];

        int firstPos = (nextFirst + 1) % size;
        int frontSize = size - firstPos;

        for (int i = 0; i < frontSize; i++) {
            int arrIdx = arr.length - frontSize + i;
            int itemIdx = firstPos + i;
            arr[arrIdx] = items[itemIdx];
        }

        for (int i = 0; i < firstPos; i++) {
            arr[i] = items[i];
        }

        nextFirst = arr.length - frontSize - 1;
        nextLast = firstPos;
        items = arr;
    }

    private void resizeDown(int capacity) {
        if (capacity < 8) {
            return;
        }

        if (nextLast < nextFirst) {
            resize(capacity);
        } else {
            T[] arr = (T[]) new Object[capacity];
            for (int i = nextFirst + 1, j = 0; i < nextLast; i++, j++) {
                arr[j] = items[i];
            }

            nextLast = nextLast - nextFirst - 1;
            nextFirst = arr.length - 1;
            items = arr;
        }

    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * REFACTOR);
        }
        items[nextFirst] = x;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * REFACTOR);
        }
        items[nextLast] = x;

        nextLast = (nextLast + 1 + items.length) % items.length;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if (nextFirst < nextLast && size != items.length) {
            returnList.addAll(Arrays.asList(items).subList(nextFirst + 1, nextLast));
            return returnList;
        }

        int firstNum = 0;
        for (int i = nextFirst + 1; i < items.length; i++) {
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
        if (isEmpty()) {
            return null;
        }
        int pos = (nextFirst + 1 + items.length) % items.length;
        T removedItem = items[pos];
        items[pos] = null;
        nextFirst = pos;
        size--;
        if (size < items.length / 4) {
            resizeDown(items.length / REFACTOR);
        }
        return removedItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        int pos = (nextLast - 1 + items.length) % items.length;
        T removedItem = items[pos];
        items[pos] = null;
        nextLast = pos;
        size--;

        if (size < items.length / 4) {
            resizeDown(items.length / REFACTOR);
        }
        return removedItem;
    }

    @Override
    public T get(int index) {
        if (size == items.length && index >= 0 && index < items.length) {
            return items[index];
        }

        if (nextFirst < index && index < nextLast) {
            return items[index];
        }

        if (nextFirst < index && index < items.length) {
            return items[index];
        }

        if (index >= 0 && index < nextLast) {
            return items[index];
        }

        return null;

    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
