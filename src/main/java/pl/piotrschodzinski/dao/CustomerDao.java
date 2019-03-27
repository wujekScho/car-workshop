package pl.piotrschodzinski.dao;

import pl.piotrschodzinski.model.Customer;
import pl.piotrschodzinski.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDao implements IDao<Customer> {
    private final static String CREATE_CUSTOMER = "INSERT INTO customer (name, surname, birthDate) VALUES (?, ?, ?)";
    private final static String UPDATE_CUSTOMER = "UPDATE customer SET name=?, surname=?, birthDate=? WHERE id=?";
    private final static String DELETE_CUSTOMER = "DELETE  FROM customer WHERE id=?";
    private final static String GET_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE id=?";
    private final static String GET_ALL_CUSTOMERS = "SELECT * FROM customer ORDER BY id ASC";

    private static CustomerDao instance;

    public static CustomerDao getInstance() {
        if (instance == null) {
            instance = new CustomerDao();
        }
        return instance;
    }

    @Override
    public Customer create(Customer object) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
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
    public void update(Customer object, int id) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER);
            setStatementParameters(statement, object);
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void setStatementParameters(PreparedStatement statement, Customer customer) throws SQLException {
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getSurname());
        statement.setDate(3, Date.valueOf(customer.getBirthDate()));
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer readById(int id) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(GET_CUSTOMER_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return loadSingleCustomer(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Customer> readAll() {
        try (Connection connection = DBUtil.getConn()) {
            ArrayList<Customer> customers = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_CUSTOMERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customers.add(loadSingleCustomer(resultSet));
            }
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Customer loadSingleCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setName(resultSet.getString("name"));
        customer.setSurname(resultSet.getString("surname"));
        if (resultSet.getDate("birthDate") == null) {
            customer.setBirthDate(null);
        } else {
            customer.setBirthDate(resultSet.getDate("birthDate").toLocalDate());
        }
        return customer;
    }
}

