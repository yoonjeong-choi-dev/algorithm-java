package from101to200;

// https://leetcode.com/problems/binary-tree-maximum-path-sum/
public class Prob124BinaryTreeMaximumPathSum {
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

    private int ans;

    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        postOrderSum(root);
        return ans;
    }

    private int postOrderSum(TreeNode node) {
        if (node == null) return 0;

        int left = Math.max(postOrderSum(node.left), 0);
        int right = Math.max(postOrderSum(node.right), 0);

        // node를 루트로 할 때의 최대 경로
        int ret = left + right + node.val;
        if (ans < ret) ans = ret;

        // node 상위로 올라가는 경우
        ret = Math.max(left, right) + node.val;
        return ret;
    }

}
