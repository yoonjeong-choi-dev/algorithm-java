package from1301to1400;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/product-of-the-last-k-numbers/
public class Prob1352ProductOfTheLastKNumbers {
    class ProductOfNumbers {

        private List<Integer> partialProduct, lastZeroIndex;

        public ProductOfNumbers() {
            partialProduct = new ArrayList<>();
            lastZeroIndex = new ArrayList<>();

            partialProduct.add(1);
            lastZeroIndex.add(-1);
        }

        public void add(int num) {
            int lastIndex = partialProduct.size() - 1;
            if (num == 0) {
                partialProduct.add(1);
                lastZeroIndex.add(lastIndex);
            } else {

                partialProduct.add(num * partialProduct.get(lastIndex));
                lastZeroIndex.add(lastZeroIndex.get(lastIndex));
            }
        }

        public int getProduct(int k) {
            int lastIndex = partialProduct.size() - 1;
            int prevIdx = lastIndex - k;
            int lastZero = lastZeroIndex.get(lastIndex);
            if (prevIdx < lastZero) return 0;
            return partialProduct.get(lastIndex) / partialProduct.get(prevIdx);
        }
    }
}
