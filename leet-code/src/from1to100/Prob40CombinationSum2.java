package from1to100;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/combination-sum-ii/
// Ref : Problem 90
public class Prob40CombinationSum2 {
    // 재귀 호출을 위한 변수들
    private int len;
    List<List<Integer>> ans;
    int[] arr;
    int[] counts;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int num : candidates) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        ans = new LinkedList<>();
        len = countMap.size();
        arr = new int[len];
        counts = new int[len];

        int curIdx = 0;
        for (int key : countMap.keySet()) {
            arr[curIdx] = key;
            counts[curIdx] = countMap.get(key);
            curIdx++;
        }

        recursive(0, target);
        return ans;
    }

    private void recursive(int curIdx, int remain) {
        if(remain == 0) {
            List<Integer> curAns = new LinkedList<>();

            // [0:curIdx] 까지만 탐색된 결과
            for (int i = 0; i < curIdx; i++) {
                for (int j = 0; j < counts[i]; j++) curAns.add(arr[i]);
            }
            ans.add(curAns);
            return;
        } else if(curIdx == len) return;

        int temp = counts[curIdx];
        int maxCount = Math.min(remain / arr[curIdx], temp);
        for (int i = 0; i <= maxCount; i++) {
            counts[curIdx] = i;
            recursive(curIdx + 1, remain);
            remain -= arr[curIdx];
        }

        // 재귀호출 완료후 데이터 원상 복구
        counts[curIdx] = temp;
    }
}
