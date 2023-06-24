public class ItemSlot {
    private String itemName;
    private int quantity;
    private double price;
    private int calories;

    public ItemSlot(String itemName, int quantity, double price, int calories) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.calories = calories;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
