package from701to800;

// https://leetcode.com/problems/insert-into-a-binary-search-tree/
public class Prob701InsertIntoBST {
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

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) {
            return new TreeNode(val);
        }

        TreeNode insertNode = new TreeNode(val);
        TreeNode curNode = root;
        while(curNode != null) {
            if(val < curNode.val) {
                // 왼쪽 자식 노드가 없는 경우 삽입
                if(curNode.left == null) {
                    curNode.left = insertNode;
                    break;
                } else {
                    curNode = curNode.left;
                }
            } else {
                // 오른쪽 자식 노드가 없는 경우 삽입
                if(curNode.right == null){
                    curNode.right = insertNode;
                    break;
                } else {
                    curNode = curNode.right;
                }
            }
        }

        return root;
    }
}
