package from1101to1200;

import java.util.Arrays;

// https://leetcode.com/problems/how-many-apples-can-you-put-into-the-basket/
public class Prob1196HowManyApplesCanYouPutIntoTheBasket {
    public int maxNumberOfApples(int[] weight) {
        final int MAX_WEIGHT = 5000;

        // 내림차순으로 정렬 : 작은 무게의 사과를 우선 담는다
        Arrays.sort(weight);

        int ans = 0, curWeight = 0;
        for (int w : weight) {
            curWeight += w;

            if (curWeight <= MAX_WEIGHT) ans++;
            else break;
        }
        return ans;
    }
}
