package from1601to1700;

// https://leetcode.com/problems/design-parking-system/
public class Prob1603DesignParkingSystem {
    class ParkingSystem {

        // big, medium, or small, which are represented by 1, 2, and 3 respectively
        private int[] available = new int[3];

        public ParkingSystem(int big, int medium, int small) {
            available[0] = big;
            available[1] = medium;
            available[2] = small;
        }

        public boolean addCar(int carType) {
            if (available[carType - 1] == 0) return false;

            available[carType - 1]--;
            return true;
        }
    }

    public void test() {
        ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);

        System.out.printf("Result : %s vs Ans : %s\n", parkingSystem.addCar(1), true);
        System.out.printf("Result : %s vs Ans : %s\n", parkingSystem.addCar(2), true);
        System.out.printf("Result : %s vs Ans : %s\n", parkingSystem.addCar(3), false);
        System.out.printf("Result : %s vs Ans : %s\n", parkingSystem.addCar(1), false);
    }

    public static void main(String[] args) {
        Prob1603DesignParkingSystem sol = new Prob1603DesignParkingSystem();
        sol.test();
    }
}
