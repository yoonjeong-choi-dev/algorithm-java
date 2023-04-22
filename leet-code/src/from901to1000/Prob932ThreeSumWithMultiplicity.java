package from901to1000;

// https://leetcode.com/problems/3sum-with-multiplicity/
public class Prob932ThreeSumWithMultiplicity {
    public int threeSumMulti(int[] arr, int target) {
        // Condition : 0 <= arr[i] <= 100
        int maxElem = arr[0];
        for(int num : arr) {
            if(maxElem < num) maxElem = num;
        }

        int[] countMap = new int[maxElem+1];
        return 0;
    }
}
