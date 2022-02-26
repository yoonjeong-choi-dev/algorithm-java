package book.ch10Hashing;

import book.ch09MapInterface.MyLinearMap;

import java.util.*;

public class MyBetterMap<K, V> implements Map<K, V> {
    protected List<MyLinearMap<K, V>> maps;

    public class Entry implements Map.Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V v) {
            value = v;
            return value;
        }
    }

    public MyBetterMap() {
        makeMaps(2);
    }

    public MyBetterMap(int k) {
        makeMaps(k);
    }

    protected void makeMaps(int k) {
        maps = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            maps.add(new MyLinearMap<>());
        }
    }

    // 주어지 키에 대한 해시를 계산하여 ArrayList maps의 적절한 요소 반환
    protected MyLinearMap<K, V> chooseMap(Object key) {
        int idx = 0;
        if (key != null) {
            idx = Math.abs(key.hashCode()) % maps.size();
        }
        return maps.get(idx);
    }

    // 인터페이스 구현에 사용될 유틸 메서드
    private boolean equals(Object target, Object obj) {
        if (target == null) {
            return obj == null;
        }
        return target.equals(obj);
    }

    @Override
    public int size() {
        int total = 0;
        for (MyLinearMap<K, V> map : maps) {
            total += map.size();
        }
        return total;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object o) {
        MyLinearMap<K, V> map = chooseMap(o);
        return map.containsKey(o);
    }

    @Override
    public boolean containsValue(Object o) {
        // target 키를 가지는 엔트리 반환
        for (MyLinearMap<K, V> map : maps) {
            if (map.containsValue(o)) return true;
        }
        return false;
    }

    @Override
    public V get(Object o) {
        MyLinearMap<K, V> map = chooseMap(o);
        return map.get(o);
    }

    @Override
    public V put(K k, V v) {
        MyLinearMap<K, V> map = chooseMap(k);
        return map.put(k, v);
    }

    @Override
    public V remove(Object o) {
        MyLinearMap<K, V> map = chooseMap(o);
        return map.remove(o);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        for (MyLinearMap<K, V> map : maps) {
            map.clear();
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (MyLinearMap<K, V> map : maps) {
            set.addAll(map.keySet());
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Set<V> set = new HashSet<>();
        for (MyLinearMap<K, V> map : maps) {
            set.addAll(map.values());
        }
        return set;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();

        for(int i=0;i<maps.size();i++){
            ret.append(i).append("-th bucket : ");
            if(maps.get(i).isEmpty()) {
                ret.append("EMPTY\n");
            } else {
                ret.append("\n").append(maps.get(i));
            }
        }
        return ret.toString();
    }
}
