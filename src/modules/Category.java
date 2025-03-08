package modules;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String categoryName;
    private List<Product> products;

    public Category(String name) {
        this.categoryName = name;
        this.products = new ArrayList<>();
    }

    public String getName() {
        return categoryName;
    }

    public void setName(String newCateName) {
        this.categoryName = newCateName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void listProducts() {
        System.out.println("Category: " + categoryName);
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + categoryName + '\'' +
                ", products=" + products +
                '}';
    }
}
