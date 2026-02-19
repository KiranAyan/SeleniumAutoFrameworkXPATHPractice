package basicJava;

import java.util.HashSet;

public class FirstRepeatChar {
    public static void main(String[] args) {
        String input = "automation";
        char result = findFirstRepeat(input);

        if (result != '\0') {
            System.out.println("First repeating character: " + result);
        } else {
            System.out.println("No repeating characters found.");
        }
    }

    public static char findFirstRepeat(String str) {
        // Create a HashSet to store characters we have already seen
        HashSet<Character> seen = new HashSet<>();

        // Convert string to character array and loop
        for (char c : str.toCharArray()) {
            // .add() returns false if the element is already present
            if (!seen.add(c)) {
                return c; // We found our first duplicate!
            }
        }

        // Return a null character if no repeat is found
        return '\0';
    }
}