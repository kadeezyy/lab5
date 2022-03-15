package command;

import interfaces.CommandInterface;
import manager.CollectionManager;
import util.LoggerUtil;

public class RemoveByIdCommand implements CommandInterface {
    private CollectionManager collectionManager;

    public String getName(){
        return "remove_by_id";
    }

    public String getDescription(){
        return "удалить элемент коллекции по ID";
    }

    public void execute(Object[] args){
        try{
            int id = Integer.parseInt(String.valueOf(args[0]));
            collectionManager.getCollection().removeById(id);
            LoggerUtil.positive("Элемент был успешно удален из коллекции");
        }catch(Exception ex){
            LoggerUtil.negative("Не удалось удалить элемент (необходимо указать число)");
        }
    }
}
