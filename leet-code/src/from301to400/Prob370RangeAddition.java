package from301to400;

// https://leetcode.com/problems/range-addition/
public class Prob370RangeAddition {
    public int[] getModifiedArray(int length, int[][] updates) {

        // ans[i] = diff[i] : partialSum[i-1]
        // updates[i] = {start, end, value}
        // start ~ len 까지 모두 영향을 받는다고 가정
        // => end+1 에 value 를 빼줘서 end+1 이후 값에 대한 영향을 주지 않도록 함
        int[] diff = new int[length];
        for (int[] data : updates) {
            diff[data[0]] += data[2];
            if (data[1] + 1 < length) diff[data[1] + 1] -= data[2];
        }

        int[] ans = new int[length];
        ans[0] = diff[0];
        for (int i = 1; i < length; i++) ans[i] = ans[i - 1] + diff[i];

        return ans;
    }
}
