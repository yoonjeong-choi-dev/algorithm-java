package from601to700;

import java.util.*;

// https://leetcode.com/problems/design-search-autocomplete-system/
public class Prob642DesignSearchAutocompleteSystem {
    class TrieNode implements Comparable<TrieNode> {
        Map<Character, TrieNode> children;
        String sentence;
        int numTyped;
        List<TrieNode> hotWords;

        TrieNode() {
            children = new HashMap<>();
            sentence = null;
            numTyped = 0;

            // The returned top 3 hot sentences should be sorted by hot degree
            hotWords = new ArrayList<>();
        }

        public void updateHotWords(TrieNode node) {
            // 현재 리스트에 없는 경우에 추가
            if (!hotWords.contains(node)) {
                hotWords.add(node);
            }

            // 정렬
            Collections.sort(hotWords);

            // 3개 초과한 경우 마지막 삭제
            if (hotWords.size() == 4) {
                hotWords.remove(3);
            }
        }

        @Override
        public int compareTo(TrieNode o) {
            // The returned top 3 hot sentences should be sorted by hot degree
            if (this.numTyped != o.numTyped) {
                return o.numTyped - this.numTyped;
            } else {
                return this.sentence.compareTo(o.sentence);
            }
        }
    }

    class AutocompleteSystem {

        TrieNode root, cur;
        StringBuilder query;

        public AutocompleteSystem(String[] sentences, int[] times) {
            root = new TrieNode();
            cur = root;
            query = new StringBuilder();

            for (int i = 0; i < sentences.length; i++) addSentence(sentences[i], times[i]);
        }

        private void addSentence(String sentence, int time) {
            TrieNode temp = root;

            // sentence 를 표현한 TrieNode Path list
            List<TrieNode> path = new ArrayList<>(sentence.length());

            for (char c : sentence.toCharArray()) {
                if (!temp.children.containsKey(c)) temp.children.put(c, new TrieNode());
                temp = temp.children.get(c);
                path.add(temp);
            }

            // 마지막 문자에 문장 정보 및 타이핑 횟수 저장
            temp.sentence = sentence;
            temp.numTyped += time;

            // 현재 문장의 path 에 마지막 노드 업데이트 : 자동완성 용
            for (TrieNode p : path) p.updateHotWords(temp);
        }


        public List<String> input(char c) {
            List<String> ret = new ArrayList<>();

            // 현재 쿼리가 끝난 경우
            // => Returns an empty array [] if c == '#' and stores the inputted sentence in the system.
            if(c == '#') {
                cur = root;
                addSentence(query.toString(), 1);
                query = new StringBuilder();
                return ret;
            }

            // 현재 쿼리 진행 중
            query.append(c);
            if(cur != null) cur = cur.children.getOrDefault(c, null);

            // 현재 쿼리와 일치하는 경로가 없는 경우
            if(cur == null) return ret;
            for(TrieNode hot : cur.hotWords) ret.add(hot.sentence);
            return ret;
        }
    }
}
