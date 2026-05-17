import java.util.*;

public class Main {
    static String savedPassword = "bachi";
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        if (!authenticate(s)) return;

        FinanceManager m = new FinanceManager();
        int choice;

        while (true) {
            System.out.println("\n~~~ Personal Finance and Budget Assistant ~~~\n");
            System.out.println("1. Transaction");
            System.out.println("2. Budget");
            System.out.println("3. Reset Password");
            System.out.println("4. Exit\n");
            System.out.print("Enter your choice: ");
            choice = s.nextInt();
            s.nextLine(); 

            switch (choice) {
                case 1:
                    transactionMenu(m, s);
                    break;

                case 2:
                    budgetMenu(m, s);
                    break;
                    
                case 3:
                    System.out.print("Enter current password: ");
                    String current = s.nextLine();
                    if (!current.equals(savedPassword)) {
                        System.out.println("Incorrect password. Cannot reset.");
                    } else {
                        System.out.print("Enter new password: ");
                        String newPass = s.nextLine();
                        savedPassword = newPass;
                        System.out.println("Password reset successful. Please login again.\n");
                         if (!authenticate(s)) return;
                    }
                    break;

                case 4:
                    System.out.println("Thank you! Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
    public static boolean authenticate(Scanner s) {
        System.out.print("\nEnter password: ");
        String inputPass = s.nextLine();
        if (!inputPass.equals(savedPassword)) {
            System.out.println("Incorrect password. Exiting...");
            return false;
        }
        return true;
    }

    public static void transactionMenu(FinanceManager m, Scanner s) {
        int choice;
        while (true) {
            System.out.println("\n~~~ Transaction Menu ~~~");
            System.out.println("1. Add Transaction");
            System.out.println("2. Update Transaction");
            System.out.println("3. Delete Transaction");
            System.out.println("4. View Transactions");
            System.out.println("5. Generate Summary");
            System.out.println("6. Back to Main Menu\n");
            System.out.print("Enter choice: ");
            choice = s.nextInt();
            s.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Type (income/expense): ");
                    String type = s.nextLine();
                    System.out.print("Category: ");
                    String cate = s.nextLine();
                    System.out.print("Amount: ");
                    double amt = s.nextDouble();
                    s.nextLine();
                    System.out.print("Description: ");
                    String desc = s.nextLine();
                    m.addTransaction(type, cate, amt, desc);
                    m.generateSummary();
                    break;

                case 2:
                    System.out.print("Transaction ID to update: ");
                    int idU = s.nextInt();
                    s.nextLine();
                    System.out.print("New Category: ");
                    String cateU = s.nextLine();
                    System.out.print("New Amount: ");
                    double amtU = s.nextDouble();
                    s.nextLine();
                    System.out.print("New Description: ");
                    String descU = s.nextLine();
                    m.updateTransaction(idU, cateU, amtU, descU);
                    m.generateSummary();
                    break;

                case 3:
                    System.out.print("Transaction ID to delete: ");
                    int idD = s.nextInt();
                    m.deleteTransaction(idD);
                    m.generateSummary();
                    break;

                case 4:
                    while (true) {
                        System.out.println("\n~~~ View Transactions ~~~");
                        System.out.println("1. View by Month");
                        System.out.println("2. View by Category");
                        System.out.println("3. View All");
                        System.out.println("4. Back");
                        System.out.print("Enter your choice: ");
                        int viewChoice = s.nextInt();
                        s.nextLine(); 
                
                        switch (viewChoice) {
                            case 1:
                                System.out.print("Enter month (1-12): ");
                                int month = s.nextInt();
                                System.out.print("Enter year (e.g., 2025): ");
                                int year = s.nextInt();
                                s.nextLine();
                                m.viewTransactionsByMonth(month, year);
                                break;
                
                            case 2:
                                System.out.print("Enter category: ");
                                String category = s.nextLine();
                                m.viewTransactionsByCategory(category);
                                break;
                
                            case 3:
                                m.viewTransactions();
                                break;
                
                            case 4:
                                return;
                
                            default:
                                System.out.println("Invalid choice. Try again.");
                        }
                    }

                case 5:
                    m.generateSummary();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void budgetMenu(FinanceManager m, Scanner s) {
        int choice;
        while (true) {
            System.out.println("\n~~~ Budget Menu ~~~");
            System.out.println("1. Add Budget Limit");
            System.out.println("2. Update Budget Limit");
            System.out.println("3. Delete Budget Limit");
            System.out.println("4. View Budgets");
            System.out.println("5. Compare Budgets");
            System.out.println("6. Back to Main Menu\n");
            System.out.print("Enter choice: ");
            choice = s.nextInt();
            s.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Budget Category: ");
                    String budCate = s.nextLine();
                    System.out.print("Limit Amount: ");
                    double limitAmt = s.nextDouble();
                    s.nextLine();
                    m.addBudget(budCate, limitAmt);
                    break;

                case 2:
                    System.out.print("Enter Budget Category to update: ");
                    String updateCat = s.nextLine();
                    System.out.print("New Limit Amount: ");
                    double newLimit = s.nextDouble();
                    s.nextLine();
                    m.updateBudget(updateCat, newLimit);
                    break;

                case 3:
                    System.out.print("Enter Budget Category to delete: ");
                    String delCat = s.nextLine();
                    m.deleteBudget(delCat);
                    break;

                case 4:
                    m.viewBudgets();
                    break;

                case 5:
                    compareBudgetsSubMenu(m,s);
                    break;
                
                case 6:
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
    public static void compareBudgetsSubMenu(FinanceManager m, Scanner s) {
        while (true) {
            System.out.println("\n===== Compare Budgets Submenu =====");
            System.out.println("1. Compare Budgets with Income");
            System.out.println("2. Compare Budgets with Balance");
            System.out.println("3. Back to Budget Menu");
            System.out.print("Enter your choice: ");
    
            int subChoice = Integer.parseInt(s.nextLine());
    
            switch (subChoice) {
                case 1:
                    m.compareBudgetsWithIncome();
                    break;
                case 2:
                    m.compareBudgetsWithBalance();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

class FinanceManager {
    private List<Transaction> transactions = new ArrayList<>();
    private List<Budget> budgets = new ArrayList<>();

    public void addTransaction(String type, String category, double amount, String description) {
        transactions.add(new Transaction(type, category, amount, description));
        System.out.println("\nTransaction added successfully!");
    }

    public void updateTransaction(int id, String category, double amount, String description) {
        for (Transaction t : transactions) {
            if (t.getId() == id) {
                t.setCategory(category);
                t.setAmount(amount);
                t.setDescription(description);
                System.out.println("\nTransaction updated successfully!");
                return;
            }
        }
        System.out.println("Transaction not found.");
    }

    public void deleteTransaction(int id) {
        Iterator<Transaction> it = transactions.iterator();
        while (it.hasNext()) {
            Transaction t = it.next();
            if (t.getId() == id) {
                it.remove();
                System.out.println("\nTransaction deleted.");
                return;
            }
        }
        System.out.println("Transaction not found.");
    }

    public void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }

    public void viewTransactionsByMonth(int month, int year) {
        boolean found = false;
        System.out.println("\nTransactions for " + month + "/" + year + ":");
        for (Transaction t : transactions) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(t.getDate());
            int tMonth = cal.get(Calendar.MONTH) + 1;
            int tYear = cal.get(Calendar.YEAR);
            if (tMonth == month && tYear == year) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) System.out.println("No transactions found for this month.");
    }

    public void viewTransactionsByCategory(String category) {
        boolean found = false;
        System.out.println("\nTransactions in category: " + category);
        for (Transaction t : transactions) {
            if (t.getCategory().equalsIgnoreCase(category)) {
                System.out.println(t);
                found = true;
            }
        }
        if (!found) System.out.println("No transactions found in this category.");
    }


    public void addBudget(String category, double limit) {
        budgets.add(new Budget(category, limit));
        System.out.println("Budget added.");
    }

    public void updateBudget(String category, double newLimit) {
        for (Budget b : budgets) {
            if (b.getCategory().equalsIgnoreCase(category)) {
                b.setLimitAmount(newLimit);
                System.out.println("Budget updated for category: " + category);
                return;
            }
        }
        System.out.println("Budget category not found.");
    }

    public void deleteBudget(String category) {
        Iterator<Budget> iterator = budgets.iterator();
        while (iterator.hasNext()) {
            Budget b = iterator.next();
            if (b.getCategory().equalsIgnoreCase(category)) {
                iterator.remove();
                System.out.println("Budget deleted for category: " + category);
                return;
            }
        }
        System.out.println("Budget category not found.");
    }

    public void viewBudgets() {
        if (budgets.isEmpty()) {
            System.out.println("No budgets set.");
        } else {
            for (Budget b : budgets) {
                System.out.println(b);
            }
        }
    }

    public void generateSummary() {
        double income = 0, expense = 0;
        for (Transaction t : transactions) {
            if (t.getType().equalsIgnoreCase("income")) {
                income += t.getAmount();
            } else {
                expense += t.getAmount();
            }
        }
        System.out.println("\nTotal Income: ₹" + income);
        System.out.println("Total Expense: ₹" + expense);
        System.out.println("Balance: Rs." + (income - expense));

        if ((income - expense) < 0) {
            System.out.println("⚠️ Warning: Your balance is negative! Consider reducing expenses or increasing income.");
        }
    
    }

    public void compareBudgetsWithBalance() {
        System.out.println("\n===== Total Budget vs Total Balance =====");
    
        double totalBudget = 0;
        double totalIncome = 0;
        double totalExpense = 0;
    
        for (Budget b : budgets) {
            totalBudget += b.getLimitAmount();
        }
    
        for (Transaction t : transactions) {
            if (t.getType().equalsIgnoreCase("income")) {
                totalIncome += t.getAmount();
            } else if (t.getType().equalsIgnoreCase("expense")) {
                totalExpense += t.getAmount();
            }
        }
    
        double balance = totalIncome - totalExpense;
    
        System.out.printf("Total Budget: %.2f\n", totalBudget);
        System.out.printf("Total Balance: %.2f\n", balance);
        System.out.println("Status: " + (balance >= totalBudget ? "Within Budget" : "Below Budget"));
    }

    
    public void compareBudgetsWithIncome() {
        System.out.println("\n===== Total Budget vs Total Income =====");
    
        double totalBudget = 0;
        double totalIncome = 0;

        for (Budget b : budgets) {
            totalBudget += b.getLimitAmount();
        }

        for (Transaction t : transactions) {
            if (t.getType().equalsIgnoreCase("income")) {
                totalIncome += t.getAmount();
            }
        }
    
        System.out.printf("Total Budget: %.2f\n", totalBudget);
        System.out.printf("Total Income: %.2f\n", totalIncome);
        System.out.println("Status: " + (totalIncome >= totalBudget ? "Within Budget" : "Below Budget"));
    }

}


class Transaction {
    private static int counter = 1;
    private int id;
    private Date date;
    private String type;
    private String category;
    private double amount;
    private String description;

    public Transaction(String type, String category, double amount, String description) {
        this.id = counter++;
        this.date = new Date();
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }
    
    public int getId() {
    return id;
    }
    public String getType() {
        return type;
    }
    public String getCategory() {
        return category;
    }
    public double getAmount() {
        return amount;
    }
    public Date getDate() {
    return date;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return id + " | " + date + " | " + type.toUpperCase() + " | " + category + " | ₹" + amount + " | " + description;
    }
}

class Budget {
    private String category;
    private double limitAmount;
    public Budget(String category, double limitAmount) {
    this.category = category;
    this.limitAmount = limitAmount;
    }
    
    public String getCategory() {
        return category;
    }
    public double getLimitAmount() {
        return limitAmount;
    }
    
    public void setLimitAmount(double limitAmount) {
        this.limitAmount = limitAmount;
    }
    
    @Override
    public String toString() {
        return "Category: " + category + ", Limit: ₹" + limitAmount;
    }
}
