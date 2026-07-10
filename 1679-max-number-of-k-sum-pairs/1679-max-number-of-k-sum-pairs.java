class Solution {
    public int maxOperations(int[] nums, int k) {

        // Sort the array
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;

        int count = 0;

        while (left < right) {

            int sum = nums[left] + nums[right];

            // Pair found
            if (sum == k) {
                count++;
                left++;
                right--;
            }

            // Need a bigger sum
            else if (sum < k) {
                left++;
            }

            // Need a smaller sum
            else {
                right--;
            }
        }

        return count;
    }
}