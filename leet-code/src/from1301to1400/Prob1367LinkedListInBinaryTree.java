package from1301to1400;

// https://leetcode.com/problems/linked-list-in-binary-tree/
public class Prob1367LinkedListInBinaryTree {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

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

    public boolean isSubPath(ListNode head, TreeNode root) {
        // 모든 경로 추척을 완료한 경우
        if (head == null) return true;

        // 경로 추척을 완료하지 못한 상태에서 트리가 끝난 경우
        if (root == null) return false;

        // 현재 노드값이 같은 경우 현재 노드를 포함한 경로 탐색
        if(root.val == head.val) {
            if(validateCurTree(root.left, head.next)) return true;
            if(validateCurTree(root.right, head.next)) return true;
        }

        // 현재 노드값이 다르거나 현재 노드를 포함한 경로가 없는 경우, 서브트리에 대해서 탐색
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    // curRoot 노드를 시작하는 경로 탐색
    private boolean validateCurTree(TreeNode curRoot, ListNode curPath) {
        // 모든 경로 추척을 완료한 경우
        if (curPath == null) return true;

        // 경로 추척을 완료하지 못한 상태에서 트리가 끝난 경우
        if (curRoot == null) return false;

        // 현재 노드 값과 경로 값이 다른 경우
        if (curRoot.val != curPath.val) return false;

        return validateCurTree(curRoot.left, curPath.next) || validateCurTree(curRoot.right, curPath.next);
    }
}
