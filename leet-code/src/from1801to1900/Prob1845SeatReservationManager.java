package from1801to1900;

import java.util.PriorityQueue;

// https://leetcode.com/problems/seat-reservation-manager/
public class Prob1845SeatReservationManager {
    class SeatManager {

        // 예약 가능한 좌석들을 오름차순으로 힙에 저장
        PriorityQueue<Integer> canBeReserved = new PriorityQueue<>();

        public SeatManager(int n) {
            for (int i = 1; i <= n; i++) {
                canBeReserved.offer(i);
            }
        }

        public int reserve() {
            return canBeReserved.poll();
        }

        public void unreserve(int seatNumber) {
            canBeReserved.offer(seatNumber);
        }
    }

}
