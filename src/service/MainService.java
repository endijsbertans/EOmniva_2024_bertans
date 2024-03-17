package service;

import model.AbstractCustomer;
import model.Driver;

import java.util.ArrayList;

public class MainService {
    private static ArrayList<Driver> allDrivers =  new ArrayList<Driver>();
    private static ArrayList<AbstractCustomer> AllCustomers = new ArrayList<AbstractCustomer>();

    public static void main(String[] args) {
        Driver d1 = new Driver();
        System.out.println(d1);
    }
}
