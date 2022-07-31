package from1201to1300;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Union;

import java.util.*;

// https://leetcode.com/problems/smallest-string-with-swaps/
public class Prob1202SmallestStringWithSwaps {
    class UnionFind {

        int[] parent;
        int[] rank;

        UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];

            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            if (parent[x] == x) return x;
            return parent[x] = find(parent[x]);
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return;

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

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int len = s.length();

        // Build UnionFind
        UnionFind uf = new UnionFind(len);
        for (List<Integer> p : pairs) {
            uf.union(p.get(0), p.get(1));
        }

        // Build Component w.r.t the root
        Map<Integer, List<Integer>> componentMap = new HashMap<>();
        int root;
        for (int i = 0; i < len; i++) {
            root = uf.find(i);

            if (!componentMap.containsKey(root)) componentMap.put(root, new ArrayList<>());
            componentMap.get(root).add(i);
        }

        // Build the lexicographically smallest string
        char[] ans = new char[len];
        List<Character> curChars;
        int curIdx;
        for (List<Integer> component : componentMap.values()) {
            // sort characters of the current component
            curChars = new ArrayList<>(component.size());
            for (int i : component) curChars.add(s.charAt(i));
            Collections.sort(curChars);

            curIdx = 0;
            for (int i : component) ans[i] = curChars.get(curIdx++);
        }


        return new String(ans);
    }
}
