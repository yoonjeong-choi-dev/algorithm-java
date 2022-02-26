package book.ch09MapInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyLinearMap<K, V> implements Map<K, V> {

    private final List<Entry> entries = new ArrayList<>();

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

    // 인터페이스 구현에 사용될 유틸 메서드
    private boolean equals(Object target, Object obj) {
        if (target == null) {
            return obj == null;
        }
        return target.equals(obj);
    }

    private Entry findEntry(Object target) {
        // target 키를 가지는 엔트리 반환
        for (Entry entry : entries) {
            if (equals(target, entry.getKey())) return entry;
        }
        return null;
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    @Override
    public boolean containsKey(Object o) {
        Entry entry = findEntry(o);
        return entry != null;
    }

    @Override
    public boolean containsValue(Object o) {
        // target 키를 가지는 엔트리 반환
        for (Entry entry : entries) {
            if (o.equals(entry.getValue())) return true;
        }
        return false;
    }

    @Override
    public V get(Object o) {
        Entry ret = findEntry(o);
        return ret == null ? null : ret.getValue();
    }

    @Override
    public V put(K k, V v) {
        Entry entry = findEntry(k);
        if(entry == null) {
            // 없으면 새로 생성
            entry = new Entry(k, v);
            entries.add(entry);
            return null;
        } else {
            // 있으면 값만 변경하고 이전 값 반환
            V old = entry.getValue();
            entry.setValue(v);
            return old;
        }
    }

    @Override
    public V remove(Object o) {
        Entry entry = findEntry(o);
        if(entry == null) return null;

        V ret = entry.getValue();
        entries.remove(entry);

        return ret;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry: map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        entries.clear();
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (Entry entry : entries) {
            set.add(entry.getKey());
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Set<V> set = new HashSet<>();
        for (Entry entry : entries) {
            set.add(entry.getValue());
        }
        return set;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    public Collection<? extends java.util.Map.Entry<K, V>> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (K key: keySet()) {
            ret.append(key).append(" : ").append(get(key)).append("\n");
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new MyLinearMap<>();
        map.put("Word1", 1);
        map.put("Word2", 2);
        Integer value = map.get("Word1");
        System.out.println(value);

        for (String key: map.keySet()) {
            System.out.println(key + ", " + map.get(key));
        }
    }
}
