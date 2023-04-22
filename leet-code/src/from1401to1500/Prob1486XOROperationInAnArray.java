package from1401to1500;

// https://leetcode.com/problems/xor-operation-in-an-array/
public class Prob1486XOROperationInAnArray {
    public int xorOperation(int n, int start) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = ans ^ start;
            start += 2;
        }
        return ans;
    }
}
