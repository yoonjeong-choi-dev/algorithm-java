package from701to800;

import java.util.PriorityQueue;

// https://leetcode.com/problems/kth-largest-element-in-a-stream/
public class Prob703KthLargestNumberInStream {
    class KthLargest {
        int size;
        PriorityQueue<Integer> pq;

        public KthLargest(int k, int[] nums) {
            size = k;
            pq = new PriorityQueue<>();

            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            if (pq.size() < size) {
                pq.add(val);
            } else {
                // 큐가 가득 차 있는 경우, 현재 삽입할 요소가 큐의 최소값보다 큰 경우에만 서로 교환
                int small = pq.peek();
                if (small < val) {
                    pq.poll();
                    pq.add(val);
                }
            }

            return pq.peek();
        }

        // for test
        public String toString() {
            return pq.toString();
        }
    }

    public void test() {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});

        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest);

        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest);

        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest);

        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest);

        System.out.println(kthLargest.add(4));
        System.out.println(kthLargest);
    }

    public static void main(String[] args) {
        Prob703KthLargestNumberInStream sol = new Prob703KthLargestNumberInStream();
        sol.test();
        ;
    }
}
