package book.ch08Indexer;


import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 검색어와 검색어가 등장하는 페이지 컬렉션 매핑
public class Index {
    // index[term] : term 검색어가 등장하는 페이지들에 대한 카운터
    private final Map<String, Set<TermCounter>> index = new HashMap<>();

    public Set<TermCounter> get(String term) {
        return index.get(term);
    }

    public Set<String> keySet() {
        return index.keySet();
    }

    public void add(String term, TermCounter counter) {
        Set<TermCounter> set = get(term);

        // 검색어를 처음 찾는 경우 Set 생성
        if(set == null) {
            set = new HashSet<>();
            index.put(term, set);
        }

        // 카운터 셋에 추가
        set.add(counter);
    }

    public void indexPage(String url, Elements paragraphs) {
        // url 에 해당하는 페이지에 대해서 모든 단어의 등장 횟수 업데이트
        TermCounter counter = new TermCounter(url);
        counter.process(paragraphs);

        // 등장한 단어들에 대한 index 업데이트
        for(String term : counter.keySet()) {
            add(term, counter);
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for(String term : keySet()) {
            ret.append("Term : ").append(term).append("\n");

            Set<TermCounter> counters = get(term);
            for(TermCounter counter : counters) {
                ret.append("\t").append(counter.getLabel()).append(" : ").append(counter.get(term)).append("\n");
            }
        }

        return ret.toString();
    }
}
