/*
 * Module 4.2 - Test Results and Analysis
 *
 * Test 1: 50,000 integers
 * Iterator Time: 3.751 ms
 * get(index) Time: 774.636 ms
 * get(index) was 206.54 times slower.
 *
 * Test 2: 500,000 integers
 * Iterator Time: 4.480 ms
 * get(index) Time: 135244.880 ms
 * get(index) was 30191.28 times slower.
 *
 * Both tests passed and returned the expected sums.
 *
 * Results:
 * The iterator was significantly faster in both tests.
 * It moves sequentially through the LinkedList, which
 * makes traversal more efficient.
 *
 * The get(index) method was much slower because it
 * repeatedly searches through the linked nodes to
 * retrieve each element.
 *
 * Increasing the list from 50,000 to 500,000 elements
 * made the get(index) approachs approximately 174.6
 * times slower in these measurements.
 *
 * Conclusion:
 * An iterator is the better choice when traversing
 * every element in a LinkedList. Iterator traversal
 * has O(n) time complexity, while repeatedly calling
 * get(index) has O(n^2) time complexity.
 *
 * Execution times may vary depending on the system,
 * JVM optimization, and other running processes.

 * Student: Peter Ddamulira
 * Course: CSD420 - Advanced Java Programming
 * Assignment: Module 4.2 - LinkedList Performance
 * Date: November 6, 2026
 *
 * Purpose:
 * Compare the time required to traverse a LinkedList
 * using an Iterator and the get(index) method.
 *
 * Test sizes:
 * 1. 50,000 integers
 * 2. 500,000 integers
 *
 * Expected performance:
 * Iterator traversal is O(n) because it moves
 * sequentially through the linked nodes.
 *
 * Repeated get(index) traversal is O(n^2)
 * because LinkedList must walk through nodes
 * to reach each requested index.
 *
 * Actual results and observations should be
 * documented after running both tests.
 */

import java.util.Iterator;
import java.util.LinkedList;

public class DdamuliraLinkedListPerformance {

    // Prevents the benchmark results from being ignored.
    private static volatile long resultSink;

    // Traverses the list using an Iterator.
    public static long traverseWithIterator(
            LinkedList<Integer> list) {

        long sum = 0;
        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            sum += iterator.next();
        }

        return sum;
    }

    // Traverses the list using get(index).
    public static long traverseWithGet(
            LinkedList<Integer> list) {

        long sum = 0;

        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }

        return sum;
    }

    // Checks correctness using a small known list.
    public static void runCorrectnessTest() {

        LinkedList<Integer> testList = new LinkedList<>();

        testList.add(10);
        testList.add(20);
        testList.add(30);
        testList.add(40);
        testList.add(50);

        long expectedSum = 150;

        long iteratorSum = traverseWithIterator(testList);
        long getSum = traverseWithGet(testList);

        if (iteratorSum != expectedSum ||
                getSum != expectedSum) {

            throw new AssertionError(
                    "Correctness test failed.");
        }

        System.out.println(
                "Correctness Test: PASSED");
        System.out.println(
                "Both methods returned the expected sum: "
                        + expectedSum);
    }

    // Measures both traversal methods.
    public static void runPerformanceTest(int size) {

        LinkedList<Integer> numbers = new LinkedList<>();

        for (int i = 1; i <= size; i++) {
            numbers.add(i);
        }

        long expectedSum = (long) size * (size + 1) / 2;

        System.out.println(
                "\n------------------------------------");
        System.out.println(
                "Testing LinkedList with " + size + " integers");
        System.out.println(
                "------------------------------------");

        // Measure Iterator traversal.
        long startIterator = System.nanoTime();

        long iteratorSum = traverseWithIterator(numbers);

        long endIterator = System.nanoTime();

        double iteratorTime =
                (endIterator - startIterator) / 1_000_000.0;

        // Measure get(index) traversal.
        long startGet = System.nanoTime();

        long getSum = traverseWithGet(numbers);

        long endGet = System.nanoTime();

        double getTime =
                (endGet - startGet) / 1_000_000.0;

        resultSink = iteratorSum + getSum;

        // Verify both methods produce correct results.
        boolean passed =
                iteratorSum == expectedSum &&
                getSum == expectedSum;

        System.out.println(
                "Expected Sum: " + expectedSum);
        System.out.println(
                "Iterator Sum: " + iteratorSum);
        System.out.println(
                "get(index) Sum: " + getSum);

        System.out.println(
                "Result Verification: "
                        + (passed ? "PASSED" : "FAILED"));

        System.out.printf(
                "Iterator Time: %.3f ms%n",
                iteratorTime);

        System.out.printf(
                "get(index) Time: %.3f ms%n",
                getTime);

        if (iteratorTime > 0) {
            System.out.printf(
                    "get(index) was approximately %.2f times slower.%n",
                    getTime / iteratorTime);
        }

        if (!passed) {
            throw new AssertionError(
                    "Performance test results did not match.");
        }
    }

    public static void main(String[] args) {

        System.out.println(
                "CSD420 - Module 4.2");
        System.out.println(
                "LinkedList Traversal Performance Comparison");

        runCorrectnessTest();

        // Test 1: 50,000 integers.
        runPerformanceTest(50_000);

        // Test 2: 500,000 integers.
        runPerformanceTest(500_000);

        System.out.println(
                "\nAll tests completed successfully.");
    }
}
