package programmers.level2;

import java.util.HashMap;

// https://programmers.co.kr/learn/courses/30/lessons/1835
public class Prob24KakaoPicture {

    private String[] data;
    private final String[] characters = {"A", "C", "F", "J", "M", "N", "R", "T"};
    private final int characterNum = characters.length;
    private HashMap<String, Integer> posInfo;

    public int solution(int n, String[] data) {
        this.data = data;
        posInfo = new HashMap<>();
        for (int i = 0; i < characterNum; i++) {
            posInfo.put(characters[i], -1);
        }

        return possibleNumber(0);
    }

    // 총 8명을 배치하는 것이므로 전체 탐색 가능(8!)
    private int possibleNumber(int step) {
        // 순열을 만든 경우 => 테스트
        if (step == characterNum) {

            boolean isPossible = true;
            for (String d : data) {
                String c1 = d.substring(0,1), c2 = d.substring(2,3);
                int diff = Math.abs(posInfo.get(c1) - posInfo.get(c2)) - 1;
                char cond = d.charAt(3);
                int num = Character.getNumericValue(d.charAt(4));
                if (cond == '=') {
                    if (diff != num) {
                        isPossible = false;
                        break;
                    }
                } else if (cond == '<') {
                    if (diff >= num) {
                        isPossible = false;
                        break;
                    }
                } else {
                    if (diff <= num) {
                        isPossible = false;
                        break;
                    }
                }
            }

            return isPossible ? 1 : 0;
        }

        // 현재 배치 시킬 캐릭터 선택 후 재귀호출
        int ans = 0;
        for (int i = 0; i < characterNum; i++) {
            if (posInfo.get(characters[i]) < 0) {
                posInfo.put(characters[i], step);
                ans += possibleNumber(step + 1);
                posInfo.put(characters[i], -1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Prob24KakaoPicture sol = new Prob24KakaoPicture();
        int[] n = {2, 2};
        String[][] data = {{"N~F=0", "R~T>2"}, {"M~C<2", "C~M>1"}};

        int[] ans = {3648, 0};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            int mySol = sol.solution(n[i], data[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
