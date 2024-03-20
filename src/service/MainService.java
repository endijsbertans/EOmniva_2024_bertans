package service;

import model.*;

import java.time.LocalDateTime;
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
        Parcel pckg1 = new Parcel();
        System.out.println(pckg1);
        Parcel pckg2 = new Parcel(LocalDateTime.now().plusDays(2), ParcelSize.M, true, d2);
        System.out.println(pckg2);
    }
    // start of driver CRUD
    public static void addDriver(String name, String surname, String personCode, String licenseNo, float experienceInYears) throws Exception {
        if(name == null || name.matches("[A-ZĒŪĪĻĶĢŠĀČŅ][a-zēūīļķģšāžčņ]+")
        || surname == null || surname.matches("[A-ZĒŪĪĻĶĢŠĀČŅ][a-zēūīļķģšāžčņ]+")
        || personCode == null || !personCode.matches("\\d{5,6}-\\d{5}")
        || licenseNo == null || !licenseNo.matches("[A]{1}[F]{1}\\d{5,7}")
        || experienceInYears < 0 || experienceInYears > 99)
            throw new Exception("Invalid input parameters for driver");

        Driver temp = new Driver(name, surname, personCode, licenseNo, experienceInYears);
        allDrivers.add(temp);
    }
    public static Driver getDriverByPersonCode(String personCode) throws Exception {
        if(personCode == null ||
                !personCode.matches("\\d{5,6}-\\d{5}")) throw new Exception("Person code is invalid");
        for(Driver d : allDrivers){
            if(d.getPersonCode().equals(personCode))
                return d;
        }
        return null;
    }
    public static void editLicenseNobyPersonCode(String personCode, String licenseNo) throws Exception {
        if(personCode == null ||
                !personCode.matches("\\d{5,6}-\\d{5}")) throw new Exception("Person code is invalid");
        if(licenseNo == null ||
                !licenseNo.matches("[A]{1}[F]{1}\\d{5,7}")) throw new Exception("License number is invalid");
        getDriverByPersonCode(personCode).setLicenseNo(licenseNo);
    }
    public static void editExperienceInYearsByPersonCode(String personCode, float experienceInYears) throws Exception {
        if(personCode == null ||
                !personCode.matches("\\d{5,6}-\\d{5}")) throw new Exception("Person code is invalid");
        if(experienceInYears < 0 || experienceInYears > 99) throw new Exception("Experience in years is invalid");
        getDriverByPersonCode(personCode).setExperienceInYears(experienceInYears);
    }
    // start of customer CRUD
    public static void addCustomerAsPerson(String name, String surname, String personCode, Address address, String phone) throws Exception {

        if(name == null || name.matches("[A-ZĒŪĪĻĶĢŠĀČŅ][a-zēūīļķģšāžčņ]+")
        || surname == null || surname.matches("[A-ZĒŪĪĻĶĢŠĀČŅ][a-zēūīļķģšāžčņ]+")
        || personCode == null || !personCode.matches("\\d{5,6}-\\d{5}")
        || address == null || address.getCity() == City.notSet
        || phone == null || phone.matches("2\\d{7}"))
            throw new Exception("Invalid input parameters for customer");
        CustomerAsPerson temp = new CustomerAsPerson(name, surname, personCode, address, phone);
        AllCustomers.add(temp);
    }
    public static void addCustomerAsCompany(City city, String streetOrHouseTitle, int houseNo, String phone, String title, String companyRegNo) throws Exception {
        if(city == City.notSet
        || streetOrHouseTitle == null || !streetOrHouseTitle.matches("[A-ZĒŪĪĻĶĢŠĀČŅ]{1}[a-zēūīļķģšāžčņ]+")
        || houseNo < 1
        || phone == null || !phone.matches("2\\d{7}")
        || title == null
        || companyRegNo == null || !companyRegNo.matches("\\d{11}"))
            throw new Exception("Invalid input parameters for customer");
        CustomerAsCompany temp = new CustomerAsCompany(new Address(city, streetOrHouseTitle, houseNo), phone, title, companyRegNo);
        AllCustomers.add(temp);
    }
    public static ArrayList<CustomerAsPerson> getAllIndividualCustomers(){
        ArrayList<CustomerAsPerson> temp = new ArrayList<CustomerAsPerson>();
        for(AbstractCustomer c : AllCustomers){
            if(c instanceof CustomerAsPerson)
                temp.add((CustomerAsPerson)c);
        }
        return temp;
    }
    public static ArrayList<CustomerAsCompany> getAllCompanyCustomers(){
        ArrayList<CustomerAsCompany> temp = new ArrayList<CustomerAsCompany>();
        for(AbstractCustomer c : AllCustomers){
            if(c instanceof CustomerAsCompany)
                temp.add((CustomerAsCompany)c);
        }
        return temp;
    }
    public static AbstractCustomer getCustomerByCustomerCode(String customerCode) throws Exception {
        if(customerCode != null) {
            for (AbstractCustomer c : AllCustomers) {
                if (c.getCustomerCode().equals(customerCode))
                    return c;
            }
        }
        throw new Exception("Customer code is invalid or customer does not exist");
    }
    public static void createNewParcelForCustomer(LocalDateTime PlannedDelivery, ParcelSize size, boolean isFragile, Driver driver, String customerCode) throws Exception {
        Parcel temp = new Parcel(PlannedDelivery, size, isFragile, driver);
        getCustomerByCustomerCode(customerCode).addNewParcel(temp);
    }
    public static ArrayList<Parcel> getAllParcelsByCustomerCode(String customerCode) throws Exception {
        return getCustomerByCustomerCode(customerCode).getParcels();
    }
    public static ArrayList<Parcel>  getAllParcelsByDriverPersonCode(String personCode) throws Exception {
        if(personCode == null || !personCode.matches("\\d{5,6}-\\d{5}")) throw new Exception("Person code is invalid");
        ArrayList<Parcel> parcels = new ArrayList<Parcel>();

        for(AbstractCustomer c : AllCustomers){
            for(Parcel p : c.getParcels()){
                if(p.getDriver().getPersonCode().equals(personCode))
                    parcels.add(p);
            }
        }
        return parcels;
    }
    public static ArrayList<Parcel> getAllParcelsByCity(City city) throws Exception {
        if(city == City.notSet || city == null) throw new Exception("City is invalid or not set");
        ArrayList<Parcel> parcels = new ArrayList<Parcel>();
        for(AbstractCustomer c : AllCustomers){
            if(c.getAddress().getCity().equals(city))
                parcels.addAll(c.getParcels());
        }
        return parcels;
    }
    public static ArrayList<Parcel> getAllParcelsBySize(ParcelSize size) throws Exception {
        if(size == null) throw new Exception("Parcel size is invalid");
        ArrayList<Parcel> parcels = new ArrayList<Parcel>();
        for(AbstractCustomer c : AllCustomers){
            for(Parcel p : c.getParcels()){
                if(p.getSize().equals(size))
                    parcels.add(p);
            }
        }
        return parcels;
    }
    public static float calculatePriceOfAllCustomerParcelsByCustomerCode(String customerCode) throws Exception {
        if(customerCode == null) throw new Exception("Customer code is invalid");
        float sum = 0;
        for(Parcel p : getAllParcelsByCustomerCode(customerCode)){
            sum += p.getPrice();
        }
        return sum;
    }
    public static int[] getStatisticsOfCustomerParcelSizesByCustomerCode(String customerCode) throws Exception {
        if(customerCode == null) throw new Exception("Customer code is invalid");
        int[] sizes = new int[]{0, 0, 0, 0, 0}; // X, S, M, L, XL
        for(Parcel p : getAllParcelsByCustomerCode(customerCode)){
            switch(p.getSize()){
                case X:
                    sizes[0]++;
                    break;
                case S:
                    sizes[1]++;
                    break;
                case M:
                    sizes[2]++;
                    break;
                case L:
                    sizes[3]++;
                    break;
                case XL:
                    sizes[4]++;
                    break;
            }
        }
        return sizes;
    }
    public static void sortDriversByExperience() throws Exception {
        for (int i = 0; i < allDrivers.size(); i++) {
            for (int j = 0; j < allDrivers.size(); j++) {
                if (allDrivers.get(i).getExperienceInYears() < allDrivers.get(j).getExperienceInYears()) {
                    Driver temp = allDrivers.get(i);
                    allDrivers.set(i, allDrivers.get(j));
                    allDrivers.set(i, temp);
                }
            }
        }
    }
    public static int calculateHowManyParcelsTodayDeliveredToSpecificCity(City city) throws Exception {
        if(city == City.notSet || city == null) throw new Exception("City is invalid or not set");
        int count = 0;
        for(Parcel p : getAllParcelsByCity(city)){
            if(p.getPlannedDelivery().isBefore(LocalDateTime.now()))
                count++;
        }
        return count;
    }
    public static void generateCustomerAsPersonAndParcel(){
        Driver driverTest = new Driver("Janis", "Berzins", "123456-12345", "AF12122", 2);
        String[] names = new String[]{"Janis", "Peteris", "Andris", "Juris", "Guntis", "Aigars", "Kaspars", "Valdis", "Voldemars", "Vilnis"};
        String[] surnames = new String[]{"Berzins", "Ozolins", "Liepins", "Kalnins", "Sprogis", "Krumins", "Vitols", "Vilks", "Kakis", "Kakis"};
        String[] personCodes = new String[]{"123456-12345", "123456-12346", "123456-12347", "123456-12348", "123456-12349", "123456-12350", "123456-12351", "123456-12352", "123456-12353", "123456-12354"};
        String[] phone = new String[]{"22123456", "22123457", "22123458", "22123459", "22123460", "22123461", "22123462", "22123463", "22123464", "22123465"};
        City[] cities = new City[]{City.Jelgava, City.Ventspils, City.Liepaja, City.Daugavpils, City.Jelgava, City.Ventspils, City.Liepaja, City.Daugavpils, City.Jelgava, City.Ventspils, };
        String[] streetOrHouseTitle = new String[]{"Brivibas", "Rigas", "Talsu", "Kurzemes", "Brivibas", "Rigas", "Talsu", "Kurzemes", "Brivibas", "Rigas"};
        ParcelSize[] sizes = new ParcelSize[]{ParcelSize.X, ParcelSize.S, ParcelSize.M, ParcelSize.L, ParcelSize.XL,ParcelSize.X, ParcelSize.S, ParcelSize.M, ParcelSize.L, ParcelSize.XL};
        boolean[] isFragile = new boolean[]{true, false, true, false, true, false, true, false, true, false};

        // generate 4 random int
        int[] randoms = new int[7];
        for(int i = 0; i < randoms.length; i++){
            randoms[i] = (int)(Math.random() * 10);
        }
            try {
                CustomerAsPerson customer = new CustomerAsPerson(names[randoms[0]], surnames[randoms[1]], personCodes[randoms[2]], new Address(cities[randoms[4]], streetOrHouseTitle[randoms[5]], randoms[6]), phone[randoms[3]]);
                AllCustomers.add(customer);
                customer.addNewParcel(new Parcel(LocalDateTime.now().plusDays(randoms[3]), sizes[randoms[0]], isFragile[randoms[1]], driverTest));
            } catch (Exception e) {
                e.printStackTrace();
            }
            // ahj vso galva jau saopp
    }

}
