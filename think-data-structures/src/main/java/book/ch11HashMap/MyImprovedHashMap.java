package book.ch11HashMap;

import book.ch09MapInterface.MyLinearMap;
import book.ch10Hashing.MyBetterMap;

import java.util.List;
import java.util.Map;

public class MyImprovedHashMap<K, V> extends MyHashMap<K, V> implements Map<K, V>  {
    // MyBetterMap size 메서드가 선형이므로, 따로 엔트리 개수 저장
    private int size = 0;

    @Override
    public V put(K key, V value) {
        MyLinearMap<K, V> map = chooseMap(key);

        int curMapSize = map.size();
        V oldValue = map.put(key, value);

        if(curMapSize != map.size()) size++;

        // 리해싱이 필요한 경우
        if (size() > maps.size() * FACTOR) {
            rehash();
        }

        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        super.clear();
        size = 0;
    }

    @Override
    public V remove(Object o) {
        MyLinearMap<K, V> map = chooseMap(o);

        int curMapSize = map.size();
        V oldValue = map.remove(o);

        if(curMapSize != map.size()) size--;

        return oldValue;
    }
}
