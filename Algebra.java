// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
        int result = x1;
        // If x2 is positive, add 1 to x1, x2 times
        while (x2 > 0) {
            result++;  
            x2--;
        }
        // If x2 is negative, subtract 1 from x1, |x2| times
        while (x2 < 0) {
            result--;  
            x2++;
        }
        return result;
    }

    public static int minus(int x1, int x2) {
        int result = x1;
        // If x2 is positive, subtract 1 from x1, x2 times
        while (x2 > 0) {
            result--;
            x2--;
        }
        // If x2 is negative, add 1 to x1, |x2| times
        while (x2 < 0) {
            result++;
            x2++;
        }
        return result;
    }

    public static int times(int x1, int x2) {
        int result = 0;
        
        // Check the signs of x1 and x2 to determine if the result should be positive or negative
        boolean negativeResult = (x1 < 0 && x2 >= 0) || (x1 >= 0 && x2 < 0);  // If signs are different, result will be negative
    
        // Work with absolute values of x1 and x2 for the multiplication
        x1 = Math.abs(x1);
        x2 = Math.abs(x2);
    
        // Multiply by adding x1 to result, x2 times
        while (x2 > 0) {
            result = plus(result, x1);  // Add x1 to result, x2 times
            x2--;
        }
    
        // If the result should be negative, subtract the result from 0
        if (negativeResult) {
            result = minus(0, result);  // Convert to negative if needed
        }
    
        return result;
    }

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
        int result = 1;
        while (n > 0) {
            result = times(result, x);  // Multiply result by x, n times
            n--;
        }
        return result;
	}

    public static int div(int x1, int x2) {
        // Handle division by zero
        if (x2 == 0) {
            throw new ArithmeticException("Division by zero");
        }
    
        int result = 0;
    
        boolean negativeResult = (x1 < 0 && x2 >= 0) || (x1 >= 0 && x2 < 0);  // Signs are different
    
        x1 = Math.abs(x1);
        x2 = Math.abs(x2);
    
        while (x1 >= x2) {
            x1 = minus(x1, x2);  // Subtract x2 from x1
            result++;  // Increment result for each subtraction
        }
    
        if (negativeResult) {
            result = minus(0, result);  // Make result negative
        }
    
        return result;
    }

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
        while (x1 >= x2) {
            x1 = minus(x1, x2);  // Subtract x2 from x1
        }
        return x1;
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
        int result = 0;
        while (times(result, result) <= x) {
            result++;  // Increase result until result^2 exceeds x
        }
        return minus(result, 1);  // Subtract 1 since result^2 is now greater than x
    }	  	  
}