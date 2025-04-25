package com.vahoss;

import com.vahoss.MedianFinderInsertionSort;
import java.util.ArrayList;
import java.util.List;

public class MedianFinderJava {

    private List<Integer> numbers = new ArrayList<>();

    public void addNum(int num) {
        numbers.add(num);

        var i = numbers.size() - 2;
        while (i >= 0 && num < numbers.get(i)) {
            var temp = numbers.get(i);
            numbers.set(i, numbers.get(i+1));
            numbers.set(i + 1, temp);
            i--;
        }
    }

    public double findMedian() {
        int size = numbers.size();
        return size % 2 == 1 ? numbers.get(size / 2) : (numbers.get(size / 2 - 1) + numbers.get(size / 2)) / 2.0;
    }

    public static void main(String[] args) {
        MedianFinderInsertionSort medianFinder = new MedianFinderInsertionSort();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println("Median: " + medianFinder.findMedian()); // Output: Median: 1.5
        medianFinder.addNum(3);
        System.out.println("Median: " + medianFinder.findMedian()); // Output: Median: 2.0
    }
}


