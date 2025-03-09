package gui;

import modules.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OrdersPanel extends JPanel {
    private DefaultTableModel ordersModel;

    public OrdersPanel() {
        initializeUI();
        refreshOrders();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Таблица заказов
        ordersModel = new DefaultTableModel(new Object[]{"ID", "Статус", "Сумма"}, 0);
        JTable ordersTable = new JTable(ordersModel);
        add(new JScrollPane(ordersTable), BorderLayout.CENTER);

        // Панель управления
        JPanel controlPanel = new JPanel();
        JButton statusButton = new JButton("Изменить статус");
        JButton detailsButton = new JButton("Показать детали");

        controlPanel.add(statusButton);
        controlPanel.add(detailsButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Обработчики событий
        statusButton.addActionListener(e -> {
            int row = ordersTable.getSelectedRow();
            if (row >= 0) {
                Order order = DataManager.orders.get(row);
                String[] statuses = {"В обработке", "Отправлен", "Доставлен"};
                String newStatus = (String) JOptionPane.showInputDialog(
                        this,
                        "Выберите новый статус:",
                        "Изменение статуса",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        statuses,
                        order.getStatus()
                );
                if (newStatus != null) {
                    order.setStatus(newStatus);
                    refreshOrders();
                }
            }
        });

        detailsButton.addActionListener(e -> {
            int row = ordersTable.getSelectedRow();
            if (row >= 0) {
                Order order = DataManager.orders.get(row);
                StringBuilder details = new StringBuilder("Детали заказа #" + order.getOrderID() + "\n");
                order.getProducts().forEach(p ->
                        details.append(p.getName()).append(" - ").append(p.getPrice()).append("\n")
                );
                JOptionPane.showMessageDialog(this, details.toString());
            }
        });
    }

    private void refreshOrders() {
        ordersModel.setRowCount(0);
        DataManager.orders.forEach(o -> ordersModel.addRow(new Object[]{
                o.getOrderID(),
                o.getStatus(),
                o.getTotalPrice()
        }));
    }
}