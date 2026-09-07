class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0, open = 0, close = 0;

        // Left to right
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') open++;
            else close++;
            if (open == close) maxLen = Math.max(maxLen, 2 * close);
            else if (close > open) open = close = 0;
        }

        open = close = 0;
        // Right to left
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') open++;
            else close++;
            if (open == close) maxLen = Math.max(maxLen, 2 * open);
            else if (open > close) open = close = 0;
        }

        return maxLen;
    }
}
