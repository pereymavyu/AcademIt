package ru.academits.pereyma.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final List<E>[] hashTable;
    private static final int DEFAULT_HASHTABLE_SIZE = 10;
    private int modCount = 0;

    public HashTable() {
        this(DEFAULT_HASHTABLE_SIZE);
    }

    public HashTable(int hashTableSize) {
        //noinspection unchecked
        hashTable = (ArrayList<E>[]) (new ArrayList<?>[hashTableSize]);
    }

    @Override
    public int size() {
        int size = 0;

        for (List<E> e : hashTable) {
            if (e == null) {
                continue;
            }

            size += e.size();
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private int getNormalizedHashCode(Object o) {
        return Math.abs(o.hashCode() % hashTable.length);
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }

        int normalizedHashCode = getNormalizedHashCode(o);

        if (hashTable[normalizedHashCode] == null) {
            return false;
        }

        return hashTable[normalizedHashCode].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyHashTableIterator();
    }

    private class MyHashTableIterator implements Iterator<E> {
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

            for (; hashTable[listIndex] == null || itemIndex >= hashTable[listIndex].size(); ++listIndex) {
                if (hashTable[listIndex] == null) {
                    continue;
                }

                itemIndex -= hashTable[listIndex].size();
            }

            return hashTable[listIndex].get(itemIndex);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] resultingArray = new Object[size()];

        int copiedItemsAmount = 0;

        for (List<E> e : hashTable) {
            if (e == null) {
                continue;
            }

            Object[] arrayFromCurrentList = e.toArray();
            System.arraycopy(arrayFromCurrentList, 0, resultingArray, copiedItemsAmount, arrayFromCurrentList.length);

            copiedItemsAmount += arrayFromCurrentList.length;
        }

        return resultingArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size()) {
            //noinspection unchecked
            return (T[]) toArray();
        }

        int copiedItemsAmount = 0;

        for (List<E> e : hashTable) {
            if (e == null) {
                continue;
            }

            @SuppressWarnings("unchecked") T[] arrayFromCurrentList = (T[]) e.toArray();
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
        int normalizedHashCode = getNormalizedHashCode(e);

        if (hashTable[normalizedHashCode] == null) {
            hashTable[normalizedHashCode] = new ArrayList<>();
        }

        ++modCount;

        return hashTable[normalizedHashCode].add(e);
    }

    @Override
    public boolean remove(Object o) {
        int normalizedHashCode = getNormalizedHashCode(o);

        if (hashTable[normalizedHashCode] == null) {
            return false;
        }

        ++modCount;

        return hashTable[normalizedHashCode].remove(o);
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
        if (c.contains(null)) {
            throw new NullPointerException("Adding null items to HashTable is not allowed");
        }

        for (E e : c) {
            add(e);
        }

        ++modCount;

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

        if (isChanged) {
            ++modCount;
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;

        for (E e : this) {
            if (!c.contains(e)) {
                int normalizedHashCode = getNormalizedHashCode(e);

                while (hashTable[normalizedHashCode].contains(e)) {
                    hashTable[normalizedHashCode].remove(e);
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
        for (List<E> e : hashTable) {
            e.clear();
        }

        ++modCount;
    }

    @Override
    public String toString() {
        return "HashTable" + Arrays.toString(hashTable);
    }
}