package from1501to1600;

// https://leetcode.com/problems/minimum-operations-to-make-array-equal/
public class Prob1551MinimumOperationsToMakeArrayEquals {
    public int minOperations(int n) {
        // Idea : 양 끝들(i,n-i-1)에 대해서 타겟값이 되도록 수행
        // n is even(2m) : 타겟값은 평균값인 n
        // => (n-1) + (n-3) + .. + 1 =  n*m/2 = n/2 * n/2 = n*n/4
        // n is odd(2m+1) : 타겟값은 미디안인 2m+1. 이 때 미디안에 해당하는 arr[m]은 수정하지 않음
        // => 2m + 2(m-1) + ... + 2 = (2m+2)*m/2 = m*(m+1) = (n^2-1)/4
        return n % 2 == 0 ? n * n / 4 : (n * n - 1) / 4;
    }
}
