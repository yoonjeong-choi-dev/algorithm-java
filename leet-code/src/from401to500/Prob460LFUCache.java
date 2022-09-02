package from401to500;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/lfu-cache/
public class Prob460LFUCache {
    class Node {
        int key, val, count;
        Node prev, next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            count = 1;
            prev = next = null;
        }
    }

    class DLL {
        Node head, tail;
        int size;

        DLL() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        // LRU Cache : 가장 나중에 접근한 노드는 앞으로 이동
        void addFirst(Node node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLast() {
            if (size == 0) return null;

            Node node = tail.prev;
            remove(node);
            return node;
        }

    }

    class LFUCache {

        // 캐시 정보 : 용량, 현재 크기, 가장 낮은 빈도 수
        int capacity, size, minFreq;

        // main cache map : (key, Node)
        // => 키에 대한 노드 매핑(데이터 및 빈도수)
        Map<Integer, Node> keyToNode;

        // frequency map : (freq, list)
        // => LFU 삭제 연산 시 필요
        Map<Integer, DLL> freqToList;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            size = 0;
            minFreq = 0;

            keyToNode = new HashMap<>();
            freqToList = new HashMap<>();
        }

        public int get(int key) {
            Node node = keyToNode.getOrDefault(key, null);
            if (node == null) return -1;

            // 접근 횟수 업데이트 및 값 반환
            updateFrequency(node);
            return node.val;
        }

        public void put(int key, int value) {
            // Edge Case : 용량이 0
            if (capacity == 0) return;

            Node node = keyToNode.getOrDefault(key, null);
            if (node != null) {
                // update node
                node.val = value;
                updateFrequency(node);
            } else {

                // 용량이 가득찬 경우, LFU + LRU 정책을 이용하여 노드 하나 삭제
                if (capacity == size) {
                    // LFU
                    DLL lowestFreqList = freqToList.get(minFreq);

                    // LRU
                    Node toRemove = lowestFreqList.removeLast();
                    keyToNode.remove(toRemove.key);
                    size--;
                }

                // add node
                size++;
                node = new Node(key, value);
                keyToNode.put(key, node);

                // 새로 추가하는 노드의 접근 횟수는 1 => 가장 작은 접근 횟수는 1
                minFreq = 1;
                DLL list = freqToList.getOrDefault(1, new DLL());
                list.addFirst(node);
                freqToList.put(1, list);
            }
        }

        // 해당 노드 접근 시, 접근 횟수 업데이트
        private void updateFrequency(Node node) {
            DLL list = freqToList.get(node.count);

            // 리스트에서 삭제
            list.remove(node);

            // 가장 낮은 빈도수 업데이트
            // 현재 리스트에서 노드가 삭제되어 빈 리스트가 되고, 해당 리스트의 빈도 수가 현재 최소 빈도 수 인 경우
            // => node 의 빈도수가 +1 되므로, 최소 빈도수도 +1이 된다
            if (list.size == 0 && node.count == minFreq) minFreq++;

            // 카운터 증가 후 맵 업데이트
            node.count += 1;

            // LRU 구현을 위해 가장 앞에 추가
            DLL nextList = freqToList.getOrDefault(node.count, new DLL());
            nextList.addFirst(node);
            freqToList.put(node.count, nextList);
        }
    }
}
