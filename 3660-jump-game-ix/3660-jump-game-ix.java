class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int[] preMax = new int[n];

        // Step 1: Precompute prefix maximums
        preMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], nums[i]);
        }

        // Step 2: Iterate backward to determine max reachable values
        // We use 2^30 as a proxy for infinity for the suffix minimum
        int sufMin = 1 << 30; 
        for (int i = n - 1; i >= 0; i--) {
            // If the current prefix max is greater than a value to the right,
            // we can jump forward then backward to inherit the next's max reach.
            if (i < n - 1 && preMax[i] > sufMin) {
                ans[i] = ans[i + 1];
            } else {
                ans[i] = preMax[i];
            }
            // Update suffix minimum with current element
            sufMin = Math.min(sufMin, nums[i]);
        }

        return ans;
    }
}
