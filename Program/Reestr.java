package Program;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import Program.Model.*;

public class Reestr {
    private static Animal[] animals;

    public void AddAnimal(Animal animal){
        int n = 1;
        if (animals == null)
        {
            animals = new Animal[n];
            animals[n-1] = animal;
        }
        else
        {
            n = animals.length;
            Animal[] animalsNew = new Animal[n+1];
            for ( int i = 0; i < n ; i++) {
                animalsNew[i] = animals [i];
            }
            animalsNew[n] = animal;
            animals = new Animal[n+1];
            System.arraycopy(animalsNew, 0, animals, 0, n+1);       
        }     
    }

    private String GetClass(Animal animal){
        if (animal instanceof Cat)
           return "Cat";
        if (animal instanceof Dog)
           return "Dog";
        else
            return "Hamster";
    }

    public void PrintReestr() {
        try {
            for (int i = 0; i < animals.length ; i++) {
                System.out.println("№" + i + " Имя: " + animals[i].getName() + " Тип: " + GetClass(animals[i]));
                
            };
        } catch (NullPointerException e) {
            System.out.println("Нет животных в реестре!!!");
        }
        
    }

    public void PrintComands(int i){
        try {
            animals[i].PrintComands();  
        } 
        catch (Exception e) {
            System.out.println("Нет такого животного!!!");
        }
    }

    public void addComand(int i, String comand){
        try {
            List<String> commandsTmp = new ArrayList<>();
            for (String s : animals[i].commands) {
                commandsTmp.add(s);
            }
            commandsTmp.add(comand);
            animals[i].commands = commandsTmp;  
        } 
        catch (Exception e) {
            System.out.println("Нет такого животного!!!" + e.getMessage());
        }
    }
}
