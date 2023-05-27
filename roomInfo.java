/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospms;

/**
 *
 * @author AIYZA_K
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class roomInfo extends JFrame implements ActionListener {
    private JTextField roomNoTextField;
    private JTextField roomTypeTextField;
    private JTextField roomChargesTextField;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton newButton;
    private JTable table;
    private DefaultTableModel tableModel;

    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement selectAllStatement;

    public roomInfo() {
        connectToDatabase(); // Connect to the database

        setTitle("Room Services");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Room No:"));
        roomNoTextField = new JTextField();
        formPanel.add(roomNoTextField);

        formPanel.add(new JLabel("Room Type:"));
        roomTypeTextField = new JTextField();
        formPanel.add(roomTypeTextField);

        formPanel.add(new JLabel("Room Charges:"));
        roomChargesTextField = new JTextField();
        formPanel.add(roomChargesTextField);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        formPanel.add(saveButton);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        formPanel.add(deleteButton);

        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        formPanel.add(updateButton);

        newButton = new JButton("New");
        newButton.addActionListener(this);
        formPanel.add(newButton);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Room No");
        tableModel.addColumn("Room Type");
        tableModel.addColumn("Room Charges");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        fetchAllData(); // Fetch data from the database and populate the table
    }

    private void connectToDatabase() {
        try {
            // Replace the connection URL, username, and password with your own
            String url = "jdbc:mysql://localhost:3306/your_database";
            String username = "your_username";
            String password = "your_password";

            connection = DriverManager.getConnection(url, username, password);

            // Prepare SQL statements
            insertStatement = connection.prepareStatement("INSERT INTO rooms (room_no, room_type, room_charges) VALUES (?, ?, ?)");
            deleteStatement = connection.prepareStatement("DELETE FROM rooms WHERE room_no = ?");
            updateStatement = connection.prepareStatement("UPDATE rooms SET room_type = ?, room_charges = ? WHERE room_no = ?");
            selectAllStatement = connection.prepareStatement("SELECT * FROM rooms");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveRoom();
        } else if (e.getSource() == deleteButton) {
            deleteRoom();
        } else if (e.getSource() == updateButton) {
            updateRoom();
        } else if (e.getSource() == newButton) {
            clearFields();
        }
    }

    private void saveRoom() {
        String roomNo = roomNoTextField.getText();
        String roomType = roomTypeTextField.getText();
        String roomCharges = roomChargesTextField.getText();

        try {
            insertStatement.setString(1, roomNo);
            insertStatement.setString(2, roomType);
            insertStatement.setString(3, roomCharges);
            insertStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Room saved successfully!");
            clearFields();
            fetchAllData(); // Update the table with the latest data
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to save the room. Please try again.");
        }
    }

    private void deleteRoom() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table.");
            return;
        }

        String roomNo = table.getValueAt(selectedRow, 0).toString();

        try {
            deleteStatement.setString(1, roomNo);
            deleteStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Room deleted successfully!");
            clearFields();
            fetchAllData(); // Update the table with the latest data
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to delete the room. Please try again.");
        }
    }

    private void updateRoom() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table.");
            return;
        }

        String roomNo = table.getValueAt(selectedRow, 0).toString();
        String roomType = roomTypeTextField.getText();
        String roomCharges = roomChargesTextField.getText();

        try {
            updateStatement.setString(1, roomType);
            updateStatement.setString(2, roomCharges);
            updateStatement.setString(3, roomNo);
            updateStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Room updated successfully!");
            clearFields();
            fetchAllData(); // Update the table with the latest data
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to update the room. Please try again.");
        }
    }

    private void fetchAllData() {
        tableModel.setRowCount(0); // Clear the table

        try {
            ResultSet resultSet = selectAllStatement.executeQuery();

            while (resultSet.next()) {
                String roomNo = resultSet.getString("room_no");
                String roomType = resultSet.getString("room_type");
                String roomCharges = resultSet.getString("room_charges");

                tableModel.addRow(new Object[]{roomNo, roomType, roomCharges});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        roomNoTextField.setText("");
        roomTypeTextField.setText("");
        roomChargesTextField.setText("");
        table.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(roomInfo::new);
    }
}
