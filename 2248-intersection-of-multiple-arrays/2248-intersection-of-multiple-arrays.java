class Solution {
    public List<Integer> intersection(int[][] nums) {
        int[] cnt = new int[1001];
        for(int[] num:nums){
            for(int n: num){
                cnt[n]++;
            }
        }
        List<Integer> res = new ArrayList();
        for(int i=0; i<cnt.length; i++){
            if(cnt[i]==nums.length){
                res.add(i);
            }
        }
        return res;
    }
}