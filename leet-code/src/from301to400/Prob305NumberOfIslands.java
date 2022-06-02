package from301to400;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/number-of-islands-ii/
public class Prob305NumberOfIslands {

    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // 각 요소들의 컴포넌트 표현을 위한 유니온-파인드 자료구조
    class UnionFind {
        private int numComponents;

        // 2차원 배열을 1차원으로 생각
        // 자신의 부모 노드 및 자신을 루트로 하는 트리의 높이 저장
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            numComponents = 0;
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = -1;
                rank[i] = 0;
            }
        }

        public boolean isRegistered(int x) {
            // 해당 노드가 현재 자료구조에 등록되어 있는지 여부 확인
            return parent[x] >= 0;
        }

        public void register(int x) {
            if(parent[x] != -1) return;

            parent[x] = x;
            numComponents++;
        }

        public int findRoot(int x) {
            if (parent[x] == x) return x;

            // 재귀적으로 자신이 속한 집합의 루트 번호 계산
            return parent[x] = findRoot(parent[x]);
        }

        public void union(int x, int y) {
            int rootX = findRoot(x);
            int rootY = findRoot(y);

            // 이미 같은 집합인 경우 연결할 필요가 없음
            if (rootX == rootY) return;

            // 큰 집합 아래로 작은 크기의 집합 연결
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                // 높이가 같은 경우 합치는 과정에서 트리의 높이가 1증가
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            // 두 집합을 합쳤으므로 컴포넌트 개수 -1
            numComponents--;
        }

        public int getNumComponents() {
            return numComponents;
        }

    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>(positions.length);
        UnionFind uf = new UnionFind(m * n);

        int x, y, curIndex, nextX, nextY;
        List<Integer> neighborLands = new ArrayList<>(4);
        for (int[] pos : positions) {
            x = pos[0];
            y = pos[1];

            // 현재 위치의 주변 중에 땅인 위치 검색
            neighborLands.clear();
            for (int[] d : directions) {
                nextX = x + d[0];
                nextY = y + d[1];

                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) continue;

                curIndex = nextX * n + nextY;

                if (uf.isRegistered(curIndex)) neighborLands.add(curIndex);
            }

            // 현재 추가하는 땅 등록
            curIndex = x * n + y;
            uf.register(curIndex);

            // 주변 땅과 합침
            for (int neighbor : neighborLands) uf.union(curIndex, neighbor);

            ans.add(uf.getNumComponents());
        }

        return ans;
    }
}
