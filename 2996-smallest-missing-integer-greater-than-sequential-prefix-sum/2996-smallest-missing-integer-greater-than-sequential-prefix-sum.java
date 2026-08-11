import java.util.*;

class Solution {
    public int missingInteger(int[] nums) {

        int sum = nums[0];

        // Find the longest sequential prefix
        for (int i = 1; i < nums.length; i++) {

            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        // Store all elements
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        // Find the smallest missing number >= sum
        while (set.contains(sum)) {
            sum++;
        }

        return sum;
    }
}