package from401to500;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/n-ary-tree-level-order-traversal/
public class Prob429NTreeLevelOrderTraversal {
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

    public List<List<Integer>> levelOrder(Node root) {
        if(root == null) return new ArrayList<>();

        List<List<Integer>> ans = new LinkedList<>();

        // 각 레벨을 구분하기 위한 더미 노드
        Node dummy = new Node(-1);
        Queue<Node> bfs = new LinkedList<>();
        bfs.offer(root);
        bfs.offer(dummy);

        Node curNode;
        while (!bfs.isEmpty()) {
            // 현재 레벨 탐색
            List<Integer> curAns = new ArrayList<>();
            while (!bfs.isEmpty()) {
                curNode = bfs.poll();
                if (curNode == dummy) break;

                curAns.add(curNode.val);
                bfs.addAll(curNode.children);
            }
            ans.add(curAns);

            if (bfs.isEmpty()) break;
            bfs.add(dummy);
        }


        return ans;
    }
}
