/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospms;

/**
 *
 * @author AIYZA_K
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class doctorDB {
    private Connection connection;

    public doctorDB() {
        // Establish a database connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean saveDoctor(Doctor doctor) {
        String query = "INSERT INTO doctors (doctor_id, name, father_name, email, contact_number, address, qualification, gender, date_of_joining) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, doctor.getDoctorID());
            statement.setString(2, doctor.getName());
            statement.setString(3, doctor.getFatherName());
            statement.setString(4, doctor.getEmail());
            statement.setString(5, doctor.getContactNumber());
            statement.setString(6, doctor.getAddress());
            statement.setString(7, doctor.getQualification());
            statement.setString(8, doctor.getGender());
            statement.setString(9, doctor.getDateOfJoining());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDoctor(String doctorID) {
        String query = "DELETE FROM doctors WHERE doctor_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, doctorID);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDoctor(Doctor doctor) {
        String query = "UPDATE doctors SET name = ?, father_name = ?, email = ?, contact_number = ?, " +
                       "address = ?, qualification = ?, gender = ?, date_of_joining = ? WHERE doctor_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getFatherName());
            statement.setString(3, doctor.getEmail());
            statement.setString(4, doctor.getContactNumber());
            statement.setString(5, doctor.getAddress());
            statement.setString(6, doctor.getQualification());
            statement.setString(7, doctor.getGender());
            statement.setString(8, doctor.getDateOfJoining());
            statement.setString(9, doctor.getDoctorID());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Doctor getDoctorByID(String doctorID) {
        String query = "SELECT * FROM doctors WHERE doctor_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, doctorID);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorID(resultSet.getString("doctor_id"));
                doctor.setName(resultSet.getString("name"));
                doctor.setFatherName(resultSet.getString("father_name"));
                doctor.setEmail(resultSet.getString("email"));
                doctor.setContactNumber(resultSet.getString("contact_number"));
                doctor.setAddress(resultSet.getString("address"));
                doctor.setQualification(resultSet.getString("qualification"));
                doctor.setGender(resultSet.getString("gender"));
                doctor.setDateOfJoining(resultSet.getString("date_of_joining"));

                return doctor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
