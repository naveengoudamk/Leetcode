import java.util.*;

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            // Check if current number matches any previous reversed number
            if (map.containsKey(num)) {
                minDist = Math.min(minDist, i - map.get(num));
            }

            // Store reversed value
            int rev = reverse(num);
            map.put(rev, i);
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    private int reverse(int num) {
        int rev = 0;
        while (num > 0) {
            rev = rev * 10 + (num % 10);
            num /= 10;
        }
        return rev;
    }
}