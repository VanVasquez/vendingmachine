import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        VendingMachine vm = null;

        //Display menu on a loop
        while (true) {

            System.out.println("+-------------------------------------------------+");
            System.out.println("| MENU                                            |");
            System.out.println("| [1] Create Vending Machine                      |");
            System.out.println("| [2] Test Vending Machine                        |");
            System.out.println("| [0] EXIT                                        |");
            System.out.println("+-------------------------------------------------+");
            System.out.print(">> ");

            int option;
            try {
                option = scn.nextInt();
                scn.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scn.nextLine();
                continue;
            }

            switch (option) {
                case 1 -> {
                    System.out.println("Creating...\n");
                    System.out.println("+-------------------------------------------------+");
                    System.out.println("| Choose Vending Machine type:                    |");
                    System.out.println("| [1] Regular                                     |");
                    System.out.println("| [2] Special                                     |");
                    System.out.println("+-------------------------------------------------+");
                    System.out.print(">> ");

                    int type;
                    try {
                        type = scn.nextInt();
                        scn.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        scn.nextLine();
                        continue;
                    }

                    if(type == 1) {
                        vm = new RegularVendingMachine();
                        vm.createVendingMachine();
                    }
                    else if (type == 2) {
                        vm = new SpecialVendingMachine();
                        vm.createVendingMachine();
                    }
                    else System.out.println("Invalid option");
                }
                case 2 -> {
                    //Check if Vending Machine has been initialized
                    if(vm == null) {
                        System.out.println("No Vending Machine has been created yet.");
                        continue;
                    }
                    vm.testVendingMachine();
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