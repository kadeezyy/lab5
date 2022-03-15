package command;

import Collection.Organization;
import interfaces.CommandInterface;
import manager.CollectionManager;
import util.LoggerUtil;

import java.util.List;

public class FilterByFullNameCommand implements CommandInterface {
    private CollectionManager collectionManager;

    public String getName(){
        return "filter_by_full_name";
    }

    public String getDescription(){
        return "Вывести элементы, имеющие такую же фамилию";
    }

    public void execute(Object[] args){
        List<Organization> list = collectionManager.getCollection().filter_by_fullName(String.valueOf(args[0]));
        if (list.isEmpty()){
            LoggerUtil.negative("В коллекции нет элементов");
        }else{
            LoggerUtil.infoAsString("Элементы с такой фамилией:");
            list.forEach(fullName -> fullName.toString());
        }
    }
}
