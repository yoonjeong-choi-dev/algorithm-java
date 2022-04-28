package from101to200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/copy-list-with-random-pointer/
public class Prob138CopyListWithRandomPointer {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        return solutionWithArrayList(head);
    }

    private Node solutionWithArrayList(Node head) {
        Map<Node, Integer> nodeIndex = new HashMap<>();
        ArrayList<Node> copyArr = new ArrayList<>();
        Node ans = new Node(-1);


        Node cur = head, copy, copyCur = ans;
        while (cur != null) {
            copy = new Node(cur.val);
            copyCur.next = copy;

            nodeIndex.put(cur, copyArr.size());
            copyArr.add(copy);

            cur = cur.next;
            copyCur = copyCur.next;
        }

        cur = head;
        int curIdx = 0;

        while (cur != null) {
            if (cur.random != null) {
                copyArr.get(curIdx).random = copyArr.get(nodeIndex.get(cur.random));
            }
            curIdx++;
            cur = cur.next;
        }


        return ans.next;
    }

    private Map<Node, Node> oldToNew;

    private Node solutionWithMap(Node head) {
        if (head == null) return null;

        Node ans = new Node(head.val);

        oldToNew = new HashMap<>();
        oldToNew.put(head, ans);

        // while 문 역할 : 노드의 연결(next, random) 작업
        // getOrCreateNode 역할 : 노드 생성
        Node curOld = head, curNew = ans;
        while (curOld != null) {
            // next 및 random 작업
            curNew.next = getOrCreateNode(curOld.next);
            curNew.random = getOrCreateNode(curOld.random);

            curOld = curOld.next;
            curNew = curNew.next;
        }

        return ans;
    }

    private Node getOrCreateNode(Node node) {
        if (node == null) return null;

        // 없는 경우 새로 만들어 준다
        if (!oldToNew.containsKey(node)) {
            oldToNew.put(node, new Node(node.val));
        }

        return oldToNew.get(node);
    }
}
