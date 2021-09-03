package ru.academits.pereyma.myarraylist;

import java.util.*;
import java.util.function.UnaryOperator;

public class MyArrayList<E> implements List<E> {
    private Object[] items;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private int modCount;

    public MyArrayList() {
        items = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be one or greater");
        }

        items = new Object[capacity];
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
        for (Object e : items) {
            if (Objects.equals(o, e)) {
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
        private int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            ++currentIndex;
            return (E) items[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(items, size);
        }

        for (int i = 0; i < size; ++i) {
            a[i] = (T) items[i];

            if (a.length > size) {
                a[i + 1] = null;
            }
        }

        return a;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public boolean add(E e) {
        if (size >= items.length) {
            increaseCapacity();
        }

        items[size] = e;

        ++size;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(o, get(i))) {
                System.arraycopy(items, i + 1, items, i, size - i - 1);

                --size;
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

        System.arraycopy(c.toArray(), 0, items, size, c.size());

        size += c.size();

        return true;
    }

    @Override

    public boolean addAll(int index, Collection<? extends E> c) {
        while (items.length < size + c.size()) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + c.size(), c.size());
        System.arraycopy(c.toArray(), 0, items, index, c.size());

        size += c.size();

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;

        for (Object e : c) {
            for (int i = 0; i < size; ++i) {
                if (Objects.equals(e, items[i])) {
                    System.arraycopy(items, i + 1, items, i, size - i + 1);
                    --i;

                    --size;
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
                System.arraycopy(items, i + 1, items, i, size - i + 1);
                --i;

                --size;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayList<?> that = (MyArrayList<?>) o;
        return size == that.size && Arrays.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(items);
        return result;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index must be greater than 0 and less than List size");
        }
        return (E)items[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index must be greater than 0 and less than List size");
        }

        E temp = (E) items[index];
        items[index] = element;
        return temp;
    }

    @Override
    public void add(int index, E element) {
        if (items.length <= size) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index + 1);
        items[index] = element;
        ++size;
    }

    @Override
    public E remove(int index) {
        E temp = (E)items[index];
        System.arraycopy(items, index, items, index - 1, size - index);

        --size;

        return temp;
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < size; ++i) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for(int i = size - 1; i >= 0; --i) {
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
        return "MyArrayList{" +
                "items=" + Arrays.toString(items) +
                ", size=" + size +
                ", modCount=" + modCount +
                '}';
    }
}
