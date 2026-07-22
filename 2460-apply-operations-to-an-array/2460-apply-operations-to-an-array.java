class Solution {
    public int[] applyOperations(int[] nums) {
        int s = nums.length;
        for(int i = 0; i < s - 1; i++){
            if(nums[i] ==nums[i+1] && nums[i] != 0){
                nums[i]=nums[i]*2;
                nums[i+1]=0;
            }
        }
        int[] res = new int[s];
        int index = 0;
        for(int n : nums){
            if(n != 0){
                res[index++] = n;
            }
        }
        return res;
    }
}