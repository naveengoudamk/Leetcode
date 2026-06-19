class Solution {
    public int largestAltitude(int[] gain) {
        int max = 0, curr = 0;

        for (int g : gain) {
            curr += g;
            max = Math.max(max, curr);
        }
        return max;
    }
}
