
/*
 * Name: Peter Ddamulira
 * Course: CSD420 - Advanced Java Programming
 * Assignment: Module 2.2 - Binary File I/O
 *
 * Purpose:
 * Generate five random integers and five random double values.
 * Write them to a binary file, appending if the file exists.
 */

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class DdamuliraWriteData {

    public static void main(String[] args) {

        String fileName = "Ddamulira datafile.dat";
        Random random = new Random();

        int[] integers = new int[5];
        double[] doubles = new double[5];

        for (int i = 0; i < 5; i++) {
            integers[i] = random.nextInt(100) + 1;
            doubles[i] = random.nextDouble() * 100;
        }

        try (DataOutputStream output = new DataOutputStream(
                new FileOutputStream(fileName, true))) {

            for (int number : integers) {
                output.writeInt(number);
            }

            for (double number : doubles) {
                output.writeDouble(number);
            }

            System.out.println("Data saved to " + fileName);
            System.out.println("Five integers and five doubles added.");

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
