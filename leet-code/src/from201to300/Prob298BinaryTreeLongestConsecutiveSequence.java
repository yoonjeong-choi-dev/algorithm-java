package from201to300;

// https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
public class Prob298BinaryTreeLongestConsecutiveSequence {
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

    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;

        int val = root.val;
        int ans = 1;
        if (root.left != null) {
            if (root.left.val == val + 1) ans = Math.max(ans, fromRoot(root.left) + 1);
            else ans = Math.max(ans, longestConsecutive(root.left));
        }
        if (root.right != null) {
            if (root.right.val == val + 1) ans = Math.max(ans, fromRoot(root.right) + 1);
            else ans = Math.max(ans, longestConsecutive(root.right));
        }

        return ans;
    }

    private int fromRoot(TreeNode root) {
        int val = root.val;
        int ans = 0;

        if (root.left != null && root.left.val == val + 1) ans = fromRoot(root.left);
        if (root.right != null && root.right.val == val + 1) ans = Math.max(ans, fromRoot(root.right));

        return ans + 1;
    }
}
