class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;

        long dp = 1; // empty subsequence

        long[] last = new long[26];

        for (char c : s.toCharArray()) {
            int index = c - 'a';

            long newDp = (2 * dp - last[index]) % MOD;

            if (newDp < 0) {
                newDp += MOD;
            }

            // Store dp BEFORE processing this character
            last[index] = dp;

            dp = newDp;
        }

        // Remove the empty subsequence
        return (int) ((dp - 1 + MOD) % MOD);
    }
}