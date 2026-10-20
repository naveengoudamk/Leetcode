import java.util.ArrayList;
import java.util.List;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
            }
        }
        
        for (int k : divisors) {
            if (isValid(nums, n, k)) {
                sum += k;
            }
        }
        
        return sum;
    }
    
    private boolean isValid(int[] nums, int n, int k) {
        int prevMax = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i += k) {
            int currentMin = Integer.MAX_VALUE;
            int currentMax = Integer.MIN_VALUE;
            int drops = 0;
            
            for (int j = 0; j < k; j++) {
                int currIdx = i + j;
                int nextIdx = i + (j + 1) % k;
                
                currentMin = Math.min(currentMin, nums[currIdx]);
                currentMax = Math.max(currentMax, nums[currIdx]);
                
                if (nums[currIdx] > nums[nextIdx]) {
                    drops++;
                }
            }
            
            if (drops > 1) {
                return false;
            }
            
            if (currentMin < prevMax) {
                return false;
            }
            
            prevMax = currentMax;
        }
        
        return true;
    }
}
