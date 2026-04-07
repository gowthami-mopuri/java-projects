import java.util.*;
import java.io.*;

// Loan Class
class Loan {
    int loanId;
    String name;
    double amount;
    double interestRate;
    int tenure; // in months
    String status; // Approved / Rejected / Pending

    Loan(int loanId, String name, double amount, double interestRate, int tenure) {
        this.loanId = loanId;
        this.name = name;
        this.amount = amount;
        this.interestRate = interestRate;
        this.tenure = tenure;
        this.status = "Pending";
    }

    // EMI Calculation
    double calculateEMI() {
        double r = interestRate / (12 * 100);
        double emi = (amount * r * Math.pow(1 + r, tenure)) / (Math.pow(1 + r, tenure) - 1);
        return emi;
    }

    void display() {
        System.out.println("Loan ID: " + loanId);
        System.out.println("Name: " + name);
        System.out.println("Amount: " + amount);
        System.out.println("Interest: " + interestRate);
        System.out.println("Tenure: " + tenure);
        System.out.println("Status: " + status);
        System.out.println("EMI: " + calculateEMI());
        System.out.println("----------------------");
    }
}

// Main Class
public class LoanManagement {

    static ArrayList<Loan> loans = new ArrayList<>();

    // Apply Loan
    static void applyLoan(int id, String name, double amount, double rate, int tenure) {
        loans.add(new Loan(id, name, amount, rate, tenure));
        System.out.println("Loan applied successfully!");
    }

    // Approve / Reject Loan
    static void updateStatus(int id, String status) {
        for (Loan l : loans) {
            if (l.loanId == id) {
                l.status = status;
                System.out.println("Loan " + status);
                return;
            }
        }
        System.out.println("Loan not found!");
    }

    // View Loans
    static void viewLoans() {
        for (Loan l : loans) {
            l.display();
        }
    }

    // Track Repayment
    static void trackRepayment(int id, int monthsPaid) {
        for (Loan l : loans) {
            if (l.loanId == id) {
                double emi = l.calculateEMI();
                double paid = emi * monthsPaid;
                double remaining = (emi * l.tenure) - paid;

                System.out.println("Paid: " + paid);
                System.out.println("Remaining: " + remaining);
                return;
            }
        }
        System.out.println("Loan not found!");
    }

    // Save to File
    static void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("loans.txt"));
            for (Loan l : loans) {
                bw.write(l.loanId + "," + l.name + "," + l.amount + "," +
                         l.interestRate + "," + l.tenure + "," + l.status);
                bw.newLine();
            }
            bw.close();
            System.out.println("Data saved!");
        } catch (Exception e) {
            System.out.println("Error saving file!");
        }
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Loan Management ---");
            System.out.println("1. Apply Loan");
            System.out.println("2. Approve Loan");
            System.out.println("3. Reject Loan");
            System.out.println("4. View Loans");
            System.out.println("5. Track Repayment");
            System.out.println("6. Save Data");
            System.out.println("7. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Amount: ");
                    double amt = sc.nextDouble();
                    System.out.print("Enter Interest Rate: ");
                    double rate = sc.nextDouble();
                    System.out.print("Enter Tenure (months): ");
                    int tenure = sc.nextInt();

                    applyLoan(id, name, amt, rate, tenure);
                    break;

                case 2:
                    System.out.print("Enter Loan ID: ");
                    updateStatus(sc.nextInt(), "Approved");
                    break;

                case 3:
                    System.out.print("Enter Loan ID: ");
                    updateStatus(sc.nextInt(), "Rejected");
                    break;

                case 4:
                    viewLoans();
                    break;

                case 5:
                    System.out.print("Enter Loan ID: ");
                    int lid = sc.nextInt();
                    System.out.print("Months Paid: ");
                    int months = sc.nextInt();
                    trackRepayment(lid, months);
                    break;

                case 6:
                    saveToFile();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}