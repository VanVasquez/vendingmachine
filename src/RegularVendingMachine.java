import java.util.Scanner;

public class RegularVendingMachine extends VendingMachine{
    private int slot;
    private int capacity;
    private final Scanner scanner;
    public RegularVendingMachine() {
        super();
        scanner = new Scanner(System.in);
    }

    @Override
    public void createVendingMachine() {
        System.out.println("CREATE REGULAR VENDING MACHINE");
        System.out.println("Enter max slots");
        slot = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter max capacity");
        capacity = scanner.nextInt();
        scanner.nextLine();
    }

    @Override
    public void testVendingMachine() {
        System.out.println("TEST REGULAR VENDING MACHINE");
    }

    @Override
    public void restockItems() {
        System.out.println("RESTOCK ITEM PRICE IN REGULAR VENDING MACHINE");
    }

    @Override
    public void setItemPrice() {
        System.out.println("SET ITEM PRICE IN REGULAR VENDING MACHINE");
    }

    @Override
    public void collectMoney() {
        System.out.println("COLLECT MONEY IN REGULAR VENDING MACHINE");
    }

    @Override
    public void replenishChange() {
        System.out.println("REPLENISHING CHANGE IN REGULAR VENDING MACHINE");
    }

    @Override
    public void printTransactionSummary() {
        System.out.println("PRINT TRANSACTION IN REGULAR VENDING MACHINE");
    }
}
