package pl.piotrschodzinski.dao;

import pl.piotrschodzinski.model.Worker;

import java.sql.*;
import java.util.ArrayList;

public class WorkerDao {
    private final static String CREATE_WORKER = "INSERT INTO worker (name, surname, address, phoneNumber, note, ratePerHour) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_WORKER = "UPDATE worker SET name=?, surname=?, address=?, phoneNumber=?, note=?, ratePerHour=? WHERE id=?";
    private final static String DELETE_WORKER = "DELETE  FROM worker WHERE id=?";
    private final static String GET_WORKER_BY_ID = "SELECT * FROM worker WHERE id=?";
    private final static String GET_ALL_WORKERS = "SELECT * FROM worker ORDER BY id ASC";

    public static Worker createWorker(Connection connection, Worker worker) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CREATE_WORKER, Statement.RETURN_GENERATED_KEYS);
        setStatementParameters(statement, worker);
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            worker.setId(resultSet.getInt(1));
            return worker;
        }
        return null;
    }

    public static void updateWorker(Connection connection, Worker worker, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_WORKER);
        setStatementParameters(statement, worker);
        statement.setInt(7, id);
        statement.executeUpdate();
    }

    private static void setStatementParameters(PreparedStatement statement, Worker worker) throws SQLException {
        statement.setString(1, worker.getName());
        statement.setString(2, worker.getSurname());
        statement.setString(3, worker.getAddress());
        statement.setString(4, worker.getPhoneNumber());
        statement.setString(5, worker.getNote());
        statement.setDouble(6, worker.getRatePerHour());
    }

    public static void deleteWorker(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_WORKER);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public static Worker getWorkerById(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(GET_WORKER_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return loadSingleWorker(resultSet);
        }
        return null;
    }

    public static ArrayList<Worker> getAllWorkers(Connection connection) throws SQLException {
        ArrayList<Worker> workers = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(GET_ALL_WORKERS);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            workers.add(loadSingleWorker(resultSet));
        }
        return workers;
    }


    private static Worker loadSingleWorker(ResultSet resultSet) throws SQLException {
        Worker worker = new Worker();
        worker.setId(resultSet.getInt("id"));
        worker.setName(resultSet.getString("name"));
        worker.setSurname(resultSet.getString("surname"));
        worker.setAddress(resultSet.getString("address"));
        worker.setPhoneNumber(resultSet.getString("phoneNumber"));
        worker.setNote(resultSet.getString("note"));
        worker.setRatePerHour(resultSet.getDouble("ratePerHour"));
        return worker;
    }
}
