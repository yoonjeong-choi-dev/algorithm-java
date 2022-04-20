package from1to100;

import java.util.*;

public class Prob90Subsets2 {
    // 재귀 호출을 위한 변수들
    private int len;
    List<List<Integer>> ans;
    int[] arr;

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        len = countMap.size();
        arr = new int[len];
        int[] count = new int[len];
        int totalCount = 1;

        int curIdx = 0;
        for (int key : countMap.keySet()) {
            arr[curIdx] = key;
            count[curIdx] = countMap.get(key);
            totalCount *= (count[curIdx] + 1);

            curIdx++;
        }


        ans = new ArrayList<>(totalCount);
        recurse(0, count);
        return ans;
    }

    private void recurse(int curIdx, int[] count) {
        if (curIdx == len) {
            List<Integer> curAns = new LinkedList<>();
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < count[i]; j++) {
                    curAns.add(arr[i]);
                }
            }

            ans.add(curAns);
            return;
        }

        int numPossible = count[curIdx];
        for (int i = 0; i <= numPossible; i++) {
            count[curIdx] = i;
            recurse(curIdx + 1, count);
        }
    }

    public static void main(String[] args) {
        Prob90Subsets2 sol = new Prob90Subsets2();
        System.out.println(sol.subsetsWithDup(new int[] {1,2,2}));
    }

}
