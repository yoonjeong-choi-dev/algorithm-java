package topcoder.ch7_dp;

import java.util.Arrays;

public class CorporationSalary {
    private long[] salaryInfo;
    private String[] relations;

    public long totalSalary(String[] relations) {
        // relations[i].charAt(j) == 'Y' : i 가 j의 직접적인 매니저(상사)인 경우
        this.relations = relations;

        // salaryInfo[i] : i 의 연봉
        salaryInfo = new long[relations.length];
        Arrays.fill(salaryInfo, -1);

        long ans = 0;
        for(int i=0;i<salaryInfo.length;i++){
            ans += calculateSalary(i);
        }

        return ans;
    }

    // idx 직원의 급여를 계산
    private long calculateSalary(int idx) {
        // 이미 계산되어 있으면 그대로 반환
        if (salaryInfo[idx] != -1) return salaryInfo[idx];

        long salary = 0;

        // 부하직원들에 대해서 봉급을 계산하여 저장
        for (int i = 0; i < relations.length; i++) {
            if (relations[idx].charAt(i) == 'Y') {
                salary += calculateSalary(i);
            }
        }

        // 부하직원이 없는 경우는 1로 초기화
        salaryInfo[idx] = salary == 0 ? 1 : salary;
        return salaryInfo[idx];
    }

    public static void main(String[] args) {
        CorporationSalary sol = new CorporationSalary();

        String[][] relations = {
                {"N"},
                {"NNYN", "NNYN", "NNNN", "NYYN"},
                {"NNNNNN", "YNYNNY", "YNNNNY", "NNNNNN", "YNYNNN", "YNNYNN"},
                {"NYNNYN", "NNNNNN", "NNNNNN", "NNYNNN", "NNNNNN", "NNNYYN"},
                {"NNNN", "NNNN", "NNNN", "NNNN"}
        };

        long[] ans = {1, 5, 17, 8, 4};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            long mySol = sol.totalSalary(relations[i]);
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
