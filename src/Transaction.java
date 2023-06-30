import java.util.List;

public class Transaction {
    private final Item item;
    private List<Item> flavor;
    private final double totalPrice;

    public Transaction(Item item, List<Item> flavor, double totalPrice) {
        this.item = item;
        this.flavor = flavor;
        this.totalPrice = totalPrice;
    }

    public Transaction(Item item, double totalPrice) {
        this.item = item;
        this.totalPrice = totalPrice;
    }
    public List<Item> getFlavor() {
        return flavor;
    }

    public Item getItem() {
        return item;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
