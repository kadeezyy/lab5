import command.*;
import common.InputRequester;
import manager.CollectionManager;
import util.LoggerUtil;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        List<String> commandHistory = new ArrayList<>();
        InputRequester inputRequester = new InputRequester(new BufferedReader(new InputStreamReader(System.in)));
        CollectionManager collectionManager = new CollectionManager();

        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите команду:");
            try{
                String command = inputRequester.getReader().readLine().trim();
                StartRun.run(command, collectionManager, scanner, commandHistory);
            }catch (IOException ex){
                LoggerUtil.negative("Данные введены неверно!" + ex.getMessage());
            }
        }
    }
}

