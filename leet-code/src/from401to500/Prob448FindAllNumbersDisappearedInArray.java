package from401to500;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
public class Prob448FindAllNumbersDisappearedInArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int size = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        List<Integer> ans = new ArrayList<>(size - set.size());
        for (int i = 1; i <= size; i++) {
            if (!set.contains(i)) ans.add(i);
        }

        return ans;
    }

    private List<Integer> solutionWithConstantMemory(int[] nums) {
        int size = nums.length;

        // nums[i] = i+1 형태로 변형이 필요
        // nums[i] -> nums[i] * -1 : nums 배열에 i+1 이 있는 경우
        int numVisited = 0;
        int curNum;
        for (int i = 0; i < size; i++) {
            curNum = Math.abs(nums[i]) - 1;

            // curNum 방문을 하지 않은 경우 방문
            if (nums[curNum] > 0) {
                nums[curNum] *= -1;
                numVisited++;
            }
        }

        // 방문되지 않은 숫자들 저장
        List<Integer> ans = new ArrayList<>(size - numVisited);
        for (int i = 0; i < size; i++) {
            if (nums[i] > 0) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
