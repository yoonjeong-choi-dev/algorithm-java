package programmers.level2;

import java.util.*;

// https://programmers.co.kr/learn/courses/30/lessons/72411
public class Prob38KakaoMenuRenewal {

    private final Set<Character> chosen = new TreeSet<>();
    private final List<String> candidates = new LinkedList<>();
    private String curOrder;

    public String[] solution(String[] orders, int[] course) {

        // 각 주문으로 만들 수 있는 코스 요리의 횟수
        Map<String, Integer> courseCount = new HashMap<>();

        // 각 코스 요리 갯수의 최대 주문 횟수
        Map<Integer, Integer> courseMax = new HashMap<>();
        int curCount, max;

        // 각 주문에 대한 조합 탐색
        for (String order : orders) {
            curOrder = order;

            for (int courseNum : course) {
                max = courseMax.getOrDefault(courseNum, 0);
                chosen.clear();
                candidates.clear();
                findAllCombination(0, courseNum);


                for (String candidate : candidates) {
                    curCount = courseCount.getOrDefault(candidate, 0) + 1;
                    max = Math.max(max, curCount);
                    courseCount.put(candidate, curCount);
                }

                courseMax.put(courseNum, max);
            }
        }

        List<String> ans = new ArrayList<>();
        int curScore, curMaxScore;
        for (String candidate : courseCount.keySet()) {
            curScore = courseCount.get(candidate);
            curMaxScore = courseMax.get(candidate.length());
            if (curMaxScore >= 2 && curScore == curMaxScore) {
                ans.add(candidate);
            }
        }

        Collections.sort(ans);
        return ans.toArray(new String[0]);
    }

    private void findAllCombination(int curIdx, int remain) {
        if (remain == 0) {
            StringBuilder sb = new StringBuilder();
            for (Character c : chosen) {
                sb.append(c);
            }
            candidates.add(sb.toString());
            return;
        } else if (curIdx == curOrder.length()) return;

        // curIdx Pass or Add
        findAllCombination(curIdx + 1, remain);

        chosen.add(curOrder.charAt(curIdx));
        findAllCombination(curIdx + 1, remain - 1);
        chosen.remove(curOrder.charAt(curIdx));
    }

    public static void main(String[] args) {
        Prob38KakaoMenuRenewal sol = new Prob38KakaoMenuRenewal();

        String[][] orders = {
                {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"},
                {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"},
                {"XYZ", "XWY", "WXA"},
                {"ABCD", "ABCD", "ABCD"}
        };
        int[][] courses = {
                {2, 3, 4},
                {2, 3, 5},
                {2, 3, 4},
                {2,3,4}
        };

        String[][] ans = {
                {"AC", "ACDE", "BCFG", "CDE"},
                {"ACD", "AD", "ADE", "CD", "XYZ"},
                {"WX", "XY"},
                {"AB", "ABC", "ABCD", "ABD", "AC", "ACD", "AD", "BC", "BCD", "BD", "CD"}
        };
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d\n", i);
            String[] mySol = sol.solution(orders[i], courses[i]);
            if (Arrays.equals(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + Arrays.toString(mySol));
                System.out.println("Expected : " + Arrays.toString(ans[i]));
            }
        }
    }
}
