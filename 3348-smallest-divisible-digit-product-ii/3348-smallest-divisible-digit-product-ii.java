import java.util.*;

public class Solution {
    public String smallestNumber(String num, long t) {
        // 1. Validate prime factors of t
        long temp = t;
        int[] primes = {2, 3, 5, 7};
        for (int p : primes) {
            while (temp % p == 0) {
                temp /= p;
            }
        }
        if (temp > 1) {
            return "-1";
        }

        int n = num.length();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) {
            digits[i] = num.charAt(i) - '0';
        }

        // 2. Try to keep a prefix of num as long as possible
        // We will precalculate the required suffix factors needed after prefix i
        long[] requiredT = new long[n + 1];
        requiredT[0] = t;
        
        // Find the first position containing a 0, if any
        int firstZero = -1;
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) {
                firstZero = i;
                break;
            }
        }

        int limit = (firstZero == -1) ? n : firstZero;
        for (int i = 0; i < limit; i++) {
            long currentT = requiredT[i];
            long gcd = gcd(currentT, digits[i]);
            requiredT[i + 1] = currentT / gcd;
        }

        // If num itself is valid (no zeros and product divisible by t)
        if (firstZero == -1 && requiredT[n] == 1) {
            return num;
        }

        // 3. Backtrack from right to left to find the pivot
        for (int i = n - 1; i >= 0; i--) {
            // If we are past a zero digit, we cannot match this prefix anymore
            if (firstZero != -1 && i > firstZero) {
                continue;
            }

            int startDigit = digits[i] + 1;
            for (int d = startDigit; d <= 9; d++) {
                long remT = requiredT[i];
                long gcd = gcd(remT, d);
                remT /= gcd;

                int remLen = n - 1 - i;
                if (canForm(remT, remLen)) {
                    // Found the smallest valid digit configuration
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < i; j++) {
                        sb.append(digits[j]);
                    }
                    sb.append(d);
                    sb.append(getSmallestSuffix(remT, remLen));
                    return sb.toString();
                }
            }
        }

        // 4. If no same-length number works, expand the length
        int newLen = n + 1;
        while (true) {
            if (canForm(t, newLen)) {
                return getSmallestSuffix(t, newLen);
            }
            newLen++;
        }
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // Helper to check if remT can be completely factored into 'len' single digits
    private boolean canForm(long remT, int len) {
        int count2 = 0, count3 = 0, count5 = 0, count7 = 0;
        long temp = remT;
        while (temp % 2 == 0) { count2++; temp /= 2; }
        while (temp % 3 == 0) { count3++; temp /= 3; }
        while (temp % 5 == 0) { count5++; temp /= 5; }
        while (temp % 7 == 0) { count7++; temp /= 7; }

        if (temp > 1) return false;

        // Greedy optimization to pack factors into largest possible digits (9, 8, 6, 4)
        int digitsNeeded = count7 + count5;
        
        // Pack 3s into 9s
        int n9 = count3 / 2;
        int rem3 = count3 % 2;
        digitsNeeded += n9;

        // Pack 2s into 8s
        int n8 = count2 / 3;
        int rem2 = count2 % 3;
        digitsNeeded += n8;

        // Combine remaining 2 and 3 into 6
        if (rem2 > 0 && rem3 > 0) {
            digitsNeeded += 1;
            rem2--;
            rem3--;
        }
        
        // Remaining 3 goes to a single digit '3'
        digitsNeeded += rem3;

        // Remaining 2s go to '4' or '2'
        if (rem2 == 2) {
            digitsNeeded += 1; // digit 4
        } else if (rem2 == 1) {
            digitsNeeded += 1; // digit 2
        }

        return digitsNeeded <= len;
    }

    // Generates the smallest lexicographical valid suffix of length 'len'
    private String getSmallestSuffix(long remT, int len) {
        int count2 = 0, count3 = 0, count5 = 0, count7 = 0;
        long temp = remT;
        while (temp % 2 == 0) { count2++; temp /= 2; }
        while (temp % 3 == 0) { count3++; temp /= 3; }
        while (temp % 5 == 0) { count5++; temp /= 5; }
        while (temp % 7 == 0) { count7++; temp /= 7; }

        // Combine to form the largest possible digits to minimize count,
        // and fill the rest with '1's at the front to maintain the smallest number value.
        List<Integer> targetDigits = new ArrayList<>();
        while (count7 > 0) { targetDigits.add(7); count7--; }
        while (count5 > 0) { targetDigits.add(5); count5--; }
        while (count3 >= 2) { targetDigits.add(9); count3 -= 2; }
        while (count2 >= 3) { targetDigits.add(8); count2 -= 3; }
        
        if (count2 > 0 && count3 > 0) {
            targetDigits.add(6);
            count2--;
            count3--;
        }
        while (count3 > 0) { targetDigits.add(3); count3--; }
        if (count2 == 2) {
            targetDigits.add(4);
        } else if (count2 == 1) {
            targetDigits.add(2);
        }

        Collections.sort(targetDigits);

        StringBuilder sb = new StringBuilder();
        int ones = len - targetDigits.size();
        for (int i = 0; i < ones; i++) {
            sb.append('1');
        }
        for (int d : targetDigits) {
            sb.append(d);
        }
        return sb.toString();
    }
}
