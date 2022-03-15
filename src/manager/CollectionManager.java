package manager;

import Collection.Organization;
import org.json.simple.*;
import Collection.OrganizationCollection;
import common.InputRequester;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import util.LoggerUtil;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class CollectionManager {
    OrganizationCollection collection = new OrganizationCollection();
    InputRequester inputRequester;
    String fileName;
    long initDate = 0;
    public CollectionManager(){

    }

    public CollectionManager(String fileName) {
        this.fileName = fileName;
    }

    /**
     * writes data from collection to json file
     */
    public void writeCollectionToFile() {
        boolean isSuccessfully = false;
        try (BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(fileName))) {
            JSONObject jo = new JSONObject();
            Map map = new LinkedHashMap<>();
            for (Organization organization : collection) {
                map.put("id:", organization.getId());
                map.put("name", organization.getName());
                map.put("coordinates", organization.getCoordinates());
                map.put("annualTurnover", organization.getAnnualTurnover());
                map.put("fullName", organization.getFullName());
                map.put("employeesCount", organization.getEmployeesCount());
                map.put("type", organization.getType());
                map.put("postalAddress", organization.getPostalAddress());
                jo.put(organization.getId(), map);
                if (map.size() > 0) {
                    isSuccessfully = true;
                    os.write(jo.toJSONString().getBytes());
                    map.clear();
                }
            }
            if(isSuccessfully) {
                LoggerUtil.positive("Коллекция была успешно сохранена в файл");
            }else {
                LoggerUtil.negative("Не удалось сохранить коллекцию в файл");
            }
        } catch (Exception ex) {
            LoggerUtil.negative("Не удалось сохранить коллекцию в файл");
        }
    }

    /**
     * Reads collection from a file in csv format
     */
    public void readCollectionFromFile() {
        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(fileName))) {
            Object obj = new JSONParser().parse(fileName);
            JSONObject jo = (JSONObject) obj;
            Map id = (Map) jo.get("ID");

            Iterator<Map.Entry> itr = id.entrySet().iterator();
            List<String> keyList = new ArrayList<>();
            List<String> valueList = new ArrayList<>();
            while (itr.hasNext()) {
                Map.Entry pair = itr.next();
                keyList.add((String) pair.getKey());
                valueList.add((String) pair.getValue());
            }
            String[] coordinates = valueList.get(1).replaceFirst(".+=;", "").split(";");
            Organization organization = new Organization(valueList.get(0),
                    new Organization.Coordinates(Integer.parseInt(coordinates[0]),
                            Integer.parseInt(coordinates[1])),
                    Float.parseFloat(valueList.get(2)), valueList.get(3),
                    Integer.parseInt(valueList.get(4)),
                    Organization.OrganizationType.valueOf(valueList.get(5)),
                    new Organization.Address(valueList.get(6)));
            collection.add(organization);
            initDate = System.currentTimeMillis();
            LoggerUtil.positive("Коллекция была успешно загружена из файла");
        } catch (FileNotFoundException ex) {
            LoggerUtil.negative("Не удалось сохранить коллекцию из файла");
        } catch (IOException | ParseException ex) {
            LoggerUtil.negative("Не удалось сохранить коллекцию из файла");
        }
    }

    /**
     * Instantiates new Organization object by requesting all the parameters
     *
     * @return Instantiated Organization
     */
    public Organization requestOrganization() {
        Function<String, Object> function = input -> {
            try {
                int l = Integer.parseInt(input);
                return l > 0 ? l : null;
            } catch (NumberFormatException ex) {
                return null;
            }
        };
        return new Organization(
                inputRequester.requestInput("Введите название: ", input -> {
                    if (input.isEmpty()) {
                        return null;
                    }
                    return null;
                }),
                new Organization.Coordinates(
                        inputRequester.requestInput("Введите х: ", Integer::valueOf),
                        inputRequester.requestInput("Введите у: ", Integer::valueOf)
                ),
                inputRequester.requestInput("Введите годовой оборот: ", function),
                inputRequester.requestInput("Введите фамилию: ", input -> {
                    try {
                        String name = toString(input);
                        return name != "" ? name : null;
                    } catch (RuntimeException ex) {
                        return null;
                    }
                }),
                inputRequester.requestInput("Введите количество рабочих: ", function),
                inputRequester.requestInput(Arrays.stream(Organization.OrganizationType.values()).map(Enum::name)
                                .collect(Collectors.joining(",\n",
                                        "Возможные значения:\n", "\n")) + "Введите тип:",
                        input -> {
                            try {
                                return Organization.OrganizationType.valueOf(input.toUpperCase());
                            } catch (IllegalArgumentException ex) {
                                return null;
                            }
                        }), new Organization.Address(inputRequester.requestInput("Введите почтовый индекс: ",
                input -> input.isEmpty() ? "null" : input))
        );

    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public OrganizationCollection getCollection() {
        return collection;
    }

    public String toString(Object input) {
        return input.toString();
    }

    public long getInitDate(){
        return initDate;
    }


}
