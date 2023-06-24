import java.sql.SQLOutput;

public class SpecialVendingMachine extends VendingMachine{
    @Override
    public void createVendingMachine() {
        System.out.println("CREATE SPECIAL VENDING MACHINE");
    }

    @Override
    public void testVendingMachine() {
        System.out.println("TEST SPECIAL VENDING MACHINE");
    }

    @Override
    public void restockItems() {
        System.out.println("RESTOCK ITEMS IN SPECIAL VENDING MACHINE");
    }

    @Override
    public void setItemPrice() {
        System.out.println("SET ITEMS IN SPECIAL VENDING MACHINE");
    }

    @Override
    public void collectMoney() {
        System.out.println("COLLECT MONEY IN SPECIAL VENDING MACHINE");
    }

    @Override
    public void replenishChange() {
        System.out.println("REPLENISH CHANGE IN SPECIAL VENDING MACHINE");
    }

    @Override
    public void printTransactionSummary() {
        System.out.println("PRINT TRANSACTION IN REGULAR VENDING MACHINE");
    }
}
