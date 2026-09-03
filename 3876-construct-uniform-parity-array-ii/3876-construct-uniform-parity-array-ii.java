import java.util.*;

public class Solution {
    public boolean uniformArray(int[] nums1) {
        Arrays.sort(nums1);
        boolean hasOdd = false, hasEven = false;
        for (int num : nums1) {
            if (num % 2 == 0) hasEven = true;
            else hasOdd = true;
        }
        if (!hasOdd || !hasEven) return true; // all same parity

        // Try to make all odd
        int smallestOdd = -1;
        for (int num : nums1) {
            if (num % 2 != 0) {
                smallestOdd = num;
                break;
            }
        }
        boolean allOddPossible = true;
        for (int num : nums1) {
            if (num % 2 == 0) { // even
                if (num > smallestOdd) {
                    // can subtract odd → odd result
                } else {
                    allOddPossible = false;
                    break;
                }
            }
        }
        if (allOddPossible) return true;

        // Try to make all even
        int smallestEven = -1;
        for (int num : nums1) {
            if (num % 2 == 0) {
                smallestEven = num;
                break;
            }
        }
        boolean allEvenPossible = true;
        for (int num : nums1) {
            if (num % 2 != 0) { // odd
                if (num > smallestEven) {
                    // odd - even = odd (not even!)
                    allEvenPossible = false;
                    break;
                }
            }
        }
        return allEvenPossible;
    }
}
