class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        
        int k = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                k = i;
                break;
            }
        }
        
        boolean isIncreasing = true;
        for (int i = 0; i < n - 1; i++) {
            int currentIdx = (k + i) % n;
            int nextIdx = (k + i + 1) % n;
            if (nums[nextIdx] != nums[currentIdx] + 1) {
                isIncreasing = false;
                break;
            }
        }
        
        if (isIncreasing) {
            return Math.min(k, 2 + n - k);
        }
        
        boolean isDecreasing = true;
        for (int i = 0; i < n - 1; i++) {
            int currentIdx = (k - i + n) % n;
            int nextIdx = (k - i - 1 + n) % n;
            if (nums[nextIdx] != nums[currentIdx] + 1) {
                isDecreasing = false;
                break;
            }
        }
        
        if (isDecreasing) {
            return Math.min(n - k, k + 2);
        }
        
        return -1;
    }
}
