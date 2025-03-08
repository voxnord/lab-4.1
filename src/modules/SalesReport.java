package modules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesReport {
    public static void generateProductSalesReport(List<Order> orders) {
        Map<Product, Integer> productSales = new HashMap<>();

        for (Order order : orders) {
            for (Product product : order.getProducts()) {
                productSales.put(product, productSales.getOrDefault(product, 0) + 1);
            }
        }

        System.out.println("Product Sales Report:");
        for (Map.Entry<Product, Integer> entry : productSales.entrySet()) {
            System.out.println(entry.getKey().getName() + " - Sold: " + entry.getValue());
        }
    }

    public static void generateCustomerSalesReport(List<Customer> customers) {
        System.out.println("Customer Sales Report:");
        for (Customer customer : customers) {
            System.out.println(customer.getName() + " - Orders Placed: " + customer.getOrder().size());
        }
    }
}
