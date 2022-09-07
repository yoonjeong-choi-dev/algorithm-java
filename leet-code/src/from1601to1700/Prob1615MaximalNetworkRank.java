package from1601to1700;

// https://leetcode.com/problems/maximal-network-rank/
public class Prob1615MaximalNetworkRank {
    public int maximalNetworkRank(int n, int[][] roads) {
        int[] degree = new int[n];
        boolean[][] isConnected = new boolean[n][n];

        for(int[] edge : roads) {
            for(int v : edge) degree[v]++;
            isConnected[edge[0]][edge[1]] = true;
            isConnected[edge[1]][edge[0]] = true;
        }

        int ans = 0;
        int curRank;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                // Rank :the total number of directly connected roads
                curRank = degree[i] + degree[j];

                // 겹치는 경우 : 두 도시가 연결되어 하나의 도로를 공유하는 것
                if(isConnected[i][j]) curRank--;

                ans = Math.max(ans, curRank);
            }
        }

        return ans;
    }
}
