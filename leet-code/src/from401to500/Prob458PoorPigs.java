package from401to500;

// https://leetcode.com/problems/poor-pigs/
public class Prob458PoorPigs {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        // 필요한 돼지 수를 p 라고, 테스트 횟수를 n 이라 하자
        // p 차원의 길이가 n+1 인 배열을 생각 i.e (n+1)^p
        // 각 테스트마다 한 차원에 있는 n+1 개의 먹이를 각 돼지에게 준다. 이때 한 방향(차원)의 길이는 n+1
        // 모든 돼지가 죽엇을 때의 좌표를 구하면 됨.
        // i.e i번째 돼지가 x_i 테스트에서 죽은 경우, (x_1, x_2, ..., x_p) 좌표에 해당하는 먹이가 독이 들어 있는 것
        // 모든 돼지가 다 살아난 경우에는 맨 마지막 먹이가 독이 들어 있음
        // => 따라서, 최대로 테스트 가능한 먹이의 개수는 N = (n+1)^p <=> p = log(N) / log(n+1)
        int n = minutesToTest / minutesToDie + 1;
        return (int) Math.ceil(Math.log(buckets) / Math.log(n));
    }
}
