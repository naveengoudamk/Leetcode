class Solution {
    public int longestOnes(int[] nums, int k) {

        // Left pointer of the sliding window
        int left = 0;

        // Number of zeros in the current window
        int zeroCount = 0;

        // Stores the maximum valid window length
        int maxLength = 0;

        // Expand the window using the right pointer
        for (int right = 0; right < nums.length; right++) {

            // If current element is 0, count it
            if (nums[right] == 0) {
                zeroCount++;
            }

            // If zeros exceed k, shrink the window
            while (zeroCount > k) {

                // If the outgoing element is 0,
                // reduce zero count
                if (nums[left] == 0) {
                    zeroCount--;
                }

                left++;
            }

            // Update maximum window size
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}