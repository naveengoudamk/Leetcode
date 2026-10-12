import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long MOD = 1_000_000_007;
        int[] bitCounts = new int[30];
        
        for (int num : nums) {
            for (int i = 0; i < 30; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCounts[i]++;
                }
            }
        }
        long totalSumOfSquares = 0;
        
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int b = 0; b < 30; b++) {
                if (bitCounts[b] > 0) {
                    currentNum |= (1L << b);
                    bitCounts[b]--;
                }
            }
            totalSumOfSquares = (totalSumOfSquares + (currentNum * currentNum) % MOD) % MOD;
        }
        
        return (int) totalSumOfSquares;
    }
}
