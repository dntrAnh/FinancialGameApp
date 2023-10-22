import java.util.Scanner;

public class FinancialGameApp {
	public double childAccount;
	private double weeklyAllowance;
	private Scanner input;

	public FinancialGameApp() {
		this.input = new Scanner(System.in);
	}
	

	public void reportAccountBalance() {
		System.out.println("Current account balance is: " + getChildAccount());
	}

	public void setFinancialGoals() {
		System.out.print("Do you want to set a financial goal? (yes/no) ");
		String response = input.next().toLowerCase();
		if (response.equals("yes")) {
			System.out.print("Enter the name of the item you want to buy: ");
			String goalName = input.next();
			System.out.print("Enter the cost of the item: $");
			double goalCost = input.nextDouble();


			if ((getChildAccount() - goalCost) >= 0) {
				System.out.println("You earned it!");

			}
			else {
				System.out.println("You need to earn $" +  (-((getChildAccount() - goalCost))) + " to achieve your goal.");
				System.out.print("How much money do you earn per week? $");
				double weeklyEarnings = input.nextDouble();


				int weeksToAchieve = calculateWeeksToAchieve(goalCost, weeklyEarnings);
				int remainingDays = calculateRemainingDays(goalCost, getChildAccount(), weeklyEarnings);

				String timeToAchieve = formatTime(weeksToAchieve, remainingDays);
				System.out.println("Goal set: " + goalName + " ($" + goalCost + ")");

				System.out.println( timeToAchieve );
			}
		}
	}

	public int calculateWeeksToAchieve(double goalCost, double weeklyEarnings) {
		double remainingBalance = goalCost - getChildAccount();
		if (remainingBalance <= 0) {
			return 0; // Already have enough
		} else {
			double weeklySavings = remainingBalance / weeklyEarnings;
			return (int) Math.floor(weeklySavings);
		}
	}


	public int calculateRemainingDays(double goalCost, double currentBalance, double weeklyEarnings) {
		double remainingBalance = goalCost - currentBalance;
		if (remainingBalance <= 0) {
			return 0; // Already have enough
		} else {
			double moneyDay = weeklyEarnings / 7;
			int remainingWeeks = (int) Math.floor(remainingBalance / weeklyEarnings);
			double remainingDays = (remainingBalance - weeklyEarnings * remainingWeeks) / (int) Math.ceil(moneyDay);
			return (int) Math.ceil(remainingDays);
		}
	}



	public String formatTime(int weeks, int days) {
		if (weeks > 0 && days > 0) {
			return "It will take " + weeks + " week" + (weeks > 1 ? "s" : "") + " and " + days + " day" + (days > 1 ? "s" : "") + " to achieve your goal.";
		} else if (weeks > 0) {
			return "It will take " + weeks + " week" + (weeks > 1 ? "s" : "") + " to achieve your goal.";
		} else if (days > 0) {
			return "It will take " + days + " day" + (days > 1 ? "s" : "") + " to achieve your goal.";
		} else {
			return "You earned it!";
		}
	}


	public void earnMoney() {
		ChildSavingsAccount save = new ChildSavingsAccount();
	    System.out.print("Do you want to report a weekly income or make a one-time input of money? (weekly/one-time): ");
	    String incomeType = input.next();
	    if (incomeType.equalsIgnoreCase("weekly")) {
	        System.out.print("How much money did you earn this week? $");
	        double weeklyIncome = input.nextDouble();
	        this.weeklyAllowance = weeklyIncome;
	        childAccount = getChildAccount() + weeklyIncome;
	        System.out.println("Your account balance is now: $" + getChildAccount());
	    } else if (incomeType.equalsIgnoreCase("one-time")) {
	        System.out.print("How much money did you earn as a one-time input? $");
	        double oneTimeIncome = input.nextDouble();
	        childAccount = getChildAccount() + oneTimeIncome;
	        System.out.println("Your account balance is now: $" + childAccount);
	    } else {
	        System.out.println("Invalid choice. Please choose 'weekly' or 'one-time'.");
	        return;
	    }

	    System.out.print("Would you like to save money? (yes/ no): ");
	    String choice = input.next();
	    if (choice.equalsIgnoreCase("yes")) {
	        save.saveMoney();
	    }
	}


	public void spendMoney() {
		System.out.print("How much money do you want to spend? $");
		double spendAmount = input.nextDouble();
		if (getChildAccount() >= spendAmount) {
			childAccount = getChildAccount() - spendAmount;
			System.out.println("Purchase successful!");
		} else {
			System.out.print("Insufficient funds. Save more to make this purchase.");
		}
		System.out.println("Your account balance is now: $" + getChildAccount());
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		FinancialGameApp baseAccount = new FinancialGameApp();
        ChildSavingsAccount account = new ChildSavingsAccount();

        int choice = 0;
        do {
            System.out.println("===== Financial Education Game Menu =====");
            System.out.println("1. Report Account Balance");
            System.out.println("2. Set Financial Goals");
            System.out.println("3. Earn Money (Report new Allowance)");
            System.out.println("4. Save Money (From Current Balance)");
            System.out.println("5. Withdraw from Savings");
            System.out.println("6. Spend Money");
            System.out.println("7. Quit");
            System.out.print("Enter your choice (1/2/3/4/5/6/7): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character.
                switch (choice) {
                    case 1:
                        baseAccount.reportAccountBalance();
                        break;
                    case 2:
                        baseAccount.setFinancialGoals();
                        break;
                    case 3:
                        baseAccount.earnMoney();
                        break;
                    case 4:
                        account.saveMoney();
                        break;
                    case 5:
                        account.withdrawFromSavings();
                        break;
                    case 6:
                        baseAccount.spendMoney();
                        break;
                    case 7:
                        System.out.println("Thank you for using FinChild. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose a valid option.");
                        break;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine(); // Consume the invalid input.
            }
        } while (choice != 7);

        scanner.close(); // Close the scanner when done.
		}


	public double getChildAccount() {
		return childAccount;
	}
	
}
