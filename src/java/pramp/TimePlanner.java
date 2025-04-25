package java.pramp;

import java.util.Arrays;

public class TimePlanner {
    static int[] meetingPlanner(int[][] slotsA, int[][] slotsB, int dur) {
        // your code goes here
        int p = 0;
        int q = 0;
        while (p < slotsA.length && q < slotsB.length) {
            int beg1 = slotsA[p][0];
            int end1 = slotsA[p][1];
            int beg2 = slotsB[q][0];
            int end2 = slotsB[q][1];

            int maxBeg = Math.max(beg1, beg2);
            int minEnd = Math.min(end1, end2);
            // check for overlap
            if (minEnd > maxBeg) {
                int diff = minEnd - maxBeg;
                // we have an answer
                if (diff >= dur)
                    return new int[]{maxBeg, maxBeg + dur};

            }
            if (end1 <= end2) p++;
            else q++;
        }

        // no answer found
        return new int[0];
    }

    static int[] meetingPlanner2(int[][] slotsA, int[][] slotsB, int dur) {
        int p = 0;
        int q = 0;

        while (p < slotsA.length && q < slotsB.length) {
            int startA = slotsA[p][0];
            int endA = slotsA[p][1];
            int startB = slotsB[q][0];
            int endB = slotsB[q][1];

            // Find the overlap between slotsA[p] and slotsB[q]
            int overlapStart = Math.max(startA, startB);
            int overlapEnd = Math.min(endA, endB);

            // Check if there is an overlap and if it's long enough for the meeting
            if (overlapEnd - overlapStart >= dur) {
                return new int[]{overlapStart, overlapStart + dur};
            }

            // Move to the next slot in the list with the earlier end time
            if (endA <= endB) p++;
            else q++;
        }

        // If no suitable time slot is found
        return new int[0];
    }


    public static void main(String[] args) {
        int[][] slotsA = {{10, 50}, {60, 120}, {140, 210}};
        int[][] slotsB = {{0, 15}, {60, 70}};
        int dur = 8;
        System.out.println(Arrays.toString(meetingPlanner(slotsA, slotsB, dur)));

        slotsA = new int[][]{{10, 20}, {25, 35}};
        slotsB = new int[][]{{40, 50}, {60, 70}};
        dur = 10;
        System.out.println(Arrays.toString(meetingPlanner(slotsA, slotsB, dur)));

        slotsA = new int[][]{{10, 50}, {60, 120}, {140, 210}};
        slotsB = new int[][]{{0, 15}, {25, 55}, {70, 140}};
        dur = 30;
        System.out.println(Arrays.toString(meetingPlanner(slotsA, slotsB, dur)));
    }
}
