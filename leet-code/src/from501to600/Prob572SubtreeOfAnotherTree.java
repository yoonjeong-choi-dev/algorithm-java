package from501to600;

// https://leetcode.com/problems/subtree-of-another-tree/
public class Prob572SubtreeOfAnotherTree {
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

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // 양쪽 모두 탐색이 끝난 경우는 참
        if (root == null && subRoot == null) return true;

        // 양쪽 트리 중 한쪽만 끝난 경우는 거짓
        if (root == null || subRoot == null) return false;

        // 서브 트리의 루트와 같은 노드 찾기
        if (root.val == subRoot.val) {
            // 현재 노드의 서브트리들에 대해서 검증
            if (isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right)) return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    // 루트 노드가 같은 값을 가지는 경우의 2개의 트리가 같은지 확인하는 함수
    private boolean isSameTree(TreeNode node1, TreeNode node2) {
        // 양쪽 모두 탐색이 끝난 경우는 참
        if (node1 == null && node2 == null) return true;

        // 양쪽 트리 중 한쪽만 끝난 경우는 거짓
        if (node1 == null || node2 == null) return false;

        if(node1.val != node2.val) return false;

        return isSameTree(node1.left, node2.left) && isSameTree(node1.right, node2.right);
    }

}
