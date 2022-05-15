package from101to200;

// https://leetcode.com/problems/sort-list/
public class Prob148SortList {
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

    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode node) {
        // 사이즈가 1 이하면 정렬 완료
        if (node == null || node.next == null) return node;

        // 분할 정복을 위한 분할
        ListNode mid = getMid(node);

        // 분할된 문제 풀기
        ListNode left = mergeSort(node);
        ListNode right = mergeSort(mid);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode ans = new ListNode();
        ListNode cur = ans;

        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }

        cur.next = (left != null) ? left : right;

        return ans.next;
    }

    private ListNode getMid(ListNode node) {

        // Assumption : 노드 길이는 2이상
        ListNode prev = null;
        while (node != null && node.next != null) {
            prev = (prev == null) ? node : prev.next;
            node = node.next.next;
        }

        // 노드를 node->prev, ret(prev.next) 로 쪼갬
        // => prev.next 를 널로 만들어서 쪼갬
        ListNode ret = prev.next;
        prev.next = null;
        return ret;
    }
}
