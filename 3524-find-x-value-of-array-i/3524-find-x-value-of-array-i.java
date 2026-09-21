class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k]; // dp[r] = count of subarrays ending at previous index with remainder r
        for (int num : nums) {
            long[] newDp = new long[k];
            int rem = num % k;
            // Start new subarray with current element
            newDp[rem] += 1;
            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (int)((r * rem) % k);
                    newDp[newRem] += dp[r];
                }
            }
            // Update result counts
            for (int r = 0; r < k; r++) {
                result[r] += newDp[r];
            }
            dp = newDp;
        }
        return result;
    }
}
