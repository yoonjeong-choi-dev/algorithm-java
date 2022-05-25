package from1301to1400;

// https://leetcode.com/problems/delete-leaves-with-a-given-value/
public class Prob1325DeleteLeavesWithGivenValue {
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

    private int target;

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root.left == null && root.right == null && root.val == target) return null;

        this.target = target;

        boolean isRootDeleted = removeByPostOrder(root);
        return isRootDeleted ? null : root;
    }

    private boolean removeByPostOrder(TreeNode node) {
        if (node.left == null && node.right == null) {
            return node.val == target;
        }

        boolean isLeftDeleted = true, isRightDeleted = true;
        if (node.left != null) isLeftDeleted = removeByPostOrder(node.left);
        if (node.right != null) isRightDeleted = removeByPostOrder(node.right);

        if (isLeftDeleted) node.left = null;
        if (isRightDeleted) node.right = null;

        if (isLeftDeleted && isRightDeleted) {
            return node.val == target;
        } else {
            return false;
        }
    }
}
