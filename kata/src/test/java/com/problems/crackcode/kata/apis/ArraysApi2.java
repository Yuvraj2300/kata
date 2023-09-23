package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ArraysApi2 {

    @Test
    void testAllRoation() throws Exception {
        allRotations("geeks");
    }

    private void allRotations(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        while (i < s.length()) {
            int j = chars.length - 1;
            char last = s.charAt(s.length() - 1);
            while (j >= 0) {
                if (j == 0) {
                    chars[j] = last;
                } else {
                    chars[j] = chars[j - 1];
                }
                j--;
            }
            i++;
        }

    }


    @Test
    void testTwoSumPair() throws Exception {
        int A[] = {1, 4, 45, 6, 10, -8};
        int sum = 14;

        assertTrue(twoSumPair(A, sum));
    }


    @Test
    void testTwoSumPair_1() throws Exception {
        int A[] = {0, -1, 2, -3, 1};
        int sum = -2;

        assertTrue(twoSumPair(A, sum));
    }


    boolean twoSumPair(int[] arr, int sum) {
        //binary search the complement

        Arrays.sort(arr);
        int i = 0;
        while (i < arr.length) {
            int keyToSearch = sum - arr[i];

            if (binarySearchAKeyPresence(keyToSearch, i + 1, arr.length - 1, arr)) return true;

            i++;
        }

        return false;
    }


    private boolean binarySearchAKeyPresence(int key, int low, int high, int[] arr) {
        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] == key) {
                return true;
            }

            if (key > arr[mid]) return binarySearchAKeyPresence(key, mid + 1, high, arr);
            else return binarySearchAKeyPresence(key, low, mid - 1, arr);
        }

        return false;
    }


    @Test
    void testGetMajElement() throws Exception {
        int[] A = {1, 1, 2, 1, 3, 5, 1};
        assertEquals(1, getTheMajEelement(A));
    }


    @Test
    void testGetMajElement_1() throws Exception {
        int[] A = {3, 3, 4, 2, 4, 4, 2, 4, 4};
        assertEquals(4, getTheMajEelement(A));
    }


    private int getTheMajEelement(int[] a) {
        int i = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while (i < a.length) {
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
            i++;
        }

        int arrLt = a.length;

        return map.entrySet().stream().filter(e -> e.getValue() > arrLt / 2).findAny().get().getKey();
    }


    @Test
    void testGetEquilibIndex() throws Exception {
        int[] A = {-7, 1, 5, 2, -4, 3, 0};
        assertEquals(3, getEquilibIndex(A));
    }


    @Test
    void testGetEquilibIndex_1() throws Exception {
        int[] A = {1, 2, 3};
        assertEquals(-1, getEquilibIndex(A));
    }

    @Test
    void testGetEquilibIndex_2() throws Exception {
        int[] A = {-1, 3, -4, 5, 1, -6, 2, 1};
        assertEquals(1, getEquilibIndex(A));
    }


    //Since the array can have multiple equilib indeices, we will consider the first one from the left
    int getEquilibIndex(int[] a) {
        int lsum = 0;
        int rsum = 0;
        int tsum = 0;
        for (int i = 0; i < a.length; i++) {
            tsum += a[i];
        }
        rsum = tsum;
        int i = 0;
        while (i < a.length) {
            rsum -= a[i];
            if (lsum == rsum) {
                return i;
            }
            lsum += a[i];
            i++;
        }
        return -1;
    }


    @Test
    void testFindPeak() throws Exception {
        int arr[] = {1, 3, 20, 4, 1, 0};
        assertEquals(20, findPeak(arr));

        int arr2[] = {5, 10, 20, 15};
        assertEquals(20, findPeak(arr));
    }


    int findPeak(int[] arr) {
        int l = 0;
        int h = arr.length - 1;

        return _binSrchPeak(arr, l, h);

    }

    private int _binSrchPeak(int[] arr, int l, int h) {
        int mid = (l + h) / 2;

        if (arr[mid + 1] < arr[mid] && arr[mid - 1] < arr[mid]) {
            return arr[mid];
        } else if (arr[mid + 1] < arr[mid]) {
            return _binSrchPeak(arr, mid + 1, arr.length - 1);
        } else {
            return _binSrchPeak(arr, 0, mid - 1);
        }
    }

    @Test
    void testFindSubArray() throws Exception {
        int[] a = {1, 4, 20, 3, 10, 5};
        int sum = 33;

        SubArrayWindow subArray = findSubArray(a, sum);
        assertNotNull(subArray);
        assertEquals(2, subArray.getStart());
        assertEquals(4, subArray.getEnd());
    }


    @Test
    void testFindSubArray_1() throws Exception {
        int[] a = {1, 4, 0, 0, 3, 10, 5};
        int sum = 7;

        SubArrayWindow subArray = findSubArray(a, sum);
        assertNotNull(subArray);
        assertEquals(1, subArray.getStart());
        assertEquals(4, subArray.getEnd());
    }


    @Test
    void testFindSubArray_2() throws Exception {
        int[] a = {1, 4};
        int sum = 0;

        SubArrayWindow subArray = findSubArray(a, sum);
        assertEquals(0, subArray.getStart());
        assertEquals(0, subArray.getEnd());
    }

    @Test
    void testFindSubArray_3() throws Exception {
        int[] a = {1, 4, 5, 4, 6, 2};
        int sum = 9;

        SubArrayWindow subArray = findSubArray(a, sum);
        assertEquals(1, subArray.getStart());
        assertEquals(2, subArray.getEnd());
    }


    SubArrayWindow findSubArray(int[] a, int sum) {
        SubArrayWindow subArrayWindow = new SubArrayWindow();
        int ts = 0;
        int i = 0;
        int j = 0;

        while (i < a.length && ts < sum) {
            ts += a[i];
            i++;
        }

        if (ts == sum) {
            subArrayWindow.setStart(j);
            subArrayWindow.setEnd(i);
            return subArrayWindow;
        }

        while (j < a.length && ts > sum) {
            ts -= a[j];
            j++;
        }

        if (ts == sum) {
            subArrayWindow.setStart(j);
            subArrayWindow.setEnd(i - 1);
            return subArrayWindow;
        }

        return subArrayWindow;
    }


    class SubArrayWindow {
        int start;
        int end;

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        @Override
        public String toString() {
            return "SubArrayWindow [start=" + start + ", end=" + end + "]";
        }


    }


    @Test
    void testSortAlternatively() throws Exception {
        int[] a = {7, 1, 2, 3, 4, 5, 6};

        int[] sol = sortAlternatively(a);

        for (int i : sol) {
            System.out.print(i + ", ");
        }
    }


    @Test
    void testSortAlternatively_1() throws Exception {
        int[] a = {1, 6, 9, 4, 3, 7, 8, 2};

        int[] sol = sortAlternatively(a);

        for (int i : sol) {
            System.out.print(i + ", ");
        }
    }


    int[] sortAlternatively(int[] a) {
        Arrays.sort(a);
        int[] temp = new int[a.length];
        int k = 0;

        int i = 0;
        int j = a.length - 1;

        while (i <= j && k < temp.length) {
            temp[k++] = a[j--];
            if (k < temp.length) {
                temp[k++] = a[i++];
            }
        }

        return temp;
    }


    //Given an unsorted array of integers, sort the array into a wave array. An array arr[0..n-1] is sorted in wave form if:
    //arr[0] >= arr[1] <= arr[2] >= arr[3] <= arr[4] >= …..
    @Test
    void testSortInWaveForm() throws Exception {
        int[] expected = {10, 5, 6, 2, 20, 3, 100, 80};
        int[] a = {10, 5, 6, 3, 2, 20, 100, 80};
        int[] sortedArr = sortInWaveForm(a);

        assertArrayEquals(expected, a);
    }


    @Test
    void testSortInWaveForm_1() throws Exception {
        int[] expected = {90, 10, 49, 1, 5, 2, 23};
        int[] a = {10, 90, 49, 2, 1, 5, 23};
        int[] sortedArr = sortInWaveForm(a);

        assertArrayEquals(expected, a);
    }


    int[] sortInWaveForm(int[] a) {
        int i = 1;
        while (i < a.length) {
            if (!(a[i] <= a[i - 1])) {
                swap(a, i, i - 1);
            }
            if (i < a.length - 1 && !(a[i] <= a[i + 1])) {
                swap(a, i, i + 1);
            }
            i += 2;
        }

        return a;
    }


    @Test
    void testSortInWaveForm2_1() throws Exception {
        int[] a = {10, 5, 6, 3, 2, 20, 100, 80};
        int[] sortedArr = sortInWaveForm(a);

        for (int i : sortedArr) {
            System.out.print(i + ", ");
        }
    }


    @Test
    void testSortInWaveForm2_2() throws Exception {
        int[] a = {10, 90, 49, 2, 1, 5, 23};
        int[] sortedArr = sortInWaveForm(a);

        for (int i : sortedArr) {
            System.out.print(i + ", ");
        }
    }


    int[] sortInWaveForm_1(int[] a) {
        int i = 0;

        while (i < a.length) {
            if (i > 0 && !(a[i - 1] > a[i])) {
                _swap(a, i);
                if (i < a.length - 1 && !(a[i] < a[i + 1])) {
                    _swap(a, i);
                }
            }

            i += 2;
        }
        return a;
    }


    private void _swap(int[] a, int i) {
        int temp = a[i];
        a[i] = a[i + 1];
        a[i + 1] = temp;
    }


    @Test
    void testGetArraySortedBy() throws Exception {
        int[] expected = {8, 8, 8, 2, 2, 5, 5, 6};
        int[] a = {2, 5, 2, 8, 5, 6, 8, 8};
        int[] sorted = getArraySortedByFreq(a);
        assertArrayEquals(expected, sorted);
    }


    @Test
    void testGetArraySortedBy1() throws Exception {
        int[] expected = {4, 4, 4, 4, 4, 1, 1, 2};
        int[] a = {4, 4, 4, 4, 4, 2, 1, 1};
        int[] sorted = getArraySortedByFreq(a);
        assertArrayEquals(expected, sorted);
    }


    //this logic is only good for arrays that do not ahve BIG numbers in the like 999999999 due to the extra frq array we are creating
    // and positive numbers only , otherwise we need to write some way by whihc we can store negative numerb freq as well
    private int[] getArraySortedByFreq(int[] a) {
        int MAX = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            MAX = a[i] > MAX ? a[i] : MAX;
        }
        int[] frq = new int[MAX + 1];

        for (int i = 0; i < a.length; i++) {
            frq[a[i]]++;
        }

        //this is a partition logic to partition the array based on the condition of frequency
        //ofocurse we need to partinition the halves hence the below code :
        int l = 0;
        int h = a.length - 1;
        _helperPartitionBasedOnFreq(l, h, a, frq);

        return a;
    }


    private void _helperPartitionBasedOnFreq(int l, int h, int[] a, int[] frq) {
        if (l < h) {
            int pi = _partitionOnFrq(l, h, a, frq);
            _helperPartitionBasedOnFreq(l, pi - 1, a, frq);
            _helperPartitionBasedOnFreq(pi + 1, h, a, frq);
        }
    }


    private int _partitionOnFrq(int l, int h, int[] a, int[] frq) {
        int k = l - 1;
        int i = l;
        while (i < h) {
            if (frq[a[i]] > frq[a[h]]) {
                k++;
                swap(a, i, k);
            } else if (frq[a[i]] == frq[a[h]]) {
                if (a[i] == a[h]) {
                    k++;
                    swap(a, i, k);
                }
            }
            i++;
        }
        k++;
        swap(a, k, h);
        return k;
    }


    @Test
    void testGetArraySortedBy_1() throws Exception {
        int[] a = {2, 5, 2, 6, -1, 9999999, 5, 8, 8, 8};
        int[] expected = {8, 8, 8, 2, 2, 5, 5, 6, -1, 9999999};
        getArraySortedByFreq_ByStreamsAndHashMap(a);
        //int[] sorted = getArraySortedByFreq_ByStreamsAndHashMap(a);
        //		assertArrayEquals(expected, sorted);
    }


    void getArraySortedByFreq_ByStreamsAndHashMap(int[] a) {
        Map<Integer, Integer> hm = new HashMap<>();
        int i = 0;

        while (i < a.length) {
            hm.put(a[i], hm.getOrDefault(a[i], 0) + 1);
            i++;
        }

        // @formatter:off
		Map<Integer, Integer> valueSortedMap =
				hm.entrySet().stream()
						.sorted(Collections.reverseOrder(Entry.comparingByValue()))
						.collect(
								Collectors.toMap(
										Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


		// @formatter:on

        for (Map.Entry<Integer, Integer> e : valueSortedMap.entrySet()) {
            int j = 0;
            while (j < e.getValue()) {
                System.out.print(e.getKey() + ", ");
                j++;
            }
        }
    }


    @Test
    void testMergeSortedArraysContent() throws Exception {
        int[] expectedA = {1, 2, 3, 5, 8, 9};
        int[] expectedB = {10, 13, 15, 20};

        int[] a = {1, 5, 9, 10, 15, 20};
        int[] b = {2, 3, 8, 13};

        mergeSortedArraysContents(a, b);

        for (int i : a) {
            System.out.print(i + ", ");
        }
        System.out.println();
        for (int i : b) {
            System.out.print(i + ", ");
        }

        Assertions.assertArrayEquals(expectedA, a);
        Assertions.assertArrayEquals(expectedB, b);
    }


    @Test
    void testMergeSortedArraysContent_1() throws Exception {
        int[] expectedA = {2};
        int[] expectedB = {3, 10};

        int[] a = {10};
        int[] b = {2, 3};
        mergeSortedArraysContents(a, b);

        for (int i : a) {
            System.out.print(i + ", ");
        }
        System.out.println();
        for (int i : b) {
            System.out.print(i + ", ");
        }

        Assertions.assertArrayEquals(expectedA, a);
        Assertions.assertArrayEquals(expectedB, b);
    }


    //when it comes to sorting and swapping always do from the posterior end :P
    void mergeSortedArraysContents(int[] a, int[] b) {
        int j = b.length - 1;
        while (j >= 0) {
            int temp = a[a.length - 1];
            int i = a.length - 2;
            if (b[j] < temp) {
                while (i >= 0 && a[i] > b[j]) {
                    a[i + 1] = a[i];
                    i--;
                }
                a[i + 1] = b[j];
            }
            b[j] = temp;
            j--;
        }
    }

    @Test
    void testMergeSortedArrays() throws Exception {
        int[] a = {1, 2, 3, 0, 0, 0};
        int[] b = {2, 5, 6};

        int[] mergedArr = mergeSortedArraysWithBuffer(a, b);

        for (int i : mergedArr) {
            System.out.print(i + ", ");
        }
    }


    @Test
    void testMergeSortedArrays_1() throws Exception {
        int[] a = {10, 12, 13, 14, 18, 0, 0, 0, 0, 0};
        int[] b = {16, 17, 19, 20, 22};

        int[] expected = {10, 12, 13, 14, 16, 17, 18, 19, 20, 22};

        int[] mergedArr = mergeSortedArraysWithBuffer(a, b);
        Arrays.stream(mergedArr).forEach(i -> System.out.print(i + ", "));

        assertArrayEquals(expected, mergedArr);
    }


    @Test
    void testMergeSortedArrays_2() throws Exception {
        int[] a = {10, 12, 13, 14, 16, 0, 0, 0, 0, 0};
        int[] b = {16, 17, 19, 20, 22};

        int[] expected = {10, 12, 13, 14, 16, 16, 17, 19, 20, 22};

        int[] mergedArr = mergeSortedArraysWithBuffer(a, b);
        Arrays.stream(mergedArr).forEach(i -> System.out.print(i + ", "));

        assertArrayEquals(expected, mergedArr);
    }


    @Test
    void testMergeSortedArrays_3() throws Exception {
        int[] a = {10, 12, 13, 14, 20, 0, 0, 0, 0, 0};
        int[] b = {16, 17, 19, 20, 22};

        int[] expected = {10, 12, 13, 14, 16, 17, 19, 20, 20, 22};

        int[] mergedArr = mergeSortedArraysWithBuffer(a, b);
        Arrays.stream(mergedArr).forEach(i -> System.out.print(i + ", "));

        assertArrayEquals(expected, mergedArr);
    }


    int[] mergeSortedArraysWithBuffer(int[] a, int[] b) {
        // assuming a is the array with buffer always
        int i = 0;
        while (i < a.length && a[i + 1] != 0) {
            i++;
        }

        int j = b.length - 1;
        int k = a.length - 1;
        while (j >= 0 && i >= 0) {
            if (a[i] < b[j]) {
                a[k] = b[j];
                j--;
            } else {
                a[k] = a[i];
//                a[i] = 0;
                i--;
            }
            k--;
        }

        return a;
    }


    @Test
    void testRotateArrayByN() throws Exception {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        int n = 2;
        int[] arr = rotateArrayByN_ExtraSpace(a, n);
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }


    @Test
    void testRotateArrayByN_1() throws Exception {
        int[] a = {3, 4, 5, 6, 7, 1, 2};
        int n = 2;
        int[] arr = rotateArrayByN_ExtraSpace(a, n);
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }


    int[] rotateArrayByN_ExtraSpace(int[] a, int n) {
        int[] toReturn = new int[a.length];
        int i = 0;
        int j = n;

        while (j < a.length) {
            toReturn[i] = a[j];
            i++;
            j++;
        }

        int k = 0;
        while (i < toReturn.length && k < n) {
            toReturn[i] = a[k];
            i++;
            k++;
        }

        return toReturn;
    }


    @Test
    void testFindMinSubArrayWithSumGreaterThan() throws Exception {
        int[] a = {1, 4, 45, 6, 0, 19};
        int x = 51;
        assertEquals(3, findMinSubArrayWithSumGreaterThan(a, x));
    }


    @Test
    void testFindMinSubArrayWithSumGreaterThan_1() throws Exception {
        int[] a = {1, 10, 5, 2, 7};
        int x = 9;
        assertEquals(1, findMinSubArrayWithSumGreaterThan(a, x));
    }


    @Test
    void testFindMinSubArrayWithSumGreaterThan_2() throws Exception {
        int[] a = {1, 11, 100, 1, 0, 200, 3, 2, 1, 250};
        int x = 280;
        assertEquals(4, findMinSubArrayWithSumGreaterThan(a, x));
    }


    int findMinSubArrayWithSumGreaterThan(int[] a, int x) {
        int sl = 0;
        int sr = 0;
        int sum = 0;

        while (sr < a.length && sum < x) {
            sum += a[sr];
            sr++;
        }

        while (sl < sr && sum > x) {
            sum -= a[sl];
            sl++;
        }

        return sr - sl + 1;
    }


    @Test
    void testFindStartOfSubArrayWithMaxAvgSum() throws Exception {
        int arr[] = {1, 12, -5, -6, 50, 3};
        int k = 4;
        assertEquals(1, findStartOfSubArrayWithMaxAvgSum(arr, k));
    }

    int findStartOfSubArrayWithMaxAvgSum(int[] a, int k) {
        int i = 0;
        int j = 0;
        int sum = 0;
        int maxSum = 0;
        int maxSumStart = 0;

        while (j < k) {
            sum += a[i];
            j++;
        }

        maxSum = sum;
        maxSumStart = i;

        while (j < a.length) {
            sum = sum + a[j];

            if (j - i != k) {
                sum -= a[i];
                i++;
            }

            if (sum > maxSum) {
                maxSum = sum;
                maxSum = i;
            }
            j++;
        }


        return maxSum;
    }


    @Test
    void testSizeOfArrayWithMaxSum() throws Exception {
        int[] a = {1, -2, 1, 1, -2, 1};
        assertEquals(2, findSizeOfTheArrayWithMaxSum(a));
    }


    @Test
    void testSizeOfArrayWithMaxSum_1() throws Exception {
        int[] a = {1, 0, 1, 1, -2, 1};
        assertEquals(4, findSizeOfTheArrayWithMaxSum(a));
    }


    @Test
    void testSizeOfArrayWithMaxSum_2() throws Exception {
        int[] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        assertEquals(5, findSizeOfTheArrayWithMaxSum(a));
    }


    int findSizeOfTheArrayWithMaxSum(int[] a) {
        int i = 0;
        int start = 0;
        int end = 0;
        int currSum = 0;
        int maxSum = 0;

        while (i < a.length) {
            currSum += a[i];
            if (maxSum < currSum) {
                maxSum = currSum;
                end = i;
            }

            //since we want max
            if (currSum < 0) {
                currSum = 0;
                start = i + 1;
            }
            i++;
        }

        return end - start + 1;
    }


    @Test
    void testSubarrayWithMaxSum() throws Exception {
        int a[] = {-2, -3, 4, -1, -2, 1, 5, -3};
        assertEquals(7, findMaxSumOfSubArray(a));
    }


    @Test
    void testSubarrayWithMaxSum_1() throws Exception {
        int a[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        assertEquals(6, findMaxSumOfSubArray(a));
    }

    //if all the elements of the array are negative then the largest negative number is the answer
    //If the array contains all non-positive numbers,
    //then a solution is any subarray of size 1 containing the maximal value of the array (or the empty subarray, if it is permitted).
    int findMaxSumOfSubArray(int[] a) {
        int csum = 0;
        int msum = 0;
        int i = 0;
        while (i < a.length) {
            csum += a[i];
            if (csum < 0)
                csum = 0;
            if (msum < csum) {
                msum = csum;
            }
            i++;
        }
        return msum;
    }


    @Test
    void testSubarrayWithMax9iSumPos() throws Exception {
        int a[] = {-2, -3, 4, -1, -2, 1, 5, -3};
        assertEquals(7, findMaxSumOfSubArray_1(a));
    }


    @Test
    void testSubarrayWithMaxSumPos_1() throws Exception {
        int a[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        assertEquals(6, findMaxSumOfSubArray_1(a));
    }

    @Test
    void testSubarrayWithMaxSumPos_2() throws Exception {
        int a[] = {-2, -1, -3, -4, -1, -2, -1, -5, -4};
        //assuming zero is permiited if not then a logic would be needed track the largest negative number
        assertEquals(0, findMaxSumOfSubArray_1(a));
    }


    //keeping track of positions
    int findMaxSumOfSubArray_1(int[] a) {
        int csum = 0, msum = 0;
        int i = 0, s = 0, fs = 0, e = 0;

        while (i < a.length) {
            csum += a[i];

            if (csum < 0) {
                csum = 0;
                s = i + 1;
            }
            if (msum < csum) {
                msum = csum;
                fs = s;
                e = i;
            }

            i++;
        }


        System.out.println("s:" + fs + ", e:" + e);

        return msum;
    }


    @Test
    void testSortTwoArraysAmongTem() throws Exception {
        int[] a1 = {1, 5, 9, 10, 15, 20};
        int[] a2 = {2, 3, 8, 13};
        sortTwoArraysAmongThem(a1, a2);

        Arrays.sort(a1);
        Arrays.sort(a2);

        for (int i : a1) {
            System.out.print(i + ", ");
        }
        System.out.println();

        for (int i : a2) {
            System.out.print(i + ", ");
        }
    }


    void sortTwoArraysAmongThem(int[] a1, int[] a2) {
        int i = 0;
        int j = 0;
        int k = a1.length - 1;

        while (i < a1.length && k >= 0) {
            if (a1[i] < a2[j]) {
                i++;
            } else {
                int temp = a1[k];
                a1[k] = a2[j];
                a2[j] = temp;

                k--;
                j++;
            }
        }

    }


    @Test
    void testFindSubArrayWindow() throws Exception {
        int[] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        SubArrayWindow window = findSubArrayWindow(a);
    }


    @Test
    void testFindSubArrayWindow_1() throws Exception {
        int[] a = {1, -2, 1, 1, -2, 1};
        SubArrayWindow window = findSubArrayWindow(a);
    }


    @Test
    void testFindSubArrayWindow_2() throws Exception {
        int[] a = {-2, -3, 4, -1, -2, 1, 5, -3, -9, -4};
        SubArrayWindow window = findSubArrayWindow(a);
    }


    SubArrayWindow findSubArrayWindow(int[] a) {
        int start = 0;
        int end = 0;
        int maxSum = 0;

        int currSum = 0;

        int s = 0;
        int i = 0;

        while (i < a.length) {
            currSum += a[i];

            if (currSum < 0) {
                s = i + 1;
                currSum = 0;
            }
            if (maxSum < currSum) {
                maxSum = currSum;
                start = s;
                end = i;
            }
            i++;
        }

        SubArrayWindow window = new SubArrayWindow();
        window.setStart(start);
        window.setEnd(end);

        System.out.println("max sum : " + maxSum);
        System.out.println("window : " + window);
        return window;
    }


    @Test
    void testSortArrayWithBuffer() throws Exception {
        int[] expected = {10, 12, 13, 14, 16, 17, 18, 19, 20, 22};

        int[] a = {10, 12, 13, 14, 18, 0, 0, 0, 0, 0};
        int[] b = {16, 17, 19, 20, 22};

        int[] merged = sortArrayWithBuffer(a, b);
        assertArrayEquals(expected, merged);
    }


    @Test
    void testSortArrayWithBuffer1() throws Exception {
        int[] expected = {2, 4, 6, 7, 8, 10, 12, 13, 14, 18};

        int[] a = {10, 12, 13, 14, 18, 0, 0, 0, 0, 0};
        int[] b = {2, 4, 6, 7, 8};

        int[] merged = sortArrayWithBuffer(a, b);
        assertArrayEquals(expected, merged);
    }


    int[] sortArrayWithBuffer(int[] a, int[] b) {
        int i = a.length - 1;
        int j = b.length - 1;
        int k = 0;
        while (k < a.length) {
            if (a[k + 1] != 0) {
                k++;
            } else {
                break;
            }
        }

        while (i >= 0 && j >= 0 && k >= 0) {
            if (a[k] < b[j]) {
                a[i] = b[j];
                i--;
                j--;
            } else {
                int temp = a[k];
                a[k] = a[i];
                a[i] = temp;
                k--;
                i--;
            }
        }

        if (j >= 0) {
            int x = 0;
            int y = 0;
            while (y <= j) {
                a[x] = b[y];
                y++;
                x++;
            }
        }
        return a;
    }


    @Test
    void testGetMaxLength() throws Exception {
        assertEquals(10, getMaxLength(11));
    }


    int getMaxLength(int n) {
        int low = 0;
        int high = 1000;

        while (high - low > 1) {
            int mid = (high - low) / 2 + low;

            if (totalCost(mid) <= n) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        if (totalCost(high) <= n) {
            return high;
        }

        if (totalCost(low) <= n) {
            return low;
        }

        return 0;
    }


    private int totalCost(int n) {
        int cnt = 0;
        for (int i = 1; i <= n; i *= 10) {
            cnt += (n - i + 1);
        }
        return cnt;
    }

    @Test
    void testGetAllRotaions() throws Exception {
        getAllRots("geeks");
    }

    void getAllRots(String s) {
        int j = 0;
        int i = 0;
        char[] charArray = s.toCharArray();
        int lt = charArray.length;
        int looper = 0;

        while (looper < lt) {
            char init = charArray[j];
            while (i < s.length()) {
                if (i == lt - 1) {
                    charArray[i] = init;
                } else {
                    charArray[i] = charArray[i + 1];
                }
                i++;
            }
            System.out.println(new String(charArray));
            i = 0;
            looper++;
        }
    }


    @Test
    void testGetSortedMergeArray() {
        int[] a = {10, 12, 13, 14, 18, 0, 0, 0, 0, 0};
        int[] b = {16, 17, 19, 20, 22};
        int[] sortedMergeArray = getSortedMergeArray(a, b);

        for (int i : sortedMergeArray) {
            System.out.print(i + ", ");
        }
    }


    /**
     * Give first array as the larger one please
     */
    public int[] getSortedMergeArray(int[] a, int[] b) {
        int k = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i + 1] != 0) {
                k++;
            } else {
                break;
            }
        }
        int i = a.length - 1;
        int j = b.length - 1;

        while (j >= 0) {
            if (a[k] < b[j]) {
                a[i] = b[j];
            } else {
                int temp = a[k];
                a[k] = b[j];
                a[i] = temp;
            }
            i--;
            j--;
        }

        return a;
    }


    @Test
    @DisplayName("Test Find Second Greatest Value")
    void testFindSecondGreatestValue() {
        int op = findSecondGreatestValue(new int[]{12, 35, 1, 10, 34, 1});
        assertEquals(34, op);
    }


    int findSecondGreatestValue(int[] a) {
        int fg = Integer.MIN_VALUE;
        int sg = Integer.MIN_VALUE;

        int i = 0;
        while (i < a.length) {
            if (a[i] > fg) {
                sg = fg;
                fg = a[i];
            }

            if (a[i] < fg && a[i] > sg) sg = a[i];

            i++;
        }

        if (sg != Integer.MIN_VALUE) {
            return sg;
        }
        return -1;
    }


    @Test
    @DisplayName("Test Move All The Zeros To The Left")
    void testMoveAllTheZerosToTheLeft() {
        int[] expected = {34, 42, 15, 5, 0, 0};
        int[] op = moveZerosToTheLeft(new int[]{0, 0, 34, 42, 15, 5});

        assertArrayEquals(expected, op);
    }


    int[] moveZerosToTheLeft(int[] a) {
        int i = 0;
        int j = 0;

        while (i < a.length) {
            if (a[i] != 0) {
                a[j] = a[i];
                j++;
            }
            i++;
        }
        while (j < a.length) {
            a[j] = 0;
            j++;
        }

        return a;
    }


    @Test
    @DisplayName("Test Vals At Event Must Be Greater")
    void testValsAtEventMustBeGreater() {
        int[] expected = {1, 2, 1, 2};
        int[] op = valsAtEvenMustBeGreater(new int[]{1, 2, 2, 1});

        assertArrayEquals(expected, op);
    }


    @Test
    @DisplayName("Test Vals At Event Must Be Greater")
    void testValsAtEventMustBeGreater2() {
        int[] expected = {1, 3, 2, 5, 2};
        int[] op = valsAtEvenMustBeGreater(new int[]{1, 3, 2, 2, 5});

        assertArrayEquals(expected, op);
    }


    @Test
    @DisplayName("Test Vals At Event Must Be Greater")
    void testValsAtEventMustBeGreater3() {
        int[] expected = {2, 5};
        int[] op = valsAtEvenMustBeGreater(new int[]{5, 2});

        assertArrayEquals(expected, op);
    }


    int[] valsAtEvenMustBeGreater(int[] a) {
        int i = 0;
        while (i < a.length) {
            if ((i + 1) % 2 == 0) {
                if (i > 0 && a[i] < a[i - 1]) {
                    int temp = a[i - 1];
                    a[i - 1] = a[i];
                    a[i] = temp;
                }
            } else {
                if (i > 0 && a[i - 1] < a[i]) {
                    int temp = a[i - 1];
                    a[i - 1] = a[i];
                    a[i] = temp;
                }
            }
            i++;
        }

        return a;
    }


    @Test
    @DisplayName("Test Arrange Max Min From Sorted")
    void testArrangeMaxMinFromSorted() {
        int[] expected = {7, 1, 6, 2, 5, 3, 4};
        int[] op = arrangeMaxMinFromSorted(new int[]{1, 2, 3, 4, 5, 6, 7});

        assertArrayEquals(expected, op);
    }


    int[] arrangeMaxMinFromSorted(int[] a) {
        int[] op = new int[a.length];

        int l = 0;
        int h = a.length - 1;

        boolean flag = true;

        int i = 0;
        while (i < op.length) {
            if (flag) {
                op[i] = a[h--];
                flag = false;
            } else {
                op[i] = a[l++];
                flag = true;
            }
            i++;
        }

        return op;
    }


    @Test
    @DisplayName("Test Event Before Odd Solution 1")
    void testEventBeforeOddSolution1() {
        int[] a = {1, 3, 2, 4, 7, 6, 9, 10};
        int[] expected = {2, 4, 6, 10, 7, 1, 9, 3};
        int[] op = evenBeforeOddSol1(a);

        assertArrayEquals(expected, op);
    }


    @Test
    @DisplayName("Test Event Before Odd Solution 1")
    void testEventBeforeOddSolution2() {
        int[] a = {1, 9, 1, 3, 7, 6, 9, 10};
        //		int[] expected = { 2, 4, 6, 10, 7, 1, 9, 3 };
        int[] op = evenBeforeOddSol1(a);
        Arrays.stream(op).forEach(i -> System.out.print(i + ","));
        //		assertArrayEquals(expected, op);
    }

    int[] evenBeforeOddSol1(int[] a) {
        int k = -1;
        int i = 0;
        while (i < a.length) {
            if (a[i] % 2 == 0) {
                k++;
                swap(a, i, k);
            }
            i++;
        }

        return a;
    }


    int[] evenBeforeOddSol2(int[] a) {
        int[] idxA = new int[a.length];
        int[] op = new int[a.length];

        int i = 0;
        int j = 0;
        while (i < a.length) {
            if (a[i] % 2 == 0) {
                idxA[j] = i;
                j++;
            }
            i++;
        }

        i = 0;

        while (i < a.length) {
            if (a[i] % 2 != 0) {
                idxA[j] = i;
                j++;
            }
            i++;
        }

        for (int k = 0; k < idxA.length; k++) {
            op[k] = a[idxA[k]];
        }

        return op;
    }

    @Test
    @DisplayName("Rotate Array")
    void testRotateArray() {
        int[] expected = {3, 4, 5, 6, 7, 1, 2};
        int[] op = rotateArray(new int[]{1, 2, 3, 4, 5, 6, 7}, 2);

        assertArrayEquals(expected, op);
    }


    int[] rotateArray(int[] a, int d) {
        while (d > 0) {
            int first = a[0];
            int i = 0;
            while (i < a.length) {
                if (i != a.length - 1) {
                    a[i] = a[i + 1];
                } else {
                    a[i] = first;
                }
                i++;
            }
            d--;
        }
        return a;
    }


    @Test
    @DisplayName("Test Find Sum Array Index With Given Sum")
    void testFindSumArrayIndexWithGivenSum() {
        int[] expect = {2, 4};
        int[] inp = {1, 4, 20, 3, 10, 5};
        int[] op = findSubArrayIdxWithSum(inp, 33);
        assertArrayEquals(expect, op);
    }


    int[] findSubArrayIdxWithSum(int[] a, int sum) {
        int[] op = new int[2];
        int s = 0;
        int e = 0;
        int csum = 0;
        while (e < a.length && csum <= sum) {
            csum += a[e];
            e++;
        }

        while (e < a.length && csum > sum) {
            csum -= a[s];
            s++;
        }
        op[0] = s;
        op[1] = e - 1;
        return op;
    }


    @Test
    @DisplayName("Test Make A of I as I")
    void testMakeAOfIAsI() {
        int[] expected = {-1, 1, 2, -1, 4};
        int[] op = makeAofIAsI(new int[]{-1, -1, 4, 1, 2});
        assertArrayEquals(expected, op);
    }


    @Test
    @DisplayName("Test Make A of I as I")
    void testMakeAOfIAsI_1() {
        int[] expected = {-1, 1, 2, 3, 4, -1, 6, -1, -1, 9};
        int[] op = makeAofIAsI(new int[]{-1, -1, 6, 1, 9, 3, 2, -1, 4, -1});
        assertArrayEquals(expected, op);
    }

    int[] makeAofIAsI(int[] a) {
        int i = 0;
        while (i < a.length) {
            if (a[i] != -1 && a[i] != i) {
                if (a[a[i]] == -1) {
                    a[a[i]] = a[i];
                    a[i] = -1;
                    i++;
                } else {
                    int temp = a[i];
                    a[i] = a[a[i]];
                    a[temp] = temp;
                }
            } else {
                i++;
            }
        }
        return a;
    }


    @Test
    @DisplayName("Test My Quick Sort")
    void testMyQuickSort() {
        int[] expected = {1, 3, 5, 5, 7, 34};
        int[] sorted = quickSort(new int[]{5, 34, 1, 3, 5, 7});

        assertArrayEquals(expected, sorted);
    }


    @Test
    @DisplayName("Test My Quick Sort")
    void testMyQuickSort1() {
        int[] expected = {1, 2, 3, 6, 88, 89, 90};
        int[] sorted = quickSort(new int[]{90, 89, 1, 6, 3, 2, 88});

        assertArrayEquals(expected, sorted);
    }


    private int[] quickSort(int[] ints) {
        int l = 0;
        int h = ints.length - 1;
        _insertionSortHelper(l, h, ints);
        return ints;
    }

    private void _insertionSortHelper(int l, int h, int[] a) {
        if (l < h) {
            int pi = _partition(l, h, a);
            _insertionSortHelper(l, pi - 1, a);
            _insertionSortHelper(pi + 1, h, a);
        }
    }

    private int _partition(int l, int h, int[] a) {
        int pe = a[h];
        int i = l;
        int k = -1;

        while (i < h) {
            if (a[i] < pe) {
                k++;
                swap(a, i, k);
            }
            i++;
        }
        k++;
        swap(a, k, h);
        return k;
    }


    @Test
    @DisplayName("Test Swap Negatives With Positives")
    void testSwapNegativesWithPositives() {
        int[] op = swapNegativesPositives(new int[]{-2, -3, -1, 6, 9, -1, 3});
        Arrays.stream(op).forEach(i -> System.out.print(i + ", "));
    }


    @Test
    @DisplayName("Test Swap Negatives With Positives")
    void testSwapNegativesWithPositives1() {
        int[] op = swapNegativesPositives(new int[]{-2, -3, -1, 6, 9, -1, -13});
        Arrays.stream(op).forEach(i -> System.out.print(i + ", "));
    }


    int[] swapNegativesPositives(int[] a) {
        int l = 0;
        int h = a.length - 1;
        //assuming h is the partition value always

        int i = 0;
        int k = l - 1;
        while (i < h) {
            if (a[i] < 0) {
                k++;
                swap(a, k, i);
            }
            i++;
        }
        //bring the partition element at it's right position
        swap(a, k + 1, h);
        int pi = -1;
        //this is to make sure that you do not end up swapping a negative value
        //as we are partitioning on the basis of a[i]<0 and not using element at the pivot
        if (a[k + 1] < 0) {
            pi = k + 2;
        } else {
            pi = k + 1;
        }
        //all values at and beyond pi should be positive now

        i = 0;
        while (i < a.length) {
            if (a[i] < 0 && pi <= h) {
                swap(a, i + 1, pi);
                pi++;
            }
            i++;
        }

        return a;
    }


    @Test
    @DisplayName("Test Swap as Per Mapping Given")
    void testSwapAsPerMappingGiven() {
        int[] expected = {11, 10, 12};

        int[] map = {1, 0, 2};
        int[] a = {10, 11, 12};
        int[] op = swapAsPerMappingGiven(a, map);

        assertArrayEquals(expected, op);
    }


    @Test
    @DisplayName("Test Swap as Per Mapping Given")
    void testSwapAsPerMappingGiven1() {
        int[] expected = {40, 60, 90, 50, 70};

        int[] map = {3, 0, 4, 1, 2};
        int[] a = {50, 40, 70, 60, 90};
        int[] op = swapAsPerMappingGiven(a, map);

        assertArrayEquals(expected, op);
    }


    int[] swapAsPerMappingGiven(int[] a, int[] map) {
        int i = 0;
        while (i < a.length) {
            if (a[i] != a[map[i]]) {
                swap(a, i, map[i]);
                swap(map, i, map[i]);
            }

            i++;
        }

        return a;
    }


    @Test
    @DisplayName("Test Find Smallest Missing Number")
    void testFindSmallestMissingNumber() {
        int op = findSmallestMissingNo(new int[]{0, 1, 2, 6, 9}, 10);
        assertNotEquals(-1, op);
        assertEquals(3, op);
    }


    @Test
    @DisplayName("Test Find Smallest Missing Number")
    void testFindSmallestMissingNumber1() {
        int op = findSmallestMissingNo(new int[]{4, 5, 10, 11}, 12);
        assertNotEquals(-1, op);
        assertEquals(0, op);
    }


    @Test
    @DisplayName("Test Find Smallest Missing Number")
    void testFindSmallestMissingNumber2() {
        int op = findSmallestMissingNo(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 10}, 11);
        assertNotEquals(-1, op);
        assertEquals(8, op);
    }


    @Test
    @DisplayName("Test Find Smallest Missing Number")
    void testFindSmallestMissingNumber3() {
        int op = findSmallestMissingNo(new int[]{0, 1, 2, 3}, 5);
        assertNotEquals(-1, op);
        assertEquals(4, op);
    }


    @Test
    @DisplayName("Test Find Smallest Missing Number")
    void testFindSmallestMissingNumber4() {
        // @formatter:off
		assertThrows(
				RuntimeException.class,
				() -> findSmallestMissingNo(new int[] { 0, 1, 2, 3, 4 }, 5),
				"No Number was missing at all");
		// @formatter:on
    }


    private int findSmallestMissingNo(int[] a, int m) {
        int l = 0;
        int h = a.length - 1;

        if (a[0] != 0) return 0;

        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (mid == h) {
                if (a[mid] != m - 1) {
                    return a[mid] + 1;
                } else {
                    throw new RuntimeException("No Number was missing at all");
                }
            } else if (a[mid + 1] != a[mid] + 1) {
                return a[mid] + 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }


    @Test
    @DisplayName("Test Find Sub Array With Sum Greater Than Given")
    void testFindSubArrayWithSumGreaterThanGiven() {
        int expected[] = {4, 45, 6};
        int[] op = findSubArrayWithSumGreaterThanGiven(new int[]{1, 4, 45, 6, 0, 19}, 51);
        assertArrayEquals(expected, op);
    }


    @Test
    @DisplayName("Test Find Sub Array With Sum Greater Than Given")
    void testFindSubArrayWithSumGreaterThanGiven_1() {
        int expected[] = {10};
        int[] op = findSubArrayWithSumGreaterThanGiven(new int[]{1, 10, 5, 2, 7}, 9);
        assertArrayEquals(expected, op);
    }


    @Test
    @DisplayName("Test Find Sub Array With Sum Greater Than Given")
    void testFindSubArrayWithSumGreaterThanGiven_2() {
        int expected[] = {100, 1, 0, 200};
        int[] op = findSubArrayWithSumGreaterThanGiven(new int[]{1, 11, 100, 1, 0, 200, 3, 2, 1, 250}, 280);
        assertArrayEquals(expected, op);
    }


    @Test
    @DisplayName("Test Find Sub Array With Sum Greater Than Given")
    void testFindSubArrayWithSumGreaterThanGiven_3() {
        int expected[] = {50, 1};
        int[] op = findSubArrayWithSumGreaterThanGiven(new int[]{1, 4, 45, 6, 0, 19, 50, 1}, 51);
        assertArrayEquals(expected, op);
    }


    int[] findSubArrayWithSumGreaterThanGiven(int[] a, int x) {
        int i = 0;
        int j = 0;

        int fi = 0;
        int fj = 0;
        int minL = a.length;


        int csum = 0;
        //check the coomplete array to see if a shorter sub array is present ?
        while (i < a.length) {
            while (i < a.length && csum <= x) {
                csum += a[i];
                i++;
            }

            while (j < a.length && j < i && csum > x) {
                csum -= a[j];
                j++;
            }

            if (i - j + 1 < minL) {
                fi = i - 1;
                fj = j - 1;
                minL = fi - fj + 1;
                csum = 0;

                j = i;
            }
        }

        int[] ans = new int[minL];
        int m = 0;
        for (int k = fj; k <= fi; k++) {
            ans[m] = a[k];
            m++;
        }
        return ans;
    }


    @Test
    @DisplayName("Test Merge Two Sorted Arrays In Relative Order")
    void testMergeTwoSortedArraysInRelativeOrder() {
        int[] a = {1, 5, 9, 10, 15, 20};
        int[] b = {2, 3, 8, 13};
        mergeTwoSortedArraysInRelativeOrders(a, b);

        Arrays.stream(a).forEach(i -> System.out.print(i + ", "));
        System.out.println();
        Arrays.stream(b).forEach(i -> System.out.print(i + ", "));
    }


    @Test
    @DisplayName("Test Merge Two Sorted Arrays In Relative Order")
    void testMergeTwoSortedArraysInRelativeOrder1() {
        int[] a = {10};
        int[] b = {2, 3};
        mergeTwoSortedArraysInRelativeOrders(a, b);

        Arrays.stream(a).forEach(i -> System.out.print(i + ", "));
        System.out.println();
        Arrays.stream(b).forEach(i -> System.out.print(i + ", "));
    }


    void mergeTwoSortedArraysInRelativeOrders(int[] a, int[] b) {
        int j = b.length - 1;

        while (j >= 0) {
            int last = a[a.length - 1];
            int k = a.length - 2;
            if (last > b[j]) {
                int temp = b[j];
                b[j] = last;
                while (k >= 0 && a[k] > temp) {
                    a[k + 1] = a[k];
                    k--;
                }
                a[k + 1] = temp;
            }
            j--;
        }
    }


    @Test
    @DisplayName("Test Find Majority Element")
    void testFindMajorityElement() {
        int majorityElement = findMajorityElement(new int[]{3, 3, 4, 2, 4, 4, 2, 4, 4});
        assertEquals(4, majorityElement);
    }


    @Test
    @DisplayName("Test Find Majority Element")
    void testFindMajorityElement1() {
        int majorityElement = findMajorityElement(new int[]{3, 3, 4, 2, 4, 4, 2, 4});
        assertEquals(-1, majorityElement);
    }


    int findMajorityElement(int[] a) {
        int reqLt = a.length / 2;

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            max = max < a[i] ? a[i] : max;
        }

        int[] frq = new int[max + 1];

        int i = 0;
        while (i < a.length) {
            frq[a[i]]++;
            i++;
        }

        int majEle = -1, maxOccur = 0;

        i = 0;

        while (i < frq.length) {
            if (frq[i] > maxOccur) {
                maxOccur = frq[i];
                majEle = i;
            }
            i++;
        }

        return maxOccur > reqLt ? majEle : -1;
    }


    @Test
    @DisplayName("Find Pair with Sum In Sorted Array")
    void findPairWithSumInSortedArray() {
        int[] expected = {4, 5};
        int[] op = findPairWithSumInSortedArray(new int[]{1, 2, 4, 5, 6}, 9);
        assertArrayEquals(expected, op);
    }


    @Test
    @DisplayName("Find Pair with Sum In Sorted Array")
    void findPairWithSumInSortedArray1() {
        int[] expected = {20, 50};
        int[] op = findPairWithSumInSortedArray(new int[]{10, 20, 35, 50, 75, 80}, 70);
        assertArrayEquals(expected, op);
    }


    int[] findPairWithSumInSortedArray(int[] a, int x) {
        int[] op = new int[2];
        int i = 0;
        int j = a.length - 1;
        while (i <= j) {
            int sum = a[i] + a[j];
            if (sum == x) {
                op[0] = a[i];
                op[1] = a[j];
                return op;
            } else if (sum > x) {
                j--;
            } else {
                i++;
            }
        }
        return op;
    }


    @Test
    @DisplayName("Test Find Point Of Eqlbrm")
    void testFindPointOfEqlbrm() {
        int ptOfEqlbrm = findPointOfEqulbrm(new int[]{-7, 1, 5, 2, -4, 3, 0});
        assertEquals(3, ptOfEqlbrm);
    }


    @Test
    @DisplayName("Test Find Point Of Eqlbrm")
    void testFindPointOfEqlbrm1() {
        int ptOfEqlbrm = findPointOfEqulbrm(new int[]{1, 2, 3});
        assertEquals(-1, ptOfEqlbrm);
    }


    int findPointOfEqulbrm(int[] a) {
        int i = 0;
        int j = a.length - 1;
        int sL = 0;
        int sR = 0;
        while (i <= j) {
            sL += a[i++];
            sR += a[j--];

            if (sL == sR && i == j) {
                return i;
            }
        }

        return -1;
    }


    void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    @Test
    @DisplayName("Test Move All Zeros To End")
    void testMoveAllZerosToEnd() {
        int[] expected = {1, 2, 4, 3, 5, 0, 0, 0};
        int[] op = moveAllZerosToEnd(new int[]{1, 2, 0, 4, 3, 0, 5, 0});
        assertArrayEquals(expected, op);
    }


    @Test
    @DisplayName("Test Move All Zeros To End")
    void testMoveAllZerosToEnd1() {
        int[] expected = {5, 4, 23, 1, 0};
        int[] op = moveAllZerosToEnd(new int[]{5, 4, 0, 23, 1});
        assertArrayEquals(expected, op);
    }


    int[] moveAllZerosToEnd(int[] a) {
        int i = 0;
        int j = 0;

        while (i < a.length) {
            if (a[i] != 0) {
                a[j] = a[i];
                j++;
            }
            i++;
        }

        while (j < a.length) {
            a[j] = 0;
            j++;
        }

        return a;
    }


    @Test
    @DisplayName("Test Find Leaders in The Array")
    void testFindLeadersInTheArray() {
        int[] expected = {17, 5, 2};
        int[] op = findLeadersInTheArray(new int[]{16, 17, 4, 3, 5, 2});
        assertArrayEquals(expected, op);
    }

    @Test
    @DisplayName("Test Find Leaders in The Array")
    void testFindLeadersInTheArray1() {
        int[] expected = {17, 5, 2};
        int[] op = findLeadersInTheArray(new int[]{16, 17, 4, 3, 5, 2, 2, 6, 1});

        for (int i : Arrays.stream(op).mapToObj(Integer::valueOf).collect(Collectors.toList())) {
            System.out.print(i + ", ");
        }
    }


    int[] findLeadersInTheArray(int[] a) {
        Stack<Integer> st = new Stack<>();
        st.push(0);
        int i = 1;
        while (i < a.length) {
            while (!st.isEmpty() && a[i] > a[st.peek()]) {
                st.pop();
            }
            st.push(i);
            i++;
        }
        return st.stream().map(index -> a[index]).mapToInt(v -> v.intValue()).toArray();
    }


    @Test
    @DisplayName("Test Find Leaders in The Array")
    void testFindLeadersInTheArrayOtherAlgo() {
        int[] expected = {17, 5, 2};
        int[] op = findLeadersInTheArrayAlgo1(new int[]{16, 17, 4, 3, 5, 2});
        assertArrayEquals(expected, op);
    }


    @Test
    @DisplayName("Test Find Leaders in The Array")
    void testFindLeadersInTheArrayOtherAlgo1() {
        int[] expected = {17, 6, 1};
        int[] op = findLeadersInTheArrayAlgo1(new int[]{16, 17, 4, 3, 5, 2, 2, 6, 1});
        assertArrayEquals(expected, op);
    }


    @Test
    @DisplayName("Test Find Leaders in The Array")
    void testFindLeadersInTheArrayOtherAlgo2() {
        int[] expected = {5, 2};
        int[] op = findLeadersInTheArrayAlgo1(new int[]{1, 2, 3, 4, 5, 2});
        assertArrayEquals(expected, op);
    }


    int[] findLeadersInTheArrayAlgo1(int[] a) {
        List<Integer> leaders = new LinkedList<>();
        int i = 1;
        int cl = Integer.MIN_VALUE;
        while (i < a.length) {
            if (i == a.length - 1) {
                leaders.add(a[i]);
            } else if (a[i] > a[i - 1] && a[i] > a[i + 1]) {
                if (cl < a[i]) {
                    cl = a[i];
                    if (leaders.isEmpty()) {
                        leaders.add(a[i]);
                    } else {
                        leaders.remove(leaders.size() - 1);
                        leaders.add(cl);
                    }
                } else {
                    cl = a[i];
                    leaders.add(a[i]);
                }
            }
            i++;
        }
        return leaders.stream().mapToInt(intr -> intr).toArray();
    }


    @Test
    @DisplayName("Find Range With Sum")
    void findRangeWithSum() {
        int[] expected = {2, 4};
        List<? super Number> rangeWithSum = findRangeWithSum(new int[]{1, 4, 20, 3, 10, 5}, 33);
        rangeWithSum.forEach(i -> System.out.print(i + ", "));
    }


    List<? super Number> findRangeWithSum(int[] a, int x) {
        List<? super Number> op = new LinkedList<>();
        int i = 0;
        int j = 0;
        int csum = 0;
        while (i < a.length && csum < x) {
            csum += a[i++];
        }

        while (j < a.length && csum >= x) {
            csum -= a[++j];
        }

        op.add(i);
        op.add(j - 1);

        return op;
    }


    @Test
    @DisplayName("Test Find Distinct Value")
    void testFindDistinctValue() {
        int[] expected = {1, 4, 3, 5, 6, 7};
        int[] op = findDistinctValues(new int[]{1, 4, 3, 1, 3, 5, 5, 7, 6, 7});
        Set<Integer> asSet = Arrays.stream(expected).mapToObj(i -> i).collect(Collectors.toSet());
        for (int i : op) {
            assertTrue(asSet.contains(i));
        }
    }


    int[] findDistinctValues(int[] a) {
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > maxVal) maxVal = a[i];
        }
        int[] f = new int[maxVal + 1];
        List<Integer> opList = new LinkedList<>();

        int i = 0;
        while (i < a.length) {
            f[a[i]]++;
            i++;
        }

        for (int j = 0; j < f.length; j++) {
            if (f[j] == 1) opList.add(j);
        }

        return opList.stream().mapToInt(k -> k).toArray();
    }


    @Test
    @DisplayName("Test Sort Two Sorted Arrays Without Using Space")
    void testSortTwoSortedArraysWithoutUsingSpace() {
        int[] a = {2, 3, 7, 9, 999};
        int[] b = {5, 7, 88};
        sortTwoSortedArraysWithoutUsingSpaceAtAll(a, b);

        Arrays.stream(a).forEach(i -> System.out.print(i + ", "));
        System.out.println();
        Arrays.stream(b).forEach(i -> System.out.print(i + ", "));

        _sortedChecker(a, b);
    }

    private void _sortedChecker(int[]... a) {
        for (int[] arr : a) {
            int[] copy = Arrays.copyOf(arr, arr.length);
            Arrays.sort(copy);
            assertArrayEquals(copy, arr);
        }
    }


    void sortTwoSortedArraysWithoutUsingSpaceAtAll(int[] a, int[] b) {
        int j = b.length - 1;
        while (j >= 0) {
            int i = a.length - 2;
            int last = a[a.length - 1];
            while (i >= 0 && a[i] > b[j]) {
                a[i + 1] = a[i];
                i--;
            }
            a[i + 1] = b[j];
            b[j] = last;
            j--;
        }
    }


    @Test
    @DisplayName("Test Find the Max Profit In Day Trading")
    void testFindTheMaxProfitInDayTrading() {
        int maxProfit = findTheMaxProfitInDayTrading(new int[]{2, 4, 5, 30, 15, 10, 8, 25, 80});
        assertEquals(100, maxProfit);
    }


    @Test
    @DisplayName("Test Find the Max Profit In Day Trading")
    void testFindTheMaxProfitInDayTrading1() {
        int maxProfit = findTheMaxProfitInDayTrading(new int[]{10, 22, 5, 75, 65, 80});
        assertEquals(87, maxProfit);
    }


    int findTheMaxProfitInDayTrading(int[] a) {
        int[] ledger = new int[a.length];

        int minPrice = a[0];
        int i = 1;
        while (i < a.length) {
            if (a[i] < minPrice) {
                minPrice = a[i];
            }
            ledger[i] = Math.max(ledger[i - 1], a[i] - minPrice);
            i++;
        }

        int maxPrice = a[a.length - 1];
        i = a.length - 2;
        while (i >= 0) {
            if (a[i] > maxPrice) {
                maxPrice = a[i];
            }

            ledger[i] = Math.max(ledger[i + 1], ledger[i] + maxPrice - a[i]);
            i--;
        }

        return ledger[0];
    }


    @Test
    @DisplayName("Test Min Jumps To make")
    void testMinJumpsToMake() {
        int jumps = findMinJumpsToReachEndOfArray(new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9});
        assertEquals(3, jumps);
    }


    int findMinJumpsToReachEndOfArray(int[] a) {
        int n = a.length;
        int jumps = 0;// _minJumpsHelper(a, n, 0, 0, Integer.MAX_VALUE);

        return jumps;
    }


    @Test
    @DisplayName("Test Rearrange A of I iteratively")
    void testRearrangeAOfIIteratively() {
        int[] expected = new int[]{-1, 1, 2, 3, 4, -1, 6, -1, -1, 9};
        int[] op = rearrangeForAofI(new int[]{-1, -1, 6, 1, 9, 3, 2, -1, 4, -1});
        assertArrayEquals(expected, op);
    }

    @Test
    @DisplayName("Test Rearrange A of I iteratively")
    void testRearrangeAOfIIteratively1() {
        int[] expected = new int[]{-1, -1, 2, 3};
        int[] op = rearrangeForAofI(new int[]{-1, 3, -1, 2});
        assertArrayEquals(expected, op);
    }

    int[] rearrangeForAofI(int[] a) {
        int i = 0;
        while (i < a.length) {
            if (a[i] != -1 && a[i] != i) {
                int temp = a[i];
                while (a[temp] != -1 && a[temp] != temp) {
                    int temp2 = a[temp];
                    a[temp] = temp;
                    temp = temp2;
                }
                a[temp] = temp;

                if (a[i] != i) {
                    a[i] = -1;
                }
            }
            i++;
        }
        return a;
    }

    @Test
    @DisplayName("Test Find Fixed Point In Array")
    void testFindFixedPointInArray() {
        int pt = findFixedPointInArray(new int[]{-10, -6, 2, 4, 55});
        assertEquals(2, pt);
    }


    @Test
    @DisplayName("Test Find Fixed Point In Array")
    void testFindFixedPointInArray1() {
        int pt = findFixedPointInArray(new int[]{-10, -6, 4, 51, 55});
        assertEquals(-1, pt);
    }


    @Test
    @DisplayName("Test Find Fixed Point In Array")
    void testFindFixedPointInArray2() {
        int pt = findFixedPointInArray(new int[]{-10, 1, 4, 51, 55});
        assertEquals(1, pt);
    }


    int findFixedPointInArray(int[] a) {
        int l = 0, h = a.length - 1;

        while (l <= h) {
            int mid = l + (h - l) / 2;

            if (a[mid] == mid) return mid;

            if (a[mid] > mid) h = mid - 1;
            else l = mid + 1;
        }

        return -1;
    }


    @Test
    @DisplayName("Test SubArray With Same Avg")
    void testSubArrayWithSameAvg() {
        int[] a = {1, 5, 7, 2, 0};
        int sepPoint = findSubarraysWithSameAvg(a);

        assertEquals(1, sepPoint);

        System.out.println("Sub Array 1 :");
        for (int i = 0; i <= sepPoint; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.println();
        System.out.println("Sub Array 2 :");
        for (int i = sepPoint + 1; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
    }


    @Test
    @DisplayName("Test SubArray With Same Avg")
    void testSubArrayWithSameAvg1() {
        int[] a = {4, 3, 5, 9, 11};
        int sepPoint = findSubarraysWithSameAvg(a);

        assertEquals(-1, sepPoint);
    }


    int findSubarraysWithSameAvg(int[] a) {
        int rsum = 0;
        for (int i = 0; i < a.length; i++) {
            rsum += a[i];
        }

        int lsum = 0, i = 0;
        while (i < a.length) {
            rsum -= a[i];
            lsum += a[i];

            int lavg = lsum / (i + 1);

            int ravg = 0;
            if (i + 1 < a.length) {
                ravg = rsum / (a.length - (i + 1));
            }

            if (lavg == ravg) return i;

            i++;
        }

        return -1;
    }


    @Test
    @DisplayName("Test Reorder As Given Mapping")
    void testReorderAsGivenMapping() {
        int[] expectedA = {40, 60, 90, 50, 70};
        int[] expectedIdx = {0, 1, 2, 3, 4};

        int[] a = {50, 40, 70, 60, 90};
        int[] idx = {3, 0, 4, 1, 2};
        reorderAsPerMapping(a, idx);

        assertArrayEquals(expectedA, a);
        assertArrayEquals(expectedIdx, idx);

        Arrays.stream(a).forEach(i -> System.out.print(i + ", "));
        System.out.println();
        Arrays.stream(idx).forEach(i -> System.out.print(i + ", "));
    }


    void reorderAsPerMapping(int[] a, int[] idx) {
        int i = 0;
        while (i < a.length) {
            if (i != idx[i]) {
                while (i != idx[i]) {
                    int mapping = idx[idx[i]];
                    int temp = a[idx[i]];

                    a[idx[i]] = a[i];
                    idx[idx[i]] = idx[i];

                    idx[i] = mapping;
                    a[i] = temp;
                }
            }
            i++;
        }
    }

    @Test
    @DisplayName("Test ReductionOfArrays")
    void testReductionOfArrays() {
        int[] expected = {1, 2, 2, 3};
        int[] ints = reduceArrayWithKTimesEle(new int[]{1, 2, 2, 2, 3}, 2);
        assertArrayEquals(expected, ints);
    }


    @Test
    @DisplayName("Test ReductionOfArrays")
    void testReductionOfArrays1() {
        int[] expected = {3};
        int[] ints = reduceArrayWithKTimesEle(new int[]{3, 3, 3}, 1);
        assertArrayEquals(expected, ints);
    }


    @Test
    @DisplayName("Test ReductionOfArrays")
    void testReductionOfArrays2() {
        int[] expected = {1, 1, 2, 2, 3, 3, 4, 5};
        int[] ints = reduceArrayWithKTimesEle(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 5}, 2);
        assertArrayEquals(expected, ints);
    }


    //Reduce the array such that each element appears at most K times
    int[] reduceArrayWithKTimesEle(int[] a, int k) {
        int i = 0;
        int pele = -1;
        int count = 0;
        List<Integer> op = new LinkedList<>();

        while (i < a.length) {
            if (pele == -1 || pele == a[i]) {
                pele = a[i];
                count++;
            } else {
                int x = 0;
                while (x < k && count != 0) {
                    op.add(pele);
                    x++;
                    count--;
                }
                pele = a[i];
                count = 1;
            }
            i++;
        }

        if (count > 0) {
            int x = 0;
            while (x < k && count != 0) {
                op.add(pele);
                x++;
                count--;
            }
        }

        return op.stream().mapToInt(it -> it).toArray();
    }


    @Test
    @DisplayName("Test Find Max Sum Path")
    void testFindMaxSumPath() {
        int[] a = {2, 3, 7, 10, 12};
        int[] b = {1, 5, 7, 8};

        int sum = maxSumPathInArrays(a, b);
        assertEquals(35, sum);
    }


    @Test
    @DisplayName("Test Find Max Sum Path")
    void testFindMaxSumPath_1() {
        int[] a = {10, 12};
        int[] b = {5, 7, 9};

        int sum = maxSumPathInArrays(a, b);
        assertEquals(22, sum);
    }


    @Test
    @DisplayName("Test Find Max Sum Path")
    void testFindMaxSumPath_2() {
        int[] a = {2, 3, 7, 10, 12, 15, 30, 34};
        int[] b = {1, 5, 7, 8, 10, 15, 16, 19};

        int sum = maxSumPathInArrays(a, b);
        assertEquals(122, sum);
    }


    int maxSumPathInArrays(int[] a, int[] b) {
        return Math.max(_findMaxSumPath(a, b), _findMaxSumPath(b, a));
    }

    private int _findMaxSumPath(int[] a, int[] b) {
        int csum = 0;
        int i = 0;
        int[] ca = a;
        while (i < ca.length) {
            csum += ca[i];
            ca = _checkIfElementExist(b, ca[i], ca);
            i++;
        }
        return csum;
    }

    private int[] _checkIfElementExist(int[] a, int x, int[] def) {
        int l = 0;
        int h = a.length - 1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (a[mid] == x) {
                return a;
            } else if (a[mid] < x) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return def;
    }


    @Test
    @DisplayName("Test Rotate Matrix By 90")
    void testRotateMatrixBy90() {
        //formatter:off
        int[][] expected = {{13, 9, 5, 1}, {14, 10, 6, 2}, {15, 11, 7, 3}, {16, 12, 8, 4}};


        int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        //formatter:on
        int[][] ints = rotateMatrixBy90Degs(a);
        Assertions.assertArrayEquals(expected, ints);
    }


    int[][] rotateMatrixBy90Degs(int[][] a) {
        if (a.length == 0 || a.length != a[0].length) {
            throw new RuntimeException();
        }

        int n = a.length;

        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;

            for (int i = first; i < last; i++) {
                int offset = i - first;

                // Save the top element
                int top = a[first][i];

                // Move left element to the top
                a[first][i] = a[last - offset][first];

                // Move bottom element to the left
                a[last - offset][first] = a[last][last - offset];

                // Move right element to the bottom
                a[last][last - offset] = a[i][last];

                // Move the saved top element to the right
                a[i][last] = top;
            }
        }


        return a;
    }


    //	##### BELOW QUESTIONS ARE FROM THE GOD OF GODs - LEETCODE :


    @Test
    @DisplayName("Test Find Leader In Election")
    void testFindLeaderInElection() {
        int[] p = {0, 1, 1, 0, 0, 1, 0};
        int[] t = {0, 5, 10, 15, 20, 25, 30};

        int leader = findLeadersInOnlineElection(p, t, 3);
        assertEquals(0, leader);
    }


    @Test
    @DisplayName("Test Find Leader In Election")
    void testFindLeaderInElection1() {
        int[] p = {0, 1, 1, 0, 0, 1, 0};
        int[] t = {0, 5, 10, 15, 20, 25, 30};

        int leader = findLeadersInOnlineElection(p, t, 12);
        assertEquals(1, leader);
    }


    @Test
    @DisplayName("Test Find Leader In Election")
    void testFindLeaderInElection2() {
        int[] p = {0, 1, 1, 0, 0, 1, 0};
        int[] t = {0, 5, 10, 15, 20, 25, 30};

        int leader = findLeadersInOnlineElection(p, t, 25);
        assertEquals(1, leader);
    }


    @Test
    @DisplayName("Test Find Leader In Election")
    void testFindLeaderInElection3() {
        int[] p = {0, 1, 1, 0, 0, 1, 0};
        int[] t = {0, 5, 10, 15, 20, 25, 30};

        int leader = findLeadersInOnlineElection(p, t, 15);
        assertEquals(0, leader);
    }


    @Test
    @DisplayName("Test Find Leader In Election")
    void testFindLeaderInElection4() {
        int[] p = {0, 1, 1, 0, 0, 1, 0};
        int[] t = {0, 5, 10, 15, 20, 25, 30};

        int leader = findLeadersInOnlineElection(p, t, 24);
        assertEquals(0, leader);
    }


    @Test
    @DisplayName("Test Find Leader In Election")
    void testFindLeaderInElection5() {
        int[] p = {0, 1, 1, 0, 0, 1, 0};
        int[] t = {0, 5, 10, 15, 20, 25, 30};

        int leader = findLeadersInOnlineElection(p, t, 8);
        assertEquals(1, leader);
    }


    @Test
    @DisplayName("Test Find Leader In Election")
    void testFindLeaderInElection6() {
        int[] p = {0, 0, 0, 0, 1};
        int[] t = {0, 6, 39, 52, 75};

        int leader = findLeadersInOnlineElection(p, t, 78);
        assertEquals(0, leader);
    }

    @Test
    @DisplayName("Test Find Leader In Election")
    void testFindLeaderInElection7() {
        int[] p = {0, 0, 0, 0, 1};
        int[] t = {0, 6, 39, 52, 75};

        int leader = findLeadersInOnlineElection(p, t, 99);
        assertEquals(0, leader);
    }


    @Test
    @DisplayName("Test Find Leader In Election")
    void testFindLeaderInElection8() {
        int[] p = {0, 1, 1, 0, 2, 2, 0, 2, 3, 1};
        int[] t = {2, 4, 11, 17, 20, 43, 53, 81, 91, 97};

        int leader = findLeadersInOnlineElection(p, t, 20);
        assertEquals(0, leader);
    }

    public int findLeadersInOnlineElection(int[] persons, int[] times, int t) {
        if (t < times[0]) {
            //not possible
            return -1;
        }

        int idxToCheckLimit = -1;

        if (t > times[times.length - 1]) {
            idxToCheckLimit = persons.length - 1;
        } else {
            //find the index to check for in the person array based on the given query time
            //using binary search since the time array will always be sorted
            int l = 0;
            int h = times.length - 1;


            while (l <= h) {
                int mid = l + (h - l) / 2;
                if (mid != 0 && times[mid - 1] < t && t < times[mid]) {
                    idxToCheckLimit = mid - 1;
                    break;
                } else if (t == times[mid]) {
                    idxToCheckLimit = mid;
                    break;
                } else if (times[mid] > t) {
                    h = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }

        int maxCandidate = Integer.MIN_VALUE;
        for (int i = 0; i < persons.length; i++) {
            maxCandidate = persons[i] > maxCandidate ? persons[i] : maxCandidate;
        }

        //check for the leader with the calculation of frqMap only to save time
        //if this was in a batch like when queries are coming in a batch
        // you can create a class, and load the leadrs by time in memory and then
        //binary search the index to return (Inital thought) !
        int[] frqMap = new int[maxCandidate + 1];
        int maxVotes = Integer.MIN_VALUE;
        int maxVotesTo = -1;
        for (int i = 0; i <= idxToCheckLimit; i++) {
            int person = persons[i];
            frqMap[person]++;
            if (frqMap[person] > maxVotes) {
                maxVotes = frqMap[person];
                maxVotesTo = person;
            } else if (frqMap[person] == maxVotes) {
                maxVotesTo = person;
            }
        }
        return maxVotesTo;
    }


    @Test
    @DisplayName("Test Insert Position Suggestion")
    void testInsertPositionSuggestion() {
        int i = suggestInsertPosition(new int[]{1, 3, 5, 6}, 5);
        assertEquals(2, i);
    }


    @Test
    @DisplayName("Test Insert Position Suggestion")
    void testInsertPositionSuggestion1() {
        int i = suggestInsertPosition(new int[]{1, 3, 5, 6}, 2);
        assertEquals(1, i);
    }


    @Test
    @DisplayName("Test Insert Position Suggestion")
    void testInsertPositionSuggestion2() {
        int i = suggestInsertPosition(new int[]{1, 3, 5, 6}, 7);
        assertEquals(4, i);
    }


    @Test
    @DisplayName("Test Insert Position Suggestion")
    void testInsertPositionSuggestion3() {
        int i = suggestInsertPosition(new int[]{1, 3, 5, 6}, 0);
        assertEquals(0, i);
    }


    @Test
    @DisplayName("Test Insert Position Suggestion")
    void testInsertPositionSuggestion4() {
        int i = suggestInsertPosition(new int[]{1}, 1);
        assertEquals(0, i);
    }


    @Test
    @DisplayName("Test Insert Position Suggestion")
    void testInsertPositionSuggestion5() {
        int i = suggestInsertPosition(new int[]{1, 2, 3, 4, 5, 10}, 2);
        assertEquals(1, i);
    }


    int suggestInsertPosition(int[] a, int x) {
        int l = 0;
        int h = a.length - 1;

        if (a[0] >= x) {
            return 0;
        }
        if (a[a.length - 1] <= x) {
            return a.length;
        }

        while (l <= h) {
            int mid = l + (h - l) / 2;

            if (a[mid] == x || a[mid] >= x && a[mid - 1] < x) {
                return mid;
            } else if (a[mid] < x) {
                l = mid + 1;
            } else {
                h = mid;
            }
        }

        return -1;
    }

    // @formatter:off


	@Test
	@DisplayName("Test Sudoku")
	void testSudoku(){

		char[][] mat = { 	{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
							{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
							{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
							{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
							{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
							{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
							{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
							{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
							{'.', '.', '.', '.', '8', '.', '.', '7', '9'}
		};


		boolean b = sudokuChecker(mat);
		assertTrue(b);
	}


	@Test
	@DisplayName("Test Sudoku")
	void testSudoku1(){

		char[][] mat = {
				{'8', '3', '.', '.', '7', '.', '.', '.', '.'},
				{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
				{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
				{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
				{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
				{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
				{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
				{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
				{'.', '.', '.', '.', '8', '.', '.', '7', '9'}
		};


		boolean b = sudokuChecker(mat);
		assertFalse(b);
	}



	/**
	 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following
	 * rules:
	 *
	 * Each row must contain the digits 1-9 without repetition.
	 * Each column must contain the digits 1-9 without repetition.
	 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
	 *
	 * Note:
	 *
	 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
	 * Only the filled cells need to be validated according to the mentioned rules.
	 */
	// @formatter:on
    boolean sudokuChecker(char[][] mat) {
        //check row wise
		/*
		int i = 0;
		boolean validRowsFlag = true;
		while (i < mat.length) {
			if (validRowsFlag) {
				int j = 0;
				Set<Character> seenValues = new HashSet<>();
				while (j < mat[i].length) {
					char val = mat[i][j];
					if (val != '.') {
						if (!seenValues.contains(val)) {
							seenValues.add(val);
						} else {
							validRowsFlag = false;
							break;
						}
					}
					j++;
				}
			} else {
				break;
			}
			i++;
		}
*/

        AtomicBoolean result = new AtomicBoolean(true);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CyclicBarrier barrier = new CyclicBarrier(4);


        SudokuRowIteratingWorker rowWorkerFullMatrix = new SudokuRowIteratingWorker(result, barrier, mat, mat.length, mat[0].length, false);
        SudokuColumnIteratingWorker colWorkerFullMatrix = new SudokuColumnIteratingWorker(result, barrier, mat, mat.length, mat[0].length, false);

        SudokuRowIteratingWorker rowWorkerQuarters = new SudokuRowIteratingWorker(result, barrier, mat, mat.length, mat[0].length, true);
        SudokuColumnIteratingWorker colWorkerQuarters = new SudokuColumnIteratingWorker(result, barrier, mat, mat.length, mat[0].length, true);

        executorService.submit(rowWorkerFullMatrix);
        executorService.submit(rowWorkerQuarters);
        executorService.submit(colWorkerFullMatrix);
        executorService.submit(colWorkerQuarters);

        executorService.shutdown();

        //check Col wise
		/*
		int j = 0;
		boolean validColFlag = true;
		while (j < mat[0].length) {
			int row = 0;
			if (validColFlag) {
				Set<Character> seenValues = new HashSet<>();
				while (row < mat.length) {
					char val = mat[row][j];
					if (val != '.') {
						if (!seenValues.contains(val)) {
							seenValues.add(val);
						} else {
							validColFlag = false;
							break;
						}
					}
					row++;
				}
			} else {
				break;
			}
			j++;
		}
		*/


        return result.get();
    }


    @Test
    @DisplayName("Test Get Next Lexico Gr8tr Num")
    void testGetNextLexicoGr8TrNum() {
        int[] expected = {1, 3, 2};
        int[] nextLexicoGr8trNumber = getNextLexicoGr8trNumber(new int[]{1, 2, 3});
        assertArrayEquals(expected, nextLexicoGr8trNumber);
    }


    @Test
    @DisplayName("Test Get Next Lexico Gr8tr Num")
    void testGetNextLexicoGr8TrNum1() {
        int[] expected = {1, 5, 1};
        int[] nextLexicoGr8trNumber = getNextLexicoGr8trNumber(new int[]{1, 1, 5});
        assertArrayEquals(expected, nextLexicoGr8trNumber);
    }


    @Test
    @DisplayName("Test Get Next Lexico Gr8tr Num")
    void testGetNextLexicoGr8TrNum2() {
        int[] expected = {4, 5, 6, 2, 8};
        int[] nextLexicoGr8trNumber = getNextLexicoGr8trNumber(new int[]{4, 5, 2, 8, 6});
        assertArrayEquals(expected, nextLexicoGr8trNumber);
    }


    @Test
    @DisplayName("Test Get Next Lexico Gr8tr Num")
    void testGetNextLexicoGr8TrNum4() {
        int[] expected = {1, 2, 3};
        int[] nextLexicoGr8trNumber = getNextLexicoGr8trNumber(new int[]{3, 2, 1});
        assertArrayEquals(expected, nextLexicoGr8trNumber);
    }


    @Test
    @DisplayName("Test Get Next Lexico Gr8tr Num")
    void testGetNextLexicoGr8TrNum5() {
        int[] expected = {3, 1, 2};
        int[] nextLexicoGr8trNumber = getNextLexicoGr8trNumber(new int[]{2, 3, 1});
        assertArrayEquals(expected, nextLexicoGr8trNumber);
    }

    @Test
    @DisplayName("Test Get Next Lexico Gr8tr Num")
    void testGetNextLexicoGr8TrNum6() {
        int[] expected = {1, 2, 4, 3, 5, 6};
        int[] nextLexicoGr8trNumber = getNextLexicoGr8trNumber(new int[]{1, 2, 3, 6, 5, 4});
        assertArrayEquals(expected, nextLexicoGr8trNumber);
    }

    int[] getNextLexicoGr8trNumber(int[] a) {
        int i = a.length - 2;
        while (i >= 0 && a[i] > a[i + 1]) {
            i--;
        }
        if (i < 0) {
            _reverse(a, 0, a.length - 1);
            return a;
        }

        int j = a.length - 1;
        while (j > i && a[j] < a[i]) {
            j--;
        }

        swap(a, i, j);
        _reverse(a, i + 1, a.length - 1);

        return a;
    }

    private void _reverse(int[] a, int start, int end) {
        while (start <= end) {
            swap(a, start, end);
            start++;
            end--;
        }
    }

    // @formatter:off
	/**
	 * Given n non-negative integers representing an elevation map where
	 * the width of each bar is 1, compute how much water it can trap
	 * after raining.
	 *
	 *
	 *
	 * Example 1:
	 * link : https://leetcode.com/problems/trapping-rain-water/
	 *
	 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
	 * Output: 6
	 * Explanation: The above elevation map (black section)
	 * is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
	 * In this case, 6 units of rain water (blue section) are being trapped.
	 *
	 *
	 * Example 2:
	 *
	 * Input: height = [4,2,0,3,2,5]
	 * Output: 9
	 */
	// @formatter:on
    @Test
    @DisplayName("Test Water Can Be Trapped")
    void testWaterCanBeTrapped() {
        int[] a = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int op = findWaterThatCanBeTrapped(a);
        Assertions.assertEquals(6, op);
    }


    @Test
    @DisplayName("Test Water Can Be Trapped")
    void testWaterCanBeTrappe4() {
        int[] a = {2, 1, 2, 3};
        int op = findWaterThatCanBeTrapped(a);
        Assertions.assertEquals(1, op);
    }


    @Test
    @DisplayName("Test Water Can Be Trapped")
    void testWaterCanBeTrappe5() {
        int[] a = {2, 1, 1, 3};
        int op = findWaterThatCanBeTrapped(a);
        Assertions.assertEquals(2, op);
    }

    @Test
    @DisplayName("Test Water Can Be Trapped")
    void testWaterCanBeTrapped1() {
        int[] a = {4, 2, 0, 3, 2, 5};
        int op = findWaterThatCanBeTrapped(a);
        Assertions.assertEquals(9, op);
    }

    @Test
    @DisplayName("Test Water Can Be Trapped")
    void testWaterCanBeTrapped2() {
        int[] a = {2, 1, 0, 1, 2};
        int op = findWaterThatCanBeTrapped(a);
        Assertions.assertEquals(4, op);
    }


    @Test
    @DisplayName("Test Water Can Be Trapped")
    void testWaterCanBeTrapped6() {
        int[] a = {4, 2, 3};
        int op = findWaterThatCanBeTrapped(a);
        Assertions.assertEquals(1, op);
    }


    int findWaterThatCanBeTrapped(int[] a) {
        int lmax = 0;
        int rmax = 0;
        int l = 0;
        int h = a.length - 1;
        int totalCap = 0;
        while (l < h) {
            int currCap = 0;
            if (a[l] < a[h]) {
                if (lmax > a[l]) {
                    currCap = lmax - a[l];
                } else {
                    lmax = a[l];
                }
                l++;
            } else {
                if (rmax > a[h]) {
                    currCap = rmax - a[h];
                } else {
                    rmax = a[h];
                }
                h--;
            }
            totalCap += currCap;
        }
        return totalCap;
    }


    /**
     * Tigerman is jumping on the varying heights of buildings in his town. While jumping from one building of height h1
     * to another building of height h2. He lost his |h1 - h2| powers. Now he wants to calculate the minimum energy he
     * loses if he jumps from 1 building and reaches the last building. He can jump at most 2 buildings in a jump.
     * <p>
     * (Note: Jump 2 buildings mean he can skip 1 building at most)
     * <p>
     * "Constraints. 0 <= N <= 100000 0 <= heights[i] <= 100
     * <p>
     * Test Cases: [20, 10, 30, 40] = 20 [0,0,10,10] = 20;
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * (20  30) + (30 - 40)  = 20
     * <p>
     * [7,4,4,2,6,6,3,4] = 7
     * <p>
     * 7  4 6 6  4 =  7
     * <p>
     * [4,8,3,10,4,4] = 2
     * <p>
     * <p>
     * <p>
     * <p>
     * [0,10,10,5] = 15 [6,3,5,4,10,6] = 4
     */

    @Test
    @DisplayName("Test Name")
    void testGetMinEnergy() {
        int minMumEnergy = findMinMumEnergy(new int[]{20, 10, 30, 40});
        assertEquals(20, minMumEnergy);

    }


    @Test
    @DisplayName("Test Name")
    void testGetMinEnergy1() {
        int minMumEnergy = findMinMumEnergy(new int[]{0, 10, 10, 5});
        assertEquals(15, minMumEnergy);
    }


    @Test
    @DisplayName("Test Name")
    void testGetMinEnergy2() {
        int minMumEnergy = findMinMumEnergy(new int[]{7, 4, 4, 2, 6, 6, 3, 4});
        assertEquals(8, minMumEnergy);
    }


    int findMinMumEnergy(int[] a) {
        //Test Cases: [20, 10, 30, 40] = 20 [0,0,10,10] = 20;
        int[] ans = new int[a.length];

        int firstJump = _calculateEnergy(a, 0, 1);
        int secondJump = _calculateEnergy(a, 0, 2);

        int i = firstJump < secondJump ? 1 : 2;
        ans[i] = Math.min(firstJump, secondJump);
        i++;
        while (i < a.length) {
            //ans[2] = 10
            //			[0, 0, 10, 10];
            //			currSum += Math.min(_calculateEnergy(a, i - 1, i), _calculateEnergy(a, i - 2, i));
            ans[i] = Math.min(_calculateEnergy(a, i - 1, i), _calculateEnergy(a, i - 2, i));
            i++;
        }
        return Arrays.stream(ans).sum();
    }

    private int _calculateEnergy(int[] a, int x, int y) {
        int e = a[x] - a[y];
        if (e < 0) {
            return -e;
        } else {
            return e;
        }
    }


    @Test
    @DisplayName("Test Find The Missing Natural Number")
    void testFindTheMissingNaturalNumber() {
        int missingNum = findTheMissingNaturalNumber(new int[]{1, 2, 4, 5}, 5);
        assertEquals(3, missingNum);
    }


    int findTheMissingNaturalNumber(int[] a, int x) {

        int as = 0;
        for (int i = 0; i <= x; i++) {
            as += i;
        }

        int cs = 0;
        for (int i = 0; i < a.length; i++) {
            cs += a[i];
        }

        if (cs < as) {
            return as - cs;
        } else if (cs == as) {
            throw new RuntimeException("");
        } else {
            throw new RuntimeException("");
        }
    }


    @Test
    @DisplayName("Test My Merge Sort")
    void testMyMergeSort() {
        int[] expected = {1, 3, 3, 5, 9, 21, 44};
        int[] a = {9, 3, 1, 3, 44, 21, 5};
        sortUsingMergeSort(a);
        assertArrayEquals(expected, a);
    }


    void sortUsingMergeSort(int[] a) {
        int l = 0;
        int h = a.length - 1;
        _mergeSortHelper(a, l, h);
    }

    private void _mergeSortHelper(int[] a, int l, int h) {
        if (l < h) {
            int mid = l + (h - l) / 2;
            _mergeSortHelper(a, l, mid);
            _mergeSortHelper(a, mid + 1, h);
            _merge(a, l, h, mid);
        }

    }

    private void _merge(int[] a, int l, int h, int mid) {
        int l1 = mid - l + 1;
        int l2 = h - mid;

        int[] t1 = new int[l1];
        int[] t2 = new int[l2];

        for (int i = 0; i < l1; i++) {
            t1[i] = a[l + i];
        }

        for (int i = 0; i < l2; i++) {
            t2[i] = a[mid + 1 + i];
        }

        int k = l;
        int i = 0;
        int j = 0;

        while (i < t1.length && j < t2.length) {
            if (t1[i] <= t2[j]) {
                a[k] = t1[i];
                i++;
            } else {
                a[k] = t2[j];
                j++;
            }
            k++;
        }

        while (i < t1.length) {
            a[k] = t1[i];
            k++;
            i++;
        }

        while (j < t2.length) {
            a[k] = t2[j];
            k++;
            j++;
        }
    }

}


class SudokuRowIteratingWorker implements Runnable {

    CyclicBarrier barrier;


    char[][] mat;
    int rowLimit;
    int colLimit;

    boolean quarterWorker;
    boolean validRowsFlag;
    AtomicBoolean resultCapture;

    public SudokuRowIteratingWorker(AtomicBoolean resultCapture, CyclicBarrier barrier, char[][] mat, int rowLimit, int colLimit, boolean quarterWorker) {
        this.barrier = barrier;
        this.resultCapture = resultCapture;
        this.mat = mat;
        this.rowLimit = rowLimit;
        this.colLimit = colLimit;
        this.validRowsFlag = true;
        this.quarterWorker = quarterWorker;
    }

    @Override
    public void run() {
        if (quarterWorker) {
            while (rowLimit < mat.length && colLimit < mat[0].length) {
                _checkValidity(0, 0, 3, 3);
                rowLimit = rowLimit + 3;
                colLimit = rowLimit + 3;
            }
        } else {
            _checkValidity(0, 0, rowLimit, colLimit);
        }
        resultCapture.set(validRowsFlag);
        try {
            barrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    private void _checkValidity(int startRow, int startColumn, int rowLimit, int colLimit) {
        int i = startRow;
        while (i < rowLimit) {
            if (validRowsFlag) {
                int j = startColumn;
                Set<Character> seenValues = new HashSet<>();
                while (j < colLimit) {
                    char val = mat[i][j];
                    if (val != '.') {
                        if (!seenValues.contains(val)) {
                            seenValues.add(val);
                        } else {
                            validRowsFlag = false;
                            break;
                        }
                    }
                    j++;
                }
            } else {
                break;
            }
            i++;
        }
    }
}


class SudokuColumnIteratingWorker implements Runnable {

    CyclicBarrier barrier;
    char[][] mat;
    int rowLimit;
    int colLimit;

    boolean quarterWorker;
    boolean validColFlag;

    AtomicBoolean resultCapture;

    public SudokuColumnIteratingWorker(AtomicBoolean resultCapture, CyclicBarrier barrier, char[][] mat, int rowLimit, int colLimit, boolean quarterWorker) {
        this.barrier = barrier;
        this.resultCapture = resultCapture;
        this.mat = mat;
        this.rowLimit = rowLimit;
        this.colLimit = colLimit;
        this.validColFlag = true;
        this.quarterWorker = quarterWorker;
    }

    @Override
    public void run() {
        if (quarterWorker) {
            while (rowLimit < mat.length && colLimit < mat[0].length) {
                _checkValidity(0, 0, 3, 3);
                rowLimit = rowLimit + 3;
                colLimit = rowLimit + 3;
            }
        } else {
            _checkValidity(0, 0, rowLimit, colLimit);
        }
        resultCapture.set(validColFlag);
        try {
            barrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }


    private void _checkValidity(int startRow, int startColumn, int rowLimit, int colLimit) {
        int j = 0;
        while (j < colLimit) {
            int row = 0;
            if (validColFlag) {
                Set<Character> seenValues = new HashSet<>();
                while (row < rowLimit) {
                    char val = mat[row][j];
                    if (val != '.') {
                        if (!seenValues.contains(val)) {
                            seenValues.add(val);
                        } else {
                            validColFlag = false;
                            break;
                        }
                    }
                    row++;
                }
            } else {
                break;
            }
            j++;
        }
    }
}





