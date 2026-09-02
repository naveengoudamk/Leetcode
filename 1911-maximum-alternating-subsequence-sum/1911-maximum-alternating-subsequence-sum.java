class Solution {
    public long maxAlternatingSum(int[] nums) {

        long sum = nums[0];

        for(int i = 1; i < nums.length; i++){
            if(nums[i]>nums[i-1]){
                sum+= nums[i] - nums[i -1];
            }
        }
        return sum;
    }
}