package com.codegym.serializer;

import com.codegym.model.Cage.*;
import com.codegym.model.Cage.enumerations.CleanlinessEnclosure;
import com.codegym.model.animal.Animal;
import com.codegym.model.animal.enumerations.GenderAnimals;
import com.codegym.model.animal.enumerations.HealAnimals;
import com.codegym.model.animal.species.*;
import com.codegym.model.foodplan.FoodPlan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadCageSerializer {
    private static ReadCageSerializer readCageSerializer;

    public static ReadCageSerializer getInstanceReadCageSerializer() {
        if (readCageSerializer == null) {
            readCageSerializer = new ReadCageSerializer();
        }
        return readCageSerializer;
    }


    private ReadCageSerializer() {
    }

    public void writeToCSV(List<Cage> objects) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Zoo-system/cage.csv"))) {
            writer.println("CageId,Name,MaxAnimals,Employee Id,Clean liness,Cage Type,Height,Deepness");
            for (Cage obj : objects) {
                if (obj instanceof Aviary) {
                    writer.println(obj.getCageId() + "," +
                            obj.getName() + "," +
                            obj.getMaxAnimals() + "," +
                            obj.getEmployeeId() + "," +
                            obj.getCleanliness() + "," +
                            obj.getCageType() + "," +
                            ((Aviary) obj).getHeight() + ","+" ,"
                    );
                }else if (obj instanceof TerrestrialHabitat) {
                    writer.println(obj.getCageId() + "," +
                            obj.getName() + "," +
                            obj.getMaxAnimals() + "," +
                            obj.getEmployeeId() + "," +
                            obj.getCleanliness() + "," +
                            obj.getCageType() + "," +
                            ((TerrestrialHabitat) obj).getHeight() + ","+" ,"
                    );
                }else {
                    writer.println(obj.getCageId() + "," +
                            obj.getName() + "," +
                            obj.getMaxAnimals() + "," +
                            obj.getEmployeeId() + "," +
                            obj.getCleanliness() + "," +
                            obj.getCageType() + "," +" "+
                            " ," +  ((AquaticHabitat) obj).getDeepness());

                }            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cage> readFromCSV() {
        List<Cage> loadedObjects = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Zoo-system/cage.csv"))) {
            String line;
            Boolean co = false;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && co) {

                    String id = data[0];
                    String name = data[1];
                    int maxAnimals = Integer.parseInt(data[2]);
                    String employeeId = data[3] == "" ? null : data[3];
                    CleanlinessEnclosure heal = CleanlinessEnclosure.valueOf(data[4]);
                    String cageType = data[5];
                    int height = 0;
                    int deepness =0;
                    if (!data[6].isBlank()){
                        height = Integer.parseInt(data[6]);
                    }
                    if (!data[7].isBlank()){
                        deepness = Integer.parseInt(data[7]);
                    }
                    Cage  cage= null;
                    if (cageType.equals("Aviary")) {
                        cage =new Aviary(id,name,maxAnimals,height,employeeId);
                    } else if (cageType.equals("TerrestrialHabitat")) {
                        cage =new TerrestrialHabitat(id,name,maxAnimals,height,employeeId);
                    } else if (cageType.equals("AquaticHabitat")) {
                        cage =new AquaticHabitat(id,name,maxAnimals,deepness,employeeId);
                    }
                    loadedObjects.add(cage);
                }
                co = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadedObjects;
    }
}
