package from501to600;

// https://leetcode.com/problems/diameter-of-binary-tree/
public class Prob543DiameterOfBinaryTree {
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

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 0;
        postOrder(root);
        return ans;
    }

    private int postOrder(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = postOrder(node.left);
        int rightHeight = postOrder(node.right);

        // 현재 노드를 루트로 하는 diameter 계산
        ans = Math.max(ans, leftHeight + rightHeight);

        // 현재 노드를 루트로 하는 최대 높이 반환
        return 1 + Math.max(leftHeight, rightHeight);
    }

}
