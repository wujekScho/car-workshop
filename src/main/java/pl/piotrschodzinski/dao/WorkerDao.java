package pl.piotrschodzinski.dao;

import pl.piotrschodzinski.model.Worker;
import pl.piotrschodzinski.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class WorkerDao implements IDao<Worker> {
    private final static String CREATE_WORKER = "INSERT INTO worker (name, surname, address, phoneNumber, note, ratePerHour) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_WORKER = "UPDATE worker SET name=?, surname=?, address=?, phoneNumber=?, note=?, ratePerHour=? WHERE id=?";
    private final static String DELETE_WORKER = "DELETE  FROM worker WHERE id=?";
    private final static String GET_WORKER_BY_ID = "SELECT * FROM worker WHERE id=?";
    private final static String GET_ALL_WORKERS = "SELECT * FROM worker ORDER BY id ASC";

    private static WorkerDao instance;

    public static WorkerDao getInstance() {
        if (instance == null) {
            instance = new WorkerDao();
        }
        return instance;
    }

    @Override
    public Worker create(Worker object) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_WORKER, Statement.RETURN_GENERATED_KEYS);
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
    public void update(Worker object, int id) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_WORKER);
            setStatementParameters(statement, object);
            statement.setInt(7, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void setStatementParameters(PreparedStatement statement, Worker worker) throws SQLException {
        statement.setString(1, worker.getName());
        statement.setString(2, worker.getSurname());
        statement.setString(3, worker.getAddress());
        statement.setString(4, worker.getPhoneNumber());
        statement.setString(5, worker.getNote());
        statement.setDouble(6, worker.getRatePerHour());
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_WORKER);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Worker readById(int id) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(GET_WORKER_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return loadSingleWorker(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Worker> readAll() {
        try (Connection connection = DBUtil.getConn()) {
            ArrayList<Worker> workers = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_WORKERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                workers.add(loadSingleWorker(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
