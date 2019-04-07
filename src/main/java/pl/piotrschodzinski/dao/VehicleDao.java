package pl.piotrschodzinski.dao;

import pl.piotrschodzinski.model.Vehicle;
import pl.piotrschodzinski.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class VehicleDao implements IDao<Vehicle> {
    private final static String CREATE_VEHICLE = "INSERT INTO vehicle (brand, model, manufactureYear, registrationNumber, serviceDate, customerId) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_VEHICLE = "UPDATE vehicle SET brand=?, model=?, manufactureYear=?, registrationNumber=?, serviceDate=?, customerId=? WHERE id=?";
    private final static String DELETE_VEHICLE = "DELETE  FROM vehicle WHERE id=?";
    private final static String GET_VEHICLE_BY_ID = "SELECT * FROM vehicle WHERE id=?";
    private final static String GET_ALL_VEHICLES = "SELECT * FROM vehicle ORDER BY id ASC";

    private static VehicleDao instance;

    public static VehicleDao getInstance() {
        if (instance == null) {
            instance = new VehicleDao();
        }
        return instance;
    }

    private static void setStatementParameters(PreparedStatement statement, Vehicle vehicle) throws SQLException {
        statement.setString(1, vehicle.getBrand());
        statement.setString(2, vehicle.getModel());
        statement.setInt(3, vehicle.getManufactureYear());
        statement.setString(4, vehicle.getRegistrationNumber());
        statement.setDate(5, Date.valueOf(vehicle.getServiceDate()), Calendar.getInstance());
        statement.setInt(6, vehicle.getCustomerId());
    }

    private static Vehicle loadSingleVehicle(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(resultSet.getInt("id"));
        vehicle.setBrand(resultSet.getString("brand"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setManufactureYear(resultSet.getInt("manufactureYear"));
        vehicle.setRegistrationNumber(resultSet.getString("registrationNumber"));
        vehicle.setServiceDate(resultSet.getDate("serviceDate").toLocalDate());
        vehicle.setCustomerId(resultSet.getInt("customerId"));
        return vehicle;
    }

    @Override
    public Vehicle create(Vehicle object) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_VEHICLE, Statement.RETURN_GENERATED_KEYS);
            setStatementParameters(statement, object);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                object.setId(resultSet.getInt(1));
                return object;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Vehicle object, int id) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_VEHICLE);
            setStatementParameters(statement, object);
            statement.setInt(7, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_VEHICLE);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vehicle readById(int id) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(GET_VEHICLE_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return loadSingleVehicle(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Vehicle> readAll() {
        try (Connection connection = DBUtil.getConn()) {
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_VEHICLES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vehicles.add(loadSingleVehicle(resultSet));
            }
            return vehicles;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
