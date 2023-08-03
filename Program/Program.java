package Program;
import java.lang.System.Logger;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import Program.Model.*;

public class Program {

    public static void main(String[] args) throws Exception
    {
        AppConsole(0);
    }

    public static void addCounter() throws Exception {
        // Счетчик
        try(Counter counter = new Counter()){
            counter.add();
        }
    }

    public static void AppConsole(int command) throws Exception {
        Reestr reestr = new Reestr();
        Scanner scanner = new Scanner(System.in);
        int x = 0;
        String comandAnimal, name, birthday;
        switch (command) {
            case 0:
                System.out.printf("Введите номер команды:\n" +
                                "1 - Добавить новое животное \n" +
                                "2 - Вывести на экран список животных в реестре \n" +                     
                                "3 - Напечатать информацию о командах животного (введя его номер)\n" +
                                "4 - Добавить команду животному (введя его номер)\n" +
                                "5 - Выйти из приложения\n");
                x = scanner.nextInt();                
                AppConsole(x);
                break;
            case 1:
                System.out.printf("Введите номер типа животного, которое хотите добавить:\n" +
                                "6 - Кошка\n" +
                                "7 - Собака\n" +
                                "8 - Хомяк\n");
                x = scanner.nextInt();
                AppConsole(x);
                break;
            case 2:
                reestr.PrintReestr();
                AppConsole(0);
                break;
            case 3:
                System.out.printf("Введите номер животного, для печати информации о его командах:\n");
                x = scanner.nextInt();
                reestr.PrintComands(x);
                AppConsole(0);
                break;
            case 4:
                System.out.printf("Введите номер животного, чтобы добавить ему команду:\n");
                x = scanner.nextInt();
                System.out.printf("Введите комангду:\n");
                comandAnimal = scanner.next();
                reestr.addComand(x, comandAnimal);
                AppConsole(0);
                break;
            case 5:
                break;
            case 6:
                System.out.printf("Введите Кличку кошки:\n");
                name = scanner.next();
                System.out.printf("Введите день рождения кошки:\n");
                birthday = scanner.next();
                System.out.printf("Введите команды через запятую:\n");
                comandAnimal = scanner.next();
                Cat cat = new Cat(name, birthday, Arrays.asList(comandAnimal.split(",")));
                reestr.AddAnimal(cat);
                addCounter();
                AppConsole(0);
                break;
            case 7:                
                System.out.printf("Введите Кличку собаки:\n");
                name = scanner.next();
                System.out.printf("Введите день рождения собаки:\n");
                birthday = scanner.next();
                System.out.printf("Введите команды через запятую:\n");
                comandAnimal = scanner.next();
                Dog dog = new Dog(name, birthday, Arrays.asList(comandAnimal.split(",")));
                reestr.AddAnimal(dog);
                addCounter();
                AppConsole(0);
                break;
            case 8:
                System.out.printf("Введите Кличку хомяка:\n");
                name = scanner.next();
                System.out.printf("Введите день рождения хомяка:\n");
                birthday = scanner.next();
                System.out.printf("Введите команды через запятую:\n");
                comandAnimal = scanner.next();
                Hamster hamster = new Hamster(name, birthday, Arrays.asList(comandAnimal.split(",")));
                reestr.AddAnimal(hamster);
                addCounter();
                AppConsole(0);
                break;
            default:
                break;
        }
    }
}