import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 0;
        
        for (int x : nums) {
            int lenPlusOne = dp.getOrDefault(x, 0) + 1;
            
            int lenAsIs = dp.getOrDefault(x - 1, 0) + 1;
            
            dp.put(x + 1, Math.max(dp.getOrDefault(x + 1, 0), lenPlusOne));
            dp.put(x, Math.max(dp.getOrDefault(x, 0), lenAsIs));
            
            maxLen = Math.max(maxLen, Math.max(dp.get(x), dp.get(x + 1)));
        }
        
        return maxLen;
    }
}
