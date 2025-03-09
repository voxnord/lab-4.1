package gui;

import modules.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductsPanel extends JPanel {
    private DefaultTableModel tableModel;

    public ProductsPanel() {
        initializeUI();
        refreshTable();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Таблица товаров
        tableModel = new DefaultTableModel(new Object[]{"Название", "Цена", "Описание", "Количество"}, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Панель кнопок
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Добавить");
        JButton editButton = new JButton("Изменить");
        JButton deleteButton = new JButton("Удалить");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Обработчики событий
        addButton.addActionListener(e -> showProductDialog(null));
        editButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) showProductDialog(DataManager.products.get(row));
        });
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                DataManager.products.remove(row);
                refreshTable();
            }
        });
    }

    private void showProductDialog(Product product) {
        JDialog dialog = new JDialog();
        dialog.setLayout(new GridLayout(5, 2));

        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField descField = new JTextField();
        JTextField quantityField = new JTextField();

        if (product != null) {
            nameField.setText(product.getName());
            priceField.setText(String.valueOf(product.getPrice()));
            descField.setText(product.getDescription());
            quantityField.setText(String.valueOf(product.getQuantity()));
        }

        dialog.add(new JLabel("Название:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Цена:"));
        dialog.add(priceField);
        dialog.add(new JLabel("Описание:"));
        dialog.add(descField);
        dialog.add(new JLabel("Количество:"));
        dialog.add(quantityField);

        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(e -> {
            try {
                Product p = product != null ? product : new Product(
                        nameField.getText(),
                        Double.parseDouble(priceField.getText()),
                        descField.getText(),
                        Integer.parseInt(quantityField.getText())
                );
                if (product == null) DataManager.products.add(p);
                else p.updateProductInfo(
                        Double.parseDouble(priceField.getText()),
                        Integer.parseInt(quantityField.getText())
                );
                refreshTable();
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Ошибка ввода!");
            }
        });

        dialog.add(saveButton);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Product p : DataManager.products) {
            tableModel.addRow(new Object[]{
                    p.getName(),
                    p.getPrice(),
                    p.getDescription(),
                    p.getQuantity()
            });
        }
    }
}