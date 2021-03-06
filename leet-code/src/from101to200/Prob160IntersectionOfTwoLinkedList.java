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


    // ????????? 1??? ????????? 2??? ??????????????? ??? ???????????? ?????? ???????????? n ?????? ????????? ???????????? ????????? ?????????
    // ?????????1->?????????2 ??? ?????????2->?????????1 ??? ???????????? ??? ????????? ???????????? ?????? ???????????? n ?????? ????????? ???????????? ?????? ??????
    private ListNode solutionWithConstantMemory(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;

        while (l1 != l2) {
            // ??? ???????????? ????????? ???????????? ?????? ????????? ??????
            l1 = (l1 == null) ? headB : l1.next;
            l2 = (l2 == null) ? headA : l2.next;
        }

        return l1;
    }
}
