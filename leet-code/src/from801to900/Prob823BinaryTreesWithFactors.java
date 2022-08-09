package from801to900;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/binary-trees-with-factors/
public class Prob823BinaryTreesWithFactors {
    public int numFactoredBinaryTrees(int[] arr) {
        final int MOD = 1000000007;

        Arrays.sort(arr);

        // dp[i] : i 값을 루트로 갖는 트리의 개수
        Map<Integer, Long> dp = new HashMap<>();
        for (int val : arr) dp.put(val, 1L);

        int root, left, right;
        long ret;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            root = arr[i];

            for (int j = 0; j < i; j++) {
                left = arr[j];
                if (root % left == 0) {
                    right = root / left;
                    if (dp.containsKey(right)) {
                        ret = (dp.get(left) * dp.get(right)) % MOD;
                        ret = (ret + dp.get(root)) % MOD;
                        dp.put(root, ret);
                    }
                }
            }
        }


        long ans = 0;
        for (long val : dp.values()) {
            ans = (ans + val) % MOD;
        }

        return (int) ans;
    }
}
