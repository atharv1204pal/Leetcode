class Solution(object):
    def distinctSubseqII(self, s):
        MOD = 10**9 + 7
        n = len(s)
        dp = [0] * (n + 1)
        dp[0] = 1  # empty subsequence
        
        last = {}
        
        for i, ch in enumerate(s):
            dp[i+1] = (2 * dp[i]) % MOD
            if ch in last:
                dp[i+1] = (dp[i+1] - dp[last[ch]]) % MOD
            last[ch] = i
        
        return (dp[n] - 1) % MOD
