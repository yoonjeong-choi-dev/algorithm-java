package from2201to2300;

// https://leetcode.com/problems/minimum-health-to-beat-game/
public class Prob2214MinimumHealthToBestGame {
    public long minimumHealth(int[] damage, int armor) {
        long totalDamage = 0L;

        int maxShieldDamage = 0;
        for (int d : damage) {
            totalDamage += d;


            if (d <= armor) maxShieldDamage = Math.max(maxShieldDamage, d);
            else maxShieldDamage = Math.max(maxShieldDamage, armor);
        }

        return totalDamage - maxShieldDamage + 1;
    }
}
