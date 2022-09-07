package from1601to1700;

// https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/
public class Prob1653MinimumDeletionsToMakeStringBalanced {
    public int minimumDeletions(String s) {
        int len = s.length();

        if (len == 1) return 0;

        // leftB[i] : 0,...,(i-1) 중에 B 의 개수
        int[] leftB = new int[len];
        for (int i = 1; i < len; i++) {
            leftB[i] = leftB[i - 1];
            if (s.charAt(i - 1) == 'b') {
                leftB[i] += 1;
            }
        }

        // rightA[i] : i+1,..,len-1 중에 A의 개수
        int[] rightA = new int[len];
        for (int i = len - 2; i >= 0; i--) {
            rightA[i] = rightA[i + 1];
            if (s.charAt(i + 1) == 'a') {
                rightA[i] += 1;
            }
        }

        int ans = len;
        for (int i = 0; i < len; i++) {
            // s[i] == A : 마지막 A 라고 가정 => 왼쪽 B 및 오른쪽 A 삭제
            // s[i] == B : 첫번째 B 라고 가정 => 왼쪽 B 및 오른쪽 A 삭제
            ans = Math.min(ans, leftB[i] + rightA[i]);
        }
        return ans;
    }
}
