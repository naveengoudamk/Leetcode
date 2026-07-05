class Solution {
    public double findMaxAverage(int[] nums, int k) {

        // Calculate sum of first window
        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        // Maximum sum seen so far
        int maxSum = sum;

        // Slide the window
        for (int i = k; i < nums.length; i++) {

            // Remove left element
            sum -= nums[i - k];

            // Add new right element
            sum += nums[i];

            // Update maximum sum
            maxSum = Math.max(maxSum, sum);
        }

        // Convert to double to get average
        return (double) maxSum / k;
    }
}