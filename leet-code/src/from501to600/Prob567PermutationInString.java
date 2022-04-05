package from501to600;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

// https://leetcode.com/problems/permutation-in-string/
public class Prob567PermutationInString {


    public boolean checkInclusion(String target, String string) {
        //return mySquareSolution(target, string);
        return improvedSolution(target, string);
    }

    // Trial 1 : O(n * m) = O(n*n) 복잡도의 해결 방법 where n = len(string), m = len(target)
    // Runtime: 1503 ms, faster than 6.62% of Java online submissions for Permutation in String
    private boolean mySquareSolution(String target, String string) {
        int lenTarget = target.length();
        int len = string.length();

        if (lenTarget > len) return false;

        // targetOccur : target 문자열을 구성하는 문자 개수
        HashMap<Character, Integer> targetOccur = new HashMap<>(lenTarget);
        char curChar;
        for (int i = 0; i < lenTarget; i++) {
            curChar = target.charAt(i);
            if (!targetOccur.containsKey(curChar)) {
                targetOccur.put(curChar, 1);
            } else {
                targetOccur.put(curChar, targetOccur.get(curChar) + 1);
            }
        }

        // target 길이의 부분 문자열에 대해서 target 문자열과 같은 문자열을 구성하는지 여부 확인
        boolean isEqual;
        HashMap<Character, Integer> stringOccur = new HashMap<>(lenTarget);
        for (int startIdx = 0; startIdx <= len - lenTarget; startIdx++) {
            stringOccur.clear();

            for (int i = 0; i < lenTarget; i++) {
                curChar = string.charAt(startIdx + i);
                if (!stringOccur.containsKey(curChar)) {
                    stringOccur.put(curChar, 1);
                } else {
                    stringOccur.put(curChar, stringOccur.get(curChar) + 1);
                }
            }

            isEqual = true;
            for (Character ch : targetOccur.keySet()) {
                if (!stringOccur.containsKey(ch) || !Objects.equals(stringOccur.get(ch), targetOccur.get(ch))) {
                    isEqual = false;
                    break;
                }
            }

            if (isEqual) {
                return true;
            }
        }

        return false;
    }

    // TODO : Improve Runtime
    private boolean improvedSolution(String target, String string) {
        int lenTarget = target.length();
        int len = string.length();

        if (lenTarget > len) return false;

        // HashMap 을 사용하는 대신 배열 사용 : 문자열들은 소문자로만 구성
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

        int remove, add;
        for (int startIdx = 0; startIdx < len - lenTarget; startIdx++) {
            if (numSameCharOccur == 26) return true;

            // startIdx 문자 제거 및 startIdx + lenTarget 문자 추가
            remove = string.charAt(startIdx) - 'a';
            add = string.charAt(startIdx + lenTarget) - 'a';

            // 삭제하는 문자와 추가하는 문자가 같은 경우에는 따로 처리할 필요없음
            // 반드시 필요한 구문
            // why? numSameCharOccur 업데이트 부분에서 중복되게 처리되어 -2 or +2가 되는 현상 발생
            if(remove == add) continue;

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

        return numSameCharOccur == 26;
    }

    public static void main(String[] args) {
        Prob567PermutationInString sol = new Prob567PermutationInString();

        String[] targets = {"ab", "ab", "adc", "abc", "trinitrophenylmethylnitramine"};
        String[] strings = {"eidbaooo", "eidboaoo", "dcda", "bbbca", "dinitrophenylhydrazinetrinitrophenylmethylnitramine"};

        boolean[] ans = {true, false, true, true, true};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            boolean mySol = sol.checkInclusion(targets[i], strings[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
