class Solution {
    public int totalWaviness(int num1, int num2) {
        int total = 0;

        for (int num = num1; num <= num2; num++) {
            total += getWaviness(num);
        }

        return total;
    }

    private int getWaviness(int num) {
        String s = String.valueOf(num);

        if (s.length() < 3) {
            return 0;
        }

        int count = 0;

        for (int i = 1; i < s.length() - 1; i++) {
            char prev = s.charAt(i - 1);
            char curr = s.charAt(i);
            char next = s.charAt(i + 1);

            if ((curr > prev && curr > next) ||
                (curr < prev && curr < next)) {
                count++;
            }
        }

        return count;
    }
}