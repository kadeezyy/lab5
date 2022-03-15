package command;

import interfaces.CommandInterface;
import util.LoggerUtil;

public class ExitCommand  {
    public String getName(){
        return "exit";
    }

    public String getDescription(){
        return "Завершить программу (без сохранения)";
    }

    public void execute(){
        LoggerUtil.negative("Программа завершилась без сохранения");
        Runtime.getRuntime().halt(0);
    }
}
