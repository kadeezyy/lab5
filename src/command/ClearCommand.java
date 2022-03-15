package command;

import interfaces.CommandInterface;
import manager.CollectionManager;
import util.LoggerUtil;


public class ClearCommand implements CommandInterface {
    private CollectionManager collectionManager;
    public String getName(){
        return "clear";
    }

    public String getDescription(){
        return "Очистить коллекцию";
    }


    public void execute(Object[] args){
        collectionManager.getCollection().clear();
        LoggerUtil.positive("Коллекция очищена");
    }
}
