package com.codegym.Test;

import com.codegym.model.animal.enumerations.GenderAnimals;
import com.codegym.model.animal.enumerations.HealAnimals;
import com.codegym.model.animal.species.*;
import com.codegym.model.supply.enumerations.FoodType;
import com.codegym.service.AnimalService;

public class Animal {
    private static AnimalService animalService = AnimalService.getAnimalService();

    public static void main(String[] args) {
//        animalService.addAnimal(new Lion(4,"Lion1","Lion",190,1.2,12,HealAnimals.GOOD,GenderAnimals.MALE,null,null));
//        animalService.addAnimal(new Panda(5,"Panda1","Panda",100,0.7,6,HealAnimals.GOOD,GenderAnimals.MALE,null,null));
//        animalService.addAnimal(new Seal(6,"Seal1","Seal",300,1.7,15,HealAnimals.GOOD,GenderAnimals.MALE,null,null));
//        animalService.addAnimal(new Shark(7,"Shark1","Shark",2100,4.5,20,HealAnimals.GOOD,GenderAnimals.MALE,null,null));
//        animalService.addAnimal(new Zebra(8,"Zebra1","Zebra",350,1.3,10,HealAnimals.GOOD,GenderAnimals.MALE,null,null));
        animalService.addAnimal(new Dolphin(9,"Dolphin3","Dolphin",130,7.8,9,HealAnimals.GOOD,GenderAnimals.FEMALE,null,null));
        animalService.addAnimal(new Eagle(10,"Eagle2","Eagle",14,2.0,4,HealAnimals.GOOD,GenderAnimals.FEMALE,null,null));
        animalService.addAnimal(new Elephant(11,"Elephant2","Elephant",4800,3.1,23,HealAnimals.GOOD,GenderAnimals.FEMALE,null,null));
        animalService.addAnimal(new Lion(12,"Lion2","Lion",180,1.1,11,HealAnimals.GOOD,GenderAnimals.FEMALE,null,null));
        animalService.addAnimal(new Panda(13,"Panda2","Panda",90,0.6,5,HealAnimals.GOOD,GenderAnimals.FEMALE,null,null));
        animalService.addAnimal(new Seal(14,"Seal2","Seal",280,1.6,14,HealAnimals.GOOD,GenderAnimals.FEMALE,null,null));
        animalService.addAnimal(new Shark(15,"Shark2","Shark",2000,4.3,18,HealAnimals.GOOD,GenderAnimals.FEMALE,null,null));
        animalService.addAnimal(new Zebra(16,"Zebra2","Zebra",330,1.2,9,HealAnimals.GOOD,GenderAnimals.FEMALE,null,null));
    }
}
