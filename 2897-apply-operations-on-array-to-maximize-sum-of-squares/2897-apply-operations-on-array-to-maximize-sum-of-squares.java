import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long MOD = 1_000_000_007;
        int[] bitCounts = new int[30]; // 10^9 < 2^30, so 30 bits are sufficient
        
        // Count the occurrences of each bit across all numbers
        for (int num : nums) {
            for (int i = 0; i < 30; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCounts[i]++;
                }
            }
        }
        
        long totalSumOfSquares = 0;
        
        // Greedily construct the k largest possible numbers
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int b = 0; b < 30; b++) {
                if (bitCounts[b] > 0) {
                    currentNum |= (1L << b);
                    bitCounts[b]--; // Consume one '1' bit for this position
                }
            }
            // Add the square of the constructed number to the total sum
            totalSumOfSquares = (totalSumOfSquares + (currentNum * currentNum) % MOD) % MOD;
        }
        
        return (int) totalSumOfSquares;
    }
}
