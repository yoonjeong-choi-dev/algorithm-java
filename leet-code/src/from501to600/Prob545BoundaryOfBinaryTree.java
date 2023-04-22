package from501to600;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/boundary-of-binary-tree/
public class Prob545BoundaryOfBinaryTree {
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

    private List<Integer> ans;

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        ans = new ArrayList<>();

        //
        return ans;
    }
}
