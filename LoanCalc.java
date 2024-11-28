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
            balance = (balance * (1 + rate)) - payment;  // Apply interest first, then subtract payment
        }

        return balance < 0 ? 0 : balance;
    }

	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        double payment = loan / n;  // Initial guess: payment without interest
        iterationCounter = 0;
    
        while (true) {
            iterationCounter++;
            double balance = endBalance(loan, rate, n, payment);
    
            // If the balance is close to zero, stop the search
            if (Math.abs(balance) <= epsilon) {
                break;
            }
    
            // Gradually adjust payment to get closer to the correct balance
            payment += epsilon;  // This may need to be smaller depending on the desired precision
        }
        
        return payment;
    }

    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        double L = loan / n;  // Lower bound guess (no interest, just principal payments)
        double H = loan * (1 + rate);  // Upper bound guess (overestimation)
        double g = 0;
        iterationCounter = 0;

        while ((H - L) > epsilon) {
            iterationCounter++;
            g = (L + H) / 2;  // Middle point (payment)

            double balance = endBalance(loan, rate, n, g);

            if (balance > 0) {
                L = g;  // Narrow the interval to the upper half
            } else {
                H = g;  // Narrow the interval to the lower half
            }
        }
        return (L + H) / 2;  // Return the midpoint as the best estimate for the payment
    }
}