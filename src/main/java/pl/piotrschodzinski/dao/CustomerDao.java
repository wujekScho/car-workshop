package pl.piotrschodzinski.dao;

import pl.piotrschodzinski.model.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDao {
    private final static String CREATE_CUSTOMER = "INSERT INTO customer (name, surname, birthDate) VALUES (?, ?, ?)";
    private final static String UPDATE_CUSTOMER = "UPDATE customer SET name=?, surname=?, birthDate=? WHERE id=?";
    private final static String DELETE_CUSTOMER = "DELETE  FROM customer WHERE id=?";
    private final static String GET_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE id=?";
    private final static String GET_ALL_CUSTOMERS = "SELECT * FROM customer ORDER BY id ASC";

    public static Customer createCustomer(Connection connection, Customer customer) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CREATE_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
        setStatementParameters(statement, customer);
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            customer.setId(resultSet.getInt(1));
            return customer;
        }
        return null;
    }

    public static void updateWorker(Connection connection, Customer customer, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER);
        setStatementParameters(statement, customer);
        statement.setInt(4, id);
        statement.executeUpdate();
    }

    private static void setStatementParameters(PreparedStatement statement, Customer customer) throws SQLException {
        statement.setString(1, customer.getName());
        statement.setString(2, customer.getSurname());
        statement.setDate(3, Date.valueOf(customer.getBirthDate()));
    }

    public static void deleteCustomer(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public static Customer getCustomerById(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(GET_CUSTOMER_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return loadSingleCustomer(resultSet);
        }
        return null;
    }

    public static ArrayList<Customer> getAllCustomers(Connection connection) throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(GET_ALL_CUSTOMERS);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            customers.add(loadSingleCustomer(resultSet));
        }
        return customers;
    }

    private static Customer loadSingleCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setName(resultSet.getString("name"));
        customer.setSurname(resultSet.getString("surname"));
        customer.setBirthDate(resultSet.getDate("birthDate").toLocalDate());
        return customer;
    }
}

