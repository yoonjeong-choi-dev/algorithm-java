package from1501to1600;

import java.util.Arrays;

// https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/
public class Prob1579RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable {

    // Ref : Problem 305
    class UnionFind {
        private int numComponents;

        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            numComponents = size;
            parent = new int[size];
            rank = new int[size];

            for(int i=0;i<size;i++){
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public UnionFind(int[] parent, int[] rank, int numComponents) {
            this.numComponents = numComponents;
            this.parent = parent.clone();
            this.rank = rank.clone();
        }

        public UnionFind getClone() {
            return new UnionFind(parent, rank, numComponents);
        }

        public boolean isFullyConnected() {
            // 0 번 노드는 임시 노드
            return numComponents == 2;
        }

        public int findRoot(int x) {
            if (parent[x] == x) return x;

            return parent[x] = findRoot(parent[x]);
        }

        // 이미 합쳐진 경우 false 반환
        public boolean union(int x, int y) {
            int rootX = findRoot(x);
            int rootY = findRoot(y);

            if (rootX == rootY) return false;

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            numComponents--;
            return true;
        }
    }

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int removed = 0;

        // Type 3 엣지로 만든 그래프
        UnionFind type3Graph = new UnionFind(n + 1);
        boolean isUnionSuccess;
        for (int[] e : edges) {
            // 타입 3인 엣지로 연결
            if (e[0] != 3) continue;

            isUnionSuccess = type3Graph.union(e[1], e[2]);

            // 이미 연결되어 있는 경우 타입 3 엣지 삭제 가능
            if (!isUnionSuccess) removed++;
        }

        // Alice :  Type 1 + Type 3 Graph
        UnionFind type13Graph = type3Graph.getClone();
        for (int[] e : edges) {
            // 타입 1인 엣지로 연결
            if (e[0] != 1) continue;

            isUnionSuccess = type13Graph.union(e[1], e[2]);

            // 이미 연결되어 있는 경우, 타입 1 엣지 삭제 가능 (for Alice)
            if (!isUnionSuccess) removed++;
        }

        // Bob : Type 2 + Type 3 => 메모리 효율을 위해 type3Graph 사용
        for (int[] e : edges) {
            // 타입 2인 엣지로 연결
            if (e[0] != 2) continue;

            isUnionSuccess = type3Graph.union(e[1], e[2]);

            // 이미 연결되어 있는 경우 타입 2 엣지 삭제 가능
            if (!isUnionSuccess) removed++;
        }

        // 두 그래프가 각 각 연결되어 있는지 확인
        if (!type3Graph.isFullyConnected() || !type13Graph.isFullyConnected()) return -1;
        return removed;
    }
}
