package ru.academits.pereyma.myarraylist;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] items;
    private int size;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be one or greater");
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(o, items[i])) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            ++currentIndex;

            return items[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    public void trimToSize() {
        items = Arrays.copyOf(items, size);
    }

    @Override
    public boolean add(E e) {
        if (size >= items.length) {
            increaseCapacity();
        }

        items[size] = e;

        ++size;
        ++modCount;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(o, get(i))) {
                System.arraycopy(items, i + 1, items, i, size - i - 1);

                --size;
                ++modCount;

                return true;
            }
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
        while (items.length < size + c.size()) {
            increaseCapacity();
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(c.toArray(), 0, items, size, c.size());

        size += c.size();
        ++modCount;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index " + index + " must be greater than 0 and not greater than list size " + size);
        }

        while (items.length < size + c.size()) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + c.size(), size - index);
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(c.toArray(), 0, items, index, c.size());

        size += c.size();
        ++modCount;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;

        for (Object e : c) {
            for (int i = 0; i < size; ++i) {
                if (Objects.equals(e, items[i])) {
                    System.arraycopy(items, i + 1, items, i, size - i - 1);

                    --i;
                    --size;
                    ++modCount;

                    isChanged = true;
                }
            }
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;

        for (int i = 0; i < size; ++i) {
            if (!c.contains(items[i])) {
                System.arraycopy(items, i + 1, items, i, size - i - 1);

                --i;
                --size;
                ++modCount;
                isChanged = true;
            }
        }

        return isChanged;
    }


    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof List) || size != ((List<?>) o).size()) {
            return false;
        }

        int i = 0;

        for (Object e : (List<?>) o) {
            if (!Objects.equals(e, items[i])) {
                return false;
            }

            ++i;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (E e : this) {
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        }

        return hashCode;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index " + index + "must be >= 0 and less than List size " + size);
        }

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index " + index + " must be >= 0 and less than List size " + size);
        }

        E oldElement = items[index];
        items[index] = element;

        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index " + index + " must be >= 0 and not greater than list size " + size);
        }

        if (items.length <= size) {
            increaseCapacity();
        }

        if (index != size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = element;

        ++size;
        ++modCount;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index " + index + " must be >= 0 and less than List size " + size);
        }

        E oldElement = items[index];

        if (index != size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        --size;
        ++modCount;

        return oldElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (int i = 0; i < size - 1; ++i) {
            sb.append(items[i]).append(", ");
        }

        return sb.append(items[size - 1]).append("]").toString();
    }
}
