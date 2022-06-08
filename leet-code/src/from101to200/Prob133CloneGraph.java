package from101to200;

import java.util.*;

// https://leetcode.com/problems/clone-graph/
public class Prob133CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        HashMap<Node, Node> oldToNew = new HashMap<>();
        oldToNew.put(node, new Node(node.val));

        Set<Node> visited = new HashSet<>();
        Queue<Node> bfs = new ArrayDeque<>();
        bfs.add(node);
        visited.add(node);

        Node cur, copyCur, copyAdj;
        while (!bfs.isEmpty()) {
            cur = bfs.poll();

            copyCur = oldToNew.get(cur);

            for (Node adj : cur.neighbors) {
                if (!oldToNew.containsKey(adj)) {
                    oldToNew.put(adj, new Node(adj.val));
                }

                copyAdj = oldToNew.get(adj);
                copyCur.neighbors.add(copyAdj);

                if (!visited.contains(adj)) {
                    visited.add(adj);
                    bfs.add(adj);
                }
            }
        }

        return oldToNew.get(node);
    }
}
