package from101to200;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/palindrome-partitioning/
public class Prob131PalindromePartitioning {
    private List<List<String>> ans;
    private String string;
    private int len;

    public List<List<String>> partition(String s) {
        string = s;
        len = s.length();
        ans = new ArrayList<>();

        List<String> curPath = new ArrayList<>();
        dfs(0, curPath);
        return ans;
    }

    // curIdx 로 시작하는 모든 회문 추가하는 방식 => O(2^N)
    // 회문 검사 : O(N)
    // => O(N * 2^N) where N <= 16
    // 최대 연산 시간 : 2^3 * 2^16 = 2^19 < 6*10^6
    private void dfs(int curIdx, List<String> curPath) {
        if (curIdx == len) {
            ans.add(new ArrayList<>(curPath));
            return;
        }

        for (int end = curIdx; end < len; end++) {
            if (isPalindrome(curIdx, end)) {
                curPath.add(string.substring(curIdx, end + 1));
                dfs(end + 1, curPath);

                curPath.remove(curPath.size() - 1);
            }
        }
    }

    // O(N) where N = s.length()
    private boolean isPalindrome(int start, int end) {
        while (start < end) {
            if (string.charAt(start++) != string.charAt(end--)) return false;
        }
        return true;
    }
}
