package from1401to1500;

// https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary/
public class Prob1491AverageSalaryExcludingMinMax {
    public double average(int[] salary) {
        int len = salary.length;
        int minIdx = 0, maxIdx = 0;

        for (int i = 0; i < len; i++) {
            if (salary[i] < salary[minIdx]) minIdx = i;
            if (salary[i] > salary[maxIdx]) maxIdx = i;
        }

        // 배열 길이는 최대 100, 각 요소의 최대값은 10^6이므로 전체 합은 최대 10^8
        // => int 형으로 저장 가능
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (i == minIdx || i == maxIdx) continue;
            sum += salary[i];
        }


        return (double) sum / (len - 2);
    }
}
