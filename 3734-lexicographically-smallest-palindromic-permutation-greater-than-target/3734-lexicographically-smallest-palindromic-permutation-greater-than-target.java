class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        int odd = 0;
        int mid = -1;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                mid = i;
            }
        }
        if (odd > 1) {
            return "";
        }
        for (int i = 0; i < 26; i++) {
            freq[i] /= 2;
        }
        int half = n / 2;
        char[] ans = new char[n];
        int pos = 0;
        while (pos < half) {
            int ch = target.charAt(pos) - 'a';
            if (freq[ch] == 0) {
                break;
            }
            ans[pos] = target.charAt(pos);
            freq[ch]--;
            pos++;
        }
        if (pos == half) {
            buildPalindrome(ans, half, mid);
            if (new String(ans).compareTo(target) > 0) {
                return new String(ans);
            }
        }
        while (pos >= 0) {
            if (pos < half) {
                int start = target.charAt(pos) - 'a' + 1;
                for (int ch = start; ch < 26; ch++) {
                    if (freq[ch] > 0) {
                        ans[pos] = (char) ('a' + ch);
                        freq[ch]--;
                        int index = pos + 1;
                        for (int c = 0; c < 26; c++) {
                            while (freq[c] > 0) {
                                ans[index++] = (char) ('a' + c);
                                freq[c]--;
                            }
                        }
                        buildPalindrome(ans, half, mid);
                        return new String(ans);
                    }
                }
            }
            if (pos == 0) {
                return "";
            }
            pos--;
            int ch = target.charAt(pos) - 'a';
            freq[ch]++;
        }
        return "";
    }
    private void buildPalindrome(char[] ans, int half, int mid) {
        if (ans.length % 2 == 1) {
            ans[half] = (char) ('a' + mid);
        }
        for (int i = 0; i < half; i++) {
            ans[ans.length - 1 - i] = ans[i];
        }
    }
}