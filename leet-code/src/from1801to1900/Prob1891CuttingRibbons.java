package from1801to1900;

// https://leetcode.com/problems/cutting-ribbons/
public class Prob1891CuttingRibbons {
    public int maxLength(int[] ribbons, int k) {
        int left = 1, right = 0;

        // 결과 리본들 중 가장 큰 값은 현재 리본들 중 가장 큰 값
        for (int ribbon : ribbons) right = Math.max(right, ribbon);

        // Binary Search
        int mid, numRibbon;
        int ans = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;

            // mid 값을 갖는 리본의 개수
            numRibbon = 0;
            for (int ribbon : ribbons) numRibbon += ribbon / mid;

            if (numRibbon >= k) {
                // 정답 업데이트
                ans = mid;

                // 최대 값을 찾아야 하므로, 리본 크기 범위를 키운다
                left = mid + 1;
            } else {
                // 리본 개수가 작으므로, 리본 크기를 줄여야 함
                right = mid - 1;
            }
        }

        return ans;
    }
}
