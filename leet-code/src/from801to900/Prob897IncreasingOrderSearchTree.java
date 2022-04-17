package from801to900;

// https://leetcode.com/problems/increasing-order-search-tree/
public class Prob897IncreasingOrderSearchTree {
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

    // 현재 일렬로 놓아진 트리의 리프 노드
    TreeNode curLeaf;
    public TreeNode increasingBST(TreeNode root) {
        if(root == null) return null;

        // 트리를 일자로 놓기 위해서는 가장 왼쪽 리프 노드가 필요
        // inorder 탐색을 통해 구해야 하기 때문에 더미 노드 하나를 만들어 탐색
        TreeNode ans = new TreeNode(-1);
        curLeaf = ans;
        inorderTraversal(root);
        return ans.right;
    }

    private void inorderTraversal(TreeNode node){
        if(node == null) return;

        // node.left 트리를 일자로 펴준다
        inorderTraversal(node.left);

        // node.left 트리의 리프 노드에 현재 노드 연결 및 왼쪽 서브트리 삭제
        curLeaf.right = node;
        curLeaf = curLeaf.right;
        node.left = null;

        // node.right 트리를 일자로 펴준다
        // curLeaf.right == node.right
        inorderTraversal(node.right);
    }
}
