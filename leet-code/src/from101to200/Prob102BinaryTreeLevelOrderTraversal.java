package from101to200;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Prob102BinaryTreeLevelOrderTraversal {
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

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<List<Integer>>();

        // 트리의 최대 높이는 log_2(2000) < 11
        List<List<Integer>> ans = new ArrayList<>(11);

        int curLevelSize;
        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.add(root);

        List<Integer> curLevelNodes;
        TreeNode curNode;
        while (!bfs.isEmpty()) {
            curLevelNodes = new LinkedList<>();

            // 현재 레벨의 노드에 대한 탐색
            curLevelSize = bfs.size();
            for(int i=0;i<curLevelSize;i++){
                curNode = bfs.poll();
                curLevelNodes.add(curNode.val);

                if(curNode.left != null) bfs.add(curNode.left);
                if(curNode.right != null) bfs.add(curNode.right);
            }

            ans.add(curLevelNodes);
        }


        return ans;
    }
}
