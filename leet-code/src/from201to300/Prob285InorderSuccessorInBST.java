package from201to300;

import java.util.Stack;

// https://leetcode.com/problems/inorder-successor-in-bst/
public class Prob285InorderSuccessorInBST {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // The successor of a node p is the node with the smallest key greater than p.val.
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null) {
            if (root.val <= p.val) {
                // 오른쪽 이동시
                // 한번이라도 왼쪽 이동이 있는 경우 successor!= null && successor 가 현재 노드의 successor
                // 왼쪽 이동이 없는 경우에는 현재 노드에 대한 successor 존재 x i.e successor==null
                // root.val == p.val 인 경우에도 node.right 서브트리에서 가장 작은값(else 블록)을 계속해서 찾아야 함
                root = root.right;
            } else {
                // 왼쪽 이동 => successor 는 현재 노드
                // i.e 이후 모든 노드는 successor.val 이 upper bound
                successor = root;
                root = root.left;
            }
        }

        return successor;
    }
}
