package from1701to1800;

// https://leetcode.com/problems/merge-strings-alternately/
public class Prob1768MergeStringsAlternately {
    public String mergeAlternately(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int min = Math.min(len1,len2);

        StringBuilder ans = new StringBuilder(len1 + len2);
        for (int i = 0; i < min; i++) {
            ans.append(word1.charAt(i));
            ans.append(word2.charAt(i));
        }

        if(len1==min) {
            for(int i=min;i<len2;i++){
                ans.append(word2.charAt(i));
            }
        } else {
            for(int i=min;i<len1;i++){
                ans.append(word1.charAt(i));
            }
        }

        return ans.toString();
    }
}
