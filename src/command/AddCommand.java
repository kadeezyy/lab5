package command;

import Collection.Organization;
import interfaces.CommandInterface;
import manager.CollectionManager;
import sun.rmi.runtime.Log;
import util.LoggerUtil;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AddCommand  {
    Scanner scanner = new Scanner(System.in);
    public String getName() {
        return "add";
    }

    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }

    public Organization execute(){
        LoggerUtil.infoAsString("Введите название организации: ");
        String name = scanner.nextLine().trim();
        LoggerUtil.infoAsString("Введите координату х: ");
        int x = Integer.parseInt(scanner.nextLine().trim());
        LoggerUtil.infoAsString("Введите координату y: ");
        int y = Integer.parseInt(scanner.nextLine().trim());
        Organization.Coordinates coordinates = new Organization.Coordinates(x, y);
        LoggerUtil.infoAsString("Введите годовой оборот: ");
        Float annualTurnover = Float.parseFloat(scanner.nextLine().trim());
        LoggerUtil.infoAsString("Введите фамилию: ");
        String fullName = scanner.nextLine().trim();
        LoggerUtil.infoAsString("Введите количество рабочих: ");
        int employeesCount = Integer.parseInt(scanner.nextLine().trim());
        LoggerUtil.infoAsString("Введите  тип организации: ");
        LoggerUtil.infoAsString("Варианты: ");
        Organization.OrganizationType[] orgType = Organization.OrganizationType.values();
        for (Organization.OrganizationType types : orgType) {
            System.out.println(types);
        }
        String type = scanner.nextLine().trim();
        Organization.OrganizationType Type = Organization.OrganizationType.valueOf(type);
        LoggerUtil.infoAsString("Введите почтовый индекс: ");
        String postalAddress = scanner.nextLine().trim();
        Organization.Address zipCode = new Organization.Address(postalAddress);
        Organization organization = new Organization(name, coordinates, annualTurnover,
                fullName,employeesCount,Type, zipCode);
        return organization;

    }

}
