import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {

        int[] count = new int[51];

        // Count how many k-sized subarrays
        // contain each number.
        for (int i = 0; i <= nums.length - k; i++) {

            boolean[] seen = new boolean[51];

            for (int j = i; j < i + k; j++) {

                int num = nums[j];

                // Count each number only once per subarray
                if (!seen[num]) {
                    count[num]++;
                    seen[num] = true;
                }
            }
        }

        // Find the largest number appearing
        // in exactly one k-sized subarray.
        int answer = -1;

        for (int num = 0; num <= 50; num++) {
            if (count[num] == 1) {
                answer = num;
            }
        }

        return answer;
    }
}