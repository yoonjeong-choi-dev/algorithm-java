package book.websearchengine;

import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// 페이지에 각 단어가 등장하는 횟수 매핑
public class TermCounter {
    private final Map<String, Integer> map;
    private final String label;

    public TermCounter(String label) {
        this.label = label;
        this.map = new HashMap<>();
    }

    public String getLabel() {
        return label;
    }

    public void put(String term, int count) {
        map.put(term, count);
    }

    public Integer get(String term) {
        Integer count = map.get(term);
        return count == null ? 0 : count;
    }

    public int size() {
        int ret = 0;
        for (Integer count : map.values()) ret += count;
        return ret;
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public void incrementTermCount(String term) {
        put(term, get(term) + 1);
    }

    public void process(Elements paragraphs) {
        for (Node node : paragraphs) {
            process(node);
        }
    }

    public void process(Node root) {
        DOMNodeIterable itr = new DOMNodeIterable(root);

        // Text 노드들을 찾아서 카운팅
        for (Node node : itr) {
            if (node instanceof TextNode) {
                process(((TextNode) node).text());
            }
        }
    }

    public void process(String text) {
        // 구두점->공백, 대문자->소문자 및 공백 기준으로 split
        String[] array = text.replaceAll("\\p{P}", " ").toLowerCase().split("\\s+");

        for (String word : array) {
            incrementTermCount(word);
        }
    }


    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();

        for(String key : keySet()) {
            ret.append(key).append(" : ").append(get(key)).append("\n");
        }
        ret.append("Total Counts = ").append(size()).append("\n");

        return ret.toString();
    }
}
