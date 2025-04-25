package main.java.pramp;

import java.util.*;

public class BracketMatch {
    static int bracketMatch(String text) {
        // your code goes here
        if (text.length() <= 1) return 1;

        int count = 0;

        List<Integer> oList = new ArrayList<>();
        List<Integer> cList = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '(') oList.add(i);
            else cList.add(i);
        }

        // match (
        int p = 0;
        // match )
        int q = 0;
        while (p < oList.size() && q < cList.size()) {
            int oIndex = oList.get(p);
            int cIndex = cList.get(q);

            // matching here p < q
            if (oIndex < cIndex) {
                p++;
                q++;
            } else {
                q++;
                count++;
            }
        }

        count += cList.size() - p;
        count += oList.size() - q;

        return count;
    }

    static int countUnmatchedParentheses(String text) {
        int count = 0;
        int result = 0;

        // Java does not have a direct forEach method for strings,
        // so we convert the string to a char array and iterate over it.
        for (char c : text.toCharArray()) {
            if (c == '(') {
                count++;
            } else {
                count--;
            }

            if (count < 0) {
                result++;
                count = 0;
            }
        }

        // Return the sum of 'result' and 'count'
        return result + count;
    }

    public static void main(String[] args) {
        System.out.println(bracketMatch("(()"));
    }
}

