public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (isBadVersion(mid)) {
                right = mid; // bad version found, search left side
            } else {
                left = mid + 1; // still good, search right side
            }
        }
        
        return left; // first bad version
    }
}
