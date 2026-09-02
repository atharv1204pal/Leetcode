class Solution {
    public boolean uniformArray(int[] nums1) {
        boolean hasOdd = false, hasEven = false;
        for (int num : nums1) {
            if (num % 2 == 0) hasEven = true;
            else hasOdd = true;
        }
        // Always possible
        return true;
    }
}