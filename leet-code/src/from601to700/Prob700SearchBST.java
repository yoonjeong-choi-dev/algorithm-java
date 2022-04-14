package from601to700;

// https://leetcode.com/problems/search-in-a-binary-search-tree/
public class Prob700SearchBST {
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

    public TreeNode searchBST(TreeNode root, int val) {
        while(root != null) {
            if(root.val == val) return root;
            else if(val < root.val){
                root = root.left;
            } else {
                root = root.right;
            }
        }

        // 리프까지 탐색이 완료된 경우 해당 노드를 찾지 못한 것
        return null;
    }
}
