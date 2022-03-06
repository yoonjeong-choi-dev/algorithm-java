package topcoder.ch8_pruning.greedy;

public class StockHistory {
    public int maximumEarnings(int initialInvestment, int monthlyContribution, String[] stockPrices) {
        int month = stockPrices.length;
        // stockPrices[i] : i번째 달의 주식들의 가격 정보 "stock_0 stock_1 ... stock_n"
        int numStocks = stockPrices[0].split(" ").length;

        // stockInfo[i][j] : i번째 달의 j 주식의 가격
        int[][] stockInfo = new int[month][numStocks];
        for (int i = 0; i < month; i++) {
            String[] stocks = stockPrices[i].split(" ");
            for (int j = 0; j < numStocks; j++) {
                stockInfo[i][j] = Integer.parseInt(stocks[j]);
            }
        }


        // i번째 달에서 구입 여부 : 수익률이 i+1 달 이후보다 가장 높은 경우 매수
        // buyStock[i] : i번째 달에 구매할 주식의 수익률, -1인 경우 구매 X
        double[] buyRate = new double[month - 1];

        // 마지막 달부터 역으로 계산하여 수익률의 최대값 추적
        double maxRate = ((double) stockInfo[month - 1][0] - stockInfo[month - 2][0]) / stockInfo[month - 2][0];
        buyRate[month - 2] = maxRate;

        double curMax, rate;
        for (int i = month - 2; i >= 0; i--) {

            // 각 주식들의 수익율 계산하여 최대갑 찾기
            curMax = ((double) stockInfo[month - 1][0] - stockInfo[i][0]) / stockInfo[i][0];
            for (int j = 1; j < numStocks; j++) {
                rate = ((double) stockInfo[month - 1][j] - stockInfo[i][j]) / stockInfo[i][j];
                if (curMax < rate) {
                    curMax = rate;
                }
            }

            if (maxRate <= curMax) {
                // i+1 이후 최대 수익률보다 높은 경우 구매
                maxRate = curMax;
                buyRate[i] = maxRate;
            } else {
                // i+1 이후 최대 수익률보다 낮은 경우 구매하지 않음
                buyRate[i] = -1;
            }
        }

        double total = 0.0;
        for (int i = 0; i < month - 1; i++) {
            if (buyRate[i] > 0) {
                // 현재 사야하는 경우
                total += initialInvestment * buyRate[i];
                initialInvestment = 0;
            }

            initialInvestment += monthlyContribution;
        }

        return (int) total;
    }

    public static void main(String[] args) {
        StockHistory sol = new StockHistory();

        int[] inits = {1000, 1000, 100};
        int[] months = {0, 0, 20};
        String[][] prices = {
                {"10 20 30", "15 24 32"},
                {"10", "9"},
                {"40 50 60", "37 48 55", "100 48 50", "105 48 47", "110 50 52", "110 50 52", "110 51 54", "109 49 53"}
        };

        int[] ans = {500, 0, 239};
        int numProblems = ans.length;
        for (int i = 0; i < numProblems; i++) {
            System.out.printf("============= Problem %d : \n", i);
            int mySol = sol.maximumEarnings(inits[i], months[i], prices[i]);
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
