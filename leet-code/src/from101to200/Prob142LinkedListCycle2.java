package from101to200;

// https://leetcode.com/problems/linked-list-cycle-ii/
public class Prob142LinkedListCycle2 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

    }

    // Cycle Detection : Floyd's tortoise and hare Algorithm
    public ListNode detectCycle(ListNode head) {
        ListNode tortoise = nextNode(head);
        ListNode hare = nextNode(tortoise);

        while (tortoise != hare) {
            tortoise = nextNode(tortoise);
            hare = nextNode(nextNode(hare));
        }

        // no cycle
        if (tortoise == null) return null;

        // Key Idea : i=k*L >= N for some k <=> f_i = f_(2i) where N : 시작점, L : 사이클 길이
        // => tortoise 는 N을 넘는 최소값의 k에 대한 k*L 번째 노드
        // N = a*L + b 라고 하면,
        // (b==0) tortoise = list.get(L*a) = list.get(N), (b!=0) tortoise = list.get(L*(a+1)) = list.get(N+L-b)
        // => tortoise 의 N 번째 노드는 list.get(N+L-b+N) = list.get(N+L-b+a*L+b) = list.get(N+L*(a+1)) = list.get(N)
        // => root 와 tortoise 를 한칸씩 이동하면서 두 개가 일치 할때의 노드가 시작 노드
        hare = head;
        while( hare != tortoise) {
            hare = hare.next;
            tortoise = tortoise.next;
        }
        return hare;
    }

    private ListNode nextNode(ListNode node) {
        return node == null ? null : node.next;
    }
}