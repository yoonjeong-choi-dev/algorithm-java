package from401to500;

// https://leetcode.com/problems/delete-node-in-a-bst/
public class Prob450DeleteNodeInBST {
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

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode removedParent = null, removed = root;

        while (removed != null) {
            if (removed.val == key) break;
            else if (key < removed.val) {
                removedParent = removed;
                removed = removed.left;
            } else {
                removedParent = removed;
                removed = removed.right;
            }
        }

        // 삭제할 노드가 없는 경우 처리 없이 반환
        if (removed == null) return root;

        // 삭제할 노드의 오른쪽 서브트리에서 가장 왼쪽 노드 찾기
        if (removed.right == null) {
            // 오른쪽 서브트리가 없으면 단순히 왼쪽 서브트리를 연결하면 됨
            if (removedParent == null) {
                root = removed.left;
            } else if (removedParent.left == removed) {
                removedParent.left = removed.left;
            } else {
                removedParent.right = removed.left;
            }
        } else {
            TreeNode parent = removed;
            TreeNode candidate = removed.right;
            while (candidate.left != null) {
                parent = candidate;
                candidate = candidate.left;
            }

            if(parent != removed) {
                parent.left = candidate.right;
                candidate.right = removed.right;
            }
            candidate.left = removed.left;

            if (removedParent == null) {
                root = candidate;
            } else if (removedParent.left == removed) {
                removedParent.left = candidate;
            } else {
                removedParent.right = candidate;
            }
        }

        return root;
    }
}
