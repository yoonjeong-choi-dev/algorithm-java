package programmers.level2;

// https://programmers.co.kr/learn/courses/30/lessons/60058
public class Prob7KakaoBracket {
    public String solution(String p) {
        if (p.isEmpty()) return "";
        if (isValidBracket(p)) return p;

        // Step 2 : 문자열 분리
        int rightIdx = findMinIdx(p);
        String u = p.substring(0, rightIdx+1);
        String v = p.substring(rightIdx+1);

        // Step 3 : u가 올바른 문자열인 경우
        if(isValidBracket(u)) {
            return u + solution(v);
        }

        // Step 4
        StringBuilder answer = new StringBuilder(p.length());

        // Step 4-1~4-3
        answer.append('(').append(solution(v)).append(')');

        // Step 4-4
        for(int i=1;i<u.length()-1;i++){
            if (u.charAt(i) == '(') {
                answer.append(')');
            } else {
                answer.append('(');
            }
        }

        return answer.toString();
    }

    private boolean isValidBracket(String s) {
        int leftNum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftNum++;
            } else {
                if (leftNum == 0) return false;
                leftNum--;
            }
        }
        return leftNum == 0;
    }

    private int findMinIdx(String s) {
        // 왼쪽부터 규형잡힌 괄호 최소 문자열 찾기
        int rightNum = 0, leftNum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftNum++;
            } else {
                rightNum++;
            }
            if (rightNum == leftNum) return i;
        }

        return 0;
    }

    public static void main(String[] args) {
        Prob7KakaoBracket sol = new Prob7KakaoBracket();
        String[] ps = {"(()())()", ")(", "()))((()"};
        String[] ans = {"(()())()", "()", "()(())()"};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("================\nProblem %d\n", i);
            String mySol = sol.solution(ps[i]);
            if (ans[i].equals(mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
