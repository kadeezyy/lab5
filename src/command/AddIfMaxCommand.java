package command;

import Collection.Organization;
import Collection.OrganizationCollection;
import interfaces.CommandInterface;
import manager.CollectionManager;
import util.LoggerUtil;

import java.util.Collections;
import java.util.List;

public class AddIfMaxCommand  {
    private CollectionManager collectionManager;

    public String getName() {
        return "add_if_max";
    }

    public String getDescription() {
        return "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента коллекции";
    }

    public void execute(Object[] args){
        if(collectionManager.getCollection().addIfMax(collectionManager.requestOrganization())){
            LoggerUtil.positive("Элемент успешно добавлен в коллекцию");
        }else{
            LoggerUtil.negative("Элемент не удалось добавить в коллекцию");
        }
    }
}
