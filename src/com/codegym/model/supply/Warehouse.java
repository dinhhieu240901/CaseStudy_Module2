package com.codegym.model.supply;

import com.codegym.service.SupplyService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private Map<Integer, Integer> supplyQuantities;
    private final static SupplyService supplyService = SupplyService.getSupplyService();
    private Map<Integer, String> supplyData;

    public Warehouse() {
        this.supplyQuantities = new HashMap<>();
        this.supplyData = new HashMap<>();
        readFromFile();
    }

    public void addSupply(int supplyId, int quantity) {
        int currentQuantity = supplyQuantities.getOrDefault(supplyId, 0);
        supplyQuantities.put(supplyId, currentQuantity + quantity);

        Supply supply = supplyService.findById(supplyId);
        if (supply != null) {
            supplyData.put(supplyId, supply.getName());
        }
        writeToFile();
    }

    public void writeToFile() {
        try (FileWriter writer = new FileWriter("Zoo-system/warehouse.csv")) {
            writer.write("Name,Quantity,Supply Id\n");
            for (Map.Entry<Integer, Integer> entry : supplyQuantities.entrySet()) {
                int supplyId = entry.getKey();
                int quantity = entry.getValue();
                String name = supplyData.getOrDefault(supplyId, "");

                writer.write(name + "," + quantity + "," + supplyId + "\n");
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }

    public Map<Integer, Integer> getSupplyQuantities() {
        return new HashMap<>(supplyQuantities);
    }

    public Map<Integer, String> getSupplyData() {
        return new HashMap<>(supplyData);
    }

    public int getSupplyQuantity(int supplyId) {
        return supplyQuantities.getOrDefault(supplyId, 0);
    }

    public void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Zoo-system/warehouse.csv"))) {
            String line;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String name = data[0].trim();
                    int quantity = Integer.parseInt(data[1].trim());
                    int supplyId = Integer.parseInt(data[2].trim());
                    supplyQuantities.put(supplyId, quantity);
                    supplyData.put(supplyId, name);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Lỗi khi đọc từ file: " + e.getMessage());
        }
    }
    public void display() {
        System.out.println("=== Danh sách nguyên vật liệu trong kho ===");
        System.out.printf("%-20s%-15s%-10s%n", "Name", "Quantity", "Supply Id");
        for (Map.Entry<Integer, Integer> entry : supplyQuantities.entrySet()) {
            int supplyId = entry.getKey();
            int quantity = entry.getValue();
            String name = supplyData.getOrDefault(supplyId, "");
            System.out.printf("%-20s%-15d%-10d%n", name, quantity, supplyId);
        }
    }
}
