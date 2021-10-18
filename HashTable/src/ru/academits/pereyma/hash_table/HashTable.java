package ru.academits.pereyma.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int DEFAULT_ARRAY_LENGTH = 10;

    private final List<E>[] array;
    private int size;
    private int modCount;

    public HashTable() {
        this(DEFAULT_ARRAY_LENGTH);
    }

    public HashTable(int arrayLength) {
        if (arrayLength < 1) {
            throw new IllegalArgumentException("Array length " + arrayLength + " must not be less than 1");
        }

        //noinspection unchecked
        array = (ArrayList<E>[]) new ArrayList<?>[arrayLength];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % array.length);
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);

        return array[index] != null && array[index].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyHashTableIterator();
    }

    private class MyHashTableIterator implements Iterator<E> {
        private int currentIndex = -1;

        private int listInArrayIndex = 0;
        private int itemInListIndex = -1;

        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("The collection may have changed during iteration");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("There is no next element");
            }

            while (array[listInArrayIndex] == null || itemInListIndex >= array[listInArrayIndex].size() - 1) {
                ++listInArrayIndex;
                itemInListIndex = -1;
            }

            ++itemInListIndex;
            ++currentIndex;

            return array[listInArrayIndex].get(itemInListIndex);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] resultingArray = new Object[size];

        int i = 0;

        for (E e : this) {
            resultingArray[i] = e;

            ++i;
        }

        return resultingArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, a.getClass());
        }

        int i = 0;

        for (E e : this) {
            //noinspection unchecked
            a[i] = (T) e;

            ++i;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        int index = getIndex(e);

        if (array[index] == null) {
            array[index] = new ArrayList<>();
        }

        ++size;
        ++modCount;

        return array[index].add(e);
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (array[index] == null) {
            return false;
        }

        if (array[index].remove(o)) {
            --size;
            ++modCount;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0) {
            return false;
        }

        for (E e : c) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;

        for (Object e : c) {
            while (remove(e)) {
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;
        int oldListSize;

        for (List<E> list : array) {
            if (list != null && (oldListSize = list.size()) > 0 && list.retainAll(c)) {
                isChanged = true;

                size += list.size() - oldListSize;
            }
        }

        if (isChanged) {
            ++modCount;
        }

        return isChanged;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (List<E> e : array) {
            if (e != null) {
                e.clear();
            }
        }

        size = 0;
        ++modCount;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}