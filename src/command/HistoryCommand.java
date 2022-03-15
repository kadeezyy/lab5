package command;

import interfaces.CommandInterface;
import manager.CollectionManager;
import util.LoggerUtil;

public class HistoryCommand  {
    private CollectionManager collectionManager;
    public String getName(){
        return "history";
    }

    public String getDescription(){
        return "Вывести последние 7 команд";
    }

}
