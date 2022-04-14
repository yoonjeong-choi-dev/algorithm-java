package from1to100;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/validate-binary-search-tree/
public class Prob98ValidateBST {
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

    public boolean isValidBST(TreeNode root) {
        Queue<TreeNode> bfs = new LinkedList<>();
        Queue<Integer> upperBound = new LinkedList<>();
        Queue<Integer> lowerBound = new LinkedList<>();

        bfs.offer(root);
        upperBound.offer(Integer.MAX_VALUE);
        lowerBound.offer(Integer.MIN_VALUE);

        TreeNode curNode;
        int lower, upper;
        while (!bfs.isEmpty()) {
            // 현재 노드에 대해서 자식 노드 검사 후, 자식 노드를 큐에 추가
            curNode = bfs.poll();
            upper = upperBound.poll();
            lower = lowerBound.poll();

            if (curNode.left != null) {
                if (curNode.val <= curNode.left.val) return false;
                if (curNode.left.val < lower) return false;

                // 왼쪽 자식 노드의 상한 값은 현재 노드의 값 -1, 하한값은 현재 노드의 하한값
                bfs.offer(curNode.left);
                upperBound.offer(curNode.val - 1);
                lowerBound.offer(lower);
            }

            if (curNode.right != null) {
                if (curNode.val >= curNode.right.val) return false;
                if (curNode.right.val > upper) return false;

                // 오른쪽 자식 노드의 상한 값은 현재 노드의 상한값, 하한값은 현재 노드의 값 +1
                bfs.offer(curNode.right);
                upperBound.offer(upper);
                lowerBound.offer(curNode.val + 1);
            }
        }

        return true;
    }
}
