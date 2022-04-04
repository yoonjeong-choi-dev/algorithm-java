package from1701to1800;

// https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
public class Prob1721SwappingNodesInLinkedList {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode swapNodes(ListNode head, int k) {
        int len = 0;

        // 노드 자체의 교환이 아닌 값을 교환하는 형태로 구현
        ListNode swap1 = null;
        ListNode curNode = head;

        // k 번째 노드 탐색 및 길이 계산
        while (curNode != null) {
            len++;
            if (len == k) {
                swap1 = curNode;
            }
            curNode = curNode.next;
        }

        ListNode swap2 = head;
        for (int i = 0; i < len - k; i++) {
            swap2 = swap2.next;
        }


        int temp = swap1.val;
        swap1.val = swap2.val;
        swap2.val = temp;

        return head;
    }
}
