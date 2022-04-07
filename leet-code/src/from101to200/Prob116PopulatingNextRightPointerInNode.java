package from101to200;

import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
public class Prob116PopulatingNextRightPointerInNode {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }


    public Node connect(Node root) {
        return myQueueSolution(root);
    }

    // Runtime: 6 ms, faster than 8.87% of Java online submissions
    // Memory Usage: 48 MB, less than 18.25% of Java online submissions
    private Node myQueueSolution(Node root) {
        if (root == null) return null;

        // root : Perfect Binary Tree
        int curLevelWidth = 1;

        // BFS : 트리의 높이는 최대 12이므로 leaf 노드의 최대 개수는 2024
        Queue<Node> bfs = new ArrayDeque<>(4048);
        bfs.add(root);

        Node curNode;
        int curLevelIdx = 1;
        while (!bfs.isEmpty()) {
            curNode = bfs.poll();

            // leaf node가 아닌 경우 bfs에 추가
            if (curNode.left != null) {
                bfs.add(curNode.left);
                bfs.add(curNode.right);
            }

            if (curLevelIdx == curLevelWidth) {
                curLevelWidth = curLevelWidth * 2;
                curLevelIdx = 1;
            } else {
                curLevelIdx++;
                curNode.next = bfs.peek();
            }
        }

        return root;
    }

    // TODO : Improve Runtime and Memory
    private Node improvedSolution(Node root) {
        if (root == null) return null;

        // 현재 레벨의 가장 왼쪽 노드
        Node curLevelLeftNode = root;

        // 가장 왼쪽 노드가 leaf 노드일 때까지 탐색
        Node curNode;
        while (curLevelLeftNode.left != null) {

            // 현재 레벨에 대해서 자식 노드들 업데이트
            // 즉, 현재 레벨에 대한 노드들은 next 포인터 탐색이 완료된 상황
            curNode = curLevelLeftNode;
            while (curNode != null) {
                // 왼쪽 자식의 다음 노드는 오른쪽 자식 노드
                curNode.left.next = curNode.right;

                // 오른쪽 자식 노드의 다음 노드 계산
                // 오른쪽 자식 노드는 자신의 다음 노드의 왼쪽 자식 노드
                if (curNode.next != null) {
                    curNode.right.next = curNode.next.left;
                }
                curNode = curNode.next;
            }

            // 다음 레벨 탐색
            curLevelLeftNode = curLevelLeftNode.left;
        }

        return root;
    }
}
