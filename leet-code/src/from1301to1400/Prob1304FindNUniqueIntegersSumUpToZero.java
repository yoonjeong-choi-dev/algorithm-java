package from1301to1400;

// https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
public class Prob1304FindNUniqueIntegersSumUpToZero {
    public int[] sumZero(int n) {
        int[] ans = new int[n];

        // 1, 2, ..., largest 및 음수 저장
        // n 이 홀수인 경우에는 마지막에 0 추가
        int largest = n / 2;
        for (int i = 1; i <= largest; i++) {
            ans[i * 2 - 2] = i;
            ans[i * 2 - 1] = -i;
        }
        return ans;
    }
}
