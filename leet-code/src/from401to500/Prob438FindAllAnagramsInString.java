package from401to500;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/find-all-anagrams-in-a-string/
public class Prob438FindAllAnagramsInString {
    public List<Integer> findAnagrams(String string, String target) {
        int lenTarget = target.length();
        int len = string.length();

        if (lenTarget > len) return new ArrayList<>();

        //  문자열들은 소문자로만 구성
        int[] targetOccur = new int[26];
        int[] stringOccur = new int[26];

        for (int i = 0; i < lenTarget; i++) {
            targetOccur[target.charAt(i) - 'a']++;

            // string.substr(0, lenTarget) 에 대한 정보 업데이트
            stringOccur[string.charAt(i) - 'a']++;
        }

        // 두 배열에서 각 문자가 발생한 숫자가 같은 개수 저장
        // => 26 인 경우, target 과 string 부분 문자열이 매칭된다고 판단 가능
        int numSameCharOccur = 0;
        for (int i = 0; i < 26; i++) {
            if (targetOccur[i] == stringOccur[i]) numSameCharOccur++;
        }

        // 처음 부분 문자열에 대한 테스트
        List<Integer> ans = new LinkedList<>();
        if (numSameCharOccur == 26) {
            ans.add(0);
        }

        int remove, add;
        for (int startIdx = 0; startIdx < len - lenTarget; startIdx++) {
            // 이전 요소 삭제 및 다음 요소 추가
            remove = string.charAt(startIdx) - 'a';
            add = string.charAt(startIdx + lenTarget) - 'a';

            // 삭제하는 요소와 추가하는 요소가 다른 경우만 체크
            if(remove != add) {
                stringOccur[remove]--;
                stringOccur[add]++;

                // numSameCharOccur 업데이트
                if (stringOccur[remove] == targetOccur[remove]) {
                    // 삭제해서 같아진 경우
                    numSameCharOccur++;
                } else if (stringOccur[remove] +1 == targetOccur[remove]) {
                    // 삭제해서 달라진 경우
                    numSameCharOccur--;
                }

                if (stringOccur[add] == targetOccur[add]) {
                    // 추가해서 같아진 경우
                    numSameCharOccur++;
                } else if (stringOccur[add] == targetOccur[add] + 1) {
                    // 추가해서 달라진 경우
                    numSameCharOccur--;
                }
            }
            if (numSameCharOccur == 26) {
                ans.add(startIdx+1);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Prob438FindAllAnagramsInString sol = new Prob438FindAllAnagramsInString();

        String[] strings = {"cbaebabacd", "abab"};
        String[] targets = {"abc", "ab"};

        int[][] ansArr = {{0, 6}, {0, 1, 2}};
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] arr : ansArr) {
            List<Integer> list = new ArrayList<>(arr.length);
            for (int n : arr) list.add(n);
            ans.add(list);
        }
        int numProblems = ansArr.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("=================Problem %d : %s\n", i, strings[i]);
            List<Integer> mySol = sol.findAnagrams(strings[i], targets[i]);
            if (ans.get(i).equals(mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans.get(i));
            }
        }
    }
}
