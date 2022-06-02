package from1101to1200;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/
public class Prob1101TheEarliestMomentWhenEveryoneBecomeFriends {
    class UnionFind {
        private int numComponent;
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            numComponent = size;
            parent = new int[size];
            rank = new int[size];

            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public boolean isFullyConnected() {
            return numComponent == 1;
        }

        public int findRoot(int x) {
            if (parent[x] == x) return x;
            return parent[x] = findRoot(parent[x]);
        }

        public void union(int x, int y) {
            int rootX = findRoot(x);
            int rootY = findRoot(y);

            if (rootX == rootY) return;

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            numComponent--;
        }
    }

    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0]) {
                    return -1;
                } else if (o1[0] > o2[0]) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        UnionFind uf = new UnionFind(n);

        for (int[] log : logs) {
            uf.union(log[1], log[2]);

            if(uf.isFullyConnected()) return log[0];
        }


        return -1;
    }
}
