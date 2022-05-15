package from1to100;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/minimum-window-substring/
// TODO : 다시 풀기
public class Prob76MinimumWindowSliding {
    public String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) return "";

        int strLen = s.length();
        int targetLen = t.length();

        Map<Character, Integer> targetCounter = new HashMap<>();
        for (int i = 0; i < targetLen; i++) {
            targetCounter.put(t.charAt(i), targetCounter.getOrDefault(t.charAt(i), 0) + 1);
        }

        Map<Character, Integer> windowCounter = new HashMap<>();

        // 현재 윈도우의 문자들 중 타겟 문자열의 문자 출현횟수보다 크거나 같은 문자 개수
        // => 두 변수가 같으면, 해당 윈도우 안에 타겟이 모두 포함됨
        final int distinctTargetNum = targetCounter.size();
        int distinctWindowNum = 0;

        int left = 0, right = 0;

        // 최적의 윈도우 정보 : 윈도우 크기, 시작 인덱스, 끝 인덱스
        int[] bestWindowInfo = {-1, 0, 0};

        char curChar;
        while (right < strLen) {
            // right 번째 문자 추가
            curChar = s.charAt(right);

            // 현재 윈도우 업데이트
            windowCounter.put(curChar, windowCounter.getOrDefault(curChar, 0));

            // 추가해서 해당 문자 출현 횟수가 같아지는 경우 => 일치 개수 +1
            if (targetCounter.containsKey(curChar)
                    && targetCounter.get(curChar).intValue() == windowCounter.get(curChar).intValue()) {
                distinctWindowNum++;
            }

            // 최소 윈도우의 크기를 구하기 위해 문자 출현횟수가 같은 경우 시작인덱스를 이동
            while (left <= right && distinctTargetNum == distinctWindowNum) {
                // left 번째 문자 삭제
                curChar = s.charAt(left);

                // 최적의 윈도우 정보 업데이트
                if (bestWindowInfo[0] == -1 || right - left + 1 < bestWindowInfo[0]) {
                    bestWindowInfo[0] = right - left + 1;
                    bestWindowInfo[1] = left;
                    bestWindowInfo[2] = right;
                }

                // 현재 윈도우 업데이트
                windowCounter.put(curChar, windowCounter.get(curChar) - 1);

                // 삭제해서 해당 문자 출현 횟수가 타겟보다 작아지는 경우 => 일치 개수 -1
                if (targetCounter.containsKey(curChar)
                        && targetCounter.get(curChar) > windowCounter.get(curChar)) {
                    distinctWindowNum--;
                }

                left++;
            }


            right++;
        }


        return bestWindowInfo[0] == -1 ? "" : s.substring(bestWindowInfo[1], bestWindowInfo[2] + 1);
    }
}
