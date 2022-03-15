package command;

import Collection.Organization;
import interfaces.CommandInterface;
import manager.CollectionManager;
import util.LoggerUtil;

import java.util.List;

public class FilterByAnnualTurnoverCommand implements CommandInterface {
    private CollectionManager collectionManager;

    public String getName(){
        return "filter_by_annual_turnover";
    }

    public String getDescription(){
        return "Вывести все элементы с равным годовым оборотом";
    }

    public void execute(Object[] args){
        List<Organization> list = collectionManager.getCollection().filter_by_annualTurnover(Float.valueOf((String) args[0]));
        if(list.isEmpty()){
            LoggerUtil.negative("В коллекции нет элементов");
        }else{
            LoggerUtil.infoAsString("Элементы с таким годовым оборотом:");
            list.forEach(fullName -> fullName.toString());
        }
    }
}
