package modules;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public static List<Product> products = new ArrayList<>();
    public static List<Customer> customers = new ArrayList<>();
    public static List<Category> categories = new ArrayList<>();
    public static List<Order> orders = new ArrayList<>();
    public static ShoppingCart shoppingCart = new ShoppingCart();

    // Инициализация тестовых данных
    static {
        products.add(new Product("Ноутбук", 999.99, "Игровой ноутбук", 10));
        products.add(new Product("Смартфон", 499.99, "Флагманский телефон", 20));

        Category electronics = new Category("Электроника");
        electronics.addProduct(products.get(0));
        electronics.addProduct(products.get(1));
        categories.add(electronics);

        customers.add(new Customer("Иван Иванов", "ivan@mail.com", "+79151234567"));
    }
}