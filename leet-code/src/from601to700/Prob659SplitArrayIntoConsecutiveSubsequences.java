package from601to700;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/split-array-into-consecutive-subsequences/
public class Prob659SplitArrayIntoConsecutiveSubsequences {
    public boolean isPossible(int[] nums) {
        // subsequences.get(n) : n 으로 끝나는 정답 배열 개수
        Map<Integer, Integer> subsequences = new HashMap<>();

        // 숫자들 개수
        Map<Integer, Integer> occurMap = new HashMap<>();
        for (int n : nums) occurMap.put(n, occurMap.getOrDefault(n, 0) + 1);

        for (int num : nums) {
            // 이미 다 쓴 경우는 무시
            if (occurMap.get(num) == 0) continue;

            if (subsequences.getOrDefault(num - 1, 0) > 0) {
                // num-1 로 끝나는 배열이 있는 경우, 추가
                subsequences.put(num - 1, subsequences.get(num - 1) - 1);
                subsequences.put(num, subsequences.getOrDefault(num, 0) + 1);
            } else if (occurMap.getOrDefault(num + 1, 0) > 0
                    && occurMap.getOrDefault(num + 2, 0) > 0) {
                // num, num+1, num+2 추가
                occurMap.put(num + 1, occurMap.get(num + 1) - 1);
                occurMap.put(num + 2, occurMap.get(num + 2) - 1);
                subsequences.put(num + 2, subsequences.getOrDefault(num + 2, 0) + 1);
            } else {
                // 현재 숫자로 만들 수 없는 경우 => false
                return false;
            }

            occurMap.put(num, occurMap.get(num) - 1);
        }

        return true;
    }
}
