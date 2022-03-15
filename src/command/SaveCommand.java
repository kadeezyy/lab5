package command;

import interfaces.CommandInterface;
import manager.CollectionManager;

public class SaveCommand implements CommandInterface {
    private CollectionManager collectionManager;

    public String getName() {
        return "save";
    }

    public String getDescription() {
        return "Сохранить коллекцию в файл";
    }

    public void execute(Object[] args) {
        collectionManager.writeCollectionToFile();
    }
}
