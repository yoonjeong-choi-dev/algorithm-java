package programmers.level2;

// https://programmers.co.kr/learn/courses/30/lessons/12953
public class Prob1LCM {

    public int solution(int[] arr) {

        int answer = arr[0];
        int gcd;
        for(int i=1;i<arr.length;i++){
            gcd = gcd(answer, arr[i]);
            answer =  answer * arr[i] / gcd;
        }

        return answer;
    }

    private int gcd(int a, int b) {
        // Assume : a >= b
        if(a<b) return gcd(b,a);

        // dividend = divisor * m + residue
        int residue, divisor, dividend;

        dividend = a;
        divisor = b;
        residue = dividend % divisor;

        while(residue > 0) {
            dividend = divisor;
            divisor = residue;
            residue = dividend % divisor;
        }

        return divisor;
    }

    public static void main(String[] args) {
        Prob1LCM sol = new Prob1LCM();

        int[][] arrs = {{2,6,8,14}, {1,2,3}};
        int[] ans = {168, 6};

        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            int mySol = sol.solution(arrs[i]);
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
