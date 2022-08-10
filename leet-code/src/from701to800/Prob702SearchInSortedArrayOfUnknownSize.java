package from701to800;

// https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/
public class Prob702SearchInSortedArrayOfUnknownSize {
    interface ArrayReader {
        int get(int index);
    }

    public int search(ArrayReader reader, int target) {
        if (reader.get(0) > target) return -1;

        // Step 1 : find the length
        final int OUT_OF_BOUNDARY = Integer.MAX_VALUE;
        int rightmostIndex = 10000;
        int left = 0, right = rightmostIndex, mid, midVal;
        while (left <= right) {
            mid = left + (right - left) / 2;
            midVal = reader.get(mid);

            if (midVal == target) return mid;

            if (midVal == OUT_OF_BOUNDARY) {
                // mid 보다 작은 값이 마지막 인덱스
                right = mid - 1;
            } else {
                rightmostIndex = mid;
                left = mid + 1;
            }
        }

        // Step 2 : binary search
        left = 0;
        right = rightmostIndex;
        while (left <= right) {
            mid = left + (right - left) / 2;
            midVal = reader.get(mid);

            if (midVal == target) return mid;
            else if (midVal < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}
