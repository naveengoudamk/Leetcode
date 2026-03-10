class Solution {
    static final int MOD = 1_000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {
        long[][] dp0 = new long[zero + 1][one + 1];
        long[][] dp1 = new long[zero + 1][one + 1];

        for (int i = 1; i <= Math.min(limit, zero); i++) {
            dp0[i][0] = 1;
        }
        for (int j = 1; j <= Math.min(limit, one); j++) {
            dp1[0][j] = 1;
        }

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {

                long val0 = (dp0[i-1][j] + dp1[i-1][j]) % MOD;
                if (i - limit - 1 >= 0) {
                    val0 = (val0 - dp1[i-limit-1][j] + MOD) % MOD;
                }
                dp0[i][j] = val0;

                long val1 = (dp0[i][j-1] + dp1[i][j-1]) % MOD;
                if (j - limit - 1 >= 0) {
                    val1 = (val1 - dp0[i][j-limit-1] + MOD) % MOD;
                }
                dp1[i][j] = val1;
            }
        }

        return (int)((dp0[zero][one] + dp1[zero][one]) % MOD);
    }
}