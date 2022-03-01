package book.ch16BooleanSearch;

import book.websearchengine.JedisIndex;

import java.util.*;
import java.util.Map.Entry;

public class WikiSearch {
    // map[url] : 검색어에 대한 페이지 관련성 점수
    // 현재는 TF 이용 : 각 페이지에 검색어들의 등장 횟수의 합
    private Map<String, Integer> map;

    public WikiSearch(Map<String, Integer> map) {
        this.map = map;
    }

    // JedisIndex 객체를 이용하여 WikiSearch 객체를 반환하는 정적 메서드
    public static WikiSearch search(String term, JedisIndex index) {
        Map<String, Integer> map = index.getCounts(term);
        return new WikiSearch(map);
    }

    public void printRelevance() {
        List<Entry<String, Integer>> entries = sort();
        for (Entry<String, Integer> entry : entries) {
            System.out.printf("%s : %d\n", entry.getKey(), entry.getValue());
        }
    }


    public Integer getRelevance(String url) {
        Integer relevance = map.get(url);
        return relevance == null ? 0 : relevance;
    }

    public WikiSearch and(WikiSearch that) {
        Map<String, Integer> ret = new HashMap<>();
        Map<String, Integer> thatMap = that.getMap();
        for (String url : map.keySet()) {
            // that 에 있는 url만 포함 시킴
            if (thatMap.containsKey(url)) {
                ret.put(url, totalRelevance(map.get(url), thatMap.get(url)));
            }
        }

        return new WikiSearch(ret);
    }

    public WikiSearch or(WikiSearch that) {
        Map<String, Integer> ret = new HashMap<>(map);
        Map<String, Integer> thatMap = that.getMap();

        for (String url : thatMap.keySet()) {
            if (map.containsKey(url)) {
                // 두 검색 결과의 공통 url은 TF 계산
                ret.put(url, totalRelevance(map.get(url), thatMap.get(url)));
            } else {
                // that에만 있는 경우는 단순히 추가
                ret.put(url, thatMap.get(url));
            }
        }

        return new WikiSearch(ret);
    }

    public WikiSearch minus(WikiSearch that) {
        Map<String, Integer> ret = new HashMap<>(map);
        Map<String, Integer> thatMap = that.getMap();

        for (String url : thatMap.keySet()) {
            if (map.containsKey(url)) {
                // 삭제
                ret.remove(url);
            }
        }

        return new WikiSearch(ret);
    }

    public List<Entry<String, Integer>> sort() {
        // 정렬 기준 정의
        Comparator<Entry<String, Integer>> comparator = new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> t0, Entry<String, Integer> t1) {
                // 관련성 점수가 높은 순서대로 정렬
                return t1.getValue().compareTo(t0.getValue());
            }
        };

        List<Entry<String, Integer>> entries = new LinkedList<Entry<String, Integer>>(map.entrySet());
        entries.sort(comparator);

        return entries;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    protected static int totalRelevance(Integer rel1, Integer rel2) {
        return rel1 + rel2;
    }
}
