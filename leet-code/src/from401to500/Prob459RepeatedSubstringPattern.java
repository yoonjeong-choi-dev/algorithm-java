package from401to500;

// https://leetcode.com/problems/repeated-substring-pattern/
public class Prob459RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();

        boolean isMatch;
        for (int l = 1; l <= len / 2; l++) {
            if (len % l != 0) continue;

            isMatch = true;
            for(int i=l;i<len;i+=l) {
                for(int j=0;j<l;j++){
                    if(s.charAt(j) != s.charAt(i+j)) {
                        isMatch = false;
                        break;
                    }
                }

                if(!isMatch) break;
            }

            if(isMatch) return true;
        }

        return false;
    }
}
