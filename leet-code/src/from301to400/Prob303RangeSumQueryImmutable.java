package from301to400;

public class Prob303RangeSumQueryImmutable {
    class NumArray {
        int[] partialSum;
        public NumArray(int[] nums) {
            // Add dummy data to calculate partial sum
            partialSum = new int[nums.length + 1];
            partialSum[0] = 0;

            for (int i = 0; i < nums.length; i++) {
                partialSum[i + 1] = partialSum[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return partialSum[right + 1] - partialSum[left];
        }
    }

    public void test() {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.printf("Result : %d vs Ans : %d\n", numArray.sumRange(0, 2), 1);
        System.out.printf("Result : %d vs Ans : %d\n", numArray.sumRange(2, 5), -1);
        System.out.printf("Result : %d vs Ans : %d\n", numArray.sumRange(0, 5), -3);
    }

    public static void main(String[] args) {
        Prob303RangeSumQueryImmutable sol = new Prob303RangeSumQueryImmutable();
        sol.test();
    }
}
