package from1601to1700;

// https://leetcode.com/problems/richest-customer-wealth/
public class Prob1672RichestCustomerWealth {
    public int maximumWealth(int[][] accounts) {
        int maxAccount = 0, curAccount;
        for (int[] account : accounts) {
            curAccount = 0;
            for (int a : account) curAccount += a;

            if (maxAccount < curAccount) maxAccount = curAccount;
        }

        return maxAccount;
    }
}
