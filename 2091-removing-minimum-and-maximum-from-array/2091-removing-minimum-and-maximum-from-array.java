class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        //min and max
        int minIndex = 0, maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        if (minIndex > maxIndex) {
            int temp = minIndex;
            minIndex = maxIndex;
            maxIndex = temp;
        }

        // Three strategies:
        int fromFront = maxIndex + 1;                  // remove both from front
        int fromBack = n - minIndex;                   // remove both from back
        int mixed = (minIndex + 1) + (n - maxIndex);   // one from front, one from back

        return Math.min(fromFront, Math.min(fromBack, mixed));
    }
}
