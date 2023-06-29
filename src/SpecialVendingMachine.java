import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SpecialVendingMachine extends VendingMachine{
    private int slot;   //Specifies the slot of vending machine
    private int capacity;   //Specifies the capacity of each slot
    private final Scanner scanner;
    StoredItems storedItems = new StoredItems();
    StoredItems flavors = new StoredItems();
    Item customizableItem;
    public SpecialVendingMachine() {
        super();
        scanner = new Scanner(System.in);
        storedItems.readXMLFile("items.xml");
        flavors.readXMLFile("flavors.xml");
        transactions = new ArrayList<>();
    }
    @Override
    public void createVendingMachine() {
        List<Item> itemList = storedItems.getItemsList();
        System.out.println("+------------------------------------------+");
        System.out.println("| CREATE SPECIAL VENDING MACHINE           |");
        System.out.println("+------------------------------------------+");
        System.out.println();
        System.out.println("Enter Name for your vending machine");
        System.out.print(">> ");
        name = scanner.nextLine();

        System.out.println("Choose toppings.");
        System.out.println("Enter max slots");
        System.out.print(">> ");
        do {
            try {
                slot = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
                continue;
            }
            if(slot < 8 || slot > 10) {
                System.out.println("Invalid input, please enter a value between 8 and 10");
            }
            System.out.print(">> ");
        } while (slot < 8 || slot > 10);
        System.out.println();

        System.out.println("Enter max capacity");
        System.out.print(">> ");
        do {
            try {
                capacity = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
                continue;
            }
            if(capacity < 10 || capacity > 20) {
                System.out.println("Invalid input, please enter a value between 10 and 20");
            }
            System.out.print(">> ");
        } while (capacity < 10 || capacity > 20);

        System.out.println();
        System.out.println("Choose products to sell: ");
        storedItems.displayItems();

        for (int i = 0; i < slot; i++) {
            int itemIndex;
            System.out.print(">> ");
            try {
                itemIndex = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
                i--;
                continue;
            }

            if (!(itemIndex >= 0 && itemIndex < itemList.size())) {
                System.out.println("Invalid selection, Please choose a valid item index");
                i--;
                continue;
            }
            Item item = itemList.get(itemIndex);
            if (itemSlots.contains(item)) {
                System.out.println("Invalid selection, Item already exist");
                i--;
                continue;
            }
            itemSlots.add(item);
        }
    }

    public void testVendingMachine() {
        List<Item> myFlavor = flavors.getItemsList();
        int option;
        while (true) {
            System.out.println("+------------------------------------------+");
            System.out.println("| TEST VENDING MACHINE                     |");
            System.out.println("| [1] - Vending Machine Features           |");
            System.out.println("| [2] - Maintenance Features               |");
            System.out.println("| [0] - EXIT                               |");
            System.out.println("+------------------------------------------+");
            System.out.print(">> ");

            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
                continue;
            }

            if (option == 0) break;
            switch (option) {
                //Testing Vending Machine
                case 1 -> {
                    int[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
                    int choice, quantity;
                    Item pickedItem;
                    System.out.println();
                    while (true) {
                        //Display's Vending Machine Name
                        System.out.println(name + " Vending Machine\n");
                        System.out.println("Choose a flavor: ");
                        System.out.println("[Index]\tFlavor\t\t\t\t\tPrice\tCalorie\tQuantity");
                        for (int i = 0; i < myFlavor.size(); i++) {
                            Item flavor = myFlavor.get(i);
                            System.out.printf(
                                    "[%d]:\t%-30s %.2f\t%.2f\t%d\n",
                                    i + 1,
                                    flavor.getItemName(),
                                    flavor.getPrice(),
                                    flavor.getCalories(),
                                    flavor.getQuantity()
                            );
                            System.out.println("[0] - Exit");
                            System.out.println("Choose an item.");
                            System.out.print(">> ");

                            //Check if choice is valid
                            try {
                                choice = scanner.nextInt();
                                scanner.nextLine();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                                scanner.nextLine();
                                continue;
                            }

                            if (choice == 0) {
                                System.out.println("Transaction Cancelled");
                                break;
                            }

                            if (choice < 0 || choice > slot) {
                                System.out.println("Invalid choice.");
                                continue;
                            }

                            pickedItem = itemSlots.get(choice - 1);

                            if (pickedItem.getQuantity() <= 0) {
                                System.out.println(pickedItem.getItemName() + " is out of stock");
                                continue;
                            }

                            System.out.println("Name " + pickedItem.getItemName());

                            while (true) {
                                System.out.print("Enter quantity: ");
                                try {
                                    quantity = scanner.nextInt();
                                    scanner.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer.");
                                    scanner.nextLine();
                                    continue;
                                }

                                if (quantity <= pickedItem.getQuantity()) {
                                    break;
                                }

                                System.out.println(pickedItem.getItemName() + " doesn't have enough stock...");
                                System.out.println("Type 0 to cancel");
                            }

                            if (!(quantity > 0 && quantity <= pickedItem.getQuantity())) {
                                System.out.println("Transaction cancelled");

                            }
                        }
                        //Display Products
                        System.out.println("Products for sale: ");
                        System.out.println("[Index]\tItem\t\t\t\t\tPrice\tCalorie\tQuantity");
                        for (int i = 0; i < itemSlots.size(); i++) {
                            Item item = itemSlots.get(i);
                            System.out.printf(
                                    "[%d]:\t%-30s %.2f\t%.2f\t%d\n",
                                    i + 1,
                                    item.getItemName(),
                                    item.getPrice(),
                                    item.getCalories(),
                                    item.getQuantity()
                            );
                        }
                        System.out.println("[0] - Exit");
                        System.out.println("Choose an item.");
                        System.out.print(">> ");

                        //Check if choice is valid
                        try {
                            choice = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            scanner.nextLine();
                            continue;
                        }

                        if (choice == 0) {
                            System.out.println("Transaction Cancelled");
                            break;
                        }

                        if (choice < 0 || choice > slot) {
                            System.out.println("Invalid choice.");
                            continue;
                        }

                        pickedItem = itemSlots.get(choice - 1);

                        if (pickedItem.getQuantity() <= 0) {
                            System.out.println(pickedItem.getItemName() + " is out of stock");
                            continue;
                        }

                        System.out.println("Name " + pickedItem.getItemName());

                        while (true) {
                            System.out.print("Enter quantity: ");
                            try {
                                quantity = scanner.nextInt();
                                scanner.nextLine();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                                scanner.nextLine();
                                continue;
                            }

                            if (quantity <= pickedItem.getQuantity()) {
                                break;
                            }

                            System.out.println(pickedItem.getItemName() + " doesn't have enough stock...");
                            System.out.println("Type 0 to cancel");
                        }

                        if (!(quantity > 0 && quantity <= pickedItem.getQuantity())) {
                            System.out.println("Transaction cancelled");

                        }

                        //COMPUTING
                        double amount, price, calories;

                        //CALCULATE TOTAL CALORIES AND PRICE
                        calories = pickedItem.getCalories() * quantity;
                        price = pickedItem.getPrice() * quantity;
                        System.out.println();
                        System.out.println("Total amount of calorie: " + calories);
                        System.out.println("Total amount: " + price);
                        System.out.println("Type 0 to cancel.");

                        //ENTER MONEY
                        while (true) {
                            System.out.println("Total balance: " + balance);
                            System.out.println("Insert money [20 / 50 / 100 / 200 / 500 / 1000]");
                            System.out.println("Enter 0 if done inserting...");
                            System.out.print(">> ");
                            amount = scanner.nextInt();
                            if (amount == 20 || amount == 50 || amount == 100 || amount == 200 || amount == 500 || amount == 1000) {
                                balance += amount;
                            } else if (amount == 0) {
                                break;
                            } else {
                                System.out.println("Invalid money value! Please insert a valid amount.");
                            }
                        }

                        if (balance < price) {
                            System.out.println("Insufficient Money. Transaction cancelled.");
                            continue;
                        }

                        System.out.println("Computing change");
                        balance -= price;
                        System.out.println("Total change: " + balance);
                        System.out.println("Vending Machine credits: " + totalChange);
                        //Transaction cancels if vending machine doesn't have enough change
                        if(totalChange < balance) {
                            System.out.println("Vending Machine doesn't have enough change...");
                            System.out.println("We're sorry for the inconvenience");
                            System.out.println("Cancelling transaction.");
                            System.out.println("Handling balance back...");
                            balance += price;
                            for (int denomination : denominations) {
                                while (balance >= denomination) {
                                    System.out.println(denomination);
                                    balance -= denomination;
                                }
                            }
                            break;
                        }
                        //If Vending machine has enough change.
                        totalChange -= balance;
                        totalSales += price;

                        System.out.println("Handling changes...");
                        for (int denomination : denominations) {
                            while (balance >= denomination) {
                                System.out.println(denomination);
                                balance -= denomination;
                            }
                        }

                        transactions.add(new Transaction(pickedItem.getItemName(), quantity, price));
                        pickedItem.setQuantity(pickedItem.getQuantity() - quantity);
                        System.out.println("Done...");
                        System.out.println("Transaction Complete.");
                    }
                }

                case 2 -> {
                    int choice;
                    while (true) {
                        System.out.println("Maintenance Features.");
                        System.out.println("[1] - Restock Items");
                        System.out.println("[2] - Price Items");
                        System.out.println("[3] - Collect Money");
                        System.out.println("[4] - Replenish Change");
                        System.out.println("[5] - Print Transactions");
                        System.out.println("[0] - Exit ");

                        try {
                            choice = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            scanner.nextLine();
                            continue;
                        }

                        if (choice == 0 ) break;
                        switch (choice) {
                            case 1 -> restockItems();
                            case 2 -> setItemPrice();
                            case 3 -> collectMoney();
                            case 4 -> replenishChange();
                            case 5 ->  printTransactionSummary();
                            default -> System.out.println("Invalid selection");
                        }
                    }
                }
                default -> System.out.println("Invalid selection");
            }
        }
    }
}
