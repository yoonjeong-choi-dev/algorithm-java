package from1to100;

import java.util.ArrayList;

public class Prob19RemoveFromLinkedList {
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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 한 번의 탐색으로 삭제 노드를 찾기 위해 각 노드를 배열에 저장
        ArrayList<ListNode> nodes = new ArrayList<>(100);
        ListNode temp = head;
        while (temp != null) {
            nodes.add(temp);
            temp = temp.next;
        }

        // 연결 리스트 길이 및 삭제 위치 검색
        int len = nodes.size();
        int removeIdx = len - n;

        // 특이 케이스 : head 노드를 삭제하는 경우
        if (removeIdx == 0) return head.next;

        // 삭제해야 하는 노드의 앞 노드 계산
        ListNode removedPrev = nodes.get(removeIdx - 1);

        // 삭제할 노드 전후를 연결
        removedPrev.next = removedPrev.next.next;
        return head;
    }
}
