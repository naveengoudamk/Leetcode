class Solution {

    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int[] ans = {-1, -1};

        // Need at least 3 nodes to have a critical point
        if (head == null || head.next == null || head.next.next == null) {
            return ans;
        }

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;

        int firstCritical = -1;
        int prevCritical = -1;

        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;

        while (curr.next != null) {

            ListNode next = curr.next;

            // Check whether current node is a critical point
            boolean isCritical =
                    (curr.val > prev.val && curr.val > next.val) ||
                    (curr.val < prev.val && curr.val < next.val);

            if (isCritical) {

                // First critical point
                if (firstCritical == -1) {
                    firstCritical = index;
                }

                // Distance from previous critical point
                if (prevCritical != -1) {
                    int distance = index - prevCritical;

                    minDistance = Math.min(minDistance, distance);
                }

                prevCritical = index;

                // Distance from first critical point
                maxDistance = index - firstCritical;
            }

            prev = curr;
            curr = next;
            index++;
        }

        // Fewer than two critical points
        if (prevCritical == firstCritical) {
            return ans;
        }

        return new int[] {minDistance, maxDistance};
    }
}