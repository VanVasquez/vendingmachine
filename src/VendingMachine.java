import java.util.List;

public class VendingMachine {
    private String name;
    private int capacity;
    private List<Items> inventory;
    private double totalSales;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void stockItems(List<Items> items) {

    }

    public void setItemPrice(String itemName, double price) {

    }

    public void acceptPayment(double amount) {

    }

    public void dispenseItem(String itemName) {

    }

    public void produceChange(double change) {

    }

    public void printTransactionSummary() {

    }
}
