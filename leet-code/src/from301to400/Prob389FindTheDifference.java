package from301to400;

// https://leetcode.com/problems/find-the-difference/
public class Prob389FindTheDifference {
    public char findTheDifference(String s, String t) {
        return myHashMapSolution(s, t);
    }

    private char myHashMapSolution(String s, String t) {
        int[] occurMap = new int[26];
        for (int i = 0; i < s.length(); i++) {
            occurMap[s.charAt(i) - 'a']++;
        }


        for (int i = 0; i < t.length(); i++) {
            if (occurMap[t.charAt(i) - 'a'] == 0) {
                return t.charAt(i);
            } else {
                occurMap[t.charAt(i) - 'a']--;
            }
        }

        return 'a';
    }

    // TODO : Improve Runtime
    private char improvedSolution(String s, String t) {
        // Idea : 두번째 문자열은 첫번째 문자열에서 하나의 문자만 추가됨
        // => 두 문자열의 차이값이 정답
        // 계산의 편의를 위해 문자의 차이를 저장
        int ans = 0;
        for(int i = s.length()-1;i>=0;i--) {
            ans +=t.charAt(i) - s.charAt(i);
        }
        ans += t.charAt(s.length());
        return (char) ans;
    }
}
