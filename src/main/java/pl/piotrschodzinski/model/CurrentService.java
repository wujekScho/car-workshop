package pl.piotrschodzinski.model;

import java.time.LocalDate;

public class CurrentService extends Service {
    private String vehicleBrand;
    private String vehicleModel;
    private String workerName;
    private String workerSurname;

    public CurrentService() {
    }

    public CurrentService(Service service) {
        super(service.getPlannedRepairDate(), service.getRepairDate(), service.getWorkerId(), service.getProblemDescription(),
                service.getRepairDescription(), service.getStatus(), service.getVehicleId(), service.getRepairCost(),
                service.getPartsCost(), service.getRatePerHour(), service.getWorkHours());
        this.id = service.getId();
    }

    public CurrentService(LocalDate plannedRepairDate, LocalDate repairDate, int workerId, String problemDescription, String repairDescription, ServiceStatus status, int vehicleId, double repairCost, double partsCost, double ratePerHour, int workHours, String vehicleBrand, String vehicleModel, String workerName, String workerSurname) {
        super(plannedRepairDate, repairDate, workerId, problemDescription, repairDescription, status, vehicleId, repairCost, partsCost, ratePerHour, workHours);
        this.vehicleBrand = vehicleBrand;
        this.vehicleModel = vehicleModel;
        this.workerName = workerName;
        this.workerSurname = workerSurname;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerSurname() {
        return workerSurname;
    }

    public void setWorkerSurname(String workerSurname) {
        this.workerSurname = workerSurname;
    }

    @Override
    public String toString() {
        return super.toString() + "CurrentService{" +
                "vehicleBrand='" + vehicleBrand + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", workerName='" + workerName + '\'' +
                ", workerSurname='" + workerSurname + '\'' +
                '}';
    }
}
