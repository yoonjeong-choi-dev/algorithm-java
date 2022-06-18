package from101to200;

import java.util.Stack;

// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
public class Prob114FlattenBinaryTreeToLinkedList {
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


    public void flatten(TreeNode root) {
        //dfsWithStack(root);
        morisTraversal(root);
    }

    // Memory Complexity : O(n)
    private void dfsWithStack(TreeNode root) {
        if (root == null) return;

        TreeNode curAns = root;

        Stack<TreeNode> dfs = new Stack<>();
        if (root.right != null) {
            dfs.push(root.right);
            root.right = null;
        }

        if (root.left != null) {
            dfs.push(root.left);
            root.left = null;
        }

        TreeNode curNode;
        while (!dfs.isEmpty()) {
            curNode = dfs.pop();

            // leaf => visit!
            if (curNode.left == null && curNode.right == null) {
                curAns.right = curNode;
                curAns = curNode;
                continue;
            }

            // save right -> left -> node to the stack
            if (curNode.right != null) {
                dfs.push(curNode.right);
                curNode.right = null;
            }

            if (curNode.left != null) {
                dfs.push(curNode.left);
                curNode.left = null;
            }

            dfs.push(curNode);
        }
    }

    // Moris Traversal
    private void morisTraversal(TreeNode root) {
        if (root == null) return;

        TreeNode cur = root;
        TreeNode rightest;
        while (cur != null) {
            // 왼쪽 서브트리가 있는 경우
            // 1) 현재 노드의 오른쪽에 바로 왼쪽 서브트리 이동
            // 2) 왼쪽 서브트리의 가장 오른쪽 노드의 오른쪽에 현재 노드의 오른쪽 서브트리 연결
            if(cur.left != null) {
                // 왼쪽 서브트리의 가장 왼쪽 노드 찾기 : root -> left(->rightest) -> right 연결을 위해서
                rightest = cur.left;
                while(rightest.right != null) {
                    rightest = rightest.right;
                }

                // cur -> cur.left -> cur.right
                rightest.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }

            // 왼쪽 서브트리가 오른쪽에 붙었으므로, 현재 노드의 왼쪽 노드는 없음 i.e 현재 노드에 대한 flatten 완료
            // => 오른쪽 서브트리에 대해서 동일한 작업 반복
            cur = cur.right;
        }
    }
}
