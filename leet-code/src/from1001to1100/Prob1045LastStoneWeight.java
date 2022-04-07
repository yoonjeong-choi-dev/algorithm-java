package from1001to1100;

import java.util.Collections;
import java.util.PriorityQueue;

// https://leetcode.com/problems/last-stone-weight/
public class Prob1045LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        //return myHeapSolution(stones);
        return bucketSortSolution(stones);
    }

    private int myHeapSolution(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            pq.add(stone);
        }

        int first, second;
        while (pq.size() >= 2) {
            first = pq.poll();
            second = pq.poll();

            if (first != second) {
                pq.add(first - second);
            }
        }

        return pq.isEmpty() ? 0 : pq.peek();
    }

    // TODO : Bucket Sort
    private int bucketSortSolution(int[] stones) {
        int maxWeight = stones[0];
        for (int stone : stones) {
            maxWeight = Math.max(maxWeight, stone);
        }

        // 돌의 최대 무게는 1000 => 버킷 정렬 가능
        int[] bucket = new int[maxWeight + 1];
        for (int stone : stones) {
            bucket[stone]++;
        }

        // 버킷을 탐색하면서 시뮬레이션
        int curMaxWeight = 0;
        int curWeight = maxWeight;

        while (curWeight > 0) {
            if (bucket[curWeight] == 0) {
                // 현재 무게에 대한 돌이 없는 경우 더 작은 무게로 탐색
                curWeight--;
            } else if (curMaxWeight == 0) {
                // 아직 현재 버킷에서 남아 있는 돌 중 최대 무게의 돌을 삭제하지 않은 경우
                // => 현재 무게에 해당하는 돌을 1개 남을 때까지 삭제
                bucket[curWeight] = bucket[curWeight] % 2;
                if (bucket[curWeight] == 1) {
                    // 두번째 무거운 돌을 찾기 위해 가장 무거운 무게 저장
                    curMaxWeight = curWeight;
                }

                // 삭제할 다음 무게 찾기
                curWeight--;
            } else {
                // 두번째 무거운 돌을 찾은 경우
                // 해당 돌을 삭제하고, 현재 남아 있는 돌 중에 가장 무거운 무게 저장
                bucket[curWeight]--;

                if (curMaxWeight <= 2 * curWeight) {
                    // 추가할 돌의 무게가 현재 탐색 범위 안에 들어 간 경우
                    // 가장 무거운 무게를 초기화하고 동일한 방식을 탐색을 이어 나간다
                    bucket[curMaxWeight - curWeight]++;
                    curMaxWeight = 0;
                } else {
                    // 추가할 돌의 무게가 현재 버킷의 인덱스(curWeight) 보다 큰 경우
                    // 버킷을 왼쪽으로 탐색 중이므로, 가장 무거운 무게 업데이트
                    curMaxWeight -= curWeight;
                }
            }
        }

        return curMaxWeight;
    }
}
