package gui;

import modules.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomersPanel extends JPanel {
    private DefaultTableModel customersModel;

    public CustomersPanel() {
        initializeUI();
        refreshCustomers();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Таблица клиентов
        customersModel = new DefaultTableModel(new Object[]{"Имя", "Email", "Телефон"}, 0);
        JTable customersTable = new JTable(customersModel);
        add(new JScrollPane(customersTable), BorderLayout.CENTER);

        // Панель управления
        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("Добавить клиента");
        JButton removeButton = new JButton("Удалить клиента");

        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Обработчики событий
        addButton.addActionListener(e -> {
            JDialog dialog = new JDialog();
            dialog.setLayout(new GridLayout(4, 2));

            JTextField nameField = new JTextField();
            JTextField emailField = new JTextField();
            JTextField phoneField = new JTextField();

            dialog.add(new JLabel("Имя:"));
            dialog.add(nameField);
            dialog.add(new JLabel("Email:"));
            dialog.add(emailField);
            dialog.add(new JLabel("Телефон:"));
            dialog.add(phoneField);

            JButton saveButton = new JButton("Сохранить");
            saveButton.addActionListener(ev -> {
                Customer customer = new Customer(
                        nameField.getText(),
                        emailField.getText(),
                        phoneField.getText()
                );
                DataManager.customers.add(customer);
                refreshCustomers();
                dialog.dispose();
            });

            dialog.add(saveButton);
            dialog.pack();
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        });

        removeButton.addActionListener(e -> {
            int row = customersTable.getSelectedRow();
            if (row >= 0) {
                DataManager.customers.remove(row);
                refreshCustomers();
            }
        });
    }

    private void refreshCustomers() {
        customersModel.setRowCount(0);
        DataManager.customers.forEach(c -> customersModel.addRow(new Object[]{
                c.getName(),
                c.getEmail(),
                c.getPhoneNum()
        }));
    }
}