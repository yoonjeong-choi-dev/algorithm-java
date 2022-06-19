package from101to200;

// https://leetcode.com/problems/gas-station/
public class Prob134GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int numStations = gas.length;

        // 0 -> N-1 까지 전체 순회 시, 남는 가스 양 추적
        // => 음수인 경우에는 정답이 없음
        int totalSum = 0;

        // totalSum >=0 인 경우, 시작 지점 및 가스 양
        int startPoint = 0;
        int curSum = 0;

        for (int i = 0; i < numStations; i++) {
            // gas[i] - cost[i] : i -> i+1 이동 시 가스 변화량
            totalSum += gas[i] - cost[i];
            curSum += gas[i] - cost[i];

            // startPoint -> i+1 이동이 불가능
            // startPoint -> i 까지 이동은 가능 => i+1부터 다시 시작
            // i에 대해서 루프를 돌기 때문에 최종적으로 startPoint -> 0까지 이동이 가능
            if (curSum < 0) {
                curSum = 0;
                startPoint = i + 1;
            }
        }


        /*
         totalSum >=0 인 경우, startPoint 검증
          1) startPoint == 0 : 모든 i에 대해서 (curSum >=0) 이므로 0에서 시작하면 끝
          2) startPoint >0 : for 루프에 의해서 startPoint -> 0 까지 이동 가능
           => 0 -> startPoint-1 까지 이동이 가능한지만 확인하면 됨
          귀류법 : 0 < k < startPoint 에 대해서 startPoint -> k 가 불가능한 k 가 존재한다고 가정
          - sum(i,j) = (gas[i]-cost[i]) + (gas[i+1]-cost[i+1]) + .. + (gas[j]-cost[j]) 로 정의
          - 가정 1 : sum(0, N) >= 0 where N+1 == 주유소 개수
          - sum(0,N) = sum(0,k) + sum(k+1, startPoint -1) + sum(startPoint, N)
          - 여기서 startPoint 찾는 과정에 의해
            - sum(startPoint, N)>=0
            - sum(k+1, startPoint -1) > 0 이면, sum(k+1, N) > 0
              => startPoint 는 이전에 발견되는 것이므로 모순
              => sum(k+1, startPoint -1) <= 0 ------------------------------->
          - 귀류법 가정에 의해, sum(0,k) + sum(startPoint, N) < 0
            => sum(k+1, startPoint -1) > 0 ----------------------------------> 모순!
        */
        return totalSum < 0 ? -1 : startPoint;
    }
}
