package com.codegym.repository;

import com.codegym.model.Cage.Cage;
import com.codegym.model.animal.Animal;
import com.codegym.serializer.ReadCageSerializer;

import java.util.ArrayList;
import java.util.List;

public class CageRepository {
    private List<Cage> cages;

    ReadCageSerializer readCageSerializer = ReadCageSerializer.getInstanceReadCageSerializer();

    private static CageRepository cageRepository;

    private static AnimalRepository animalRepository = AnimalRepository.getAnimalRepository();

    private CageRepository() {
        this.cages = readCageSerializer.readFromCSV();
        for (Cage cage : cages) {
            List<Animal> animalsInCage = animalRepository.findByCageId(cage.getCageId());
            cage.setAnimals(animalsInCage);
        }

    }

    public static CageRepository getCageRepository() {
        if (cageRepository == null) {
            cageRepository = new CageRepository();
        }
        return cageRepository;
    }

    public void addCage(Cage cage) {
        cages.add(cage);
        updateFileCSV();
    }

    public void removeCage(String cageId) {
        cages.removeIf(cage -> cage.getCageId().equals(cageId));
        updateFileCSV();

    }

    public Cage findById(String cageId) {
        for (Cage cage : cages) {
            if (cageId.equals(cage.getCageId())) {
                return cage;
            }
        }
        return null;
    }


    public List<Cage> getAllCages() {
        return cages;
    }
    public List<Cage> getAllCagesForUserId(String employeeId) {
        List<Cage> result = new ArrayList<>();
        for (Cage cage : cages) {
            if (employeeId.equals(cage.getEmployeeId())) {
                result.add(cage);
            }
        }
        return result;
    }

    public void updateCage(Cage updatedCage) {
        for (int i = 0; i < cages.size(); i++) {
            if (cages.get(i).getCageId().equals(updatedCage.getCageId())) {
                cages.set(i, updatedCage);
                updateFileCSV();
                return;
            }
        }
    }


    public void updateFileCSV() {
        readCageSerializer.writeToCSV(cages);
    }

}
