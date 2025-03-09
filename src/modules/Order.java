package modules;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> products;
    private String status;
    private double totalPrice;
    private static int orderCounter = 1;
    private int orderID;

    public Order(String status, double totalPrice, int orderID) {
        this.products = new ArrayList<>();
        this.status = "В обработке";
        this.totalPrice = 0.0;
        this.orderID = orderCounter++;
    }

    public void addProductO(Product product, int quantity) {
        if (product != null && quantity > 0 && product.getQuantity() >= quantity) {
            for(int i = 0; i < quantity; i++) {
                this.products.add(product);
            }
            this.totalPrice += product.getPrice() * quantity;
            product.decreaseQuantity(quantity);
        }
    }

    public void rmProductO(Product product) {
        if (this.products.remove(product)) {
            this.totalPrice -= product.getPrice();
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals("В обработке") || status.equals("Отправлен") || status.equals("Доставлен")){
            this.status = status;
        } else {
            System.out.println("Неверный статус заказа.");
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getOrderID() {
        return orderID;
    }

    public void placeOrder() {
        if (!products.isEmpty()) {
            this.status = "Отправлен";
            System.out.println("Заказ " + orderID + " был успешно отправлн.");
        } else {
            System.out.println("Невозможно отправить пустой заказ");
        }
    }

//    public void trackOrderStatus() {
//        System.out.println("Заказ " + orderID + " на данный момент " + status);
//    }

    @Override
    public String toString() {
        return "Order[" +
                "order=" + products +
                ", status='" + status + '\'' +
                ", orderNum=" + orderID +
                ']';
    }
}
