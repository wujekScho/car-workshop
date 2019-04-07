package pl.piotrschodzinski.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.piotrschodzinski.util.LocalDateDeserializer;
import pl.piotrschodzinski.util.LocalDateSerializer;

import java.time.LocalDate;

public class Service {
    protected int id;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate recived;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate plannedRepairDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate repairDate;
    private int workerId;
    private String problemDescription;
    private String repairDescription;
    private ServiceStatus status;
    private int vehicleId;
    private double repairCost;
    private double partsCost;
    private double ratePerHour;
    private int workHours;

    public Service() {
    }

    public Service(LocalDate plannedRepairDate, LocalDate repairDate, int workerId, String problemDescription, String repairDescription, ServiceStatus status, int vehicleId, double repairCost, double partsCost, double ratePerHour, int workHours) {
        this.recived = LocalDate.now();
        this.plannedRepairDate = plannedRepairDate;
        this.repairDate = repairDate;
        this.workerId = workerId;
        this.problemDescription = problemDescription;
        this.repairDescription = repairDescription;
        this.status = status;
        this.vehicleId = vehicleId;
        this.repairCost = repairCost;
        this.partsCost = partsCost;
        this.ratePerHour = ratePerHour;
        this.workHours = workHours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getRecived() {
        return recived;
    }

    public void setRecived(LocalDate recived) {
        this.recived = recived;
    }

    public LocalDate getPlannedRepairDate() {
        return plannedRepairDate;
    }

    public void setPlannedRepairDate(LocalDate plannedRepairDate) {
        this.plannedRepairDate = plannedRepairDate;
    }

    public LocalDate getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(LocalDate repairDate) {
        this.repairDate = repairDate;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(double repairCost) {
        this.repairCost = repairCost;
    }

    public double getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(double partsCost) {
        this.partsCost = partsCost;
    }

    public double getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    public int getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", recived=" + recived +
                ", plannedRepairDate=" + plannedRepairDate +
                ", repairDate=" + repairDate +
                ", workerId=" + workerId +
                ", problemDescription='" + problemDescription + '\'' +
                ", repairDescription='" + repairDescription + '\'' +
                ", status=" + status +
                ", vehicleId=" + vehicleId +
                ", repairCost=" + repairCost +
                ", partsCost=" + partsCost +
                ", ratePerHour=" + ratePerHour +
                ", workHours=" + workHours +
                '}';
    }
}
