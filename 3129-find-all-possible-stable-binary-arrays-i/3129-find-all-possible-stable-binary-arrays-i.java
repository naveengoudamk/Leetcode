class Solution {
    static final int MOD = 1_000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {

        long[][][] dp = new long[zero + 1][one + 1][2];

        for (int i = 1; i <= Math.min(zero, limit); i++) {
            dp[i][0][0] = 1;
        }

        for (int j = 1; j <= Math.min(one, limit); j++) {
            dp[0][j][1] = 1;
        }

        for (int z = 0; z <= zero; z++) {
            for (int o = 0; o <= one; o++) {

                if (z > 0) {
                    long sum = 0;
                    for (int k = 1; k <= limit && z - k >= 0; k++) {
                        sum = (sum + dp[z - k][o][1]) % MOD;
                    }
                    dp[z][o][0] = (dp[z][o][0] + sum) % MOD;
                }

                if (o > 0) {
                    long sum = 0;
                    for (int k = 1; k <= limit && o - k >= 0; k++) {
                        sum = (sum + dp[z][o - k][0]) % MOD;
                    }
                    dp[z][o][1] = (dp[z][o][1] + sum) % MOD;
                }
            }
        }

        return (int)((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }
}