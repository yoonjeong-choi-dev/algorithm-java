package from1001to1100;

// https://leetcode.com/problems/best-sightseeing-pair/
public class Prob1014BestSightseeingPair {
    public int maxScoreSightseeingPair(int[] values) {
        int start = 0, end = 1;

        // 이득인 시점
        // start -> i : values[start] <= values[i]
        // end -> i : values[start] + values[end] + start - end
        int startUpper;
        for (int i = 2; i < values.length; i++) {
            // 현재 인덱스를 끝점으로 변경 가능한지 여부 확인
            if (values[end] - end <= values[i] - i) {
                end = i;
            }

            if (end == i) {
                // start 인덱스를 이동 가능할 때까지 이동
                startUpper = end - 1;
                while (start < startUpper && values[start] <= values[start + 1]) start++;
            }
        }



        return values[start] + values[end] + start - end;
    }
}
