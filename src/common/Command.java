package common;

public abstract class Command {

    /**
     * Command and it's description
     */
    String command, description;

    /**
     *will contain command and its description
     */
    public Command(String command, String description){
        this.command = command;
        this.description = description;
    }


    /**
     * Called when user executes the specific command
     * @param args Command arguments
     */
    public abstract void execute(String[] args);

    public String getCommand(){
        return command;
    }

    public String getDescription(){
        return description;
    }
}
