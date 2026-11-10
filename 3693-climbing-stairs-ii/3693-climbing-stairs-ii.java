class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            int currentStepCost = costs[i - 1];
            int minCost = dp[i - 1] + currentStepCost + 1;
            
            if (i >= 2) {
                minCost = Math.min(minCost, dp[i - 2] + currentStepCost + 4);
            }
            
            if (i >= 3) {
                minCost = Math.min(minCost, dp[i - 3] + currentStepCost + 9);
            }
            
            dp[i] = minCost;
        }
        
        return dp[n];
    }
}
