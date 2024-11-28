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
        // If both are positive, add normally
        if (x1 >= 0 && x2 >= 0) {
            int result = x1;
            while (x2 > 0) {
                result++;  // Add 1 to result, x2 times
                x2--;
            }
            return result;
        }

        // If one number is negative, handle like moving in the negative direction
        if (x1 >= 0 && x2 < 0) {
            return minus(x1, Math.abs(x2)); // Subtract the absolute value of x2 from x1
        }

        if (x1 < 0 && x2 >= 0) {
            return minus(x2, Math.abs(x1)); // Subtract the absolute value of x1 from x2
        }

        // If both are negative, add them by subtracting their absolute values
        return -(plus(Math.abs(x1), Math.abs(x2)));  // Add the absolute values and make the result negative

	}

    public static int minus(int x1, int x2) {
        // If both are positive, subtract normally
        if (x1 >= 0 && x2 >= 0) {
            int result = x1;
            while (x2 > 0) {
                result--;  // Subtract 1 from result, x2 times
                x2--;
            }
            return result;
        }
    
        // If one of the numbers is negative, handle appropriately
        if (x1 >= 0 && x2 < 0) {
            return plus(x1, Math.abs(x2));  // Adding a negative is like subtracting
        }
    
        if (x1 < 0 && x2 >= 0) {
            return plus(x1, -x2);  // Negative minus positive is like adding a negative
        }
    
        // If both are negative, subtract the absolute values and return the result as negative
        return plus(Math.abs(x1), Math.abs(x2)) * -1; // Subtract and return as negative
    }

	// Returns x1 * x2
	public static int times(int x1, int x2) {
        int result = 0;
        while (x2 > 0) {
            result = plus(result, x1);  // Add x1 to result, x2 times
            x2--;
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

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
        int result = 0;
        while (x1 >= x2) {
            x1 = minus(x1, x2);  // Subtract x2 from x1
            result++;  // Increment result for each subtraction
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