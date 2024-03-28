package pramp;

import java.util.HashMap;
import java.util.Map;

public class MergingTwoPackages {

    /**
     * Optimized solution
     *
     * @param arr
     * @param limit
     * @return
     */
    static int[] getIndicesOfItemWeights(int[] arr, int limit) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int complement = limit - arr[i];

            // Check if the complement of the current element exists in the map
            if (map.containsKey(complement)) {
                // Found the pair, return the indices
                // The current index (i) is always going to be greater than the index of its complement
                // because we're adding elements to the map in the same iteration.
                return new int[]{i, map.get(complement)};
            }

            // If not found, put the current element and its index in the map
            map.put(arr[i], i);
        }

        // Return an empty array if no pair is found
        return new int[]{};
    }

    static int[] getIndicesOfItemWeights2(int[] arr, int limit) {
        // your code goes here
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }

        for (int i = 0; i < arr.length; i++) {
            int currLim = limit - arr[i];
            if (map.containsKey(currLim) && i != map.get(currLim)) {
                if (i <= map.get(currLim))
                    return new int[]{map.get(currLim), i};
                else
                    return new int[]{i, map.get(currLim)};
            }
        }

        // empty array
        return new int[]{};
    }

}
