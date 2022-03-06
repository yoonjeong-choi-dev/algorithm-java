package topcoder.ch8_pruning.binarysearch;

public class AutoLoan {
    private final double error = 1.0e-9;
    private double price;
    private double monthPay;
    private double term;

    public double interestRate(double price, double monthlyPayment, int loanTerm) {
        this.price = price;
        this.monthPay = monthlyPayment;
        this.term = loanTerm;

        // 월 이자에 대한 이분 탐색
        double minRate = 0.0;
        double maxRate = 100.0;   // 결과값은 0~100 이하

        double midRate, midRet;

        // 상대 오차를 이용하여 계산
        // maxRate과 minRate 오차가 일정 범위 아래인 경우, 정답으로 판단
        while ((maxRate - minRate) > error && ((maxRate - minRate) / maxRate) > error) {
            midRate = (minRate + maxRate) / 2;

            // 중간 이율에 대한 결과 계산
            midRet = remain(midRate);

            if (midRet < 0) {
                // 결과가 0보다 작은 경우 => 오른쪽 범위 계산
                minRate = midRate;
            } else if (midRet > 0) {
                // 결과가 0보다 큰 경우 => 왼쪽 범위 계산
                maxRate = midRate;
            } else {
                return midRate;
            }
        }

        return minRate;
    }

    private double remain(double yearRate) {

        double monthIncrement = 1.0 + yearRate / 1200;
        // n 번째 달 잔액 : p*(1+r)^n - m((1+r)^(n-1) + ... + (1+r) + 1)
        // where p : 초기 잔액, m : 매달 내는 금액, r: 월이자, n: 기간
        double ret = price * Math.pow(monthIncrement, term);
        for (int i = 0; i < term; i++) {
            ret -= monthPay * Math.pow(monthIncrement, i);
        }
        return ret;
    }

    public static void main(String[] args) {
        AutoLoan sol = new AutoLoan();

        double[] prices = {6800, 2000, 15000};
        double[] monthlyPayments = {100, 510, 364};
        int[] terms = {68, 4, 48};

        double[] ans = {1.3322616182218813e-13, 9.56205462458368, 7.687856394581649};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            double mySol = sol.interestRate(prices[i], monthlyPayments[i], terms[i]);
            if (isValid(mySol, ans[i])) {
                System.out.println("PASS");
            } else {
                System.out.println("Wrong");
                System.out.println("Result : " + mySol);
                System.out.println("Expected : " + ans[i]);
            }
        }
    }

    private static boolean isValid(double target, double answer) {
        return Math.abs(target - answer) < 1.0e-8;
    }

}
