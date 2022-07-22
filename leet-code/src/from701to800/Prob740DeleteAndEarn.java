package from701to800;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

// https://leetcode.com/problems/delete-and-earn/
// TODO : Improve Runtime
public class Prob740DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        // 각 숫자의 개수 저장
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        // 하나의 숫자로만 구성된 경우
        if (map.size() == 1) {
            return nums[0] * nums.length;
        }

        // 배열을 구성하는 수들 저장
        int numDistinct = map.size();
        Integer[] numbers = new Integer[numDistinct];
        int idx = 0;
        for (int key : map.keySet()) {
            numbers[idx++] = key;
        }

        // 높은 숫자들로 정렬
        Arrays.sort(numbers, Collections.reverseOrder());


        // f(n) : numbers[n]을 반드시 선택했을 때, numbers[0..n] 부분 문제의 최대값
        // f(n) : 바로 직전의 f(n-1) 및 max{f(i) | i<n-1} 고려
        // ans = f(n-2)까지의 최대값, prev = f(n-1)
        int ans = numbers[0] * map.get(numbers[0]);
        int prev = numbers[1] * map.get(numbers[1]);
        if (numbers[0] != numbers[1] + 1) {
            // numbers[0] 도 선택 가능한 경우
            prev += ans;
        }

        int temp;
        for (int i = 2; i < numDistinct; i++) {
            // numbers[i]를 선택하는 경우
            temp = numbers[i] * map.get(numbers[i]);

            if (numbers[i] + 1 != numbers[i - 1]) {
                // numbers[i-1] 선택 가능한 경우
                temp += Math.max(prev, ans);
            } else {
                // numbers[i-1] 선택 불가능한 경우
                temp += ans;
            }

            // ans : max{f(i) | i<n-1} 업데이트
            ans = Math.max(prev, ans);
            prev = temp;
        }


        return Math.max(prev, ans);
    }

    public static void main(String[] args) {
        Prob740DeleteAndEarn sol = new Prob740DeleteAndEarn();

        int[][] nums = {{3, 4, 2}, {2, 2, 3, 3, 3, 4}, {2, 3, 4, 5, 6, 7}, {1,1,1,2,4,5,5,5,6}};

        int[] ans = {6, 9, 15, 18};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.deleteAndEarn(nums[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
