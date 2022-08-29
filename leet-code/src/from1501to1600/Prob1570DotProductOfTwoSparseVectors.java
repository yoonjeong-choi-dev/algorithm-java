package from1501to1600;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
public class Prob1570DotProductOfTwoSparseVectors {
    class SparseVector {

        Map<Integer, Integer> nonzeroIndex;
        int size;

        SparseVector(int[] nums) {
            nonzeroIndex = new HashMap<>();
            size = nums.length;

            for (int i = 0; i < size; i++) {
                if (nums[i] != 0) nonzeroIndex.put(i, nums[i]);
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            Map<Integer, Integer> targetMap = vec.nonzeroIndex;
            Set<Integer> targetIdx = targetMap.keySet();

            int ans = 0;
            for (int idx : nonzeroIndex.keySet()) {
                if (targetIdx.contains(idx)) {
                    ans += nonzeroIndex.get(idx) * targetMap.get(idx);
                }
            }
            return ans;
        }
    }
}
