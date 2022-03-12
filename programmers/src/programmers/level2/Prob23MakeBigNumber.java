package programmers.level2;

// https://programmers.co.kr/learn/courses/30/lessons/42883
public class Prob23MakeBigNumber {
    public String solution(String number, int k) {
        int len = number.length();
        StringBuilder answer = new StringBuilder(len - k);

        int numRemoved = 0;
        for (int i = 0; i < number.length(); i++) {
            // 모두 지운 경우 => 나머지 문자열 모두 붙이고 끝
            if (numRemoved == k) {
                answer.append(number.substring(i));
                break;
            }

            // 현재 문자열에서 삭제해야 하는 것들 찾아서 삭제
            char curChar = number.charAt(i);
            while (numRemoved < k && answer.length() != 0) {
                // 문자열의 마지막 숫자보다 작거나 같은 경우 삭제 끝
                if (answer.charAt(answer.length() - 1) >= curChar) break;

                // 문자열의 마지막 숫자보다 큰 경우
                // => 마지막 숫자 제거
                answer.deleteCharAt(answer.length() - 1);
                numRemoved++;

            }

            // 현재 문자는 우선 붙인다 : i+1 단계에서 삭제여부 판단
            answer.append(number.charAt(i));
        }

        // 모두 안짜른 경우 뒷부분만 잘라준다
        if (numRemoved != k)
            return answer.substring(0, len - k);
        else
            return answer.toString();
    }

    public static void main(String[] args) {
        Prob23MakeBigNumber sol = new Prob23MakeBigNumber();

        String[] numbers = {"1924", "1231234", "4177252841"};
        int[] k = {2, 3, 4};

        String[] ans = {"94", "3234", "775841"};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d\n", i);
            String mySol = sol.solution(numbers[i], k[i]);
            if (ans[i].equals(mySol)) {
                System.out.println("PASS");
            } else {
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
