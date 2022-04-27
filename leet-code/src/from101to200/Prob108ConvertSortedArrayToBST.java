package from101to200;

// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
public class Prob108ConvertSortedArrayToBST {
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

    private int[] nums;

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return recursive(0, nums.length-1);
    }

    private TreeNode recursive(int start, int end) {
        if (start > end) return null;
        if (start == end) return new TreeNode(nums[start]);

        int mid = (start + end) / 2;
        return new TreeNode(nums[mid], recursive(start, mid-1), recursive(mid+1, end));
    }
}
