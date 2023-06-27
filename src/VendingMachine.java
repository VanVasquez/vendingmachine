import java.util.ArrayList;
import java.util.List;

abstract class VendingMachine {
    protected List<Item> itemSlots;
    protected double totalSales;
    protected double totalChange;
    protected String name;

    public VendingMachine() {
        itemSlots = new ArrayList<>();
        totalChange = 0;
        totalSales = 0;
    }

    public abstract void createVendingMachine();
    public abstract void testVendingMachine();
    public abstract void restockItems();
    public abstract void setItemPrice();
    public abstract void collectMoney();
    public abstract void replenishChange();
    public abstract void printTransactionSummary();
}
