package from1601to1700;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
public class Prob1650LowestCommonAncestorOfABinaryTree3 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        if (p == q) return p;

        Set<Node> pToRoot = new HashSet<>();
        while (p.parent != null) {
            pToRoot.add(p);
            p = p.parent;
        }

        Node root = p;
        while (root != q) {
            if (pToRoot.contains(q)) return q;
            q = q.parent;
        }

        return root;
    }
}
