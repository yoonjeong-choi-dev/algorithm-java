package book.ch11HashMap;

import book.ch09MapInterface.MyLinearMap;
import book.ch10Hashing.MyBetterMap;

import java.util.List;
import java.util.Map;

public class MyHashMap<K, V> extends MyBetterMap<K, V> implements Map<K, V> {
    // 하위 맵들의 엔트리 개수 평균 값
    protected static final double FACTOR = 1.0;

    @Override
    public V put(K key, V value) {
        // super class 메서드를 이용하여 우선 저장
        V oldValue = super.put(key, value);

        // 리해싱이 필요한 경우
        if (size() > maps.size() * FACTOR) {
            rehash();
        }

        return oldValue;
    }

    // 필요할 때, 버킷 수를 2배로 증가시키고 요소들을 다시 할당
    protected void rehash() {
        List<MyLinearMap<K, V>> origin = maps;
        makeMaps(maps.size() * 2);

        for(MyLinearMap<K, V> map : origin) {
            for(Map.Entry<K, V> entry : map.getEntries()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }
}
