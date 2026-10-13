import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }
        
        Arrays.sort(squares);
        
        long maxScore = 0;
        int negativeCount = n / 2;
        
        for (int i = 0; i < negativeCount; i++) {
            maxScore -= squares[i];
        }
        
        for (int i = negativeCount; i < n; i++) {
            maxScore += squares[i];
        }
        return maxScore;
    }
}
