class Solution {
    public String getHappyString(int n, int k) {
        StringBuilder sb = new StringBuilder();
        char[] chars = {'a', 'b', 'c'};
        int total = 3 * (int) Math.pow(2, n - 1);
        if (k > total) return "";

        for (int i = 0; i < n; i++) {
            for (char c : chars) {
                if (i > 0 && sb.charAt(i - 1) == c) continue;
                int cnt = (int) Math.pow(2, n - i - 1);
                if (k > cnt) {
                    k -= cnt;
                } else {
                    sb.append(c);
                    break;
                }
            }
        }
        return sb.toString();
    }
}
