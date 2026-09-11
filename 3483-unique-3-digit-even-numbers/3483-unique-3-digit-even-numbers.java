class Solution {
    public int totalNumbers(int[] digits) {

        int[] freq = new int[10];

        // Count how many times each digit appears
        for (int digit : digits) {
            freq[digit]++;
        }

        int count = 0;

        // Try every 3-digit even number
        for (int num = 100; num <= 998; num += 2) {

            int ones = num % 10;
            int tens = (num / 10) % 10;
            int hundreds = num / 100;

            // Use a temporary frequency array
            int[] used = new int[10];

            used[hundreds]++;
            used[tens]++;
            used[ones]++;

            boolean possible = true;

            for (int d = 0; d <= 9; d++) {
                if (used[d] > freq[d]) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                count++;
            }
        }

        return count;
    }
}