package from201to300;

// https://leetcode.com/problems/count-univalue-subtrees/
public class Prob250CountUniValueSubtrees {
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

    public int countUnivalSubtrees(TreeNode root) {
        ans = 0;
        isUniValue(root);
        return ans;
    }

    // node 를 루트로 하는 트리가 하나의 값만 있는지
    private boolean isUniValue(TreeNode node) {
        if (node == null) return true;
        if (node.left == null && node.right == null) {
            ans++;
            return true;
        }

        boolean leftTree = isUniValue(node.left);
        boolean rightTree = isUniValue(node.right);

        boolean leftRet = (node.left == null || node.left.val == node.val) && leftTree;
        boolean rightRet = (node.right == null || node.right.val == node.val) && rightTree;

        boolean ret = leftRet && rightRet;
        if (ret) ans++;
        return ret;
    }
}
