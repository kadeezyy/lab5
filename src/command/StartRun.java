package command;

import Collection.Organization;
import command.*;
import common.InputRequester;
import manager.CollectionManager;
import util.LoggerUtil;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class StartRun {

    public static void run(String command, CollectionManager collectionManager, Scanner scanner, List<String> commandHistory) throws FileNotFoundException {
        switch (command.trim()) {
            case "info":
                commandHistory.add((new InfoCommand()).getName());
                new InfoCommand().execute(collectionManager.getCollection());
                break;

            case "help":
                new HelpCommand().execute();
                commandHistory.add((new HelpCommand()).getName());
                break;

            case "show":
                new ShowCommand().execute(collectionManager);
                commandHistory.add((new ShowCommand()).getName());
                break;

            case "add":
                commandHistory.add((new AddCommand()).getName());
                Organization organization = new AddCommand().execute();
                organization.setCreationDate(LocalDateTime.now().toLocalDate());
                boolean isSuccessfully = collectionManager.getCollection().add(organization);
                if (isSuccessfully) {
                    LoggerUtil.positive("Элемент был успешно добавлен в коллекцию");
                } else {
                    LoggerUtil.negative("Элемент не удалось добавить в коллекцию");
                }
                break;

            case "update_id":
                commandHistory.add((new UpdateByIdCommand()).getName());
                LoggerUtil.infoAsString("Введите ID: ");
                int id = Integer.parseInt(scanner.nextLine().trim());
                if (collectionManager.getCollection().getID_COUNTER() < id) {
                    LoggerUtil.negative("Элемента с таким ID не существует!");
                } else {
                    Organization organization1 = new AddCommand().execute();
                    organization1.setCreationDate(LocalDateTime.now().toLocalDate());
                    isSuccessfully = collectionManager.getCollection().add(organization1);
                    if (isSuccessfully) {
                        LoggerUtil.positive("Элемент был успешно добавлен в коллекцию");
                    } else {
                        LoggerUtil.negative("Элемент не удалось добавить в коллекцию");
                    }
                    collectionManager.getCollection().updateById(id, organization1);
                }
                break;

            case "remove_by_id":
                commandHistory.add((new RemoveByIdCommand()).getName());
                LoggerUtil.infoAsString("Введите ID:");
                id = Integer.parseInt(scanner.nextLine().trim());
                if (id > collectionManager.getCollection().getID_COUNTER()) {
                    LoggerUtil.negative("Элемента с таким ID не существует в коллекции");
                } else {
                    collectionManager.getCollection().removeById(id);
                    LoggerUtil.positive("Элемент с таким ID успешно удален!");
                }
                break;

            case "clear":
                commandHistory.add((new ClearCommand()).getName());
                collectionManager.getCollection().clear();
                LoggerUtil.positive("Коллекция успешно очищена");
                break;

            case "save":
                commandHistory.add((new SaveCommand()).getName());
                collectionManager.setFileName("fileToWriteIn.json");
                collectionManager.writeCollectionToFile();
                break;

            case "exit":
                new ExitCommand().execute();
                break;

            case "add_if_max":
                commandHistory.add((new AddIfMaxCommand()).getName());
                LoggerUtil.infoAsString("Введите данные элемента, с которым нужно сравнивать другие элементы коллекции");
                Organization organizationToCompare = new AddCommand().execute();
                boolean isSuccessfullyCompared = collectionManager.getCollection().addIfMax(organizationToCompare);
                if (isSuccessfullyCompared) {
                    LoggerUtil.positive("Элемент успешно добавлен в коллекцию");
                } else {
                    LoggerUtil.positiveAsString("Элемент не был добавлен в коллекцию");
                }
                break;

            case "remove_lower":
                commandHistory.add((new RemoveLowerCommand()).getName());
                LoggerUtil.infoAsString("Введите данные элемента, с которым нужно сравнивать");
                Organization organization1 = new AddCommand().execute();
                collectionManager.getCollection().removeLower(organization1);
                break;
            case "history":
                commandHistory.add((new HistoryCommand()).getName());
                List<String> subList;
                if (commandHistory.size() > 0) {
                    if (commandHistory.size() > 7) {
                        subList = commandHistory.subList(commandHistory.size() - 7, commandHistory.size() - 1);
                    } else {
                        subList = commandHistory;
                    }
                    LoggerUtil.positiveAsString(String.valueOf(subList));
                } else {
                    LoggerUtil.positiveAsString("Не было введено ни одной команды");
                }
            case "filter_by_annual_turnover":
                commandHistory.add((new FilterByAnnualTurnoverCommand()).getName());
                LoggerUtil.infoAsString("Введите годовой оборот, по которому нужно сортировать");
                Float annualTurnover = Float.parseFloat(scanner.nextLine().trim());
                for (Organization org : collectionManager.getCollection().
                        filter_by_annualTurnover(annualTurnover)) {
                    System.out.println(org.toString());
                }
                break;
            case "filter_contains_full_name":
                commandHistory.add((new FilterByFullNameCommand()).getName());

                LoggerUtil.infoAsString("Введите фамилию, по которой нужно сортировать");
                String fullName = scanner.nextLine().trim();
                for (Organization org : collectionManager.getCollection().
                        filter_by_fullName(fullName)) {
                    System.out.println(org.toString());
                }
                break;

            case "execute_script":
                commandHistory.add((new ExecuteScriptCommand()).getName());

                LoggerUtil.infoAsString("Введите абсолютный путь до файла");
                String fileName = scanner.nextLine().trim();
                File file = new File(fileName);
                List<String> commandList = Arrays.asList("add", "update_id", "add_if_max", "remove_lower");
                List<String> allCommandList = Arrays.asList("help", "info", "show", "add", "remove_by_id", "update_id",
                        "clear", "save", "execute_script", "add_if_max", "exit", "remove_lower", "history",
                        "filter_by_annual_turnover", "filter_by_full_name");
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    ExecuteScriptCommand executeScript = new ExecuteScriptCommand();
                    while ((line = reader.readLine()) != null) {
                        if (commandList.contains(line)) {
                            executeScript.runScript(collectionManager, line, reader);
                        } else if (line.equals("remove_by_id")) {
                            int id1 = Integer.parseInt(reader.readLine());
                            collectionManager.getCollection().removeById(id1);
                        } else if (allCommandList.contains(line)) {
                            run(line, collectionManager, new Scanner(System.in), new ArrayList<>());
                        } else {
                            LoggerUtil.negative("Неверная команда в скрипте!");
                        }
                    }
                    reader.close();
                } catch (FileNotFoundException ex) {
                    LoggerUtil.negative("Не удалось найти файл");
                } catch (IOException ex) {
                    LoggerUtil.negative("Не удалось исполнить скрипт");
                }
                break;
            default:
                LoggerUtil.negative("Такой команды нет!");
        }
    }
}