package from1401to1500;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/
public class Prob1457PseudoPalindromicPathsInBinaryTree {
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

    public int pseudoPalindromicPaths(TreeNode root) {
        int ans = 0;

        Deque<TreeNode> dfs = new ArrayDeque<>();

        // pseudo-palindromic if at least one permutation of the node values in the path is a palindrome
        // => 홀수 횟수 등장하는 숫자는 1개 이하일 때만 회문을 만들 수 있음
        // => 10비트를 이용하여 1~9번째 비트로 1~9 숫자가 홀수번인지(1) 짝수번인지(0) 추적
        Deque<Integer> path = new ArrayDeque<>();

        dfs.push(root);
        path.push(0);

        TreeNode curNode;
        int curPath;
        while (!dfs.isEmpty()) {
            curNode = dfs.pop();
            curPath = path.pop();

            // 현재 경로 업데이트
            curPath = curPath ^ (1 << curNode.val);

            // 리프 노드인 경우 회문 만들 수 있는지 확인
            if (curNode.left == null && curNode.right == null) {
                // 현재 경로가 2의 제곱수인 경우에만 가능(1인 비트가 1개이하)
                if ((curPath & (curPath - 1)) == 0) ans++;
            }

            if(curNode.left != null) {
                dfs.push(curNode.left);
                path.push(curPath);
            }
            if(curNode.right != null) {
                dfs.push(curNode.right);
                path.push(curPath);
            }
        }

        return ans;
    }
}
