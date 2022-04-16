package from1to100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/subsets/
// Ref : Problem 784
public class Prob78Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        int len = nums.length;
        // 특정 인덱스에 대한 요소를 포함하는지 여부를 비트로 표현 가능
        int totalSize = (int) Math.pow(2, len);

        List<List<Integer>> ans = new ArrayList<>(totalSize);

        int curBit, curIdx;
        List<Integer> curAns;
        for (int i = 0; i < totalSize; i++) {

            // 현재 숫자의 비트 중에 1 인 값들만 포함시킨다
            curIdx = 0;
            curBit = i;
            curAns = new LinkedList<>();
            while (curBit != 0) {
                if ((curBit & 1) == 1) {
                    curAns.add(nums[curIdx]);
                }

                curIdx++;
                curBit = curBit >> 1;
            }

            ans.add(curAns);
        }

        return ans;
    }
}
