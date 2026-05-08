import java.util.*;

class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // Sieve for prime checking
        boolean[] isPrime = sieve(maxVal);

        // Map prime -> indices divisible by that prime
        Map<Integer, List<Integer>> divisibleMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            List<Integer> primeFactors = getPrimeFactors(num);

            for (int p : primeFactors) {
                divisibleMap
                        .computeIfAbsent(p, k -> new ArrayList<>())
                        .add(i);
            }
        }

        // BFS
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        Set<Integer> usedPrime = new HashSet<>();

        queue.offer(0);
        visited[0] = true;

        int jumps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int s = 0; s < size; s++) {
                int i = queue.poll();

                if (i == n - 1) {
                    return jumps;
                }

                // Adjacent left
                if (i - 1 >= 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    queue.offer(i - 1);
                }

                // Adjacent right
                if (i + 1 < n && !visited[i + 1]) {
                    visited[i + 1] = true;
                    queue.offer(i + 1);
                }

                // Prime teleportation
                int val = nums[i];

                if (val <= maxVal && isPrime[val] && !usedPrime.contains(val)) {

                    List<Integer> targets = divisibleMap.get(val);

                    if (targets != null) {
                        for (int idx : targets) {
                            if (!visited[idx]) {
                                visited[idx] = true;
                                queue.offer(idx);
                            }
                        }
                    }

                    // Use this prime only once
                    usedPrime.add(val);
                }
            }

            jumps++;
        }

        return -1;
    }

    // Sieve of Eratosthenes
    private boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];

        Arrays.fill(isPrime, true);

        if (n >= 0) isPrime[0] = false;
        if (n >= 1) isPrime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        return isPrime;
    }

    // Get unique prime factors
    private List<Integer> getPrimeFactors(int num) {
        List<Integer> factors = new ArrayList<>();

        for (int p = 2; p * p <= num; p++) {
            if (num % p == 0) {
                factors.add(p);

                while (num % p == 0) {
                    num /= p;
                }
            }
        }

        if (num > 1) {
            factors.add(num);
        }

        return factors;
    }
}