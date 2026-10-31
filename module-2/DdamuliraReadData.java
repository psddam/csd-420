
/*
 * Name: Peter Ddamulira
 * Course: CSD420 - Advanced Java Programming
 * Assignment: Module 2.2 - Binary File I/O
 *
 * Purpose:
 * Read and display the random integers and doubles
 * stored in the binary data file.
 */

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class DdamuliraReadData {

    public static void main(String[] args) {

        String fileName = "Ddamulira datafile.dat";

        try (DataInputStream input = new DataInputStream(
                new FileInputStream(fileName))) {

            int batch = 1;

            while (input.available() > 0) {

                System.out.println("\nData Set " + batch);
                System.out.println("Integers:");

                for (int i = 0; i < 5; i++) {
                    System.out.println(input.readInt());
                }

                System.out.println("Doubles:");

                for (int i = 0; i < 5; i++) {
                    System.out.printf("%.2f%n", input.readDouble());
                }

                batch++;
            }

        } catch (EOFException e) {
            System.out.println("\nFinished reading the data file.");

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
