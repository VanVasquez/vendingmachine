import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegularVendingMachine extends VendingMachine{
    private int slot;
    private int capacity;
    private final Scanner scanner;
    private int totalMoney;
    StoredItems storedItems = new StoredItems();
    public RegularVendingMachine() {
        super();
        scanner = new Scanner(System.in);
        storedItems.readXMLFile("items.xml");
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
        System.out.println("[1] - Vending Machine Features");
        System.out.println("[2] - Maintenance Features");
        System.out.print(">> ");
        option = scanner.nextInt();
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
                System.out.println("Choose an item.\tCurrent Balance: " + totalMoney);
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
                choice = scanner.nextInt();
                if(choice == 1) {
                    restockItems();
                } else if (choice == 2) {
                    setItemPrice();
                }else {
                    System.out.println("Invalid input");
                }
            }
            default -> {
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
        pickedItem = itemSlots.get(index);
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
            System.out.println("Enter item to ch" +
                    "1ange price");
            System.out.print(">> ");
            int index = scanner.nextInt();
            System.out.println("Enter new price");
            float price = scanner.nextInt();
            pickedItem = itemSlots.get(index);
            pickedItem.setPrice(price);
            System.out.println("Price changed successfully");
        }
    }

    @Override
    public void collectMoney() {
        int money;
        while (true) {
            System.out.println();
            System.out.println("Insert money [20 / 50 / 100 / 200 / 500 / 1000]");
            System.out.print(">> ");
            money = scanner.nextInt();

            if (money == 0 || money == 20 || money == 50 || money == 100 || money == 200 || money == 500 || money == 1000) {
                totalMoney += money;
            } else {
                System.out.println("Invalid money value! Please insert a valid amount.");
            }
            if(money == 0) {
                break;
            }

            System.out.println("Enter 0 if done inserting...");
            System.out.println("Current Balance: " + totalMoney);
            System.out.println("Type [0] if finish inserting");
        }
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
