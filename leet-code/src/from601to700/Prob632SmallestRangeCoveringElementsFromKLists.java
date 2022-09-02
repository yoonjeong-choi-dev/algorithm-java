package from601to700;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
public class Prob632SmallestRangeCoveringElementsFromKLists {
    public int[] smallestRange(List<List<Integer>> nums) {
        int numList = nums.size();

        // colIndex[i] : nums.get(i) 리스트에서 현재 바라보고 있는 요소의 인덱스
        int[] colIndex = new int[numList];

        // 리스트들 중 가장 작은 값으로 시작하는 행부터 고려
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return nums.get(o1).get(colIndex[o1]) - nums.get(o2).get(colIndex[o2]);
            }
        });

        // 현재 상태에서의 리스트들 중 최대값
        int curMax = Integer.MIN_VALUE;
        for (int i = 0; i < numList; i++) {
            pq.add(i);
            curMax = Math.max(curMax, nums.get(i).get(0));
        }

        int bestStart = -1, bestEnd = -1, bestLen = Integer.MAX_VALUE;
        int curRow, curVal;
        while (true) {
            curRow = pq.poll();
            curVal = nums.get(curRow).get(colIndex[curRow]);

            if (curMax - curVal < bestLen) {
                bestStart = curVal;
                bestEnd = curMax;
                bestLen = bestEnd - bestStart;
            }

            // 현재 행에서 다음 요소로 변경
            colIndex[curRow]++;
            if (colIndex[curRow] == nums.get(curRow).size()) {
                // 한 행이라도 끝까지 간 경우 탐색 중지
                break;
            } else {
                // 다음 반복문 진행 시, 현재 행 다음요소를 포함시켜야 하므로 최대값 업데이트
                curMax = Math.max(curMax, nums.get(curRow).get(colIndex[curRow]));

                // 현재 행의 업데이트된 상태를 큐에 다시 저장
                pq.add(curRow);
            }
        }

        return new int[]{bestStart, bestEnd};
    }
}
