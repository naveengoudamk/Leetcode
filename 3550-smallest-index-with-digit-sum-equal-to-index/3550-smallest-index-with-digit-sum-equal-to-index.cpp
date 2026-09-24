class Solution {
public:
    int sum(int s){
        int j=0;
        while(s>0){
            j=j+s%10;
            s=s/10;
        }
        return j;
    }
    int smallestIndex(vector<int>& nums) {
        for(int i=0;i<nums.size();i++){
            int k=sum(nums[i]);
            if(k==i){
                return i;
            }
        }
        return -1;
    }
};