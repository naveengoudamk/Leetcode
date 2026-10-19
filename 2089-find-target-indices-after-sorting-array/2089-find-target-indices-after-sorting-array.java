import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int lessThanTarget = 0;
        int equalToTarget = 0;
        
        for (int num : nums) {
            if (num < target) {
                lessThanTarget++;
            } else if (num == target) {
                equalToTarget++;
            }
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < equalToTarget; i++) {
            result.add(lessThanTarget + i);
        }
        
        return result;
    }
}
