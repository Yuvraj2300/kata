package com.problems.crackcode.kata.apis;

import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StringApi2 {

    @Test
    @DisplayName("Test Print Freq In Alph Order")
    void testPrintFreqInAlphOrder() {
        String op = printFrequencyInAlphOrder("aaabb");
        Assertions.assertEquals("a3b2", op);
    }

    String printFrequencyInAlphOrder(String s) {
        char[] sA = s.toCharArray();
        int[] cA = new int[123];
        int i = 0;

        while (i < sA.length) {
            cA[sA[i]]++;
            i++;
        }

        i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < cA.length) {
            if (cA[i] > 0) {
                sb.append((char) i);
                sb.append(cA[i]);
            }
            i++;
        }

        return sb.toString();
    }


    @Test
    @DisplayName("Test Insert Character")
    void testInsertCharacter() {
        String updated = insertCharacter("yolo", new int[]{1});
        Assertions.assertEquals(updated, "y*olo");
    }


    @Test
    @DisplayName("Test Insert Character")
    void testInsertCharacter1() {
        String updated = insertCharacter("geeksforgeeks", new int[]{1, 5, 7, 9});
        Assertions.assertEquals(updated, "g*eeks*fo*rg*eekss");
    }


    @Test
    @DisplayName("Test Insert Character")
    void testInsertCharacter2() {
        String updated = insertCharacter("spacing", new int[]{0, 1, 2, 3, 4, 5, 6});
        Assertions.assertEquals(updated, "*s*p*a*c*i*n*g");
    }


    String insertCharacter(String s, int[] pos) {
        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();
        char[] cA = s.toCharArray();
        while (i < cA.length) {
            if (j < pos.length && pos[j] == i) {
                sb.append('*');
                j++;
            } else {
                sb.append(cA[i]);
                i++;
            }
        }
        return sb.toString();
    }


    @Test
    @DisplayName("Test Get Min Flips")
    void testGetMinFlips() {
        int flips = getMinFlipsForAlternatingBinary("1001");
        Assertions.assertEquals(2, flips);
    }


    @Test
    @DisplayName("Test Get Min Flips")
    void testGetMinFlips1() {
        int flips = getMinFlipsForAlternatingBinary("0001010111");
        Assertions.assertEquals(2, flips);
    }


    int getMinFlipsForAlternatingBinary(String s) {
        int min = Math.min(_minFlipsWithExpected(s, '0'), _minFlipsWithExpected(s, '1'));
        return min;
    }


    private static int _minFlipsWithExpected(String s, char expected) {
        int i = 0;
        int flips = 0;
        char[] chars = s.toCharArray();

        while (i < chars.length) {
            if (chars[i] == expected) {
                flips++;
            }
            expected = expected == '0' ? '1' : '0';
            i++;
        }
        return flips;
    }


    @Test
    @DisplayName("Convert Number To Binary")
    void convertNumberToBinary() {
        String s = convertANumberToBinary(5);
        Assertions.assertEquals("101", s);
    }


    String convertANumberToBinary(int n) {
        int x = n;
        StringBuilder sb = new StringBuilder();

        while (x != 0) {
            int rem = x % 2;
            sb.append(rem);
            x = x / 2;
        }
        return sb.toString();
    }


    @Test
    @DisplayName("Print SubStrings")
    void printSubStrings() {
        String[] expected = {"a", "ab", "abc", "b", "bc", "c",};
        String[] substrings = substringsOfString("abc");
        Assertions.assertArrayEquals(expected, substrings);
    }


    @Test
    @DisplayName("Print SubStrings")
    void printSubStrings1() {
        substringsOfString("abcd");
    }


    String[] substringsOfString(String s) {
        char[] a = s.toCharArray();
        List<String> lst = new LinkedList<>();

        int start = 0;
        while (start < a.length) {
            int end = start;
            while (end < a.length) {
                StringBuilder sb = new StringBuilder();
                int printer = start;
                while (printer <= end) {
                    sb.append(a[printer]);
                    printer++;
                }
                lst.add(sb.toString());
                end++;
            }
            start++;
        }
        System.out.println(lst);
        return lst.stream().toArray(i -> new String[i]);
    }


    @Test
    @DisplayName("TestGetStringSequences")
    void testGetStringSequences() {
        List<String> op = getSequencesOfString("abc");
        System.out.println(op);
    }

    //aaa
    @Test
    @DisplayName("TestGetStringSequences")
    void testGetStringSequences1() {
        //abc
        List<String> op = getSequencesOfString("aaa");
        System.out.println(op);
    }

    private List<String> getSequencesOfString(String s) {
        List<String> lst = new LinkedList<>();
        _subseqHelper(s, "", lst);
        return lst;
    }

    private void _subseqHelper(String s, String temp, List<String> lst) {
        if (Strings.isNullOrEmpty(s)) {
            if (!Strings.isNullOrEmpty(temp)) {
                lst.add(new String(temp));
            }
            return;
        } else {
            _subseqHelper(s.substring(1), temp + s.charAt(0), lst);
            _subseqHelper(s.substring(1), temp, lst);
        }
    }


    @Test
    @DisplayName("Test Get Number Of Distinct Subsequences")
    void testGetNumberOfDistinctSubsequences() {
        int count = getNumberOfDistinctSubsequences("banana", "ban");
        Assertions.assertEquals(3, count);
    }


    @Test
    @DisplayName("Test Get Number Of Distinct Subsequences")
    void testGetNumberOfDistinctSubsequences1() {
        int count = getNumberOfDistinctSubsequences("geeksforgeeks", "ge");
        Assertions.assertEquals(6, count);
    }


    int getNumberOfDistinctSubsequences(String s, String t) {
        String ans = "";
        List<Integer> lst = new ArrayList<>();
        _helperNumOfDistSubSeq(s, t, ans, lst);
        return lst.size();
    }

    private void _helperNumOfDistSubSeq(String s, String t, String ans, List<Integer> lst) {
        if (s.isEmpty()) {
            if (ans.equals(t)) {
                lst.add(1);
            }
            return;
        } else if (ans.equals(t)) {
            lst.add(1);
            return;
        }

        _helperNumOfDistSubSeq(s.substring(1), t, ans + s.charAt(0), lst);
        _helperNumOfDistSubSeq(s.substring(1), t, ans, lst);

    }

    @Test
    @DisplayName("Test Get Number Of Distinct Subsequences")
    void testGetNumberOfDistinctSubsequences_check() {
        int count = getNumberOfDistinctSubsequencescheck("banana", "ban");
        Assertions.assertEquals(3, count);
    }

    private int getNumberOfDistinctSubsequencescheck(String s, String t) {
        String ans = "";
        List<Integer> lst = new ArrayList<>();
        return _helperNumOfDistSubSeq1(s, t, ans, 0);
    }

    private int _helperNumOfDistSubSeq1(String s, String t, String ans, int count) {
        if (s.isEmpty()) {
            if (ans.equals(t)) {
                count++;
            }
            return count;
        } else if (ans.equals(t)) {
            count++;
            return count;
        }

        count = _helperNumOfDistSubSeq1(s.substring(1), t, ans + s.charAt(0), count) + _helperNumOfDistSubSeq1(s.substring(1), t, ans, count);
        return count;
    }


    @Test
    @DisplayName("Test Find the Superstring")
    void testFindTheSuperstring() {
        //This is a weird question tbh but it is interesting
        // @formatter:off
		/**
		 * Given a set of n strings arr[], find the smallest string that contains each string in the given set as substring. We may assume that no string in arr[] is substring of another string.
		 * Examples:
		 *
		 * Input:  arr[] = {"geeks", "quiz", "for"}
		 * Output: geeksquizfor
		 *
		 * Input:  arr[] = {"catg", "ctaagt", "gcta", "ttca", "atgcatc"}
		 * Output: gctaagttcatgcatc
		 * */
		// @formatter:on
        //		String[] arr = { "catgc", "ctaagt", "gcta", "ttca", "atgcatc" };
        String[] arr = {"ab", "bc", "cd"};
        //		String[] arr = { "geeks", "quiz", "for" };

        int len = arr.length;

        System.out.println("The Shortest Superstring is " + findShortestSuperstring(arr));
    }

    static String str = "";

    private String findShortestSuperstring(String[] a) {
        int k = a.length;

        while (k > 1) {
            String combinedString = "";
            int maxOverlap = Integer.MIN_VALUE;
            int leftIdx = 0;
            int rightIdx = 0;
            for (int i = 0; i < a.length; i++) {
                for (int j = i + 1; j < a.length; j++) {
                    StringOverlapWithMaxOverlap overlapObj = _findOverlap(a[i], a[j]);
                    if (maxOverlap < overlapObj.currOverlap) {
                        maxOverlap = overlapObj.currOverlap;
                        combinedString = overlapObj.combinedString;

                        leftIdx = i;
                        rightIdx = j;
                    }
                }
            }
            //reduce k once you have found overlaps for the first iteration
            k--;
            if (maxOverlap == Integer.MIN_VALUE) {
                //no overlap
                a[0] += a[k];
            } else {
                a[leftIdx] = combinedString;
                //	since the value at k might not have been used yet, so you must replace the used string
                //	at right index with the unused value.
                a[rightIdx] = a[k];
            }
        }

        return a[0];
    }

    private StringOverlapWithMaxOverlap _findOverlap(String s1, String s2) {
        int overlap = Integer.MIN_VALUE;

        String combinedString = "";
        int min = Math.min(s1.length(), s2.length());

        for (int i = 1; i < min; i++) {
            if (s1.substring(0, i).equals(s2.substring(s2.length() - i))) {
                if (i > overlap) {
                    overlap = i;
                    combinedString = s1 + s2.substring(i);
                }
            }
        }

        for (int i = 1; i < min; i++) {
            if (s1.substring(s1.length() - i).equals(s2.substring(0, i))) {
                if (i > overlap) {
                    overlap = i;
                    combinedString = s1 + s2.substring(i);
                }
            }
        }

        return new StringOverlapWithMaxOverlap(combinedString, overlap);
    }

    @Test
    @DisplayName("Test Get Numeric Values Before Alphabets")
    void testGetNumericValuesBeforeAlphabets() {
        String op = getNumericFirstThenAlph("abc34a63");
        Assertions.assertEquals("3463abca", op);
    }


    public String getNumericFirstThenAlph(String s) {
        int i = 0;
        char[] chars = s.toCharArray();

        StringBuilder sbNumeric = new StringBuilder();
        StringBuilder sbAlph = new StringBuilder();

        while (i < chars.length) {
            if (chars[i] > 47 && chars[i] < 58) {
                sbNumeric.append(chars[i]);
            } else {
                sbAlph.append(chars[i]);
            }
            i++;
        }

        String finalOp = sbNumeric.append(sbAlph).toString();
        return finalOp;
    }


    class StringOverlapWithMaxOverlap {
        public StringOverlapWithMaxOverlap(String combinedString, int currOverlap) {
            this.combinedString = combinedString;
            this.currOverlap = currOverlap;
        }

        String combinedString;
        int currOverlap;
    }


    /**
     * Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
     * cannot use additional data structures?
     */

    @Test
    @DisplayName("Test Is String Unique")
    void testIsStringUnique() {
        // Your test code here
        Assertions.assertTrue(isStringUnique("yuvraj"));
    }


    @Test
    @DisplayName("Test Is String Unique")
    void testIsStringUnique1() {
        // Your test code here
        Assertions.assertFalse(isStringUnique("abbc"));
    }

    boolean isStringUnique(String s) {
        char[] ca = s.toCharArray();
        //sort - quick
        _quickSortArray(ca);

        int i = 1;
        while (i < ca.length) {
            if (ca[i - 1] == ca[i]) {
                return false;
            }
            i++;
        }

        return true;
    }

    private void _quickSortArray(char[] ca) {
        int l = 0;
        int h = ca.length - 1;
        _qshelper(ca, l, h);
    }

    private void _qshelper(char[] ca, int l, int h) {
        if (l < h) {
            int pi = _partition(l, h, ca);
            _qshelper(ca, l, pi - 1);
            _qshelper(ca, pi + 1, h);
        }
    }

    private int _partition(int l, int h, char[] a) {
        int i = l;
        int k = l - 1;
        char pe = a[h];
        while (i < h) {
            if (a[i] < pe) {
                k++;
                _swap(a, i, k);
            }
            i++;
        }
        k++;
        _swap(a, k, h);
        return k;
    }

    private static void _swap(char[] a, int i, int k) {
        char temp = a[i];
        a[i] = a[k];
        a[k] = temp;
    }


    @Test
    @DisplayName("Test If Two Strings Are Permutations of Each Other")
    void testIfTwoStringsArePermutationsOfEachOther() {
        Assertions.assertTrue(checkIfStringsArePerms("god", "dog"));
        Assertions.assertTrue(checkIfStringsArePerms("listen", "silent"));
        Assertions.assertTrue(checkIfStringsArePerms("care", "race"));

        Assertions.assertFalse(checkIfStringsArePerms("bad", "dad "));
        Assertions.assertFalse(checkIfStringsArePerms("god", "doG"));

    }


    boolean checkIfStringsArePerms(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();
        _quickSortArray(a1);
        _quickSortArray(a2);

        int i = 0;
        while (i < a1.length) {
            if (a1[i] != a2[i]) return false;

            i++;
        }
        return true;
    }


    @Test
    @DisplayName("Test URLIFY")
    void testUrlify() {
        String op = URLifyZeroSpace("Mr John         ");
        Assertions.assertEquals("Mr%20John", op);
    }


    @Test
    @DisplayName("Test URLIFY")
    void testUrlify1() {
        String op = URLifyZeroSpace("Mr John Smith                    ");
        Assertions.assertEquals("Mr%20John%20Smith", op);
    }


    @Test
    @DisplayName("Test URLIFY")
    void testUrlify2() {
        String op = URLifyZeroSpace("Hi Hello Bye See You                                       ");
        Assertions.assertEquals("Hi%20Hello%20Bye%20See%20You", op);
    }


    String URLifyZeroSpace(String s) {
        char[] a = s.toCharArray();

        int lastCharAt = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != ' ') {
                lastCharAt = i;
            }
        }


        int i = 0;
        while (i < a.length) {
            if (i < lastCharAt) {
                if (a[i] == ' ') {
                    int j = lastCharAt;
                    while (j > i) {
                        a[j + 2] = a[j];
                        j--;
                    }
                    lastCharAt = lastCharAt + 2;
                    int x = 0;
                    while (x < 3) {
                        if (x == 0) a[i] = '%';
                        else if (x == 1) a[i] = '2';
                        else if (x == 2) a[i] = '0';

                        i++;
                        x++;
                    }

                }
            }
            i++;
        }

        return String.valueOf(a).trim();
    }


    @Test
    @DisplayName("Test If String is Perm of Palin")
    void testIfStringIsPermOfPalin() {
        Assertions.assertTrue(checkPalindromePerm("tact Coa"));
    }


    boolean checkPalindromePerm(String inp) {
        char[] a = inp.toLowerCase().toCharArray();
        int[] frq = new int[27];
        int i = 0;
        int actuallt = 0;
        int oddcount = 0;
        while (i < a.length) {
            if (a[i] != ' ') {
                frq[a[i] - 'a']++;
                if (frq[a[i] - 'a'] % 2 != 0) {
                    oddcount++;
                } else {
                    oddcount--;
                }
                actuallt++;
            }
            i++;
        }

        if (actuallt % 2 == 0) {
            return oddcount == 0 ? true : false;
        } else {
            return (oddcount == 1 && actuallt % 2 != 0) ? true : false;
        }

//        int oddsFound = 0;
//        for (int j = 0; j < frq.length; j++) {
//            if (frq[j] > 0) {
//                if (frq[j] % 2 != 0 && actuallt % 2 != 0 && oddsFound == 0) {
//                    oddsFound++;
//                }
//            }
//        }
//
//        if (actuallt % 2 == 0) {
//            return oddsFound == 0 ? true : false;
//        } else {
//            return (oddsFound == 1 && actuallt % 2 != 0) ? true : false;
//        }
    }


    @Test
    @DisplayName("Test If String is One Edit Away")
    void testIfStringIsOneEditAway() {
        Assertions.assertTrue(checkIfOneAway("pale", "pal"));
    }

    @Test
    @DisplayName("Test If String is One Edit Away")
    void testIfStringIsOneEditAway_1() {
        Assertions.assertTrue(checkIfOneAway("pale", "bale"));
    }

    @Test
    @DisplayName("Test If String is One Edit Away")
    void testIfStringIsOneEditAway_2() {
        Assertions.assertTrue(checkIfOneAway("pal", "pale"));
    }


    @Test
    @DisplayName("Test If String is One Edit Away")
    void testIfStringIsOneEditAway_3() {
        Assertions.assertFalse(checkIfOneAway("pale", "bake"));
    }

    boolean checkIfOneAway(String s1, String s2) {
        if ((s1.length() > s2.length() || s2.length() > s1.length()) && Math.abs(s1.length() - s2.length()) > 1) {
            return false;
        } else if (s1.length() == s2.length()) {
            int diff = 0, i = 0;
            char[] a1 = s1.toCharArray();
            char[] a2 = s2.toCharArray();

            while (i < a1.length) {
                if (a1[i] != a2[i]) {
                    diff++;
                    if (diff > 1) return false;
                }

                i++;
            }
        }

        return true;
    }


    @Test
    @DisplayName("Test Compress String")
    void testCompressString() {
        String updatedStr = compressString("aabcccccaaa");
        Assertions.assertEquals("a2b1c5a3", updatedStr);
    }


    String compressString(String s) {
        char[] a = s.toCharArray();
        int i = 1;
        int c = 1;
        StringBuilder sb = new StringBuilder();
        while (i < a.length) {
            if (a[i] == a[i - 1]) {
                c++;
                if (i == a.length - 1) {
                    sb.append(a[i]);
                    sb.append(c);
                }
            } else {
                sb.append(a[i - 1]);
                sb.append(c);
                c = 1;
            }

            i++;
        }

        String newStr = sb.toString();
        return newStr.length() < s.length() ? newStr : str;
    }


    @Test
    @DisplayName("TestIsRotation")
    void testIsRotation() {
        Assertions.assertTrue(checkIfBisRotationOfA("waterbottle", "erbottlewat"));
        Assertions.assertTrue(checkIfBisRotationOfA("abcd", "dabc"));
    }

    boolean checkIfBisRotationOfA(String s1, String s2) {
        //s1s2 -xy
        //s2s1 - yx
        //s1s2s2s1 - xyyx
        //check if yx is a substr of xyxy
        if (s1.length() == s2.length()) {
            return customIsSubString(s1 + s1, s2);
        }

        return false;
    }


    @Test
    @DisplayName("Test My Custom IsSubString")
    void testMyCustomIsSubString() {
        Assertions.assertTrue(customIsSubString("abcd", "bc"));
        Assertions.assertFalse(customIsSubString("abcd", "bd"));
    }


    boolean customIsSubString(String s1, String s2) {
        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();

        if (!(a2.length > a1.length)) {
            int i = 0;
            int j = 0;
            int foundIdx = -1;
            while (i < a1.length && j < a2.length) {
                if (a2[j] == a1[i]) {
                    j++;
                    if (foundIdx != -1) {
                        if (i - foundIdx != 1) {
                            return false;
                        }
                    }
                    foundIdx = i;
                }
                i++;
            }
            return j == a2.length;
        }

        return false;
    }


}

