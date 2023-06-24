import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        VendingMachine vm = null;

        while (true) {
            System.out.println("+-------------------------------------------------+");
            System.out.println("| MENU                                            |");
            System.out.println("| [1] Create Vending Machine                      |");
            System.out.println("| [2] Test Vending Machine                        |");
            System.out.println("| [0] EXIT                                        |");
            System.out.println("+-------------------------------------------------+");
            System.out.print(">> ");

            int option = scn.nextInt();
            scn.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.println("+-------------------------------------------------+");
                    System.out.println("| Choose Vending Machine type:                    |");
                    System.out.println("| [1] Regular                                     |");
                    System.out.println("| [2] Special                                     |");
                    System.out.println("+-------------------------------------------------+");
                    System.out.print(">> ");
                    int type = scn.nextInt();
                    scn.nextLine();

                    if(type == 1) {
                        vm = new RegularVendingMachine();
                        vm.createVendingMachine();
                    } else if (type == 2) {
                        vm = new SpecialVendingMachine();
                        vm.createVendingMachine();
                    }
                    else System.out.println("Invalid option");
                }
                case 2 -> {
                    if(vm != null) {
                        vm.testVendingMachine();
                    }
                    else {
                        System.out.println("No Vending Machine has been created yet.");
                    }
                }
                case 0 -> {
                    System.out.println("Exiting program...");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid option");
                }
            }
        }

    }
}