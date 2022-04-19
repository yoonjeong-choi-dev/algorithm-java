package from501to600;

// https://leetcode.com/problems/number-of-provinces/
public class Prob547NumberOfProvinces {
    int numCity;
    boolean[] isVisited;
    int[][] graph;

    public int findCircleNum(int[][] isConnected) {
        numCity = isConnected.length;
        isVisited = new boolean[numCity];
        graph = isConnected;
        for (int i = 0; i < numCity; i++) graph[i][i] = 0;

        int ans = 0;
        for (int i = 0; i < numCity; i++) {
            if (!isVisited[i]) {
                ans++;
                dfs(i);
            }
        }

        return ans;
    }

    private void dfs(int curIdx) {
        isVisited[curIdx] = true;

        for (int i = 0; i < numCity; i++) {
            if (graph[curIdx][i] == 1 && !isVisited[i]) {
                dfs(i);
            }
        }
    }
}
