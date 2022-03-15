package command;

import interfaces.CommandInterface;
import manager.CollectionManager;
import util.LoggerUtil;

public class RemoveLowerCommand implements CommandInterface {
    private CollectionManager collectionManager;

    public String getName(){
        return "remove_lower";
    }

    public String getDescription(){
        return "удалить из коллекции элементы, меньшие, чем заданный";
    }

    public void execute(Object [] args){
        collectionManager.getCollection().removeLower(collectionManager.requestOrganization());
        LoggerUtil.positive("Элементы успешно удалены");
    }
}
