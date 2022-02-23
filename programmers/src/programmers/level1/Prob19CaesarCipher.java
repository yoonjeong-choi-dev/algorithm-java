package programmers.level1;

// https://programmers.co.kr/learn/courses/30/lessons/12926
public class Prob19CaesarCipher {
    char lower = 'a';
    char upper = 'A';
    int numAlphabet = 26;

    char shift(char c, int n) {
        int diff = c - upper;
        if (diff < numAlphabet) {
            return (char) (upper + (diff + n) % numAlphabet);
        } else {
            diff = c - lower;
            return (char) (lower + (diff + n) % numAlphabet);
        }
    }

    public String solution(String s, int n) {
        int len = s.length();
        StringBuilder answer = new StringBuilder(n);
        for(int i=0;i<len;i++){
            char c = s.charAt(i);
            if(c == ' ') {
                answer.append(c);
            } else {
                answer.append(shift(c, n));
            }
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        Prob19CaesarCipher sol = new Prob19CaesarCipher();

        String[] strings = {"AB", "z", "a B z"};
        int[] shifts = {1, 1, 4};

        String[] ans = {"BC", "a", "e F d"};

        int numProblems = ans.length;

        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            String mySol = sol.solution(strings[i], shifts[i]);
            if (mySol.equals(ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
