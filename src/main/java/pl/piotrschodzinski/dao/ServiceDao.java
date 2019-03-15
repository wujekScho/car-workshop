package pl.piotrschodzinski.dao;

import pl.piotrschodzinski.model.Service;
import pl.piotrschodzinski.model.ServiceStatus;

import java.sql.*;
import java.util.ArrayList;

public class ServiceDao {
    private final static String CREATE_SERVICE = "INSERT INTO service (recived, plannedRepairDate, repairDate, workerId, " +
            "problemDescription, repairDescription, status, vehicleId, repairCost, partsCost, ratePerHour, workHours) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_SERVICE = "UPDATE service SET recived=?, plannedRepairDate=?, repairDate=?, " +
            "workerId=?, problemDescription=?, repairDescription=?, status=?, vehicleId=?, repairCost=?, partsCost=?, " +
            "ratePerHour=?, workHours=? WHERE id=?";
    private final static String DELETE_SERVICE = "DELETE  FROM service WHERE id=?";
    private final static String GET_SERVICE_BY_ID = "SELECT * FROM service WHERE id=?";
    private final static String GET_ALL_SERVICES = "SELECT * FROM service ORDER BY id ASC";

    public static Service createService(Connection connection, Service service) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CREATE_SERVICE, Statement.RETURN_GENERATED_KEYS);
        setStatementParameters(statement, service);
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            service.setId(resultSet.getInt(1));
            return service;
        }
        return null;
    }

    public static void updateService(Connection connection, Service service, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_SERVICE);
        setStatementParameters(statement, service);
        statement.setInt(13, id);
        statement.executeUpdate();
    }

    private static void setStatementParameters(PreparedStatement statement, Service service) throws SQLException {
        statement.setDate(1, Date.valueOf(service.getRecived()));
        statement.setDate(2, Date.valueOf(service.getPlannedRepairDate()));
        statement.setDate(3, Date.valueOf(service.getRepairDate()));
        statement.setInt(4, service.getWorkerId());
        statement.setString(5, service.getProblemDescription());
        statement.setString(6, service.getRepairDescription());
        statement.setString(7, service.getStatus().name());
        statement.setInt(8, service.getVehicleId());
        statement.setDouble(9, service.getRepairCost());
        statement.setDouble(10, service.getPartsCost());
        statement.setDouble(11, service.getRatePerHour());
        statement.setInt(12, service.getWorkHours());
    }

    public static Service getServiceById(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(GET_SERVICE_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return loadSingleService(resultSet);
        }
        return null;
    }

    public static ArrayList<Service> getAllServices(Connection connection) throws SQLException {
        ArrayList<Service> services = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(GET_ALL_SERVICES);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            services.add(loadSingleService(resultSet));
        }
        return services;
    }

    private static Service loadSingleService(ResultSet resultSet) throws SQLException {
        Service service = new Service();
        service.setId(resultSet.getInt("id"));
        service.setRecived(resultSet.getDate("recived").toLocalDate());
        service.setPlannedRepairDate(resultSet.getDate("plannedRepairDate").toLocalDate());
        service.setRepairDate(resultSet.getDate("repairDate").toLocalDate());
        service.setWorkerId(resultSet.getInt("workerId"));
        service.setProblemDescription(resultSet.getString("problemDescription"));
        service.setRepairDescription(resultSet.getString("repairDescription"));
        service.setStatus(ServiceStatus.valueOf(resultSet.getString("status")));
        service.setVehicleId(resultSet.getInt("vehicleId"));
        service.setRepairCost(resultSet.getDouble("repairCost"));
        service.setPartsCost(resultSet.getDouble("partsCost"));
        service.setRatePerHour(resultSet.getDouble("ratePerHour"));
        service.setWorkHours(resultSet.getInt("workHour"));
        return service;
    }
}
