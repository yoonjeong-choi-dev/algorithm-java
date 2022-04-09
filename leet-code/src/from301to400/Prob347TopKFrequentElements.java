package from301to400;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

// https://leetcode.com/problems/top-k-frequent-elements/
public class Prob347TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        return myHeapSolution(nums, k);
    }

    private int[] myHeapSolution(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });

        for (int num : map.keySet()) {
            heap.add(num);
            if(heap.size() > k) heap.poll();
        }

        int[] ans = new int[k];
        int idx = 0;
        while (!heap.isEmpty()) {
            ans[idx++] = heap.poll();
        }

        return ans;
    }

    public static void main(String[] args) {
        Prob347TopKFrequentElements sol = new Prob347TopKFrequentElements();

        int[][] nums = {{1, 1, 1, 2, 2, 3}, {1}};
        int[] k = {2, 1};

        int[][] ans = {{1, 2}, {1}};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            int[] mySol = sol.topKFrequent(nums[i], k[i]);
            if (Arrays.equals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
