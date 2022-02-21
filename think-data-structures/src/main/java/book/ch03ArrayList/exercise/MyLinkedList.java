package book.ch03ArrayList.exercise;

import java.util.*;

public class MyLinkedList<T> implements List<T> {

    private class Node {
        public T data;
        public Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.format("Node(%s) -> %s", data.toString(), next == null ? "null" : "next node");
        }
    }

    private int size;
    private Node head;

    public MyLinkedList() {
        size = 0;
        head = null;
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
        T[] array = (T[]) toArray();
        return Arrays.asList(array).iterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (Node node = head; node != null; node = node.next) {
            array[i] = node.data;
            i++;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T e) {
        if (head == null) {
            // 첫 노드 삽입 시
            head = new Node(e);
        } else {
            // 이후 노드 생성 시 마지막 노드를 찾음
            Node lastNode = head;
            for (; lastNode.next != null; lastNode = lastNode.next) {
            }

            lastNode.next = new Node(e);
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int idx = indexOf(o);
        if(idx == -1) return false;

        remove(idx);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object o : collection) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean flag = true;
        for (T o : collection) {
            flag &= add(o);
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
        for (Object o : collection) {
            flag &= remove(o);
        }
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    @Override
    public T get(int i) {
        return getNode(i).data;
    }

    @Override
    public T set(int i, T e) {
        Node node = getNode(i);
        T origin = node.data;
        node.data = e;

        return origin;
    }

    @Override
    public void add(int i, T e) {
        Node node = new Node(e);
        if(i==0) {
            node.next = head;
            head = node;
        } else {
            Node before = getNode(i-1);
            Node next = before.next;
            node.next = next;
            before.next = node;
        }
        size++;
    }

    @Override
    public T remove(int i) {
        T originData = get(i);

        if(i==0){
            head = head.next;
        } else {
            Node before = getNode(i-1);
            before.next = before.next.next;
        }

        size--;
        return originData;
    }

    @Override
    public int indexOf(Object o) {
        int idx = 0;
        for (Node node = head; node != null; node = node.next) {
            if(equals(node.data, o)) return idx;
            idx++;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIdx = -1;
        Node node = head;
        for(int i=0;i<size;i++){
            if(equals(o, node.data)) {
                lastIdx = i;
            }
            node = node.next;
        }
        return lastIdx;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return null;
    }

    @Override
    public List<T> subList(int i, int i1) {
        if (i < 0 || i1 >= size || i > i1)
            throw new IndexOutOfBoundsException();

        Node curNode = getNode(i);

        MyLinkedList<T> ret = new MyLinkedList<>();
        int size = i1 - i;
        for(int idx=0;idx<size;idx++) {
            ret.add(curNode.data);
            curNode = curNode.next;
        }
        return ret;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder(size*2);

        Node node =head;
        for(;node.next!=null;node=node.next) {
            ret.append(node.data.toString()).append(" -> ");
        }

        ret.append(node.data.toString());
        return ret.toString();
    }


    private Node getNode(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node ret = head;
        for (int i = 0; i < idx; i++) ret = ret.next;
        return ret;
    }

    private boolean equals(Object target, Object element) {
        if (target == null) {
            return element == null;
        } else {
            return target.equals(element);
        }
    }
}
