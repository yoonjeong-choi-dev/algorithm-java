package programmers.level2;

import java.util.HashMap;

// https://programmers.co.kr/learn/courses/30/lessons/17677
public class Prob5KakaoNewClustering {
    public int solution(String str1, String str2) {
        // 대소문자 무시
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // 각 문자열에 대해서 단어 추출
        HashMap<String, Integer> set1 = makeMultipleSet(str1);
        HashMap<String, Integer> set2 = makeMultipleSet(str2);

        // 합집합 및 교집합을 계산 하기위해 두 맵의 카 동기화
        for (String key : set1.keySet()) {
            if (!set2.containsKey(key)) {
                set2.put(key, 0);
            }
        }
        for (String key : set2.keySet()) {
            if (!set1.containsKey(key)) {
                set1.put(key, 0);
            }
        }

        // 합집합 및 교집합 크기 계산
        int intersection = 0, union = 0;
        for (String key : set1.keySet()) {
            intersection += Math.min(set1.get(key), set2.get(key));
            union += Math.max(set1.get(key), set2.get(key));
        }

        double similarity;
        if (union == 0) {
            // 양쪽 다 공집합인 경우 1
            similarity = 1;
        } else {
            similarity = (double) intersection / union;
        }

        return (int) (similarity * 65536);
    }

    private HashMap<String, Integer> makeMultipleSet(String str) {
        HashMap<String, Integer> ret = new HashMap<>(str.length() - 1);
        for (int i = 0; i < str.length() - 1; i++) {
            // 두 문자 모두 알파벳인 경우만
            if (isAlphabet(str.charAt(i)) && isAlphabet(str.charAt(i + 1))) {
                String word = new String(new char[]{str.charAt(i), str.charAt(i + 1)});

                if (!ret.containsKey(word)) {
                    ret.put(word, 1);
                } else {
                    ret.put(word, ret.get(word) + 1);
                }
            }
        }

        return ret;
    }

    private boolean isAlphabet(char c) {
        return c >= 'a' && c <= 'z';
    }


    public static void main(String[] args) {
        Prob5KakaoNewClustering sol = new Prob5KakaoNewClustering();
        String[] str1 = {"FRANCE", "handshake", "aa1+aa2", "E=M*C^2"};
        String[] str2 = {"french", "shake hands", "AAAA12", "e=m*c^2"};

        int[] ans = {16384, 65536, 43690, 65536};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d\n", i);
            int mySol = sol.solution(str1[i], str2[i]);
            if (ans[i] == mySol) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
