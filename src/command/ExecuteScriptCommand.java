package command;

import Collection.Organization;
import common.InputRequester;
import manager.CollectionManager;
import util.LoggerUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ExecuteScriptCommand {
    private CollectionManager collectionManager;
    private InputRequester inputRequester;
    Set<String> scriptInProcess = new HashSet<>();
    Organization.Coordinates newCoordinates;
    Organization.OrganizationType orgType ;


    public String getName() {
        return "execute_script";
    }

    public String getDescription() {
        return "Считать и исполнить скрипт из файла";
    }

    public void execute(String fileName) throws FileNotFoundException {
        if (fileName.trim().equals("")) {
            LoggerUtil.negative("Необходимо указать путь к файлу со скриптом");
        }
    }
    public void runScript(CollectionManager collectionManager, String command, BufferedReader reader){
        try {
            switch (command){
                case "add":
                    boolean ifSuccessfully = collectionManager.getCollection().add(executeScriptCommand(reader));
                    if (ifSuccessfully){
                        LoggerUtil.positive("Элемент успешно добавлен в коллекцию");
                    }else{
                        LoggerUtil.negative("Не удалось сохранить элемент в коллекцию");
                    }
                    break;

                case "update_id":
                    int id = Integer.parseInt(reader.readLine());
                    collectionManager.getCollection().updateById(id, executeScriptCommand(reader));
                    break;

                case "add_if_max":
                    collectionManager.getCollection().addIfMax(executeScriptCommand(reader));
                    break;

                case "remove_lower":
                    collectionManager.getCollection().removeLower(executeScriptCommand(reader));
                    break;
            }
        } catch (IOException e) {
            LoggerUtil.negative("Неверный тип данных");
        }
    }
    public Organization executeScriptCommand(BufferedReader reader){
        try {
            String name = reader.readLine().trim();
            int x = Integer.parseInt(reader.readLine().trim());
            int y = Integer.parseInt(reader.readLine().trim());
            newCoordinates = new Organization.Coordinates(x, y);
            Float annualTurnover = Float.parseFloat(reader.readLine().trim());
            String fullName = reader.readLine().trim();
            int employeesCount = Integer.parseInt(reader.readLine());
            orgType = Organization.OrganizationType.
                    valueOf(reader.readLine().trim());
            String postalAddress = reader.readLine().trim();
            Organization.Address zipCode = new Organization.Address(postalAddress);
            Organization organization = new Organization(name, newCoordinates, annualTurnover,
                    fullName, employeesCount, orgType, zipCode);
            return organization;
        }catch(Exception ex){
            LoggerUtil.negative("Неверно введены данные в скрипт");
        }return null;
    }
}
