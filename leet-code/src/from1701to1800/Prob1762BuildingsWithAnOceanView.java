package from1701to1800;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/buildings-with-an-ocean-view/
public class Prob1762BuildingsWithAnOceanView {
    public int[] findBuildings(int[] heights) {
        int numBuildings = heights.length;

        // right[i] : nums[i+1:] 중 최대 값
        int[] right = new int[numBuildings];
        right[numBuildings - 1] = 0;

        for (int i = numBuildings - 2; i >= 0; i--) {
            right[i] = Math.max(heights[i + 1], right[i + 1]);
        }

        List<Integer> ansList = new ArrayList<>();
        for (int i = 0; i < numBuildings; i++) {
            if (heights[i] > right[i]) ansList.add(i);
        }

        int[] ans = new int[ansList.size()];
        for (int i = 0; i < ans.length; i++) ans[i] = ansList.get(i);
        return ans;
    }
}
