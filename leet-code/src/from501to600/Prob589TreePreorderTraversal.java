package from501to600;

import java.util.List;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Collections;

// https://leetcode.com/problems/n-ary-tree-preorder-traversal/
public class Prob589TreePreorderTraversal {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<Integer> preorder(Node root) {
        List<Integer> ret = new LinkedList<>();
        if(root == null) return ret;

        Stack<Node> dfs = new Stack<>();
        dfs.add(root);

        Node curNode;
        while (!dfs.isEmpty()) {
            curNode = dfs.pop();
            ret.add(curNode.val);

            // 자식 노드들을 반대 순서로 스택에 저장
            Collections.reverse(curNode.children);
            dfs.addAll(curNode.children);
        }

        return ret;
    }

}
