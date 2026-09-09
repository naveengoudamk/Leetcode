class Solution {
    public long countCommas(long n) {
        long ans = 0;

        long start = 1000;

        while (start <= n) {
            ans += n - start + 1;

            start *= 1000;
        }

        return ans;
    }
}