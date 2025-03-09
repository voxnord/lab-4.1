package modules;

public class Product {
    private String name;
    private double price;
    private String description;
    private int quantity;

    public Product(String name, double price, String description, int quantity) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        }
    }

    public void increaseQuantity() {
        if (quantity > 0) {
            this.quantity += 1;
        }
    }
    public boolean decreaseQuantity(int amount) {
        if (amount > 0 && amount <= this.quantity) {
            this.quantity -= amount;
            return true;
        }
        return false;
    }

    public void updateProductInfo(double newPrice, int newQuantity) {
        if (newPrice >= 0) {
            this.price = newPrice;
        }
        if (newQuantity >= 0) {
            this.quantity = newQuantity;
        }
    }

    @Override
    public String toString() {
        return "Product information [" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ']';
    }
}