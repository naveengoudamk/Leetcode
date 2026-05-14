class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length - 1;

        int[] freq = new int[n + 1];

        for (int num : nums) {
            // numbers must be between 1 and n
            if (num < 1 || num > n) {
                return false;
            }
            freq[num]++;
        }

        // 1 to n-1 should appear exactly once
        for (int i = 1; i < n; i++) {
            if (freq[i] != 1) {
                return false;
            }
        }

        // n should appear exactly twice
        return freq[n] == 2;
    }
}