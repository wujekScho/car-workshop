package pl.piotrschodzinski.util;

import pl.piotrschodzinski.model.Vehicle;
import pl.piotrschodzinski.model.Worker;

import java.util.ArrayList;
import java.util.Collections;

public class Tools {
    public static void swapVehicleElements(ArrayList<Vehicle> vehicles, Vehicle vehicle) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getRegistrationNumber().equals(vehicle.getRegistrationNumber())) {
                Collections.swap(vehicles, 0, i);
            }
        }
    }

    public static void swapWorkerElements(ArrayList<Worker> workers, Worker worker) {
        for (int i = 0; i < workers.size(); i++) {
            Worker loopWorker = workers.get(i);
            if (loopWorker.getAddress().equals(loopWorker.getAddress()) && loopWorker.getPhoneNumber().equals(worker.getPhoneNumber())
                    && loopWorker.getSurname().equals(worker.getSurname())) {
                Collections.swap(workers, 0, i);
            }
        }
    }

    public static void swapStatusElements(ArrayList<String> statuses, String status) {
        for (int i = 0; i < statuses.size(); i++) {
            if (statuses.get(i).equals(status)) {
                Collections.swap(statuses, 0, i);
            }
        }
    }
}
