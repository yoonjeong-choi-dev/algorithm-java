package from401to500;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/path-sum-iii/
public class Prob437PathSum3 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int ans, target;
    private Map<Integer, Integer> distanceFromRoot;

    public int pathSum(TreeNode root, int targetSum) {
        ans = 0;
        target = targetSum;
        distanceFromRoot = new HashMap<>();

        preOrder(root, 0);

        return ans;
    }

    private void preOrder(TreeNode node, int curSum) {
        if (node == null) return;

        curSum += node.val;

        // root -> cur 경로가 정답인 경우
        if (curSum == target) ans++;

        // root -> something -> node 경로에서 root->something 경로 합이 curSum - target 인 something 노드가 있는 경우
        // => something 다음 노드부터 현재 노드까지의 경로가 정답
        ans += distanceFromRoot.getOrDefault(curSum - target, 0);

        // 현재 경로 합 저장
        distanceFromRoot.put(curSum, distanceFromRoot.getOrDefault(curSum, 0) + 1);

        // 현재 경로에서 진행 가능한 왼쪽,오른쪽 서브트리 탐색
        preOrder(node.left, curSum);
        preOrder(node.right, curSum);

        // 현재 경로가 끝나기 때문에 현재 경로 정보 삭제
        // : preOrder 이기 때문에 현재 노드 하위 경로는 모두 탐색 완료했기 때문에
        // i.e 이후 탐색하는 경로에는 현재 노드가 없음
        distanceFromRoot.put(curSum, distanceFromRoot.get(curSum) - 1);
    }
}
