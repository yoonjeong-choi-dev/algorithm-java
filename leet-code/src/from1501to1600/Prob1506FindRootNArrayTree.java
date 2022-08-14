package from1501to1600;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/find-root-of-n-ary-tree/
public class Prob1506FindRootNArrayTree {
    class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public Node findRoot(List<Node> tree) {
        Map<Node, Integer> inDegree = new HashMap<>();
        for (Node node : tree) {
            if (!inDegree.containsKey(node)) inDegree.put(node, 0);

            for (Node child : node.children) {
                // node -> child
                inDegree.put(child, inDegree.getOrDefault(child, 0) + 1);
            }
        }

        for (Node node : tree) {
            if (inDegree.get(node) == 0) return node;
        }

        // never reach
        return null;
    }

    private Node constantMemorySolution(List<Node> tree) {
        // Idea : 트리는 부모 - 자식 관계
        // 루트를 빼면 탐색 시 무조건 2번씩 방문하게 됨
        // => 부모인 경우 +, 자식인 경우 - 하면 루트노드 값만 남음
        int rootVal = 0;
        for (Node node : tree) {
            rootVal += node.val;

            for (Node child : node.children) rootVal -= child.val;
        }

        for (Node node : tree) {
            if (node.val == rootVal) return node;
        }

        // never reach
        return null;
    }
}
