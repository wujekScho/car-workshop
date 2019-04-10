package pl.piotrschodzinski.model;

import java.util.ArrayList;

public enum ServiceStatus {
    accepted, repairCostApproved, underRepair, readyToPickup, cancelled, completed;

    public static ArrayList<String> getStatuses() {
        ArrayList<String> statuses = new ArrayList<>();
        statuses.add("accepted");
        statuses.add("repairCostApproved");
        statuses.add("underRepair");
        statuses.add("readyToPickup");
        statuses.add("cancelled");
        statuses.add("completed");
        return statuses;
    }
}
