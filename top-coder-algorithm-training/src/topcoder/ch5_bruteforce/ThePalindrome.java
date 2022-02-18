package topcoder.ch5_bruteforce;

class ThePalindromeSolution {
    private String probStr;
    private int legnth;

    public int find(String s) {
        probStr = s;
        legnth = s.length();

        // 마지막 문자를 회문의 마지막 문자로 고정하고 가장 긴 회문 부분 문자열의 길이를 찾아야 함
        // [0, startIdx) + [startIdx, endIdx(==length-1) ] + inverse([0, startIdx))
        // => 가장 짧은 회문 : startIdx + (length - startIdx) + startIdx = length + startIdx
        int startIdx;
        for(startIdx=0; startIdx< legnth; startIdx++) {
            if(isPalindrome(startIdx))
                break;
        }

        return s.length() + startIdx;
    }

    private boolean isPalindrome(int start) {
        boolean ret = true;
        int testLen = (legnth - start)/2 + 1;
        for(int i=0;i<testLen;i++){
            if(probStr.charAt(start + i ) != probStr.charAt(legnth -i-1) ){
                ret = false;
                break;
            }
        }
        return ret;
    }
}

public class ThePalindrome {
    public static void main(String[] args) {
        ThePalindromeSolution sol = new ThePalindromeSolution();

        String[] strings = {"abab", "abacaba", "qwerty", "abdfhdyrbdbsdfghjkllkjhgfds"};
        int[] ans ={5,7,11,38};

        int numProblems = strings.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("Problem %d : ", i);
            int mySol = sol.find(strings[i]);
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
