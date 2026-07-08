class Solution {
    public int maxOperations(int[] nums, int k) {



        Arrays.sort(nums);

        int i = 0 , j = nums.length-1 , count = 0;

        while(i<j)
        {
            int sum = nums[i] + nums[j];

            if(sum==k)
            {
                count++;
                i++;
                j--;
            }
            else if(sum<k)
            {
                i++;
            }
            else {
                j--;
            }
        }
            return count;
        }
} 

        // // Sort the array
        // Arrays.sort(nums);

        // int left = 0;
        // int right = nums.length - 1;

        // int count = 0;

        // while (left < right) {

        //     int sum = nums[left] + nums[right];

        //     // Pair found
        //     if (sum == k) {
        //         count++;
        //         left++;
        //         right--;
        //     }

        //     // Need a bigger sum
        //     else if (sum < k) {
        //         left++;
        //     }

        //     // Need a smaller sum
        //     else {
        //         right--;
        //     }
        // }

//         // return count;
//     }
// }