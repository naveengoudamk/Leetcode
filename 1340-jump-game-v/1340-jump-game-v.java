class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];

        int ans = 1;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i, arr, d, dp));
        }

        return ans;
    }

    private int dfs(int i, int[] arr, int d, int[] dp) {
        if (dp[i] != 0) {
            return dp[i];
        }

        int n = arr.length;
        int max = 1;

        // Move right
        for (int j = i + 1; j <= Math.min(i + d, n - 1); j++) {
            // stop if blocked
            if (arr[j] >= arr[i]) {
                break;
            }

            max = Math.max(max, 1 + dfs(j, arr, d, dp));
        }

        // Move left
        for (int j = i - 1; j >= Math.max(i - d, 0); j--) {
            // stop if blocked
            if (arr[j] >= arr[i]) {
                break;
            }

            max = Math.max(max, 1 + dfs(j, arr, d, dp));
        }

        dp[i] = max;
        return max;
    }
}