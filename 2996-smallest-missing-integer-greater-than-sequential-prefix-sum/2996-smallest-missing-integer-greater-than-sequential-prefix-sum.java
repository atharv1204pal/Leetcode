import java.util.*;

class Solution {
    public int missingInteger(int[] nums) {
        // Step 1: Calculate sum of the longest consecutive prefix
        int countSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                countSum += nums[i];
            } else {
                break;
            }
        }

        // Step 2: Use a HashSet for O(1) lookups instead of sorting
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // Step 3: Increment countSum until it's not in the set
        while (set.contains(countSum)) {
            countSum++;
        }

        return countSum;
    }
}
