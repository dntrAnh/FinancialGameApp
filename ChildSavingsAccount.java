import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class ChildSavingsAccount extends FinancialGameApp {
    protected double childAccount;
    private double childSavingsAccount;
    private Date lastSaveDate;
    private Date furthestSaveDate;
    private int streakMonths;
    private Scanner input;

    public ChildSavingsAccount() {
        lastSaveDate = null;
        furthestSaveDate = null;
        streakMonths = 0;
        input = new Scanner(System.in);
    }
    
    public double getChildAccount() {
    	return this.childAccount = getChildAccount();
    }

    public void saveMoney() {
        System.out.print("How much do you want to save? ");
        double toSave = input.nextDouble();
        if (childAccount >= toSave) {
            childAccount = childAccount - toSave;
            childSavingsAccount += toSave;
            lastSaveDate = new Date();

            if (furthestSaveDate == null || lastSaveDate.after(furthestSaveDate)) {
                furthestSaveDate = lastSaveDate;
                updateStreak();
            }

            System.out.println("Money saved successfully on " + formatDate(lastSaveDate));
            System.out.println("Main account: " + getChildAccount());
            System.out.println("Savings account: " + childSavingsAccount);
        } else {
            System.out.println("Insufficient funds to save.");
        }
    }

    public void withdrawFromSavings() {
        System.out.print("How much do you want to withdraw? ");
        double amount = input.nextDouble();
        System.out.println("Your last saving date was: " + lastSaveDate + ". "
                + "Would you like to withdraw? (yes/ no) ");
        String choice = input.next();
        if (choice.equalsIgnoreCase("yes") && childSavingsAccount >= amount) {
            childSavingsAccount -= amount;
            childAccount = getChildAccount() + amount;
            System.out.println("Withdrawn successfully.");
            System.out.println("Main account: " + getChildAccount());
            System.out.println("Savings account: " + childSavingsAccount);
            if (childSavingsAccount == 0) {
                lastSaveDate = null;
                updateStreak();
            }
        } else {
            System.out.println("Insufficient funds in the savings account.");
        }
    }

    private void updateStreak() {
        if (furthestSaveDate != null) {
            Calendar lastSaveCalendar = Calendar.getInstance();
            lastSaveCalendar.setTime(furthestSaveDate);
            Calendar currentCalendar = Calendar.getInstance();

            int dayDiff = 0;

            while (!lastSaveCalendar.after(currentCalendar)) {
                lastSaveCalendar.add(Calendar.DATE, 1);
                dayDiff++;
            }

            streakMonths = dayDiff / 30; // Convert days to months.

            // Determine different levels based on streakMonths
            if (streakMonths >= 12) {
                System.out.println("Saving Level: Platinum. You're a saving expert!");
            } else if (streakMonths >= 9) {
                System.out.println("Saving Level: Gold. You're a professional saver!");
            } else if (streakMonths >= 3) {
                System.out.println("Saving Level: Silver. Look at you! An amazing and fantastic saver!");
            } else if (streakMonths >= 1) {
                System.out.println("Saving Level: Bronze. You're on your track to become an amazing saver!");
            } else {
                System.out.println("No streak yet.");
            }
        }
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
    }
}
