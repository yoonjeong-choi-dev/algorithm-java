package from601to700;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/average-of-levels-in-binary-tree/
public class Prob637AverageOfLevelsInBinaryTree {
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

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();

        Queue<TreeNode> bfs = new ArrayDeque<>();
        bfs.add(root);

        TreeNode node;
        int curSize;
        double curSum;
        while(!bfs.isEmpty()) {
            curSize = bfs.size();
            curSum = 0;
            for(int i=0;i<curSize;i++){
                node = bfs.poll();
                curSum += node.val;

                if(node.left != null) bfs.add(node.left);
                if(node.right != null) bfs.add(node.right);
            }

            ans.add(curSum / curSize);
        }
        return ans;
    }
}
