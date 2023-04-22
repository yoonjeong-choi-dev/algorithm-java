package from201to300;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/design-add-and-search-words-data-structure/
public class Prob211DesignAddAndSearchWordsDataStructure {
    // Ref : Problem 208
    class TrieNode {
        private Map<Character, TrieNode> children;
        private boolean terminal;

        public TrieNode() {
            children = new HashMap<>();
            terminal = false;
        }

        public boolean containChild(char ch) {
            return children.containsKey(ch);
        }

        public TrieNode getChild(char ch) {
            return children.get(ch);
        }

        public void addChild(char ch, TrieNode node) {
            children.put(ch, node);
        }

        public void setTerminal() {
            terminal = true;
        }

        public boolean isTerminal() {
            return terminal;
        }

        public Set<Character> getChildKeys() {
            return children.keySet();
        }
    }

    class WordDictionary {

        private TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode cur = root;
            char curChar;
            for (int i = 0; i < word.length(); i++) {
                curChar = word.charAt(i);
                if (!cur.containChild(curChar)) {
                    cur.addChild(curChar, new TrieNode());
                }

                cur = cur.getChild(curChar);
            }
            cur.setTerminal();
        }

        public boolean search(String word) {
            return recursiveSearch(word, 0, root);
        }

        private boolean recursiveSearch(String word, int curIdx, TrieNode curNode) {
            if (curIdx == word.length()) return curNode.isTerminal();

            char curChar = word.charAt(curIdx);
            if (curNode.containChild(curChar)) {
                return recursiveSearch(word, curIdx + 1, curNode.getChild(curChar));
            } else {
                // 모든 트리 노드에 대해서 탐색 필요
                if (curChar == '.') {
                    for (char childKey : curNode.getChildKeys()) {
                        TrieNode next = curNode.getChild(childKey);
                        if (recursiveSearch(word, curIdx + 1, next)) {
                            return true;
                        }
                    }
                }
                // . 이 아니거나 .인 경우 모든 탐색이 종료된 경우
                return false;
            }
        }
    }
}
