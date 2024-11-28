// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
        double balance = loan;
        for (int i = 0; i < n; i++) {
            balance = (balance - payment) * (1 + rate);  // Apply the payment and interest
        }
        return balance;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        double payment = loan / n;  // Initial guess for the payment (without interest)
        double balance = endBalance(loan, rate, n, payment);
        iterationCounter = 0;  // Reset iteration counter
    
        // Brute force search: adjust payment dynamically to converge more efficiently
        double stepSize = 10;  // Initial step size (larger increments)
        int maxIterations = 10000;  // Maximum iterations to avoid infinite loops
    
        while (Math.abs(balance) > epsilon && iterationCounter < maxIterations) {
            payment += stepSize;  // Adjust payment
            balance = endBalance(loan, rate, n, payment);  // Recalculate balance with new payment
            iterationCounter++;  // Increment iteration counter
    
            // Dynamically adjust step size based on balance to speed up convergence
            if (Math.abs(balance) > 0) {
                stepSize = Math.min(stepSize * 2, 1000);  // Increase step size up to a maximum limit
            } else {
                stepSize = Math.max(stepSize / 2, 1);  // Decrease step size when balance gets closer to zero
            }
        }
    
        // If the loop exceeded max iterations, throw an error to prevent getting stuck
        if (iterationCounter >= maxIterations) {
            System.out.println("Brute force search reached maximum iterations without convergence.");
        }
    
        return payment;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        double low = loan / n;  // Initial guess for low bound
        double high = loan * (1 + rate);  // Initial guess for high bound (a bit larger than loan/n)
        double payment = (low + high) / 2;  // Midpoint of low and high
        iterationCounter = 0;  // Reset iteration counter

        // Bisection search: narrow down the range until the balance is close to zero
        while ((high - low) > epsilon) {
            payment = (low + high) / 2;  // Midpoint
            double balance = endBalance(loan, rate, n, payment);  // Calculate balance with current payment

            if (balance > 0) {
                low = payment;  // If balance is positive, search in the upper half
            } else {
                high = payment;  // If balance is negative, search in the lower half
            }

            iterationCounter++;  // Increment iteration counter
        }
        return payment;
    }
}