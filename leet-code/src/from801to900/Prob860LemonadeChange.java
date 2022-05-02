package from801to900;

// https://leetcode.com/problems/lemonade-change/
public class Prob860LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        // 0 -> $5, 1 -> $10, 2 -> $20
        int[] changes = new int[3];
        for (int bill : bills) {
            if (bill == 5) {
                changes[0]++;
            } else if (bill == 10) {
                if (changes[0] == 0) return false;
                changes[0]--;
                changes[1]++;
            } else {

                if (changes[0] > 0 && changes[1] > 0) {
                    changes[0]--;
                    changes[1]--;
                } else if (changes[0] >= 3) {
                    changes[0] -= 3;
                } else return false;

                changes[2]++;
            }
        }


        return true;
    }
}
