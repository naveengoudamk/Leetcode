class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int maxDiff = 100000;
        long[] count = new long[maxDiff + 1];
        long totalK = (long) k1 + k2;
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            count[diff]++;
        }
        
        for (int d = maxDiff; d > 0; d--) {
            if (count[d] == 0) continue;
            
            if (totalK >= count[d]) {
                totalK -= count[d];
                count[d - 1] += count[d];
                count[d] = 0;
            } else {
                count[d - 1] += totalK;
                count[d] -= totalK;
                totalK = 0;
                break; 
            }
        }
        
        long minSumSquares = 0;
        for (int d = 1; d <= maxDiff; d++) {
            if (count[d] > 0) {
                minSumSquares += count[d] * (long) d * d;
            }
        }
        
        return minSumSquares;
    }
}
