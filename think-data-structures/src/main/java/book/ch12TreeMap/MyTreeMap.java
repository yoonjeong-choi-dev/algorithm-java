package book.ch12TreeMap;

import java.util.*;

public class MyTreeMap<K, V> implements Map<K, V> {

    protected class Node {
        public K key;
        public V value;
        public Node left = null;
        public Node right = null;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size = 0;
    private Node root = null;

    // 트리 탐색을 위한 유틸 함수
    private Node findNode(Object target) {
        if (target == null) {
            throw new IllegalArgumentException();
        }

        // 비교 연산
        Comparable<? super K> k = (Comparable<? super K>) target;

        // root 노드부터 탐색 시작
        Node node = root;
        while (node != null) {
            int comp = k.compareTo(node.key);

            if (comp < 0) {
                // node < comp 인 경우 왼쪽 노드
                node = node.left;
            } else if (comp > 0) {
                // node > comp 인 경우 오른쪽 노드
                node = node.right;
            } else {
                return node;
            }

        }
        return null;
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
    public void clear() {
        size = 0;
        root = null;    // GC 발생 => 선형 시간
    }

    @Override
    public V get(Object key) {
        Node node = findNode(key);
        if (node == null) {
            return null;
        } else {
            return node.value;
        }
    }

    @Override
    public boolean containsKey(Object key) {
        return findNode(key) != null;
    }

    @Override
    public boolean containsValue(Object target) {
        return containsValueRecursive(root, target);
    }

    private boolean containsValueRecursive(Node node, Object target) {
        // 마지막까지 도달했으면 false
        if (node == null) return false;

        // 현재 노드 탐색
        if (equals(node.value, target)) return true;

        // 왼쪽 노드 탐색
        if (containsValueRecursive(node.left, target)) return true;

        // 오른쪽 노드 탐색
        if (containsValueRecursive(node.right, target)) return true;

        return false;
    }

    private boolean equals(Object target, Object obj) {
        if (target == null) {
            return obj == null;
        }
        return target.equals(obj);
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (root == null) {
            root = new Node(key, value);
            size++;
            return null;
        }

        return putHelper(root, key, value);
    }


    private V putHelper(Node node, K key, V value) {
        // 비교 연산
        Comparable<? super K> k = (Comparable<? super K>) key;

        int comp = k.compareTo(node.key);

        if (comp == 0) {
            V origin = node.value;
            node.value = value;
            return origin;
        } else if (comp < 0) {
            // 왼쪽 서브 트리에서 put 시도
            if (node.left == null) {
                // 왼쪽 자식 노드가 없는 경우에는 왼쪽에 삽입
                node.left = new Node(key, value);
                size++;
                return null;
            } else {
                // 왼쪽 자식 노드가 있는 경우, 해당 자식 노드부터 다시 탐색
                return putHelper(node.left, key, value);
            }
        } else {
            // 오른쪽 서브 트리에서 put 시도
            if (node.right == null) {
                // 오른쪽 자식 노드가 없는 경우에는 왼쪽에 삽입
                node.right = new Node(key, value);
                size++;
                return null;
            } else {
                // 오른쪽 자식 노드가 있는 경우, 해당 자식 노드부터 다시 탐색
                return putHelper(node.right, key, value);
            }
        }
    }

    @Override
    public Set<K> keySet() {
        // key 를 오름차순으로 정렬하여 반환해야 함
        Set<K> set = new LinkedHashSet<>();
        findKeyInOrder(set, root);
        return set;
    }

    private void findKeyInOrder(Set<K> set, Node node) {
        if (node == null) return;

        // left -> current -> right 순서로 순회
        findKeyInOrder(set, node.left);
        set.add(node.key);
        findKeyInOrder(set, node.right);
    }

    @Override
    public V remove(Object key) {
        // 비교 연산
        Comparable<? super K> k = (Comparable<? super K>) key;

        Node parent = null, removed = root;

        // 제거할 노드 탐색
        while (removed != null) {
            int comp = k.compareTo(removed.key);
            if (comp == 0) break;

            parent = removed;
            if (comp < 0) {
                removed = removed.left;
            } else {
                removed = removed.right;
            }
        }

        // 제거할 노드가 없는 경우
        if (removed == null) return null;

        V origin = removed.value;

        if (parent != null && removed.left == null && removed.right == null) {
            // 삭제 노드가 leaf : 부모 노드에서 링크해제하면 됨
            if (removed == parent.left) parent.left = null;
            else parent.right = null;
        } else if (removed.left == null || removed.right == null) {
            // 삭제 노드가 하나의 자식 노드가 있는 경우 자식 노드를 부모 노드에 연결
            Node child = (removed.left != null) ? removed.left : removed.right;

            // 삭제 노드가 루트 노드인 경우
            if (parent == null) {
                root = child;
            } else {
                // 삭제 노드의 자식 노드를 삭제 노드 위치로 변경
                if (parent.left == removed) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
        } else {
            // 삭제 노드가 2개의 자식 노드를 가진 경우 : 오른쪽 서브트리에서 가장 작은 값 찾기
            Node changeParent = removed, change = removed.right;
            while (change.left != null) {
                changeParent = change;
                change = change.left;
            }

            // change 노드를 삭제 노드 위치로 변경 시키기 위해 change 자식 노드와 change 부모 노드 연결
            // change 노드는 왼쪽 자식 노드가 없음 : 위 while 에 의해서
            if (changeParent.left == change) {
                changeParent.left = change.right;
            } else {
                // 제거 노드의 오른쪽 자식 노드가 change 노드인 경우
                changeParent.right = change.right;
            }

            // removed 데이터 변경
            removed.key = change.key;
            removed.value = change.value;
        }

        size--;
        return origin;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }


    @Override
    public Collection<V> values() {
        Set<V> set = new HashSet<V>();
        findValuesRecursive(set, root);
        return set;
    }

    private void findValuesRecursive(Set<V> set, Node node) {
        if (node == null) return;
        ;

        // 중위 순회
        set.add(node.value);
        findValuesRecursive(set, node.left);
        findValuesRecursive(set, node.right);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    /*
     * Methods for Test
     * */
    public MyTreeMap<K, V>.Node makeNode(K key, V value) {
        return new Node(key, value);
    }

    public void setTree(Node node, int size) {
        this.root = node;
        this.size = size;
    }

    public int height() {
        return heightHelper(root);
    }

    private int heightHelper(Node node) {
        if (node == null) {
            return 0;
        }

        int left = heightHelper(node.left);
        int right = heightHelper(node.right);

        return Math.max(left, right) + 1;
    }

    public void printLevelOrder() {
        int h = height();

        System.out.println("Tree Traversal : Level Order");
        for (int i = 0; i < h; i++) {
            printCurrentLevel(root, i + 1);
        }
    }

    private void printCurrentLevel(Node root, int level) {
        if (root == null) {
            return;
        }

        if (level == 1) {
            System.out.println(root.key + " : " + root.value);
        } else if (level > 1) {
            printCurrentLevel(root.left, level - 1);
            printCurrentLevel(root.right, level - 1);
        }

    }
}
