package from1301to1400;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/
public class Prob1356SortIntegersByTheNumberOfBits {
    public int[] sortByBits(int[] arr) {
        // Convert to use Comparator
        Integer[] ans = Arrays.stream(arr).boxed().toArray(Integer[]::new);

        Arrays.sort(ans, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int numBit1 = Integer.bitCount(o1);
                int numBit2 = Integer.bitCount(o2);

                if (numBit1 == numBit2) {
                    return o1 - o2;
                } else {
                    return numBit1 - numBit2;
                }
            }
        });


        return Arrays.stream(ans).mapToInt(i -> i).toArray();
    }
}
