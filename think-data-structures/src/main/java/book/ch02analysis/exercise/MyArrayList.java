package book.ch02analysis.exercise;

import java.util.*;

// 배열 기반의 리스트 구현
public class MyArrayList<T> implements List<T> {

    int size;
    private T[] array;

    public MyArrayList() {
        array = (T[]) new Object[10];
        size = 0;
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
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        T[] copy = Arrays.copyOf(array, size);
        return Arrays.asList(copy).iterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T e) {
        // 배열이 가득 차면 새로운 배열 할당
        if (size >= array.length) {
            T[] bigArr = (T[]) new Object[array.length * 2];
            System.arraycopy(array, 0, bigArr, 0, array.length);
            array = bigArr;
        }

        array[size] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int idx = indexOf(o);
        if (idx == -1) return false;
        remove(idx);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object e : collection) {
            if (!contains(e)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean flag = true;
        for (T element : collection) {
            flag &= add(element);
        }
        return flag;
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean flag = true;
        for (Object obj : collection) {
            flag &= remove(obj);
        }
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        array = (T[]) new Object[10];
        size = 0;
    }

    @Override
    public T get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[i];
    }

    @Override
    public T set(int i, T e) {
        // Exercise 1 : 해당 인덱스에 요소를 넣고, 원본은 반환
        T origin = get(i);
        array[i] = e;
        return origin;
    }

    @Override
    public void add(int i, T e) {
        // Exercise 3. i 위치에 e 삽입 => 쉬프트 필요
        if (i < 0 || i > size)
            throw new IndexOutOfBoundsException();

        // 먼저 마지막 위치에 삽입
        add(e);
        for (int pos = i + 1; pos < size; pos++) {
            array[pos + 1] = array[pos];
        }
        array[i] = e;
    }

    @Override
    public T remove(int idx) {
        // Exercise 4. i 위치에 요소를 삭제 후, 해당 요소 반환 => 왼쪽 쉬프트 필요
        T obj = get(idx);
        for (int i = idx; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return obj;
    }

    @Override
    public int indexOf(Object o) {
        // Exercise 2. 요소의 왼쪽 기준 인덱스 반환(없으면 -1)
        for (int i = 0; i < size; i++) {
            if (equals(array[i], o)) return i;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        // Exercise 2-1. 요소의 오른쪽 기준 인덱스 반환(없으면 -1)
        for (int i = size - 1; i >= 0; i--) {
            if (equals(array[i], o)) return i;
        }

        return -1;
    }

    // 유틸 메서드
    private boolean equals(Object target, Object element) {
        if (target == null) {
            return element == null;
        } else {
            return target.equals(element);
        }
    }

    @Override
    public ListIterator<T> listIterator() {
        T[] copy = Arrays.copyOf(array, size);
        return Arrays.asList(copy).listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        T[] copy = Arrays.copyOf(array, size);
        return Arrays.asList(copy).listIterator(i);
    }

    @Override
    public List<T> subList(int i, int i1) {
        if (i < 0 || i1 >= size || i > i1)
            throw new IndexOutOfBoundsException();

        T[] sub = Arrays.copyOfRange(array, i, i1);
        return Arrays.asList(sub);
    }
}
