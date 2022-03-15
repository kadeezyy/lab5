package command;

import interfaces.CommandInterface;
import manager.CollectionManager;
import util.LoggerUtil;

public class UpdateByIdCommand  {
    private CollectionManager collectionManager;

    public String getName(){
        return "update_id";
    }

    public String getDescription(){
        return "Обновить значение элемента коллекции по заданному ID";
    }

    public void execute(int id ){

        collectionManager.getCollection().updateById(id, collectionManager.requestOrganization());
        LoggerUtil.positive("Элемент был успешно обновлен");
    }
}
