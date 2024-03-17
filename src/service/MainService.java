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
    }
}
