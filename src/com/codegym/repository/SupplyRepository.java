package com.codegym.repository;


import com.codegym.model.supply.Supply;
import com.codegym.serializer.ReadSupplySerializer;

import java.util.List;


public class SupplyRepository {
    private List<Supply> supplys;

    ReadSupplySerializer readSupplySerializer = ReadSupplySerializer.getReadSupplySerializer();

    private static SupplyRepository supplyRepository;

    private static AnimalRepository animalRepository = AnimalRepository.getAnimalRepository();

    private SupplyRepository() {
        this.supplys = readSupplySerializer.readFromCSV();


    }

    public static SupplyRepository getSupplyRepository() {
        if (supplyRepository == null) {
            supplyRepository = new SupplyRepository();
        }
        return supplyRepository;
    }

    public void addSupply(Supply supply) {
        supplys.add(supply);
        updateFileCSV();
    }

    public void removeSupply(int supplyId) {
        supplys.removeIf(supply -> supply.getSupplyId() == supplyId);
        updateFileCSV();

    }

    public Supply findById(int supplyId) {
        for (Supply supply : supplys) {
            if (supplyId == supply.getSupplyId()) {
                return supply;
            }
        }
        return null;
    }


    public List<Supply> getAllSupplies() {
        return supplys;
    }

    public void updateSupply(Supply updatedSupply) {
        for (int i = 0; i < supplys.size(); i++) {
            supplys.set(i, updatedSupply);
            updateFileCSV();
            return;

        }
    }
    public void remove(int id) {
       supplys.removeIf(x-> x.getSupplyId() == id);
    }

    public void updateFileCSV() {
        readSupplySerializer.writeToCSV(supplys);
    }

}
