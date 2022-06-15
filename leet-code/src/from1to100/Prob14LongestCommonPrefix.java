package from1to100;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/longest-common-prefix/
public class Prob14LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        //return onePointerSolution(strs);
        return trieSolution(strs);
    }

    private String onePointerSolution(String[] strs) {
        int idx = 0;
        boolean allMatched;

        Character curChar;
        while (true) {
            allMatched = true;
            curChar = null;
            for (String s : strs) {
                if (idx >= s.length()) {
                    allMatched = false;
                    break;
                }

                if (curChar == null) {
                    curChar = s.charAt(idx);
                } else if (curChar != s.charAt(idx)) {
                    allMatched = false;
                    break;
                }
            }

            if (!allMatched) break;
            idx++;
        }

        return strs[0].substring(0, idx);
    }

    class TrieNode {
        Map<Character, TrieNode> children;
        boolean terminated;

        public TrieNode() {
            children = new HashMap<>();
            terminated = false;
        }
    }

    private String trieSolution(String[] strs) {
        TrieNode root = new TrieNode();

        // Add strings to trie
        TrieNode cur;
        for (String s : strs) {
            cur = root;
            for (char c : s.toCharArray()) {
                if (!cur.children.containsKey(c)) cur.children.put(c, new TrieNode());

                cur = cur.children.get(c);
            }

            cur.terminated = true;
        }

        // find longest prefix
        int len = 0;
        cur = root;
        while (true) {
            // 특정 문자열의 끝에 온 경우
            if (cur.terminated) break;

            // 2갈래로 나뉘어 지는 경우
            if (cur.children.size() != 1) break;

            len++;
            cur = (TrieNode) cur.children.values().toArray()[0];
        }

        return strs[0].substring(0, len);
    }
}
