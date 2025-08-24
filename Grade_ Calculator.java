import java.util.Scanner;
import java.util.InputMismatchException;

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Student Grade Calculator ---");

        // --- 1. Input: Get number of subjects ---
        int numSubjects = 0;
        while (numSubjects <= 0) {
            try {
                System.out.print("Enter the total number of subjects: ");
                numSubjects = scanner.nextInt();
                if (numSubjects <= 0) {
                    System.out.println("Number of subjects must be positive. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number.");
                scanner.next(); // Clear the invalid input from the buffer
            }
        }
        
        double totalMarks = 0;

        // --- Input: Get marks for each subject ---
        for (int i = 0; i < numSubjects; i++) {
            double mark = -1;
            while (mark < 0 || mark > 100) {
                try {
                    System.out.print("Enter marks obtained in Subject " + (i + 1) + " (out of 100): ");
                    mark = scanner.nextDouble();
                    if (mark < 0 || mark > 100) {
                        System.out.println("Marks must be between 0 and 100. Please enter a valid mark.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Clear buffer
                    mark = -1; // Reset mark to re-enter the loop
                }
            }
            totalMarks += mark;
        }

        // --- 2. Calculate Average Percentage ---
        double averagePercentage = totalMarks / numSubjects;

        // --- 3. Grade Calculation ---
        String grade;
        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B";
        } else if (averagePercentage >= 60) {
            grade = "C";
        } else if (averagePercentage >= 50) {
            grade = "D";
        } else {
            grade = "F"; // Fail
        }

        // --- 4. Display Results ---
        System.out.println("\n---------- 📊 Your Report Card ----------");
        // Using printf for formatted output
        System.out.printf("Total Marks Obtained: %.2f / %d%n", totalMarks, numSubjects * 100);
        System.out.printf("Average Percentage:   %.2f%%%n", averagePercentage);
        System.out.printf("Overall Grade:        %s%n", grade);
        System.out.println("----------------------------------------");

        scanner.close(); // Close the scanner to prevent resource leaks
    }
}
