class Solution {
    public boolean containsDuplicate(int[] nums) {
        java.util.HashSet<Integer> seen = new java.util.HashSet<>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }
}