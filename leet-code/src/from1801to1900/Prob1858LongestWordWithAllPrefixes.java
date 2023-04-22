package from1801to1900;

// https://leetcode.com/problems/longest-word-with-all-prefixes/
public class Prob1858LongestWordWithAllPrefixes {
    class TrieNode {
        private static final int NUM_CHILD = 128;
        TrieNode[] children;
        boolean terminal;

        public TrieNode() {
            children = new TrieNode[NUM_CHILD];
            terminal = false;
        }
    }


    public String longestWord(String[] words) {
        // Add all words to the trie
        TrieNode root = new TrieNode();
        TrieNode cur;
        for (String word : words) {
            cur = root;
            for (char c : word.toCharArray()) {
                if (cur.children[c] == null) cur.children[c] = new TrieNode();
                cur = cur.children[c];
            }

            cur.terminal = true;
        }

        // find the longest string in words such that every prefix of it is also in words
        // => 모든 경로가 단어여야 함
        String ans = "";
        boolean allPrefix;
        for (String word : words) {
            cur = root;
            allPrefix = true;
            for (char c : word.toCharArray()) {
                cur = cur.children[c];

                // 경로가 단어가 아닌 경우는 무시
                if (!cur.terminal) {
                    allPrefix = false;
                    break;
                }
            }

            if (allPrefix) {
                if(ans.length() < word.length()) ans = word;
                else if(ans.length() == word.length() && ans.compareTo(word) > 0) ans = word;
            }
        }

        return ans;
    }
}
