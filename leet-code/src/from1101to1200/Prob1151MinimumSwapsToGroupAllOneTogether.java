package from1101to1200;

// https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/
public class Prob1151MinimumSwapsToGroupAllOneTogether {
    public int minSwaps(int[] data) {
        int numOnes = 0;
        for (int d : data) numOnes += d;

        // 최소한 1을 조금 움직여야 함
        // => (i,i+numOnes-1) 범위 안에 1이 가장 많이 있는 i 를 탐색해야 함
        int maxOnes = 0;
        for (int i = 0; i < numOnes; i++) maxOnes += data[i];

        int curOnes = maxOnes;
        for (int i = numOnes; i < data.length; i++) {
            // i 추가 및 i-numsOnes 감소
            curOnes += data[i] - data[i - numOnes];
            maxOnes = Math.max(maxOnes, curOnes);
        }

        // 1이 가장 많이 나오는 영역의 0의 개수만큼 교환하면 됨
        return numOnes - maxOnes;
    }
}
