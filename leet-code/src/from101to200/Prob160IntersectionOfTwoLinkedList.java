package from101to200;

import java.util.HashSet;

// https://leetcode.com/problems/intersection-of-two-linked-lists/
public class Prob160IntersectionOfTwoLinkedList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        return solutionWithExtraArray(headA, headB);
    }

    // Solution 1 : Array - O(Max(N,M))
    private ListNode solutionWithExtraArray(ListNode headA, ListNode headB) {
        ListNode[] arrA = convertToArray(headA);
        ListNode[] arrB = convertToArray(headB);

        int lenA = arrA.length, lenB = arrB.length;

        int searchLen = Math.min(lenA, lenB);
        int idx = 0;
        for (; idx < searchLen; idx++) {
            if (arrA[lenA - 1 - idx] != arrB[lenB - 1 - idx]) break;
        }

        if (idx == 0) return null;

        idx = lenA - idx;
        ListNode ans = headA;
        while (idx != 0) {
            ans = ans.next;
            idx--;
        }

        return ans;
    }

    private ListNode[] convertToArray(ListNode node) {
        int len = 0;
        ListNode cur = node;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        ListNode[] ret = new ListNode[len];
        int idx = 0;
        cur = node;
        while (cur != null) {
            ret[idx++] = cur;
            cur = cur.next;
        }

        return ret;
    }

    // Solution 2 : Set - O(M*log(N))
    // Runtime: 6 ms, faster than 26.09% of Java online submissions
    private ListNode solutionWithExtraSet(ListNode headA, ListNode headB) {
        ListNode temp;

        HashSet<ListNode> nodesA = new HashSet<>();
        temp = headA;
        while (temp != null) {
            nodesA.add(temp);
            temp = temp.next;
        }

        temp = headB;
        while (temp != null) {
            if (nodesA.contains(temp)) return temp;
            else temp = temp.next;
        }

        return null;
    }


    // 리스트 1과 리스트 2가 겹쳐진다면 두 리스트의 꼬리 노드에서 n 이전 노드가 겹쳐지는 부분의 시작점
    // 리스트1->리스트2 와 리스트2->리스트1 를 비교하면 두 합쳐진 리스트의 꼬리 노드에서 n 이전 노드가 문제에서 찾는 노드
    private ListNode solutionWithConstantMemory(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;

        while (l1 != l2) {
            // 각 리스트가 꼬리에 도착하면 상대 노드로 교체
            l1 = (l1 == null) ? headB : l1.next;
            l2 = (l2 == null) ? headA : l2.next;
        }

        return l1;
    }
}
