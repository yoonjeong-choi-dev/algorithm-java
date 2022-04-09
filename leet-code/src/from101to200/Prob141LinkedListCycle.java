package from101to200;

// https://leetcode.com/problems/linked-list-cycle/
public class Prob141LinkedListCycle {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // Cycle Detection : Floyd's tortoise and hare Algorithm
    // Reference : Problem 202. Happy Number
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode tortoise = nextNode(head);
        ListNode hare = nextNode(tortoise);

        while (true) {
            if (tortoise == null) return false;
            if (hare == tortoise) return true;

            tortoise = nextNode(tortoise);
            hare = nextNode(nextNode(hare));
        }
    }

    private ListNode nextNode(ListNode node) {
        return node == null ? null : node.next;
    }
}
