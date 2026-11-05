import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int distanceValue = 0;
        
        for (int val : arr1) {
            if (!hasElementInRange(arr2, val - d, val + d)) {
                distanceValue++;
            }
        }
        
        return distanceValue;
    }
    
    private boolean hasElementInRange(int[] arr, int low, int high) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] >= low && arr[mid] <= high) {
                return true; 
            } else if (arr[mid] < low) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
