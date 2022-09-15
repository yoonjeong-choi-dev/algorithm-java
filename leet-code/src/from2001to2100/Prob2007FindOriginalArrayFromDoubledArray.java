package from2001to2100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/find-original-array-from-doubled-array/
public class Prob2007FindOriginalArrayFromDoubledArray {
    public int[] findOriginalArray(int[] changed) {
        // 길이가 홀수면 무조건 거짓
        if (changed.length % 2 == 1) return new int[0];

        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : changed) counter.put(num, counter.getOrDefault(num, 0) + 1);

        // 작은 것부터 고려 : 작은 것은 반드시 원본 배열에 있는 숫자이므로
        Arrays.sort(changed);

        int[] ans = new int[changed.length / 2];
        int ansIdx = 0;
        int doubled;
        for (int num : changed) {
            // 제거된 경우 무시
            if (counter.get(num) == 0) continue;

            // 현재 숫자 카운터에서 제거
            counter.put(num, counter.get(num) - 1);

            // 현재 숫자 원본 배열에 추가 가능한지 확인
            doubled = num * 2;
            if (counter.getOrDefault(doubled, 0) > 0) {
                counter.put(doubled, counter.get(doubled) - 1);
                ans[ansIdx++] = num;
            } else {
                return new int[0];
            }
        }
        return ans;
    }
}
