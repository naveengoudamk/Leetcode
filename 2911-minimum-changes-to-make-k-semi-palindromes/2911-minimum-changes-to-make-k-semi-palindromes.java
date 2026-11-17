import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1_000_000;

        // 1. Precompute proper divisors for all lengths 2..n
        List<Integer>[] divisors = new ArrayList[n + 1];
        for (int len = 2; len <= n; len++) {
            divisors[len] = new ArrayList<>();
            for (int d = 1; d * d <= len; d++) {
                if (len % d == 0) {
                    if (d < len) divisors[len].add(d);
                    int other = len / d;
                    if (other != d && other < len) divisors[len].add(other);
                }
            }
        }

        // 2. Precompute minimum changes to make s[i..j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost[i], INF);
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minChanges = INF;
                for (int d : divisors[len]) {
                    minChanges = Math.min(minChanges, getChanges(s, i, j, d));
                }
                cost[i][j] = minChanges;
            }
        }

        // 3. DP to partition prefix of length i into p semi-palindromes
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            // Each semi-palindrome has length >= 2, so i >= 2 * p
            for (int i = 2 * p; i <= n; i++) {
                for (int m = 2 * (p - 1); m <= i - 2; m++) {
                    if (dp[m][p - 1] != INF) {
                        dp[i][p] = Math.min(dp[i][p], dp[m][p - 1] + cost[m][i - 1]);
                    }
                }
            }
        }

        return dp[n][k];
    }

    // Helper to compute changes needed for substring s[start..end] with step d
    private int getChanges(String s, int start, int end, int d) {
        int changes = 0;
        for (int rem = 0; rem < d; rem++) {
            int left = start + rem;
            // Find the last index in s[start..end] with the same remainder mod d
            int right = end - ((end - start - rem) % d);
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    changes++;
                }
                left += d;
                right -= d;
            }
        }
        return changes;
    }
}
