package Collection;

import java.time.LocalDate;

public class Organization {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float annualTurnover; //Поле не может быть null, Значение поля должно быть больше 0
    private String fullName; //Значение этого поля должно быть уникальным, Поле не может быть null
    private Integer employeesCount; //Поле может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null
    private Address postalAddress; //Поле не может быть null


    //    public Organization(){    }
    public Organization(String name, Coordinates coordinates, Float annualTurnover,
                        String fullName, int employeesCount, OrganizationType type, Address postalAddress) {
        this.name = name;
        this.coordinates = coordinates;
        this.annualTurnover = annualTurnover;
        this.fullName = fullName;
        this.employeesCount = employeesCount;
        this.type = type;
        this.postalAddress = postalAddress;
    }

    public String toString() {
        return String.format("Идентификатор: %d%n" +
                        "Название: %s%n" +
                        "Координаты: %s%n" +
                        "Дата создания: %s%n" +
                        "Годовой оборот: %f%n" +
                        "Фамилия: %s%n" +
                        "Количество рабочих: %d%n" +
                        "Тип: %s%n" +
                        "Почтовый индекс: %s",
                id, name, Coordinates.getCoordinates(), creationDate, annualTurnover,
                fullName, employeesCount, type, getPostalAddress());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setEmployeesCount(int employeesCount) {
        this.employeesCount = employeesCount;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Float getAnnualTurnover() {
        return annualTurnover;
    }

    public String getFullName() {
        return fullName;
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

    public String getCoordinates() {
        return Coordinates.getCoordinates();
    }

    public OrganizationType getType() {
        return type;
    }

    public String getPostalAddress() {
//        return postalAddress;
        return Address.getZipCode();
    }

    public static class Coordinates {
        private static int x;
        private static int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static String getCoordinates() {
            return x + ";" + y;
        }
    }

    public static class Address {
        private String street; //Длина строки не должна быть больше 110, Поле не может быть null
        private static String zipCode; //Длина строки должна быть не меньше 4, Поле может быть null
        private Location town; //Поле не может быть null

        public Address(String zipCode) {
            this.zipCode = zipCode;
        }
        static String getZipCode(){
            return zipCode;
        }

    }

    public class Location {
        private Float x; //Поле не может быть null
        private double y;
        private long z;
        private String name; //Поле может быть null
    }

    public enum OrganizationType {
        COMMERCIAL,
        PRIVATE_LIMITED_COMPANY,
        OPEN_JOINT_STOCK_COMPANY;
    }
}
