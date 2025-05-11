package com.ps;

import java.util.*;

public class UserInterface {
    private Dealership dealership;
    private final Scanner scanner = new Scanner(System.in);

    public void display() {
        dealership = new DealershipFileManager().getDealership();
        int choice;

        do {
            System.out.println("\n===== Dealership Menu =====");
            System.out.println("1 - Find by price");
            System.out.println("2 - Find by make/model");
            System.out.println("3 - Find by year");
            System.out.println("4 - Find by color");
            System.out.println("5 - Find by mileage");
            System.out.println("6 - Find by type");
            System.out.println("7 - Show all vehicles");
            System.out.println("8 - Add vehicle");
            System.out.println("9 - Remove vehicle");
            System.out.println("99 - Quit");
            System.out.print("Enter your choice: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> getByPrice();
                case 2 -> getByMakeModel();
                case 3 -> getByYear();
                case 4 -> getByColor();
                case 5 -> getByMileage();
                case 6 -> getByType();
                case 7 -> displayVehicles(dealership.getAllVehicles());
                case 8 -> addVehicle();
                case 9 -> removeVehicle();
                case 99 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 99);
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }
    }

    private void getByPrice() {
        System.out.print("Min price: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Max price: ");
        double max = Double.parseDouble(scanner.nextLine());
        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    private void getByMakeModel() {
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    private void getByYear() {
        System.out.print("Min year: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Max year: ");
        int max = Integer.parseInt(scanner.nextLine());
        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    private void getByColor() {
        System.out.print("Color: ");
        String color = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    private void getByMileage() {
        System.out.print("Min mileage: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Max mileage: ");
        int max = Integer.parseInt(scanner.nextLine());
        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    private void getByType() {
        System.out.print("Type: ");
        String type = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByType(type));
    }

    private void addVehicle() {
        System.out.print("VIN: ");
        int vin = Integer.parseInt(scanner.nextLine());
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Type: ");
        String type = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Mileage: ");
        int mileage = Integer.parseInt(scanner.nextLine());
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        dealership.addVehicle(new Vehicle(vin, year, make, model, type, color, mileage, price));
        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership);

        System.out.println("Vehicle added!");
    }

    private void removeVehicle() {
        System.out.print("Enter VIN to remove: ");
        int vin = Integer.parseInt(scanner.nextLine());

        Vehicle match = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                match = v;
                break;
            }
        }

        if (match != null) {
            dealership.removeVehicle(match);
            DealershipFileManager fileManager = new DealershipFileManager();
            fileManager.saveDealership(dealership);

            System.out.println("Vehicle removed!");
        } else {
            System.out.println("Vehicle not found.");
        }
    }
}
