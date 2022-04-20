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

    private int len;
    List<List<Integer>> ans;
    int[] nums;
    private List<List<Integer>> recursiveSolution(int[] nums) {
        this.nums = nums;
        len = nums.length;
        ans = new ArrayList<>((int) Math.pow(2, len));

        boolean[] isContained = new boolean[len];
        recurse(0, isContained);
        return ans;
    }

    private void recurse(int curIdx, boolean[] isContained) {
        if(curIdx == len) {
            List<Integer> curAns = new LinkedList<>();
            for(int i=0;i<len;i++){
                if(isContained[i]) curAns.add(nums[i]);
            }
            ans.add(curAns);
            return;
        }

        isContained[curIdx] = true;
        recurse(curIdx+1, isContained);
        isContained[curIdx] = false;
        recurse(curIdx+1, isContained);
    }
}
