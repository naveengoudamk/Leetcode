class Solution {

    public int longestSubarray(int[] nums) {

        int left = 0;

        // Number of zeros inside current window
        int zeroCount = 0;

        int max = 0;

        // Expand window
        for (int right = 0; right < nums.length; right++) {

            // Count zero
            if (nums[right] == 0) {
                zeroCount++;
            }

            // Window is invalid if it has more than one zero
            while (zeroCount > 1) {

                if (nums[left] == 0) {
                    zeroCount--;
                }

                left++;
            }

            // Delete one element
            max = Math.max(max, right - left);
        }

        return max;
    }
}