package from1to100;

// https://leetcode.com/problems/unique-binary-search-trees/
public class Prob96UniqueBinarySearchTrees {
    public int numTrees(int n) {
        if (n <= 2) return n;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        // [0,..,(i-1)] 로 구성된 BST 개수
        for (int i = 3; i <= n; i++) {
            // root 가 루트 노드의 값일 때
            for (int root = 0; root < i; root++) {
                // 왼쪽 서브트리는 [0,1,..(root-1)] : root 개의 노드 => 왼쪽 서브트리의 경우의 수 : dp[root]
                // 오른쪽 서브트리는 [root+1, ..., i-1] : i-1-root 개의 노드 => 오른쪽 서브트리의 경우의 수 : dp[i-1-root]
                dp[i] += dp[root] * dp[i - 1 - root];
            }
        }

        return dp[n];
    }
}
