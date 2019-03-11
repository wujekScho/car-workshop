package pl.piotrschodzinski.dao;

import pl.piotrschodzinski.model.Worker;

import java.sql.*;

public class WorkerDao {
    private final static String CREATE_WORKER = "INSERT INTO worker (name, surname, address, phoneNumber, note, ratePerHour) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_WORKER = "";
    private final static String DELETE_WORKER = "";
    private final static String GET_WORKER_BY_ID = "";
    private final static String GET_ALL_WORKERS = "";

    public static Worker createWorker(Connection connection, Worker worker) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CREATE_WORKER, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, worker.getName());
        statement.setString(2, worker.getSurname());
        statement.setString(3, worker.getAddress());
        statement.setString(4, worker.getPhoneNumber());
        statement.setString(5, worker.getNote());
        statement.setDouble(6, worker.getRatePerHour());
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            worker.setId(resultSet.getInt(1));
            return worker;
        }
        return null;
    }
}
