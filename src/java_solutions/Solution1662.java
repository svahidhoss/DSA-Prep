package java_solutions;

class Solution1662 {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int w1 = 0;
        int i1 = 0;
        int w2 = 0;
        int i2 = 0;
        String wordOne = "", wordTwo = "";

        try {
            while ((w1 < word1.length || i1 < wordOne.length()) || (w2 < word2.length || i2 < wordTwo.length())) {
                if (i1 >= wordOne.length()) {
                    wordOne = word1[w1++];
                    i1 = 0;
                }
                if (i2 >= wordTwo.length()) {
                    wordTwo = word2[w2++];
                    i2 = 0;
                }

                if (wordOne.charAt(i1++) != wordTwo.charAt(i2++))
                    return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

        return true;
    }

    public boolean arrayStringsAreEqual2(String[] word1, String[] word2) {
        var sb1 = new StringBuilder();
        for (String s1 : word1) {
            sb1.append(s1);
        }
        var sb2 = new StringBuilder();
        for (String s2 : word2) {
            sb2.append(s2);
        }

        return sb1.toString().equals(sb2.toString());
    }

    public boolean arrayStringsAreEqual3(String[] word1, String[] word2) {
        return (String.join("", word1).equals(String.join("", word2)));
    }
}
