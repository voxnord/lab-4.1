package gui;

import modules.DataManager;
import modules.SalesReport;

import javax.swing.*;
import java.awt.*;

public class ReportsPanel extends JPanel {
    public ReportsPanel() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);

        JPanel buttonPanel = new JPanel();
        JButton productReportButton = new JButton("Отчет по товарам");
        JButton customerReportButton = new JButton("Отчет по клиентам");

        buttonPanel.add(productReportButton);
        buttonPanel.add(customerReportButton);

        productReportButton.addActionListener(e -> {
            reportArea.setText("");
            SalesReport.generateProductSalesReport(DataManager.orders);
        });

        customerReportButton.addActionListener(e -> {
            reportArea.setText("");
            SalesReport.generateCustomerSalesReport(DataManager.customers);
        });

        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(reportArea), BorderLayout.CENTER);
    }
}