package from1401to1500;

// https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
public class Prob1423MaximumPointsYouCanObtainFromCards {
    public int maxScore(int[] cardPoints, int k) {
        int numCards = cardPoints.length;

        int totalSum = 0;
        for(int card : cardPoints) totalSum += card;

        if(numCards == k) return totalSum;

        // 뽑지 않는 카드들은 윈도우를 형성
        final int windowSize = numCards - k;
        int curSum = 0;
        for(int i=0;i<windowSize;i++) curSum += cardPoints[i];

        int minSum = curSum;
        for(int i=windowSize;i<numCards;i++){
            // i 번째 카드 추가
            curSum -= cardPoints[i-windowSize];
            curSum += cardPoints[i];

            minSum = Math.min(minSum, curSum);
        }

        return totalSum - minSum;
    }
}
