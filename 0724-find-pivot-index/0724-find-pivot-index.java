class Solution {

    public int pivotIndex(int[] nums) {

        // Store total sum of the array
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        // Sum of elements to the left
        int leftSum = 0;

        // Traverse every index
        for (int i = 0; i < nums.length; i++) {

            // Calculate right sum
            int rightSum = totalSum - leftSum - nums[i];

            // Check pivot condition
            if (leftSum == rightSum) {
                return i;
            }

            // Include current element in left sum
            leftSum += nums[i];
        }

        // No pivot found
        return -1;
    }
}