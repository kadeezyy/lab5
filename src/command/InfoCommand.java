package command;

import Collection.OrganizationCollection;
import manager.CollectionManager;
import util.LoggerUtil;

import java.text.SimpleDateFormat;

public class InfoCommand  {

    public String getName(){
        return "info";
    }

    public String getDescription(){
        return "Вывести информацию о коллекции";
    }

    public void execute(OrganizationCollection collection){
        LoggerUtil.infoAsString("Информация о коллекции:");
        System.out.println("Тип: " + collection.getClass().getName());
        System.out.println("Дата инициализации: " + new SimpleDateFormat().format(collection.getInitDate()));
        System.out.println("Количество элементов: " + collection.size());
    }
}
