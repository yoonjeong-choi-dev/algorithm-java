package from301to400;

// https://leetcode.com/problems/largest-bst-subtree/
public class Prob333LargestBSTSubtree {
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

    class BSTInfo {
        // 현재 노드를 루트로 하는 트리의 최소값 및 최대값
        int minVal, maxVal;

        // 현재 노드를 루트로 하는 트리의 BST 인 서브트리의 크기
        int maxSize;

        public BSTInfo(int minVal, int maxVal, int maxSize) {
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.maxSize = maxSize;
        }
    }

    public int largestBSTSubtree(TreeNode root) {
        return postOrder(root).maxSize;
    }

    private BSTInfo postOrder(TreeNode node) {
        if (node == null) {
            // 모든 노드의 자식 노드가 될 수 있음
            return new BSTInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        BSTInfo left = postOrder(node.left);
        BSTInfo right = postOrder(node.right);

        // left.max < cur.val < right.min => BST
        if (left.maxVal < node.val && node.val < right.minVal) {
            // 현재 노드가 리프 노드인 경우 최대, 최소값을 현재 노드값으로 저장 필요
            return new BSTInfo(Math.min(left.minVal, node.val), Math.max(right.maxVal, node.val),
                    left.maxSize + right.maxSize + 1);
        } else {
            // Not BST
            // => 이 노드를 자식 노드로 갖는 부모 노드는 항상 해당 조건문만 태울 수 있음
            return new BSTInfo(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.maxSize, right.maxSize));
        }
    }
}
