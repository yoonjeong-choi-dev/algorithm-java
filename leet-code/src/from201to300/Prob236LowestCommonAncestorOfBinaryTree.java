package from201to300;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
public class Prob236LowestCommonAncestorOfBinaryTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    List<TreeNode> pathP;
    List<TreeNode> pathQ;
    TreeNode p,q;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.p = p;
        this.q = q;
        pathP = null;
        pathQ = null;

        findPath(root, new LinkedList<>());
        pathP.add(p);
        pathQ.add(q);

        int ansIdx = 0;
        int searchLen = Math.min(pathP.size(), pathQ.size());
        for(;ansIdx<searchLen;ansIdx++){
            if(pathP.get(ansIdx) != pathQ.get(ansIdx)) break;
        }

        return pathP.get(ansIdx-1);
    }

    private void findPath(TreeNode node, LinkedList<TreeNode> curPath) {
        if(node == null) return;

        if(pathP != null && pathQ != null) return;

        if(node == p) {
            pathP = new ArrayList<>(curPath);
        } else if(node == q) {
            pathQ = new ArrayList<>(curPath);
        }

        curPath.add(node);
        findPath(node.left, curPath);
        findPath(node.right, curPath);

        curPath.removeLast();
    }

    private void print(List<TreeNode> list) {
        for(TreeNode n : list) {
            System.out.printf("%d ", n.val);
        }
        System.out.println();
    }
}
