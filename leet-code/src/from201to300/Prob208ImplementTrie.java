package from201to300;

// https://leetcode.com/problems/implement-trie-prefix-tree/
public class Prob208ImplementTrie {
    class TrieNode {
        private static final int NUM_CHILD = 26;
        private TrieNode[] children;
        private boolean terminal;

        public TrieNode() {
            children = new TrieNode[NUM_CHILD];
            terminal = false;
        }

        public boolean containChild(char ch) {
            return children[getIndex(ch)] != null;
        }

        public TrieNode getChild(char ch) {
            return children[getIndex(ch)];
        }

        public void addChild(char ch, TrieNode node) {
            children[getIndex(ch)] = node;
        }

        private int getIndex(char ch) {
            return ch - 'a';
        }

        public void setTerminal() {
            terminal = true;
        }

        public boolean isTerminal() {
            return terminal;
        }
    }

    class Trie {

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
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
            TrieNode ret = getNodeStartsWith(word);

            // word에 해당하는 경로가 있고 해당 노드는 말단 노드여야 함
            return ret != null && ret.isTerminal();
        }

        public boolean startsWith(String prefix) {
            TrieNode ret = getNodeStartsWith(prefix);
            return ret != null;
        }

        private TrieNode getNodeStartsWith(String prefix) {
            TrieNode cur = root;
            char curChar;
            for(int i=0;i<prefix.length();i++){
                curChar = prefix.charAt(i);
                if(!cur.containChild(curChar)) {
                    return null;
                }
                cur = cur.getChild(curChar);
            }

            return cur;
        }
    }
}
