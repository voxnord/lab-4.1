package modules;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> cart;

    private ShoppingCart() {
        this.cart = new HashMap<>();
    }

    public void addProductSC(Product product, int quantity) {
        if (product != null && quantity > 0 && product.getQuantity() >= quantity) {
            cart.put(product, cart.getOrDefault(product, 0) + quantity);
            // Как-то изменить/убрать библиотеку с хэш таблицей
            // Посмотреть, как реализовано в другом
        } else {
            System.out.println("Неверное количество или товар закончился");
        }
    }

    public void rmProductSC(Product product) {
        if (cart.containsKey(product)) {
            cart.remove(product);
        } else {
            System.out.println("Товар н найден в корзине");
        }
    }

    public void viewCart() {
        if (!cart.isEmpty()) {
            System.out.println("Корзина: ");
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                System.out.printf(entry.getKey().getName()   + " - количество: " + entry.getValue());
            }
        } else {
            System.out.println("Корзина пуста");
        }
    }

    public void clearCart() {
        cart.clear();
        System.out.println("Корзина была очищена");
    }

    public Map<Product, Integer> getCartItem() {
        return cart;
    }
}
