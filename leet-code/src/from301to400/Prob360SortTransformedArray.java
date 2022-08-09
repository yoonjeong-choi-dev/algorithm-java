package from301to400;

import java.util.Collections;

// https://leetcode.com/problems/sort-transformed-array/
public class Prob360SortTransformedArray {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int len = nums.length;
        int[] ans = new int[len];
        if (a == 0) {
            // bx+c => 우상향 or 우하향
            if (b >= 0) {
                for (int i = 0; i < len; i++) ans[i] = b * nums[i] + c;
            } else {
                for (int i = 0; i < len; i++) ans[i] = b * nums[len - 1 - i] + c;
            }
            return ans;
        }

        // f(x) = a*x^2 + b*x + c
        // => x=-(b/2a) 대칭축
        // a > 0 : 대칭축과 멀어 질수록 커짐, a < 0 : 대칭축과 멀어 질수록 작아짐
        // 우선 a < 0 이라고 가정하고, 대칭축과 먼 숫자들부터 저장
        double symmetric = (double) -b / (2 * a);
        int left = 0, right = len - 1, idx = 0, x;
        double dist1, dist2;
        while (idx < len) {
            dist1 = Math.abs(symmetric - nums[left]);
            dist2 = Math.abs(symmetric - nums[right]);
            if (dist1 > dist2) {
                x = nums[left++];
            } else {
                x = nums[right--];
            }

            ans[idx++] = a * x * x + b * x + c;
        }

        // a < 0 으로 가정했기 때문에 순서를 뒤집어 준다
        if (a > 0) {
            int temp;
            left = 0;
            right = len - 1;
            while (left < right) {
                temp = ans[left];
                ans[left++] = ans[right];
                ans[right--] = temp;
            }
        }

        return ans;
    }
}
