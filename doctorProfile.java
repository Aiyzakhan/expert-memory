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

public class doctorProfile extends JFrame implements ActionListener {
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField fatherNameTextField;
    private JTextField emailTextField;
    private JTextField contactNumberTextField;
    private JTextField addressTextField;
    private JTextField qualificationTextField;
    private JTextField genderTextField;
    private JTextField dateOfJoiningTextField;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton newButton;

    private DoctorDAO doctorDAO;

    public doctorProfile() {
        doctorDAO = new DoctorDAO(); // Initialize the DoctorDAO

        setTitle("Doctor Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(11, 2, 10, 10));

        add(new JLabel("Doctor ID:"));
        idTextField = new JTextField();
        add(idTextField);

        add(new JLabel("Name:"));
        nameTextField = new JTextField();
        add(nameTextField);

        add(new JLabel("Father's Name:"));
        fatherNameTextField = new JTextField();
        add(fatherNameTextField);

        add(new JLabel("Email:"));
        emailTextField = new JTextField();
        add(emailTextField);

        add(new JLabel("Contact Number:"));
        contactNumberTextField = new JTextField();
        add(contactNumberTextField);

        add(new JLabel("Address:"));
        addressTextField = new JTextField();
        add(addressTextField);

        add(new JLabel("Qualification:"));
        qualificationTextField = new JTextField();
        add(qualificationTextField);

        add(new JLabel("Gender:"));
        genderTextField = new JTextField();
        add(genderTextField);

        add(new JLabel("Date of Joining:"));
        dateOfJoiningTextField = new JTextField();
        add(dateOfJoiningTextField);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        add(saveButton);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        add(deleteButton);

        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        add(updateButton);

        newButton = new JButton("New");
        newButton.addActionListener(this);
        add(newButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveProfile();
        } else if (e.getSource() == deleteButton) {
            deleteProfile();
        } else if (e.getSource() == updateButton) {
            updateProfile();
        } else if (e.getSource() == newButton) {
            createNewProfile();
        }
    }

    private void saveProfile() {
        String doctorID = idTextField.getText();
        String doctorName = nameTextField.getText();
        String fatherName = fatherNameTextField.getText();
        String email = emailTextField.getText();
        String contactNumber = contactNumberTextField.getText();
        String address = addressTextField.getText();
        String qualification = qualificationTextField.getText();
        String gender = genderTextField.getText();
        String dateOfJoining = dateOfJoiningTextField.getText();

        // Create a new Doctor object
        Doctor doctor = new Doctor(doctorID, doctorName, fatherName, email, contactNumber, address, qualification, gender, dateOfJoining);

        // Save the doctor profile to the database
        boolean success = doctorDAO.saveDoctor(doctor);

        if (success) {
            JOptionPane.showMessageDialog(null, "Profile saved successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to save the profile. Please try again.");
        }
    }

    private void deleteProfile() {
        String doctorID = idTextField.getText();

        // Delete the doctor profile from the database
        boolean success = doctorDAO.deleteDoctor(doctorID);

        if (success) {
            JOptionPane.showMessageDialog(null, "Profile deleted successfully!");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to delete the profile. Please try again.");
        }
    }

    private void updateProfile() {
        String doctorID = idTextField.getText();
        String doctorName = nameTextField.getText();
        String fatherName = fatherNameTextField.getText();
        String email = emailTextField.getText();
        String contactNumber = contactNumberTextField.getText();
        String address = addressTextField.getText();
        String qualification = qualificationTextField.getText();
        String gender = genderTextField.getText();
        String dateOfJoining = dateOfJoiningTextField.getText();

        // Create a new Doctor object
        Doctor doctor = new Doctor(doctorID, doctorName, fatherName, email, contactNumber, address, qualification, gender, dateOfJoining);

        // Update the doctor profile in the database
        boolean success = doctorDAO.updateDoctor(doctor);

        if (success) {
            JOptionPane.showMessageDialog(null, "Profile updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update the profile. Please try again.");
        }
    }

    private void createNewProfile() {
        clearFields();
    }

    private void clearFields() {
        idTextField.setText("");
        nameTextField.setText("");
        fatherNameTextField.setText("");
        emailTextField.setText("");
        contactNumberTextField.setText("");
        addressTextField.setText("");
        qualificationTextField.setText("");
        genderTextField.setText("");
        dateOfJoiningTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(doctorProfile::new);
    }
}

class Doctor {
    private String doctorID;
    private String doctorName;
    private String fatherName;
    private String email;
    private String contactNumber;
    private String address;
    private String qualification;
    private String gender;
    private String dateOfJoining;

    public Doctor(String doctorID, String doctorName, String fatherName, String email, String contactNumber, String address, String qualification, String gender, String dateOfJoining) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.fatherName = fatherName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.address = address;
        this.qualification = qualification;
        this.gender = gender;
        this.dateOfJoining = dateOfJoining;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getQualification() {
        return qualification;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }
}

class DoctorDAO {
    public boolean saveDoctor(Doctor doctor) {
        // Implement the logic to save the doctor profile to the database
        // Return true if successful, false otherwise
        return true;
    }

    public boolean deleteDoctor(String doctorID) {
        // Implement the logic to delete the doctor profile from the database
        // Return true if successful, false otherwise
        return true;
    }

    public boolean updateDoctor(Doctor doctor) {
        // Implement the logic to update the doctor profile in the database
        // Return true if successful, false otherwise
        return true;
    }

public static void main(String[] args) {
        SwingUtilities.invokeLater(doctorProfile::new);
    }
}