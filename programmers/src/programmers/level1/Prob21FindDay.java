package programmers.level1;

// https://programmers.co.kr/learn/courses/30/lessons/12901
public class Prob21FindDay {

    private final String[] days = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
    private final int pivotDay = 5; // 1월 1일은 금요일

    // 2016년은 윤년
    private final int[] dates = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public String solution(int a, int b) {
        // 기준은 1일이므로
        int totalDay = -1;
        for(int i=0;i<a-1;i++) {
            totalDay += dates[i];
        }
        totalDay += b;

        return days[(totalDay+pivotDay)%7];
    }

    public static void main(String[] args) {
        Prob21FindDay sol = new Prob21FindDay();

        String ans = "TUE";
        String mySol = sol.solution(5, 24);

        System.out.println(ans);
        System.out.println(mySol);
    }
}
