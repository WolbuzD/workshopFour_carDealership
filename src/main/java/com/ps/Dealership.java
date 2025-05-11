package com.ps;

import java.util.*;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private List<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        return inventory.stream().filter(v -> v.getPrice() >= min && v.getPrice() <= max).toList();
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return inventory.stream().filter(v ->
                v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)).toList();
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        return inventory.stream().filter(v -> v.getYear() >= min && v.getYear() <= max).toList();
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return inventory.stream().filter(v -> v.getColor().equalsIgnoreCase(color)).toList();
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        return inventory.stream().filter(v -> v.getOdometer() >= min && v.getOdometer() <= max).toList();
    }

    public List<Vehicle> getVehiclesByType(String type) {
        return inventory.stream().filter(v -> v.getVehicleType().equalsIgnoreCase(type)).toList();
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
}