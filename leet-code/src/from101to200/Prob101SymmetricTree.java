package from101to200;

// https://leetcode.com/problems/symmetric-tree/
// TODO : 다시 풀기
public class Prob101SymmetricTree {
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

    public boolean isSymmetric(TreeNode root) {
        return isEqual(root, root);
    }

    private boolean isEqual(TreeNode left, TreeNode right){
        // left : root 노드에서 n만큼 떨이진 왼쪽 자식
        // right : root 노드에서 n만큼 떨어진 오른쪽 자식
        // => (left.left, right.right), (left.right, right.left) 는 대칭 노드

        if(left == null && right == null) {
            // 부모 노드가 리프인 경우 항상 참
            return true;
        } else if(left == null || right == null){
            // 부모 노드의 자식 개수가 1개인 경우 항상 거짓
            return false;
        } else {
            // 자식 모두가 모두 존재 하는 경우
            // 자식 노드들의 값이 같고, 자식 노드의 자식 노들 사이에 symmetric 유지
            return (left.val == right.val) && isEqual(left.left, right.right) && isEqual(left.right, right.left);
        }
    }
}
