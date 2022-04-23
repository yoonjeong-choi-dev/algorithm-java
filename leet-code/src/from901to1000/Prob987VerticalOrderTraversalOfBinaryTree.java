package from901to1000;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
public class Prob987VerticalOrderTraversalOfBinaryTree {
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

    List<int[]> nodeInfo;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        nodeInfo = new LinkedList<>();

        inorderRecursive(root, 0, 0);

        nodeInfo.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // top-to-bottom orderings for each column index
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                } else if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[2] - o2[2];
                }
            }
        });

        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> curAns = new LinkedList<>();

        int[] prevInfo = nodeInfo.get(0);
        nodeInfo.remove(0);
        curAns.add(prevInfo[2]);

        for (int[] info : nodeInfo) {
            if (info[1] == prevInfo[1]) {
                curAns.add(info[2]);
            } else {
                ans.add(curAns);
                curAns = new LinkedList<>();
                curAns.add(info[2]);
                prevInfo = info;
            }
        }
        ans.add(curAns);
        return ans;
    }

    private void inorderRecursive(TreeNode node, int row, int col) {
        if (node == null) return;

        nodeInfo.add(new int[]{row, col, node.val});

        inorderRecursive(node.left, row + 1, col - 1);
        inorderRecursive(node.right, row + 1, col + 1);
    }

}
