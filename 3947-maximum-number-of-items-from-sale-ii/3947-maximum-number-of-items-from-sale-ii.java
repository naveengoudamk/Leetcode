import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] cntFactor = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        // Step 1: Count factor frequencies and find the minimum item price
        for (int[] item : items) {
            cntFactor[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] cntMulti = new int[n + 1];
        // Map price -> total number of 2-item deals available at this price
        TreeMap<Integer, Long> deals = new TreeMap<>();

        // Step 2: Calculate multiples using a Sieve approach and filter profitable items
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            // Only consider items cheaper than buying 2 of the cheapest item
            if ((long) price >= 2L * minPrice) {
                continue;
            }

            // Lazily compute how many items have a factor divisible by 'factor'
            if (cntMulti[factor] == 0) {
                for (int j = factor; j <= n; j += factor) {
                    cntMulti[factor] += cntFactor[j];
                }
            }

            // Number of distinct other items j that this item i can unlock for free
            int freeOpportunities = cntMulti[factor] - 1;
            if (freeOpportunities > 0) {
                deals.put(price, deals.getOrDefault(price, 0L) + freeOpportunities);
            }
        }

        // Step 3: Greedily buy the cheapest 2-for-1 deals first, then buy the cheapest item
        long totalItems = 0;
        long remainingBudget = budget;

        for (Map.Entry<Integer, Long> entry : deals.entrySet()) {
            long dealPrice = entry.getKey();
            long availableDeals = entry.getValue();

            long canBuy = Math.min(availableDeals, remainingBudget / dealPrice);
            totalItems += 2 * canBuy;
            remainingBudget -= canBuy * dealPrice;

            if (remainingBudget < dealPrice) {
                break;
            }
        }

        // Spend any leftover budget on single copies of the cheapest item
        totalItems += remainingBudget / minPrice;

        return (int) totalItems;
    }
}
