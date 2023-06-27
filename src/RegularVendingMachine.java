import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegularVendingMachine extends VendingMachine{
    private int slot;
    private int capacity;
    private final Scanner scanner;
    StoredItems storedItems = new StoredItems();

    public RegularVendingMachine() {
        super();
        scanner = new Scanner(System.in);
        storedItems.readXMLFile("items.xml");
        transactions = new ArrayList<>();
    }

    @Override
    public void createVendingMachine() {
        List<Item> itemList = storedItems.getItemsList();
        System.out.println("CREATE REGULAR VENDING MACHINE");
        System.out.println("Enter Name for your vending machine");
        System.out.print(">> ");
        name = scanner.nextLine();
        System.out.println("Enter max slots");
        System.out.print(">> ");
        do {
            slot = scanner.nextInt();
            scanner.nextLine();
            if(slot < 8 || slot > 10) {
                System.out.println("Invalid input, please enter a value between 8 and 10");
            }
            System.out.print(">> ");
        } while (slot < 8 || slot > 10);
        System.out.println();
        System.out.println("Enter max capacity");
        System.out.print(">> ");
        do {
            capacity = scanner.nextInt();
            scanner.nextLine();
            if(capacity < 10 || capacity > 20) {
                System.out.println("Invalid input, please enter a value between 10 and 20");
            }
            System.out.print(">> ");
        } while (capacity < 10 || capacity > 20);
        System.out.println("Choose products to sell: ");
        storedItems.displayItems();
        for (int i = 0; i < slot; i++) {
            int itemIndex;
            System.out.print(">> ");
            itemIndex = scanner.nextInt();

            if (itemIndex >= 0 && itemIndex < itemList.size()) {
                Item item = itemList.get(itemIndex);
                if (itemSlots.contains(item)) {
                    System.out.println("Invalid selection, Item already exist");
                    i--;
                } else {
                    itemSlots.add(item);
                }
            } else {
                System.out.println("Invalid selection, Please choose a valid item index");
                i--;
            }
        }
    }

    @Override
    public void testVendingMachine() {
        int option;
        while (true) {
            System.out.println("[1] - Vending Machine Features");
            System.out.println("[2] - Maintenance Features");
            System.out.print(">> ");
            option = scanner.nextInt();
            if (option == 0) break;
            switch (option) {
                case 1 -> {
                    int choice;
                    Item pickedItem;
                    System.out.println("Name " + name);
                    collectMoney();
                    System.out.println("Products for sale: ");
                    System.out.println("[Index]\tItem\t\t\t\t\tPrice\tCalorie\tQuantity");
                    for (int i = 0; i < itemSlots.size(); i++) {
                        Item item = itemSlots.get(i);
                        System.out.printf("[%d]:\t%-30s %.2f\t%d\t%d\n", i + 1, item.getItemName(), item.getPrice(), item.getCalories(), item.getQuantity());
                    }
                    System.out.println("Choose an item.");
                    System.out.print(">> ");
                    choice = scanner.nextInt();
                    if (choice == 0) {
                        System.out.println("Transaction Cancelled");
                    } else {
                        pickedItem = itemSlots.get(choice - 1);
                        if (pickedItem.getQuantity() <= 0) {
                            System.out.println(pickedItem.getItemName() + " is out of stock");
                        } else {
                            System.out.println("Name " + pickedItem.getItemName());
                        }
                    }
                }
                case 2 -> {
                    int choice;
                    System.out.println("Maintenance Features.");
                    System.out.println("[1] - Restock Items");
                    System.out.println("[2] - Price Items");
                    System.out.println("[3] - Collect Money");
                    System.out.println("[4] - Replenish Change");
                    System.out.println("[5] - Print Transactions");
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1 -> {
                            restockItems();
                        }
                        case 2 -> {
                            setItemPrice();
                        }
                        case 3 -> {
                            collectMoney();
                        }
                        case 4 -> {
                            replenishChange();
                        }
                        case 5 -> {
                            printTransactionSummary();
                        }
                        default -> {
                            System.out.println("Invalid selection");
                        }
                    }
                }
                default -> {
                    System.out.println("Invalid selection");
                }
            }
        }
    }

    @Override
    public void restockItems() {
        Item pickedItem;
        System.out.println("[Index]\tItem\t\t\t\t\tPrice\tCalorie\tQuantity");
        for (int i = 0; i < itemSlots.size(); i++) {
            Item item = itemSlots.get(i);
            System.out.printf("[%d]:\t%-30s\t%d\n", i+1, item.getItemName(), item.getQuantity());
        }
        System.out.println("Enter item to restock");
        System.out.print(">> ");
        int index = scanner.nextInt();
        System.out.println("Enter quantity");
        int quantity = scanner.nextInt();
        pickedItem = itemSlots.get(index-1);
        pickedItem.setQuantity(pickedItem.getQuantity() + quantity);
        System.out.println("Restocked successfully");
    }

    @Override
    public void setItemPrice() {
        Item pickedItem;
        System.out.println("[Index]\tItem\t\t\t\t\tPrice\tCalorie\tQuantity");
        for (int i = 0; i < itemSlots.size(); i++) {
            Item item = itemSlots.get(i);
            System.out.printf("[%d]:\t%-30s %.2f\n", i+1, item.getItemName(), item.getPrice());
        }
        System.out.println("Enter item to change price");
        System.out.print(">> ");
        int index = scanner.nextInt();
        System.out.println("Enter new price");
        float price = scanner.nextInt();
        pickedItem = itemSlots.get(index);
        pickedItem.setPrice(price);
        System.out.println("Price changed successfully");
    }

    @Override
    public void collectMoney() {
        double collect;
        System.out.println("Enter value you want to collect.");
        System.out.print(">> ");
        collect = scanner.nextDouble();
        System.out.println("Collecting Money...");
        if(collect > 0) {
            if(collect <= totalChange) {
                System.out.println("Successfully collected Php." + totalSales);
                totalSales = 0;
            } else {
                System.out.println("Not enough value, try again later..\n");
            }
        } else {
            System.out.println("Something went wrong");
        }
    }

    @Override
    public void replenishChange() {
        double change;
        System.out.println("Enter value of change");
        System.out.print(">> ");
        change = scanner.nextDouble();
        System.out.println("Inserting money...");
        if(change > 0) {
            totalSales += change;
        }
        else {
            System.out.println("Something went wrong");
        }
        System.out.println("Total value of change: " + totalSales);
    }

    @Override
    public void printTransactionSummary() {
        double totalEarning = 0;
        System.out.println("TRANSACTIONS");
        System.out.println("Name\t\t\t\t\tQuantity\tPrice");
        for(Transaction transaction : transactions) {
            System.out.printf("%-30s \t%d\t%.2f\n", transaction.getName(), transaction.getQuantity(), transaction.getTotalPrice());
            totalEarning += transaction.getTotalPrice();
        }
        System.out.println("Total Earnings: " + totalEarning);
    }
}
