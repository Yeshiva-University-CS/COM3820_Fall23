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

/**
 * Represents a frame for displaying stock prices in a table format.
 */
public class PriceTickerFrame {
    private JFrame frame;
    private DefaultTableModel model;
    private Runnable onCloseCallback;

    /**
     * Constructs a new PriceTickerFrame with the specified title, width, and height.
     *
     * @param title  the title of the frame
     * @param width  the width of the frame
     * @param height the height of the frame
     */
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

        // Create the Close button and hook up to callback
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener((e) -> {
            if (onCloseCallback != null)
                onCloseCallback.run();
            frame.dispose();
        });

        // Create a JPanel with GridBagLayout to center the button
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.add(closeButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        model.setRowCount(0);
        resizeColumnWidth(table);
    }

    /**
     * Sets the visibility of the frame.
     *
     * @param visible true to make the frame visible, false to hide it
     */
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    /**
     * Adds a new stock to the table and returns the index of the newly added row.
     * Note: This method does not check for duplicate stock names.
     *
     * @param symbol    the stock symbol
     * @param name      the stock name
     * @param prevClose the previous closing price of the stock
     * @param price     the current price of the stock
     * @return the index of the newly added row
     */
    public int addStock(String symobol, String name, double prevClose, double price) {
        model.addRow(new Object[] { symobol, name, prevClose, price, price - prevClose });
        return model.getRowCount() - 1;
    }

    /**
     * Updates the price and intraday change columns for a specific stock in the table.
     *
     * @param stockRowId the row index of the stock to update
     * @param price      the new price of the stock
     * @param change     the new intraday change value
     */
    public void updateStockPrice(int stockRowId, double price, double change) {
        var priceStr = String.format("%.2f", price);
        var changeStr = String.format("%.2f", change);

        model.setValueAt(priceStr, stockRowId, 3);
        model.setValueAt(changeStr, stockRowId, 4);
    }

    /**
     * Sets a callback to be executed when the close button is clicked.
     *
     * @param callback the callback to be executed
     */
    public void setOnCloseCallback(Runnable callback) {
        this.onCloseCallback = callback;
    }

    /**
     * Private method to adjusts the width of each column in the table based on its content.
     * Ensures columns are wide enough to fit their data.
     *
     * @param table the JTable whose columns need resizing
     */
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
