package from1001to1100;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/lexicographically-smallest-equivalent-string/
public class Prob1061LexicographicallySmallestEquivalentString {

    class UnionFindChar {

        private static final int NUM_CHARS = 26;
        private int[] parent;
        private int[] rank;

        public UnionFindChar() {
            // s1, s2, and baseStr consist of lowercase English letters.
            parent = new int[NUM_CHARS];
            rank = new int[NUM_CHARS];

            for (int i = 0; i < NUM_CHARS; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int findRoot(int x) {
            if (parent[x] == x) return x;
            return parent[x] = findRoot(parent[x]);
        }

        public int[] getAllRoots() {
            for (int i = 0; i < NUM_CHARS; i++) findRoot(i);
            return parent;
        }

        public void union(int x, int y) {
            int rootX = findRoot(x);
            int rootY = findRoot(y);

            if (rootX == rootY) return;

            // 큰 집합 아래로 작은 크기의 집합 연결
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        UnionFindChar ufc = new UnionFindChar();
        for (int i = 0; i < s1.length(); i++) {
            ufc.union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }

        int[] parent = ufc.getAllRoots();

        // 각 대표 번호에 대한 사전 순으로 가장 빠른 알파벳 저장
        Map<Integer, Integer> firstChars = new HashMap<>();
        for (int i = 0; i < parent.length; i++) {
            if (!firstChars.containsKey(parent[i])) firstChars.put(parent[i], i);
            else {
                if (firstChars.get(parent[i]) > i) firstChars.put(parent[i], i);
            }
        }

        StringBuilder sb = new StringBuilder(baseStr.length());
        int curRoot;
        for (char c : baseStr.toCharArray()) {
            curRoot = parent[c - 'a'];
            sb.append((char) (firstChars.get(curRoot) + 'a'));
        }
        return sb.toString();
    }
}
