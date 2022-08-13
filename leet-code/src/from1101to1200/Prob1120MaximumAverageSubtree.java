package from1101to1200;

// https://leetcode.com/problems/maximum-average-subtree/
public class Prob1120MaximumAverageSubtree {
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

    private double ans;

    public double maximumAverageSubtree(TreeNode root) {
        ans = 0.0;
        getSum(root);
        return ans;
    }

    // return (size, sum)
    private int[] getSum(TreeNode node) {
        if (node == null) return new int[]{0, 0};

        int[] left = getSum(node.left);
        int[] right = getSum(node.right);

        int size = left[0] + right[0] + 1;
        int sum = left[1] + right[1] + node.val;

        double avg = (double) sum / size;
        ans = Math.max(avg, ans);

        return new int[]{size, sum};
    }
}
