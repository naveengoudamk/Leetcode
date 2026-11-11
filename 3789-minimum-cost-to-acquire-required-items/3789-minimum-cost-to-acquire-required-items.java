class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long minCost = Long.MAX_VALUE;
        
        // Define the critical points of interest
        long[] criticalPoints = {
            0, 
            Math.min(need1, need2), 
            Math.max(need1, need2)
        };
        
        // Evaluate the cost function at each critical point
        for (long x : criticalPoints) {
            long remainingNeed1 = Math.max(0, need1 - x);
            long remainingNeed2 = Math.max(0, need2 - x);
            
            long currentCost = (x * costBoth) + (remainingNeed1 * cost1) + (remainingNeed2 * cost2);
            minCost = Math.min(minCost, currentCost);
        }
        
        return minCost;
    }
}
