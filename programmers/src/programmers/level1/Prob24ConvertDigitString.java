package programmers.level1;

// https://programmers.co.kr/learn/courses/30/lessons/81301
public class Prob24ConvertDigitString {
    private final String[] digitsStrings = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public int solution(String s) {
        for(int i=0;i<digitsStrings.length;i++) {
            s = s.replaceAll(digitsStrings[i], String.valueOf(i));
        }

        return Integer.parseInt(s);
    }

    public static void main(String[] args) {
        Prob24ConvertDigitString sol = new Prob24ConvertDigitString();

        String[] strings = {"one4seveneight", "23four5six7", "2three45sixseven", "123"};
        int[] ans = {1478, 234567, 234567, 123};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            int mySol = sol.solution(strings[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Solution : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}

