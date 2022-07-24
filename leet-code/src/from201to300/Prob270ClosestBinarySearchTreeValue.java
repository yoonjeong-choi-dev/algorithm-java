package from201to300;

// https://leetcode.com/problems/closest-binary-search-tree-value/
public class Prob270ClosestBinarySearchTreeValue {
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

    public int closestValue(TreeNode root, double target) {
        int ans = root.val;
        int curVal;

        while (root != null) {
            // 현재 노드에 대한 차이의 최소값 업데이트
            curVal = root.val;
            ans = Math.abs(curVal - target) < Math.abs(ans - target) ? curVal : ans;

            // BST 성질을 이용한 탐색
            root = target < curVal ? root.left : root.right;
        }

        return ans;
    }
}
