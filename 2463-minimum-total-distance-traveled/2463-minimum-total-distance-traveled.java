import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);

        int n = robot.size();
        int m = factory.length;

        long[][] dp = new long[n + 1][m + 1];

        // Initialize with large value
        for (long[] row : dp) Arrays.fill(row, Long.MAX_VALUE);
        dp[0][0] = 0;

        for (int j = 1; j <= m; j++) {
            int pos = factory[j - 1][0];
            int limit = factory[j - 1][1];

            for (int i = 0; i <= n; i++) {
                // Case 1: don't use this factory
                dp[i][j] = dp[i][j - 1];

                long dist = 0;

                // Case 2: assign k robots to this factory
                for (int k = 1; k <= limit && k <= i; k++) {
                    dist += Math.abs(robot.get(i - k) - pos);

                    if (dp[i - k][j - 1] != Long.MAX_VALUE) {
                        dp[i][j] = Math.min(
                            dp[i][j],
                            dp[i - k][j - 1] + dist
                        );
                    }
                }
            }
        }

        return dp[n][m];
    }
}