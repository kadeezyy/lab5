package command;

import Collection.Organization;
import interfaces.CommandInterface;
import manager.CollectionManager;
import util.LoggerUtil;

public class ShowCommand  {
    private CollectionManager collectionManager;

    public String getName() {
        return "show";
    }

    public String getDescription() {
        return "Вывести элементы коллекции";
    }

    public void execute(CollectionManager collectionManager) {
        if (collectionManager.getCollection().isEmpty()) {
            LoggerUtil.negative("Коллекция пустая");
        }else {
            LoggerUtil.infoAsString("Элементы коллекции:");
            int i = 1;
            for (Organization organization : collectionManager.getCollection()) {
                System.out.printf("Элемент %d :", i++);
                System.out.println(organization.toString());
            }
        }
    }
}
