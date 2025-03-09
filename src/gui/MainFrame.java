package gui;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Order Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Товары", new ProductsPanel());
        tabbedPane.addTab("Категории", new CategoriesPanel());
        tabbedPane.addTab("Клиенты", new CustomersPanel());
        tabbedPane.addTab("Заказы", new OrdersPanel());
        tabbedPane.addTab("Корзина", new ShoppingCartPanel());
        tabbedPane.addTab("Отчеты", new ReportsPanel());

        add(tabbedPane);
    }
}