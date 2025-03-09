package gui;

import modules.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShoppingCartPanel extends JPanel {
    private DefaultTableModel cartModel;

    public ShoppingCartPanel() {
        initializeUI();
        refreshCart();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Таблица корзины
        cartModel = new DefaultTableModel(new Object[]{"Товар", "Цена", "Количество"}, 0);
        JTable cartTable = new JTable(cartModel);
        add(new JScrollPane(cartTable), BorderLayout.CENTER);

        // Панель управления
        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("Добавить товар");
        JButton removeButton = new JButton("Удалить товар");
        JButton checkoutButton = new JButton("Оформить заказ");

        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        controlPanel.add(checkoutButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Обработчики событий
        addButton.addActionListener(e -> new AddToCartDialog());
        removeButton.addActionListener(e -> {
            int row = cartTable.getSelectedRow();
            if (row >= 0) {
                DataManager.shoppingCart.rmProductSC(
                        (Product) cartModel.getValueAt(row, 0)
                );
                refreshCart();
            }
        });

        checkoutButton.addActionListener(e -> {
            if (!DataManager.shoppingCart.getCartItem().isEmpty()) {
                Order newOrder = new Order("В обработке", 0, 0);
                DataManager.shoppingCart.getCartItem().forEach((product, quantity) ->
                        newOrder.addProductO(product, quantity)
                );
                DataManager.orders.add(newOrder);
                DataManager.shoppingCart.clearCart();
                refreshCart();
                JOptionPane.showMessageDialog(this, "Заказ успешно оформлен!");
            }
        });
    }

    private void refreshCart() {
        cartModel.setRowCount(0);
        DataManager.shoppingCart.getCartItem().forEach((product, quantity) ->
                cartModel.addRow(new Object[]{product, product.getPrice(), quantity})
        );
    }

    class AddToCartDialog extends JDialog {
        AddToCartDialog() {
            setTitle("Добавление товара в корзину");
            setLayout(new BorderLayout());

            DefaultTableModel productsModel = new DefaultTableModel(new Object[]{"Название", "Цена", "Доступно"}, 0);
            JTable productsTable = new JTable(productsModel);
            DataManager.products.forEach(p ->
                    productsModel.addRow(new Object[]{p.getName(), p.getPrice(), p.getQuantity()})
            );

            JButton addButton = new JButton("Добавить");
            addButton.addActionListener(e -> {
                int row = productsTable.getSelectedRow();
                if (row >= 0) {
                    Product product = DataManager.products.get(row);
                    String quantity = JOptionPane.showInputDialog("Введите количество:");
                    try {
                        int qty = Integer.parseInt(quantity);
                        if (qty > 0 && qty <= product.getQuantity()) {
                            DataManager.shoppingCart.addProductSC(product, qty);
                            refreshCart();
                            dispose();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Неверное количество!");
                    }
                }
            });

            add(new JScrollPane(productsTable), BorderLayout.CENTER);
            add(addButton, BorderLayout.SOUTH);
            pack();
            setLocationRelativeTo(ShoppingCartPanel.this);
            setVisible(true);
        }
    }
}