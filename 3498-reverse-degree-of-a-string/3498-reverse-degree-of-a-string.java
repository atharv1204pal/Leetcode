class Solution {
    public int reverseDegree(String s) {
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int revIndex = 26 - (ch - 'a');   // reversed alphabet position
            int strIndex = i + 1;             // 1-indexed position in string
            total += revIndex * strIndex;     // product added to sum
        }
        return total;
    }
}
