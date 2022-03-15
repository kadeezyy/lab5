package command;

import command.*;
import interfaces.CommandInterface;
import manager.CollectionManager;
import util.LoggerUtil;

import java.util.*;

public class HelpCommand  {
    private CollectionManager collectionManager;
    Map commandList = new HashMap<String, String>();

    public String getName(){
        return "help";
    }

    public String getDescription(){
        return "Вывести список доступных команд";
    }

    public void execute(){
        commandList.put((new AddCommand()).getName(), (new AddCommand()).getDescription());
        commandList.put((new AddIfMaxCommand()).getName(), (new AddIfMaxCommand()).getDescription());
        commandList.put((new ClearCommand()).getName(), (new ClearCommand()).getDescription());
        commandList.put((new ShowCommand()).getName(), (new ShowCommand()).getDescription());
        commandList.put((new HelpCommand()).getName(), (new HelpCommand()).getDescription());
        commandList.put((new InfoCommand()).getName(), (new InfoCommand()).getDescription());
        commandList.put((new ExecuteScriptCommand()).getName(), (new ExecuteScriptCommand()).getDescription());
        commandList.put((new ExitCommand()).getName(), (new ExitCommand()).getDescription());
        commandList.put((new FilterByAnnualTurnoverCommand()).getName(), (new FilterByAnnualTurnoverCommand()).getDescription());
        commandList.put((new FilterByFullNameCommand()).getName(), (new FilterByFullNameCommand()).getDescription());
        commandList.put((new HistoryCommand()).getName(), (new HistoryCommand()).getDescription());
        commandList.put((new RemoveByIdCommand()).getName(), (new RemoveByIdCommand()).getDescription());
        commandList.put((new RemoveLowerCommand()).getName(), (new RemoveLowerCommand()).getDescription());
        commandList.put((new SaveCommand()).getName(), (new SaveCommand()).getDescription());
        commandList.put((new UpdateByIdCommand()).getName(), (new UpdateByIdCommand()).getDescription());

        LoggerUtil.infoAsString("Список доступных команд:");
        Iterator<Map.Entry> itr = commandList.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry pair = itr.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
    }

}
