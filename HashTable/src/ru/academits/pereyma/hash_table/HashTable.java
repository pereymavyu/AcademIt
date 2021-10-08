package ru.academits.pereyma.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int DEFAULT_ARRAY_SIZE = 10;

    private final List<E>[] array;
    private int size;
    private int modCount;

    public HashTable() {
        this(DEFAULT_ARRAY_SIZE);
    }

    public HashTable(int arraySize) {
        //noinspection unchecked
        array = (ArrayList<E>[]) new ArrayList<?>[arraySize];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
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

    private class MyHashTableIterator implements Iterator<E> {                                  // TO DO
        private int currentIndex = -1;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size();
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("The collection may have changed during iteration");
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            ++currentIndex;

            int itemIndex = currentIndex;
            int listIndex = 0;

            for (; array[listIndex] == null || itemIndex >= array[listIndex].size(); ++listIndex) {
                if (array[listIndex] == null) {
                    continue;
                }

                itemIndex -= array[listIndex].size();
            }

            return array[listIndex].get(itemIndex);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] resultingArray = new Object[size()];

        int i = 0;

        for (E e : this) {
            resultingArray[i] = e;

            ++i;
        }

        return resultingArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size()) {                                                      //TO DO
            //noinspection unchecked
            return (T[]) toArray();
        }

        int copiedItemsAmount = 0;

        for (List<E> e : array) {
            if (e == null) {
                continue;
            }

            //noinspection unchecked
            T[] arrayFromCurrentList = (T[]) e.toArray();
            System.arraycopy(arrayFromCurrentList, 0, a, copiedItemsAmount, arrayFromCurrentList.length);

            copiedItemsAmount += arrayFromCurrentList.length;
        }

        if (a.length > size()) {
            a[size()] = null;
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

        --size;
        ++modCount;

        return array[index].remove(o);
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
    public boolean retainAll(Collection<?> c) {                           //TO DO
        boolean isChanged = false;

        for (E e : this) {
            if (!c.contains(e)) {
                int index = getIndex(e);

                while (array[index].contains(e)) {
                    array[index].remove(e);
                }

                isChanged = true;
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
            e.clear();
        }

        size = 0;
        ++modCount;
    }

    @Override
    public String toString() {
        return "HashTable" + Arrays.toString(array);
    }
}