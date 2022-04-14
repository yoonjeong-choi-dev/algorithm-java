package from1to100;

import java.util.*;

// https://leetcode.com/problems/3sum/
public class Prob15ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        return mySolutionWithTwoSum(nums);
    }

    // Ref : Problem 167 Two Sum
    // => 첫 요소를 선택하면, 문제는 167번 문제와 동일
    private List<List<Integer>> mySolutionWithTwoSum(int[] nums) {
        // Sort the array to make answer not contain duplicate triplets
        Arrays.sort(nums);

        int len = nums.length;

        List<List<Integer>> ans = new LinkedList<>();

        int left, right;
        int curTarget, sum;

        for (int i = 0; i < len - 2; i++) {
            // 이전에 찾은 요소인 경우 무시
            if (i != 0 && nums[i] == nums[i - 1]) continue;

            curTarget = -nums[i];
            left = i + 1;
            right = len - 1;
            while (left < right) {
                sum = nums[left] + nums[right];
                if (sum == curTarget) {
                    // 이후 값들에 대해서도 탐색해야 함
                    ans.add(Arrays.asList(nums[i], nums[left++], nums[right--]));

                    // 탐색할 이후 값에 대해서는 중복이 없어야 하므로 left 및 right 이동
                    while (left < right && nums[left - 1] == nums[left]) left++;
                    while (left < right && nums[right + 1] == nums[right]) right--;
                } else if (sum < curTarget) {
                    left += 1;
                } else {
                    right -= 1;
                }
            }
        }

        return ans;
    }

    // TODO : Without Sort
    // Ref : Problem 1 Two Sum
    private List<List<Integer>> withoutSort(int[] nums) {
        int len = nums.length;

        // 정렬을 하지 못하는 경우에는 Set 자료구조가 필요
        HashSet<List<Integer>> ans = new HashSet<>();

        // remainderMap : (value, index) 형태
        // nums[i] 선택 시, arr[i+1:] 부분 배열에 대한 -nums[i] 타켓의 Two Sum 문제로 변경됨
        // 각 부분 문제에 대한 정보 저장
        Map<Integer, Integer> remainderMap = new HashMap<>(len);
        int remain;

        // 효율적인 탐색을 위해 처음에 뽑는 요소는 중복적으로 탐색하지 않는다
        HashSet<Integer> firstElements = new HashSet<>();
        for (int i = 0; i < len - 2; i++) {
            // 첫번째 요소를 탐색하지 않은 경우에만 탐색
            if (firstElements.add(nums[i])) {

                // Problem 1 Two Sum 과 동일
                for (int j = i + 1; j < len; j++) {
                    remain = -nums[i] - nums[j];
                    // nums[i] 를 선택했을 때, arr[i+1:] 부분 배열에서 remain 을 검색한 경우
                    if (remainderMap.containsKey(remain) && remainderMap.get(remain) == i) {
                        List<Integer> curAns = Arrays.asList(nums[i], nums[j], remain);
                        Collections.sort(curAns);
                        ans.add(curAns);
                    }

                    // nums[i] 를 선택했을 때, arr[j] 검색 완료
                    remainderMap.put(nums[j], i);
                }
            }
        }

        return new ArrayList<>(ans);
    }


    public static void main(String[] args) {
        Prob15ThreeSum sol = new Prob15ThreeSum();
        int[][] nums = {
                {-1, 0, 1, 2, -1, -4},
                {},
                {0},
                {3, 0, -2, -1, 1, 2},
                {-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4}
        };

        int[][][] ansArr = {
                {{-1, -1, 2}, {-1, 0, 1}},
                {},
                {},
                {{-2, -1, 3}, {-2, 0, 2}, {-1, 0, 1}},
                {{-4, 0, 4}, {-4, 1, 3}, {-3, -1, 4}, {-3, 0, 3}, {-3, 1, 2}, {-2, -1, 3}, {-2, 0, 2}, {-1, -1, 2}, {-1, 0, 1}}
        };

        List<List<List<Integer>>> ans = new ArrayList<>();
        for (int[][] arr2 : ansArr) {
            List<List<Integer>> curAns = new ArrayList<>();
            for (int[] arr : arr2) {
                List<Integer> curRow = new ArrayList<>();
                for (int num : arr) curRow.add(num);

                curAns.add(curRow);
            }
            ans.add(curAns);
        }

        int numProblems = ansArr.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            List<List<Integer>> mySol = sol.threeSum(nums[i]);
            if (mySol.equals(ans.get(i))) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans.get(i));
            }
        }

    }
}
