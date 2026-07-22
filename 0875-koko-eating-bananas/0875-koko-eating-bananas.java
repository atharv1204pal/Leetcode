class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = getmax(piles);
        while(left < right){
            int mid = left + (right - left) / 2;
            if(canEatInTime(piles, mid, h)){
                right = mid;  
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
    private int getmax(int[] piles){
        int max = piles[0];
        for(int pile : piles){
            if(pile > max){
                max = pile;
            }
        }
        return max;
    }
    public boolean canEatInTime(int piles[], int k, int h){
        long hrs = 0;
        for(int pile : piles){
            hrs += (pile + k - 1) / k;
            if (hrs > h) return false;
        }
        return hrs <= h;
    }
}