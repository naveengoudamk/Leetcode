import java.util.TreeMap;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] windowCosts = getWindowCosts(nums, x);

        // dp[j] stores the minimum cost to pick j windows
        // Since we only need the values from the next indices, we can compute backwards or use a 2D array.
        // dp[i][j]: min cost to pick j windows from suffix starting at index i
        long[][] dp = new long[n + 1][k + 1];
        
        // Initialize DP table with a large value
        long INF = Long.MAX_VALUE / 2;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = INF;
            }
        }

        // Fill DP table backwards
        for (int i = n - x; i >= 0; i--) {
            for (int j = 1; j <= k; j++) {
                // Option 1: Skip the current index
                long skip = dp[i + 1][j];
                
                // Option 2: Pick the window of size x starting at index i
                long pick = windowCosts[i] + dp[i + x][j - 1];
                
                dp[i][j] = Math.min(skip, pick);
            }
        }

        return dp[0][k];
    }

    // Helper method to compute the minimum operations for all windows of size x using TreeMaps
    private long[] getWindowCosts(int[] nums, int x) {
        int n = nums.length;
        long[] costs = new long[n - x + 1];

        // Two TreeMaps to maintain lower half and upper half for streaming median
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;

        // Target sizes for balanced halves
        int maxLeftSize = (x + 1) / 2;

        for (int i = 0; i < n; i++) {
            // 1. Add current element
            int val = nums[i];
            if (leftSize == 0 || val <= left.lastKey()) {
                left.put(val, left.getOrDefault(val, 0) + 1);
                leftSum += val;
                leftSize++;
            } else {
                right.put(val, right.getOrDefault(val, 0) + 1);
                rightSum += val;
                rightSize++;
            }

            // 2. Remove out-of-bounds element
            if (i >= x) {
                int outVal = nums[i - x];
                if (left.containsKey(outVal)) {
                    int count = left.get(outVal);
                    if (count == 1) left.remove(outVal);
                    else left.put(outVal, count - 1);
                    leftSum -= outVal;
                    leftSize--;
                } else {
                    int count = right.get(outVal);
                    if (count == 1) right.remove(outVal);
                    else right.put(outVal, count - 1);
                    rightSum -= outVal;
                    rightSize--;
                }
            }

            // 3. Rebalance heaps
            while (leftSize > maxLeftSize) {
                int moveVal = left.lastKey();
                int count = left.get(moveVal);
                if (count == 1) left.remove(moveVal);
                else left.put(moveVal, count - 1);
                leftSum -= moveVal;
                leftSize--;

                right.put(moveVal, right.getOrDefault(moveVal, 0) + 1);
                rightSum += moveVal;
                rightSize++;
            }
            while (leftSize < maxLeftSize && rightSize > 0) {
                int moveVal = right.firstKey();
                int count = right.get(moveVal);
                if (count == 1) right.remove(moveVal);
                else right.put(moveVal, count - 1);
                rightSum -= moveVal;
                rightSize--;

                left.put(moveVal, left.getOrDefault(moveVal, 0) + 1);
                leftSum += moveVal;
                leftSize++;
            }

            // 4. Record cost once the first full window is formed
            if (i >= x - 1) {
                long median = left.lastKey();
                // Cost = (median * leftSize - leftSum) + (rightSum - median * rightSize)
                long cost = (median * leftSize - leftSum) + (rightSum - median * rightSize);
                costs[i - x + 1] = cost;
            }
        }

        return costs;
    }
}
