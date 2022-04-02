package from101to200;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
public class Prob167TwoSumWithSorted {

    public int[] twoSum(int[] numbers, int target) {
        // return towSumLikeProb1(numbers, target);
        return improvedSolution(numbers, target);
    }

    // Problem 1. Two Sum 차용
    // O(n*log(n)) 복잡도
    public int[] towSumLikeProb1(int[] numbers, int target) {
        int len = numbers.length;

        // remainderMap : (value, index) 형태
        Map<Integer, Integer> remainderMap = new HashMap<>(len);

        int remain;
        for (int i = 0; i < len; i++) {
            remain = target - numbers[i];

            // 오름차순이기 때문에 나머지가 현재 값보다 큰 경우에는 map 탐색없이 저장하면 됨
            if (remain > numbers[i] || !remainderMap.containsKey(remain)) {
                remainderMap.put(numbers[i], i + 1);
            } else {
                return new int[]{remainderMap.get(remain), i + 1};
            }
        }

        return null;
    }

    // TODO: 배열의 오름차순을 이용하여 O(n) 복잡도
    public int[] improvedSolution(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        int sum;

        // 왼쪽 및 오른쪽 인덱스부터 좁혀가면서 탐색
        while (left < right) {
            sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                // 합이 target 보다 작은 경우에는 최소값에 해당하는 왼쪽 인덱스 변경
                left++;
            } else {
                // 합이 target 보다 큰 경우에는 최대값에 해당하는 오른쪽 인덱스 변경
                right--;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Prob167TwoSumWithSorted sol = new Prob167TwoSumWithSorted();

        int[][] nums = {
                {2, 7, 11, 15},
                {2, 3, 4},
                {-1, 0}
        };

        int[] targets = {9, 6, -1};

        int[][] ans = {{1, 2}, {1, 3}, {1, 2}};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            int[] mySol = sol.twoSum(nums[i], targets[i]);
            if (Arrays.equals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
