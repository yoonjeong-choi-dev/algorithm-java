package from1501to1600;

// https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
public class Prob1588SumOfOddLengthSubarrays {
    public int sumOddLengthSubarrays(int[] arr) {
        //return myBruteForceSolution(arr);
        return usePartialSum(arr);
    }

    // 전체 탐색을 통한 가장 간단한 방식
    private int myBruteForceSolution(int[] arr) {
        int len = arr.length;
        int sum = 0;
        int maxSubLen;
        for (int i = 0; i < len; i++) {
            maxSubLen = len - i;
            for (int l = 1; l <= maxSubLen; l += 2) {
                if (i + l - 1 >= len) break;
                for (int j = 0; j < l; j++) {
                    sum += arr[i + j];
                }
            }
        }

        return sum;
    }

    //TODO : Improve Runtime
    private int usePartialSum(int[] arr) {
        int len = arr.length;

        // 배열을 부분합 배열로 변환
        // 부분 배열의 합을 계산해야 하므로, 부분 배열의 시작 및 끝 인덱스만을 이용하기 위해서
        for (int i = 1; i < len; i++) arr[i] += arr[i - 1];

        int sum = 0;
        int start, end;
        for (int curLen = 1; curLen <= len; curLen += 2) {
            // window : [start, end]
            start = 0;
            end = curLen - 1;

            while (end < len) {
                if (start == 0) {
                    // [0, end] 배열의 합
                    sum += arr[end];
                } else {
                    // [start, end] 배열의 합
                    sum += arr[end] - arr[start - 1];
                }

                // 한칸씩 이동
                start++;
                end++;
            }
        }

        return sum;
    }

    // TODO : Improve Runtime
    private int useFormula(int[] arr) {
        int len = arr.length;
        int sum = 0;
        int numOccur;

        for (int i = 0; i < len; i++) {
            // i 요소를 포함하는 홀수 길이의 부분 배열의 개수 계산
            // 부분 배열의 길이를 l=2a+1 이라 할때, [i-l+1, i] ~ [i, i+l-1] 까지 가능
            // 이때 시작점에 대한 범위를 [n1, n2]라고 하면(즉, 가능한 개수는 n2-n1+1),
            // n1 = max(0, i-l+1) = max(0, i-2a), n2 = min(i, len-l) = min(i,len-2a-1)

        }
        return sum;
    }

    public static void main(String[] args) {
        Prob1588SumOfOddLengthSubarrays sol = new Prob1588SumOfOddLengthSubarrays();

        int[][] arrays = {{1, 4, 2, 5, 3}, {1, 2}, {10, 11, 12}, {1}};

        int[] ans = {58, 3, 66, 1};
        int numProblems = ans.length;

        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.sumOddLengthSubarrays(arrays[i]);
            if (mySol == ans[i]) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }
}
