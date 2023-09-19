package edu.yu.parallel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class PriceTickerFrame {
    private JFrame frame;
    private DefaultTableModel model;
    private Runnable onCloseCallback;

    public PriceTickerFrame(String title, int width, int height) {
        this.frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);

        var columns = new String[] { "Symbol", "Name", "Prev Close", "Current Price", "Intraday Change" };

        this.model = new DefaultTableModel(columns, 0);
        var table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);

        // Create the Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClose();
            }
        });

        // Create a JPanel with GridBagLayout to center the button
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.add(closeButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        model.setRowCount(0);
        resizeColumnWidth(table);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    // Add a row for the given stock and return the index of the row
    // Note: does not check for duplicate stock names
    public int addStock(String symobol, String name, double prevClose, double price) {
        model.addRow(new Object[] { symobol, name, prevClose, price, price - prevClose });
        return model.getRowCount() - 1;
    }

    // Update the price and change columns for the given stock
    // with the new price and the new intraday change
    public void updateStockPrice(int stockRowId, double price, double change) {
        var priceStr = String.format("%.2f", price);
        var changeStr = String.format("%.2f", change);

        model.setValueAt(priceStr, stockRowId, 3);
        model.setValueAt(changeStr, stockRowId, 4);
    }

    // Set the onClose callback that will run
    // when the close button is clicked
    public void setOnCloseCallback(Runnable callback) {
        this.onCloseCallback = callback;
    }

    ///////////////////////////////
    // private methods
    /////////////////////////////////

    // Close the frame and run the onClose callback
    // that was supplied by the user
    private void onClose() {
        if (onCloseCallback != null)
            onCloseCallback.run();
        frame.dispose();
    }

    // Private helper function to resize the columns of the table
    // to fit the data
    private void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
   
}
