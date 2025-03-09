package gui;

import modules.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoriesPanel extends JPanel {
    private DefaultTableModel categoriesModel;
    private JList<Product> productsList;

    public CategoriesPanel() {
        initializeUI();
        refreshCategories();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Таблица категорий
        categoriesModel = new DefaultTableModel(new Object[]{"Название категории"}, 0);
        JTable categoriesTable = new JTable(categoriesModel);
        JScrollPane categoriesScroll = new JScrollPane(categoriesTable);

        // Список товаров в категории
        productsList = new JList<>();
        JScrollPane productsScroll = new JScrollPane(productsList);

        // Разделение экрана
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, categoriesScroll, productsScroll);
        splitPane.setDividerLocation(300);
        add(splitPane, BorderLayout.CENTER);

        // Панель управления
        JPanel controlPanel = new JPanel();
        JButton addButton = new JButton("Добавить категорию");
        JButton removeButton = new JButton("Удалить категорию");
        JButton addProductButton = new JButton("Добавить товар");

        controlPanel.add(addButton);
        controlPanel.add(removeButton);
        controlPanel.add(addProductButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Обработчики событий
        categoriesTable.getSelectionModel().addListSelectionListener(e -> {
            int row = categoriesTable.getSelectedRow();
            if (row >= 0) {
                Category selected = DataManager.categories.get(row);
                DefaultListModel<Product> model = new DefaultListModel<>();
                selected.getProducts().forEach(model::addElement);
                productsList.setModel(model);
            }
        });

        addButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Введите название категории:");
            if (name != null && !name.isEmpty()) {
                DataManager.categories.add(new Category(name));
                refreshCategories();
            }
        });

        removeButton.addActionListener(e -> {
            int row = categoriesTable.getSelectedRow();
            if (row >= 0) {
                DataManager.categories.remove(row);
                refreshCategories();
            }
        });

        addProductButton.addActionListener(e -> {
            int row = categoriesTable.getSelectedRow();
            if (row >= 0) {
                new AddProductToCategoryDialog(DataManager.categories.get(row));
                refreshCategories();
            }
        });
    }

    private void refreshCategories() {
        categoriesModel.setRowCount(0);
        DataManager.categories.forEach(c -> categoriesModel.addRow(new Object[]{c.getName()}));
    }

    class AddProductToCategoryDialog extends JDialog {
        AddProductToCategoryDialog(Category category) {
            setTitle("Добавление товара в категорию");
            setLayout(new BorderLayout());

            DefaultTableModel productsModel = new DefaultTableModel(new Object[]{"Название", "Цена"}, 0);
            JTable productsTable = new JTable(productsModel);
            DataManager.products.forEach(p -> productsModel.addRow(new Object[]{p.getName(), p.getPrice()}));

            JButton addButton = new JButton("Добавить выбранные");
            addButton.addActionListener(e -> {
                int[] rows = productsTable.getSelectedRows();
                for (int row : rows) {
                    category.addProduct(DataManager.products.get(row));
                }
                dispose();
            });

            add(new JScrollPane(productsTable), BorderLayout.CENTER);
            add(addButton, BorderLayout.SOUTH);
            pack();
            setLocationRelativeTo(CategoriesPanel.this);
            setVisible(true);
        }
    }
}