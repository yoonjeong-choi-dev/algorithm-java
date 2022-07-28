package from701to800;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/find-anagram-mappings/
public class Prob760FindAnagramMappings {
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        int len = nums1.length;
        int[] ans = new int[len];

        // nums2 : (val -> index)
        Map<Integer, Integer> valToIndex = new HashMap<>();
        for(int i=0;i<len;i++){
            valToIndex.put(nums2[i], i);
        }

        for(int i=0;i<len;i++){
            ans[i] = valToIndex.get(nums1[i]);
        }

        return ans;
    }
}
