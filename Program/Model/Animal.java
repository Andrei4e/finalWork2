package Program.Model;

import java.util.List;

public abstract class Animal {

    protected String name;
    protected String birthday;
    public List<String> commands;

    public Animal( String name, String birthday, List<String> commands) {
        this.name = name;
        this.birthday = birthday;
        this.commands = commands;
    }

    public String getName() {
        return this.name;
    }

    public void PrintComands() {
        for (String command : commands) {
            System.out.println("Команда: " + command); 
        }
    }
}
