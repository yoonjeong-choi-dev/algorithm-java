package from401to500;

import java.util.Stack;

// https://leetcode.com/problems/find-permutation/
public class Prob484FindPermutation {
    public int[] findPermutation(String s) {
        int[] ans = new int[s.length()+1];

        // 배열의 감소하는 부분에 저장해야 하는 숫자들
        // 감소하는 부분에 a,a+1,..,a+k 까지 저장해야 하는 경우 : a+k, ..,a+1,a 순서로 저장해야 함
        // => 스택에 a,a+1,..,a+k 까지 저장하고 감소하는 부분이 끝났을 때 스택에 있는 숫자들 저장
        Stack<Integer> decreasing = new Stack<>();

        // reconstruct the lexicographically smallest permutation
        // => 1부터 시작해야함
        int curIdx = 0, curValue = 1;
        for(char c : s.toCharArray()) {
            decreasing.push(curValue++);
            if(c == 'I') {
              while(!decreasing.isEmpty()) ans[curIdx++] = decreasing.pop();
            }
        }

        decreasing.push(curValue);
        while(!decreasing.isEmpty()) ans[curIdx++] = decreasing.pop();

        return ans;
    }
}
