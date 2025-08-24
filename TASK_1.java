import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class GuessingGame {

    public static void main(String[] args) {
        // --- Setup ---
        // Create instances of Scanner for user input and Random for number generation
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // --- Game settings ---
        final int LOWER_BOUND = 1;
        final int UPPER_BOUND = 100;
        final int MAX_ATTEMPTS = 10;
        
        // --- Game state variables ---
        int playerScore = 0;
        int roundsPlayed = 0;

        // --- Welcome Message ---
        System.out.println("🎉 Welcome to the Number Guessing Game! 🎉");
        System.out.println("I'm thinking of a number between " + LOWER_BOUND + " and " + UPPER_BOUND + ".");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it correctly in each round.");
        System.out.println("----------------------------------------------------");

        // --- Main Game Loop (for multiple rounds) ---
        while (true) {
            roundsPlayed++;
            // Generate the secret number for the new round
            // nextInt(bound) generates 0 to bound-1, so we add 1 to get 1 to 100
            int secretNumber = random.nextInt(UPPER_BOUND) + LOWER_BOUND;
            int attemptsTaken = 0;
            boolean roundWon = false;
            
            System.out.println("\n--- Round " + roundsPlayed + " ---");

            // --- Guessing Loop (for the current round) ---
            while (attemptsTaken < MAX_ATTEMPTS) {
                System.out.print("Attempt " + (attemptsTaken + 1) + "/" + MAX_ATTEMPTS + " | Enter your guess: ");
                
                try {
                    int guess = scanner.nextInt();
                    attemptsTaken++;

                    // --- Compare guess and provide feedback ---
                    if (guess < secretNumber) {
                        System.out.println("Too low! Try a higher number. ⬆");
                    } else if (guess > secretNumber) {
                        System.out.println("Too high! Try a lower number. ⬇");
                    } else {
                        System.out.println("✨ Correct! You guessed the number " + secretNumber + " in " + attemptsTaken + " attempts! ✨");
                        playerScore++;
                        roundWon = true;
                        break; // Exit the inner guessing loop
                    }
                } catch (InputMismatchException e) {
                    System.out.println("⚠ Invalid input! Please enter a whole number.");
                    scanner.next(); // Clear the invalid input from the scanner buffer
                }
            }

            // --- Round End ---
            if (!roundWon) {
                System.out.println("\n😥 Oops! You've run out of attempts.");
                System.out.println("The correct number was " + secretNumber + ".");
            }

            // Display current score
            System.out.println("Your current score: " + playerScore + " round(s) won.");
            System.out.println("----------------------------------------------------");

            // --- Ask to play another round ---
            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.next();

            if (!playAgain.equalsIgnoreCase("yes") && !playAgain.equalsIgnoreCase("y")) {
                break; // Exit the main game loop
            }
        }

        System.out.println("\nThanks for playing! Hope you had fun. 😊");
        scanner.close(); // Close the scanner to prevent resource leaks
    }
}
