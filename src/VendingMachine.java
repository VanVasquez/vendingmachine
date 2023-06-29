import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

abstract class VendingMachine {
    protected List<Item> itemSlots;
    protected double totalSales;
    protected double totalChange;
    protected String name;
    protected List<Transaction> transactions;
    protected Scanner scanner;
    protected int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
    protected int capacity;
    protected int balance;

    public VendingMachine() {
        scanner = new Scanner(System.in);
        itemSlots = new ArrayList<>();
        transactions = new ArrayList<>();
        totalChange = 0;
        totalSales = 0;
        capacity = 0;
        balance = 0;
    }

    public abstract void createVendingMachine();
    public abstract void testVendingMachine();

    protected void restockItems() {
        displayItems();
        System.out.println("Enter item to restock");
        System.out.print(">> ");
        int index = getUserInput();

        if (index == 0) {
            System.out.println("Cancelled");
            return;
        }

        System.out.println("Enter quantity");
        int quantity = getUserInput();

        if (quantity < 0 || quantity > capacity) {
            System.out.println("Exceeds capacity");
            return;
        }

        Item pickedItem = itemSlots.get(index - 1);
        pickedItem.setQuantity(pickedItem.getQuantity() + quantity);
        System.out.println("Restocked successfully");
    }

    protected void setItemPrice() {
        displayItems();
        System.out.println("[0] - Exit ");
        System.out.println("Enter item to change price");
        System.out.print(">> ");
        int index = getUserInput();

        System.out.println("Enter new price");
        double price = getUserInput();
        Item pickedItem = itemSlots.get(index-1);
        pickedItem.setPrice(price);
        System.out.println("Price changed successfully");
    }

    protected void collectMoney() {
        double collect;
        System.out.println("Total Earnings: " + totalSales);
        System.out.println("Enter value you want to collect.");
        System.out.print(">> ");
        collect = getUserInput();
        System.out.println("Collecting Money...");

        if (!(collect > 0 && collect <= totalSales)) {
            System.out.println("Not enough value, try again later..\n");
            return;
        }

        double i = collect;
        for (int denomination : denominations) {
            while (i >= denomination) {
                System.out.println(denomination);
                i -= denomination;
            }
        }
        System.out.println("Successfully collected Php." + collect);
        totalSales -= collect;
    }

    protected void replenishChange() {
        double change;
        System.out.println("Enter value of change");
        System.out.print(">> ");
        change = getUserInput();
        System.out.println("Inserting money...");
        if(change > 0) {
            totalChange += change;
        }
        else {
            System.out.println("Something went wrong");
        }
        System.out.println("Total value of change: " + totalChange);
    }

    protected void printTransactionSummary() {
        double totalEarning = 0;
        System.out.println("TRANSACTIONS");
        System.out.println("Name\t\t\t\t\tQuantity\tPrice");
        for(Transaction transaction : transactions) {
            System.out.printf("%-30s \t%d\t%.2f\n", transaction.getName(), transaction.getQuantity(), transaction.getTotalPrice());
            totalEarning += transaction.getTotalPrice();
        }
        System.out.println("Total Earnings: " + totalEarning);
    }

    protected void displayItems() {
        System.out.println("ITEMS");
        System.out.println("Index\tName\t\t\t\t\t\t\tPrice\tCalorie\tQuantity");
        for (int i = 0; i < itemSlots.size(); i++) {
            Item item = itemSlots.get(i);
            System.out.printf("%-6d %-30s\t%.2f\t%.2f\t%d\n", (i + 1), item.getItemName(), item.getPrice(),
                    item.getCalories(),item.getQuantity());
        }
        System.out.println();
    }

    protected int getUserInput() {
        int input = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                input = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        scanner.nextLine(); // Clear the scanner buffer
        return input;
    }
}
