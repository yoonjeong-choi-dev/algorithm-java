package from1501to1600;

// https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/
public class Prob1523CountOddNumber {
    public int countOdds(int low, int high) {
        if (low % 2 == 0) low++;
        if (high % 2 == 0) high--;

        if (low > high) return 0;

        return (high - low) / 2 + 1;
    }
}
