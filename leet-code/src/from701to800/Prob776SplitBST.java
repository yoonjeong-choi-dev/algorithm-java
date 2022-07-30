package from701to800;

// https://leetcode.com/problems/split-bst/
public class Prob776SplitBST {
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

    public TreeNode[] splitBST(TreeNode root, int target) {
        if(root == null) return new TreeNode[] {null, null};
        else if(root.val <= target) {
            // cut the right part
            // ans[0] : left part, ans[1] : right part of root.right
            TreeNode[] ans = splitBST(root.right, target);

            // root.right 에 left part of root.right 연결
            root.right = ans[0];
            ans[0] = root;
            return ans;
        } else {
            // cut the left part
            // ans[0] : left part, ans[1] : right part of root.left
            TreeNode[] ans = splitBST(root.left, target);

            // root.left 에 right part of root.left 연결
            root.left = ans[1];
            ans[1] = root;
            return ans;
        }
    }
}
