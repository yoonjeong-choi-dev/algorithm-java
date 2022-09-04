package from701to800;

// https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
public class Prob768MaxChucksToMakeSorted2 {
    public int maxChunksToSorted(int[] arr) {
        int len = arr.length;

        // leftMax[i] : [0:i] 중 가장 큰 값
        int[] leftMax = new int[len];
        leftMax[0] = arr[0];
        for (int i = 1; i < len; i++) leftMax[i] = Math.max(leftMax[i - 1], arr[i]);

        // rightMin[i] : [i:len-1] 중 가장 작은 값
        int[] rightMin = new int[len];
        rightMin[len - 1] = arr[len - 1];
        for (int i = len - 2; i >= 0; i--) rightMin[i] = Math.min(rightMin[i + 1], arr[i]);

        int ans = 0;
        for (int i = 0; i < len - 1; i++) {
            // ...i, i+1.... 기준으로 쪼갤 수 있는 경우
            if (leftMax[i] <= rightMin[i + 1]) {
                ans++;
            }
        }

        return ans + 1;
    }
}
