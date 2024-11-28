import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
// Pre-process both strings
        str1 = preProcess(str1);
        str2 = preProcess(str2);

        // If the lengths are not the same after preprocessing, they can't be anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Convert both strings to character arrays
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();

        // Sort both arrays
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        // Compare the sorted arrays
        return Arrays.equals(arr1, arr2);
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
        StringBuilder result = new StringBuilder();

        // Process each character in the string
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                result.append(Character.toLowerCase(c));  // Add lowercase letter
            } else if (c == ' ') {
                result.append(c);  // Keep spaces
            }
        }
    
        return result.toString();
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
        // Convert the string to a list of characters
        List<Character> charList = new ArrayList<>();
        for (char c : str.toCharArray()) {
            charList.add(c);
        }

        // Shuffle the list to create a random anagram
        Collections.shuffle(charList);

        // Build a string from the shuffled list of characters
        StringBuilder anagram = new StringBuilder();
        for (char c : charList) {
            anagram.append(c);
        }

        return anagram.toString();
	}
}
