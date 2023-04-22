package from1601to1700;

// https://leetcode.com/problems/count-of-matches-in-tournament/
public class Prob1688CountOfMatchesInTournament {
    public int numberOfMatches(int n) {
        // 한 명이 우승하기 위해서는 n-1 명이 패배해야 함
        // 토너먼트이기 때문에 한 경기 당 1명이 패배
        // => n-1 패배 -> n-1 경기
        return n-1;
    }
}
