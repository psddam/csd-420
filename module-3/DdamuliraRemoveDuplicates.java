
/*
 * Student: Peter Ddamulira
 * Course: CSD420 - Advanced Java Programming
 * Assignment: Module 3.2 - Java Generics
 * Date: November 6, 2026
 *
 * This program generates 50 random integers between
 * 1 and 20 and uses a generic method to remove
 * duplicate values without modifying the original list.
 */

import java.util.ArrayList;
import java.util.Random;

public class DdamuliraRemoveDuplicates {

    // Returns a new ArrayList containing unique values.
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {

        ArrayList<E> uniqueList = new ArrayList<>();

        for (E item : list) {
            if (!uniqueList.contains(item)) {
                uniqueList.add(item);
            }
        }

        return uniqueList;
    }

    public static void main(String[] args) {

        ArrayList<Integer> originalList = new ArrayList<>();
        Random random = new Random();

        // Generate 50 random integers between 1 and 20.
        for (int i = 0; i < 50; i++) {
            originalList.add(random.nextInt(20) + 1);
        }

        // Remove duplicates using the generic method.
        ArrayList<Integer> uniqueList =
                removeDuplicates(originalList);

        System.out.println("CSD420 - Module 3.2");
        System.out.println("Java Generics - Removing Duplicates");

        System.out.println("\nOriginal ArrayList:");
        System.out.println(originalList);

        System.out.println("\nOriginal List Size: "
                + originalList.size());

        System.out.println("\nArrayList Without Duplicates:");
        System.out.println(uniqueList);

        System.out.println("\nUnique List Size: "
                + uniqueList.size());

        System.out.println("\nDuplicates Removed: "
                + (originalList.size() - uniqueList.size()));

        // Verify the original list remains unchanged.
        if (originalList.size() != 50) {
            throw new AssertionError("Original list size is incorrect.");
        }

        // Verify all generated values are between 1 and 20.
        for (int number : originalList) {
            if (number < 1 || number > 20) {
                throw new AssertionError("Number outside required range.");
            }
        }

        // Verify the new list contains no duplicates.
        for (int i = 0; i < uniqueList.size(); i++) {
            for (int j = i + 1; j < uniqueList.size(); j++) {
                if (uniqueList.get(i).equals(uniqueList.get(j))) {
                    throw new AssertionError("Duplicate found.");
                }
            }
        }

        // Verify the generic method also works with Strings.
        ArrayList<String> languages = new ArrayList<>();
        languages.add("Java");
        languages.add("Python");
        languages.add("Java");
        languages.add("JavaScript");
        languages.add("Python");

        ArrayList<String> uniqueLanguages =
                removeDuplicates(languages);

        System.out.println("\nGeneric Method Test With Strings:");
        System.out.println("Original: " + languages);
        System.out.println("Unique: " + uniqueLanguages);

        if (uniqueLanguages.size() != 3) {
            throw new AssertionError("String test failed.");
        }

        System.out.println("\nAll Tests: PASSED");
    }
}
