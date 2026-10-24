class Solution {
    private final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int startA = dateToDayOfYear(arriveAlice);
        int endA = dateToDayOfYear(leaveAlice);
        int startB = dateToDayOfYear(arriveBob);
        int endB = dateToDayOfYear(leaveBob);

        int latestStart = Math.max(startA, startB);
        int earliestEnd = Math.min(endA, endB);
        if (latestStart <= earliestEnd) {
            return earliestEnd - latestStart + 1;
        }
        
        return 0;
    }

    private int dateToDayOfYear(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int dayCount = 0;
        for (int i = 0; i < month - 1; i++) {
            dayCount += daysInMonth[i];
        }
        dayCount += day;

        return dayCount;
    }
}
