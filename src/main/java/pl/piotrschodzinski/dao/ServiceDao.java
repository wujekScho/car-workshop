package pl.piotrschodzinski.dao;

import pl.piotrschodzinski.model.CurrentService;
import pl.piotrschodzinski.model.Service;
import pl.piotrschodzinski.model.ServiceStatus;
import pl.piotrschodzinski.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class ServiceDao implements IDao<Service> {
    private final static String CREATE_SERVICE = "INSERT INTO service (recived, plannedRepairDate, repairDate, workerId, " +
            "problemDescription, repairDescription, status, vehicleId, repairCost, partsCost, ratePerHour, workHours) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_SERVICE = "UPDATE service SET recived=?, plannedRepairDate=?, repairDate=?, " +
            "workerId=?, problemDescription=?, repairDescription=?, status=?, vehicleId=?, repairCost=?, partsCost=?, " +
            "ratePerHour=?, workHours=? WHERE id=?";
    private final static String DELETE_SERVICE = "DELETE  FROM service WHERE id=?";
    private final static String GET_SERVICE_BY_ID = "SELECT * FROM service WHERE id=?";
    private final static String GET_ALL_SERVICES = "SELECT * FROM service ORDER BY id ASC ORDER BY service.id";
    private final static String GET_CURRENT_SERVICES = "SELECT * FROM service LEFT JOIN worker ON service.workerId=worker.id " +
            "JOIN vehicle ON service.vehicleId = vehicle.id WHERE NOT status='cancelled' AND NOT status='completed' ORDER BY service.id LIMIT ?";
    private final static String GET_WORKER_SERVICES = "SELECT * FROM service LEFT JOIN worker ON service.workerId=worker.id " +
            "JOIN vehicle ON service.vehicleId = vehicle.id WHERE workerId=? AND NOT status='cancelled' AND NOT status='completed' ORDER BY service.id";
    private final static String GET_CUSTOMER_SERVICES = "SELECT * FROM service LEFT JOIN worker ON service.workerId=worker.id " +
            "JOIN vehicle ON service.vehicleId = vehicle.id WHERE customerId=? ORDER BY service.id";

    private static ServiceDao instance;

    public static ServiceDao getInstance() {
        if (instance == null) {
            instance = new ServiceDao();
        }
        return instance;
    }

    private static Service loadSingleService(ResultSet resultSet) throws SQLException {
        Service service = new Service();
        service.setId(resultSet.getInt("id"));
        if (resultSet.getDate("recived") == null) {
            service.setRecived(null);
        } else {
            service.setRecived(resultSet.getDate("recived").toLocalDate());
        }
        if (resultSet.getDate("plannedRepairDate") == null) {
            service.setPlannedRepairDate(null);
        } else {
            service.setPlannedRepairDate(resultSet.getDate("plannedRepairDate").toLocalDate());
        }
        if (resultSet.getDate("repairDate") == null) {
            service.setRepairDate(null);
        } else {
            service.setRepairDate(resultSet.getDate("repairDate").toLocalDate());
        }
        service.setWorkerId(resultSet.getInt("workerId"));
        service.setProblemDescription(resultSet.getString("problemDescription"));
        service.setRepairDescription(resultSet.getString("repairDescription"));
        service.setStatus(ServiceStatus.valueOf(resultSet.getString("status")));
        service.setVehicleId(resultSet.getInt("vehicleId"));
        service.setRepairCost(resultSet.getDouble("repairCost"));
        service.setPartsCost(resultSet.getDouble("partsCost"));
        service.setRatePerHour(resultSet.getDouble("ratePerHour"));
        service.setWorkHours(resultSet.getInt("workHours"));
        return service;
    }

    private static CurrentService loadSingleCurrentService(Service service, ResultSet resultSet) throws SQLException {
        CurrentService currentService = new CurrentService(service);
        currentService.setWorkerName(resultSet.getString("name"));
        currentService.setWorkerSurname(resultSet.getString("surname"));
        currentService.setVehicleBrand(resultSet.getString("brand"));
        currentService.setVehicleModel(resultSet.getString("model"));
        return currentService;
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

    @Override
    public Service create(Service object) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_SERVICE, Statement.RETURN_GENERATED_KEYS);
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
    public void update(Service object, int id) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SERVICE);
            setStatementParameters(statement, object);
            statement.setInt(13, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SERVICE);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Service readById(int id) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(GET_SERVICE_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return loadSingleService(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Service> readAll() {
        try (Connection connection = DBUtil.getConn()) {
            ArrayList<Service> services = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_SERVICES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                services.add(loadSingleService(resultSet));
            }
            return services;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CurrentService readCurrentById(int id) {
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(GET_SERVICE_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return loadSingleCurrentService(loadSingleService(resultSet), resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<CurrentService> readWorkerServices(int id) {
        ArrayList<CurrentService> workerServices = new ArrayList<>();
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(GET_WORKER_SERVICES);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                workerServices.add(loadSingleCurrentService(loadSingleService(resultSet), resultSet));
            }
            return workerServices;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<CurrentService> readCustomerServices(int id) {
        ArrayList<CurrentService> customerServices = new ArrayList<>();
        try (Connection connection = DBUtil.getConn()) {
            PreparedStatement statement = connection.prepareStatement(GET_CUSTOMER_SERVICES);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customerServices.add(loadSingleCurrentService(loadSingleService(resultSet), resultSet));
            }
            return customerServices;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<CurrentService> readAllCurrent(int limit) {
        try (Connection connection = DBUtil.getConn()) {
            ArrayList<CurrentService> services = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(GET_CURRENT_SERVICES);
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CurrentService currentService = loadSingleCurrentService(loadSingleService(resultSet), resultSet);
                services.add(currentService);
            }
            return services;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
