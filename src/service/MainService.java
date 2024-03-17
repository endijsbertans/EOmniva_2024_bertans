package service;

import model.*;

import java.util.ArrayList;

public class MainService {
    private static ArrayList<Driver> allDrivers =  new ArrayList<Driver>();
    private static ArrayList<AbstractCustomer> AllCustomers = new ArrayList<AbstractCustomer>();

    public static void main(String[] args) {
        Driver d1 = new Driver();
        System.out.println(d1);
        CustomerAsPerson cust1 = new CustomerAsPerson();
        System.out.println(cust1);
        CustomerAsCompany cust2 = new CustomerAsCompany();
        System.out.println(cust2);
        Driver d2 = new Driver("Janis", "Berzins", "123456-12345", "AF12122", 2);
        System.out.println(d2);
        CustomerAsPerson cust3 = new CustomerAsPerson("Endijs", "Bertāns", "123456-12345", new Address(City.Ventspils, "Brivibas", 11), "22123456");
        System.out.println(cust3);
        CustomerAsCompany cust4 = new CustomerAsCompany(new Address(City.Liepaja, "Talsu", 14), "29666117", "SIA Labais", "321456-21656");
        System.out.println(cust4);
        // Parcel(ParcelSize size, boolean isFragile, Driver driver){
        Parcel pckg1 = new Parcel();
        System.out.println(pckg1);
        Parcel pckg2 = new Parcel(ParcelSize.M, true, d2);
        System.out.println(pckg2);
    }
}
