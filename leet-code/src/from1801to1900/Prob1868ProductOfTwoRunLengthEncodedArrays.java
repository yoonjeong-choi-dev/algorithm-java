package from1801to1900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/product-of-two-run-length-encoded-arrays/
public class Prob1868ProductOfTwoRunLengthEncodedArrays {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> ans = new LinkedList<>();

        int len1 = encoded1.length, len2 = encoded2.length;
        int i1 = 0, i2 = 0;

        int product = -1, count = -1;
        while (i1 < len1 && i2 < len2) {
            if (product != encoded1[i1][0] * encoded2[i2][0]) {
                // 현재까지 압축한 내용 저장
                ans.add(new ArrayList<>(Arrays.asList(product, count)));

                // 곱 및 개수 초기화
                product = encoded1[i1][0] * encoded2[i2][0];
                count = 0;
            }

            if (encoded1[i1][1] < encoded2[i2][1]) {
                // 1번 인코딩은 모두 사용하고, 다음으로 넘김
                count += encoded1[i1][1];
                encoded2[i2][1] -= encoded1[i1][1];
                i1++;
            } else if (encoded1[i1][1] > encoded2[i2][1]) {
                // 2번 인코딩은 모두 사용하고, 다음으로 넘김
                count += encoded2[i2][1];
                encoded1[i1][1] -= encoded2[i2][1];
                i2++;
            } else {
                // 양쪽 인코딩은 모두 사용하고, 다음으로 넘김
                count += encoded1[i1][1];
                i1++;
                i2++;
            }
        }

        // 마지막 정보 저장
        ans.add(new ArrayList<>(Arrays.asList(product, count)));

        // 맨 처음 넣은 더미 데이터 (-1,-1) 제거
        ans.remove(0);
        return ans;
    }
}
