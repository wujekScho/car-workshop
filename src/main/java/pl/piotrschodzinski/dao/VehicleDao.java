package pl.piotrschodzinski.dao;

import pl.piotrschodzinski.model.Vehicle;

import java.sql.*;
import java.util.ArrayList;

public class VehicleDao {
    private final static String CREATE_VEHICLE = "INSERT INTO vehicle (brand, model, manufactureYear, " +
            "registrationNumber, serviceDate, customerId) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_VEHICLE = "UPDATE vehicle SET brand=?, model=?, manufactureYear=?, " +
            "registrationNumber=?, serviceDate=?, customerId=? WHERE id=?";
    private final static String DELETE_VEHICLE = "DELETE  FROM vehicle WHERE id=?";
    private final static String GET_VEHICLE_BY_ID = "SELECT * FROM vehicle WHERE id=?";
    private final static String GET_ALL_VEHICLES = "SELECT * FROM vehicle ORDER BY id ASC";

    public static Vehicle createVehicle(Connection connection, Vehicle vehicle) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CREATE_VEHICLE, Statement.RETURN_GENERATED_KEYS);
        setStatementParameters(statement, vehicle);
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            vehicle.setId(resultSet.getInt(1));
            return vehicle;
        }
        return null;
    }

    public static void updateVehicle(Connection connection, Vehicle vehicle, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_VEHICLE);
        setStatementParameters(statement, vehicle);
        statement.setInt(7, id);
        statement.executeUpdate();
    }

    private static void setStatementParameters(PreparedStatement statement, Vehicle vehicle) throws SQLException {
        statement.setString(1, vehicle.getBrand());
        statement.setString(2, vehicle.getModel());
        statement.setInt(3, vehicle.getManfuctureYear());
        statement.setString(4, vehicle.getRegistrationNumber());
        statement.setDate(5, Date.valueOf(vehicle.getServiceDate()));
        statement.setInt(6, vehicle.getCustomerId());
    }

    public static void deleteVehicle(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_VEHICLE);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public static Vehicle getVehicleById(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(GET_VEHICLE_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return loadSingleVehicle(resultSet);
        }
        return null;
    }

    public static ArrayList<Vehicle> getAllVehicles(Connection connection) throws SQLException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(GET_ALL_VEHICLES);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            vehicles.add(loadSingleVehicle(resultSet));
        }
        return vehicles;
    }

    private static Vehicle loadSingleVehicle(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(resultSet.getInt("id"));
        vehicle.setBrand(resultSet.getString("brand"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setManfuctureYear(resultSet.getInt("manufactureYear"));
        vehicle.setRegistrationNumber(resultSet.getString("registrationNumber"));
        vehicle.setServiceDate(resultSet.getDate("serviceDate").toLocalDate());
        vehicle.setCustomerId(resultSet.getInt("customerId"));
        return vehicle;
    }
}
