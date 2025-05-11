package com.ps;

import java.io.*;

public class DealershipFileManager {
    private static final String FILE_NAME = "inventory.csv";

    public Dealership getDealership() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILE_NAME));
            String[] header = reader.readLine().split("\\|");
            Dealership dealership = new Dealership(header[0], header[1], header[2]);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                dealership.addVehicle(new Vehicle(
                        Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]),
                        data[2],
                        data[3],
                        data[4],
                        data[5],
                        Integer.parseInt(data[6]),
                        Double.parseDouble(data[7])
                ));
            }
            return dealership;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveDealership(Dealership dealership) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(FILE_NAME));
            writer.println(String.join("|", dealership.getName(), dealership.getAddress(), dealership.getPhone()));
            for (Vehicle v : dealership.getAllVehicles()) {
                writer.printf("%d|%d|%s|%s|%s|%s|%d|%.2f\n",
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(),
                        v.getColor(), v.getOdometer(), v.getPrice());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close(); // PrintWriter’s close() does not throw, but good practice
            }
        }
    }
}
