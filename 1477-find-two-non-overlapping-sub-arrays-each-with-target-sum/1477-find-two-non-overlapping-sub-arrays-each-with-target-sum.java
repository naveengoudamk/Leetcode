class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;

        int[] best = new int[n];

        int INF = n + 1;

        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int left = 0;
        int sum = 0;
        int answer = INF;
        int minLength = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            while (sum > target && left <= right) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {

                int length = right - left + 1;

                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(
                        answer,
                        length + best[left - 1]
                    );
                }

                minLength = Math.min(minLength, length);
            }

            best[right] = minLength;
        }

        return answer == INF ? -1 : answer;
    }
}