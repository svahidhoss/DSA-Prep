package pramp;

import java.util.Arrays;

public class ShiftedArraySearch {
    static int shiftedArrSearch2(int[] shiftArr, int num) {
        for (int i = 0; i < shiftArr.length; i++) {
            if (shiftArr[i] == num) return i;
        }

        return -1;
    }

    static int shiftedArrSearch(int[] shiftArr, int num) {
        // 1. find the offset point:
        int p = 0;
        int q = shiftArr.length;
        int m = 0;

        while (p <= q) {
            m = p + (q - p) / 2;
            int mid = shiftArr[m];

            if (m > 0 && mid < shiftArr[m - 1]) break; // m is the offset point
            if (mid < shiftArr[p]) p = m + 1;
            else q = m - 1;
        }

        // 2. Find the correct portion to do bin search
        if (num >= shiftArr[m] && num <= shiftArr[shiftArr.length - 1]) {
            p = m;
            q = shiftArr.length;
        } else {
            p = 0;
            q = m;
        }

        // 3. Do the bin search on the correct portion and return the index
        int index = Arrays.binarySearch(shiftArr, p, q, num);
        return index >= 0 ? index : -1;
    }

    public static void main(String[] args) {
        System.out.println(shiftedArrSearch(new int[]{2}, 2));
        System.out.println(shiftedArrSearch(new int[]{1, 2}, 2));
        System.out.println(shiftedArrSearch(new int[]{0, 1, 2, 3, 4, 5}, 1));
        // TODO fix this one
        System.out.println(shiftedArrSearch(new int[]{1, 2, 3, 4, 5, 0}, 0));
        System.out.println(shiftedArrSearch(new int[]{9, 12, 17, 2, 4, 5}, 17));
        System.out.println(shiftedArrSearch(new int[]{9, 12, 17, 2, 4, 5, 6}, 4));
    }
}
