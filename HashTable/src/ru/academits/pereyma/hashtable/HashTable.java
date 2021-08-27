package ru.academits.pereyma.hashtable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class HashTable<E> implements Collection<E> {
    List<E>[] hashTable;

    private class MyHashTableIterator implements Iterator<E> {
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size();
        }

        @Override
        public E next() {
            ++currentIndex;

            int indexInList = -size();
            int listCounter = 0;

            for (; indexInList < 0; ++listCounter) {
                indexInList += hashTable[listCounter].size();
            }

            return hashTable[listCounter].get(indexInList);
        }

        @Override
        public void remove() {

        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {

        }
    }

    @Override
    public int size() {
        int result = 0;

        for (List<E> e : hashTable) {
            result += e.size();
        }

        return result;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return hashTable[Math.abs(o.hashCode() % hashTable.length)].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyHashTableIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[size()];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return hashTable[Math.abs(e.hashCode() % hashTable.length)].add(e);
    }

    @Override
    public boolean remove(Object o) {
        return hashTable[Math.abs(o.hashCode() % hashTable.length)].remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (List<E> e : hashTable) {
            e.clear();
        }
    }
}
