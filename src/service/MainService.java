package service;

import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

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

        System.out.println("-------------------------------");

            // TESTS OF FUNCTIONS
            try {



                addDriver("John", "Doe", "123456-12345", "AF12122", 2);
                System.out.println("added driver searched with GETDRIVERBYPERSONCODE: " + getDriverByPersonCode("123456-12345"));

                editLicenseNobyPersonCode("123456-12345", "AF12123");
                System.out.println("edited license 123456-12345, AF12123" + getDriverByPersonCode("123456-12345"));

                editExperienceInYearsByPersonCode("123456-12345", 3);
                System.out.println("editExperienceInYearsByPersonCode from 2 to 3 got: " + getDriverByPersonCode("123456-12345").getExperienceInYears());

                AllCustomers.add(generateCustomerAsPersonAndParcel());
                System.out.println("addCustomerAsPerson: " + AllCustomers.getLast());

                AllCustomers.add(generateCustomerAsCompanyAndParcel());
                System.out.println("addCustomerAsCompany: " + AllCustomers.getLast());

                AbstractCustomer customer = getCustomerByCustomerCode("5_person_12345678");
                System.out.println("getCustomerByCustomerCode, got: " + customer);

                ArrayList<Parcel> parcels = getAllParcelsByCustomerCode("5_person_12345678");
                System.out.println("getAllParcelsByCustomerCode, got: " + parcels.size() + " parcels");

                float price = calculatePriceOfAllCustomerParcelsByCustomerCode("5_person_12345678");
                System.out.println("calculatePriceOfAllCustomerParcelsByCustomerCode, total price: " + price);

                int[] sizes = getStatisticsOfCustomerParcelSizesByCustomerCode("5_person_12345678");
                System.out.println("getStatisticsOfCustomerParcelSizesByCustomerCode, sizes: " + Arrays.toString(sizes));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    // start of driver CRUD
    public static void addDriver(String name, String surname, String personCode, String licenseNo, float experienceInYears) throws Exception {
        if(name == null || !name.matches("[A-ZĒŪĪĻĶĢŠĀČŅ][a-zēūīļķģšāžčņ]+")
        || surname == null || !surname.matches("[A-ZĒŪĪĻĶĢŠĀČŅ][a-zēūīļķģšāžčņ]+")
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

        if(name == null || !name.matches("[A-ZĒŪĪĻĶĢŠĀČŅ][a-zēūīļķģšāžčņ]+")
        || surname == null || !surname.matches("[A-ZĒŪĪĻĶĢŠĀČŅ][a-zēūīļķģšāžčņ]+")
        || personCode == null || !personCode.matches("\\d{5,6}-\\d{5}")
        || address == null || address.getCity() == City.notSet
        || phone == null || !phone.matches("2\\d{7}"))
            throw new Exception("Invalid input parameters for customer");
        CustomerAsPerson temp = new CustomerAsPerson(name, surname, personCode, address, phone);
        AllCustomers.add(temp);
    }
    public static void addCustomerAsCompany(City city, String streetOrHouseTitle, int houseNo, String phone, String title, String companyRegNo) throws Exception {
        if(city == City.notSet
        || streetOrHouseTitle == null || streetOrHouseTitle.length() > 50
        || houseNo < 0
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
    public static AbstractCustomer generateCustomerAsPersonAndParcel() throws Exception {
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
                customer.addNewParcel(new Parcel(LocalDateTime.now().plusDays(randoms[3]), sizes[randoms[0]], isFragile[randoms[1]], driverTest));
                return customer;
            } catch (Exception e) {
                e.printStackTrace();
            }
            throw new Exception("Could not generate customer");
    }
    public static AbstractCustomer generateCustomerAsCompanyAndParcel() throws Exception {
        Driver driverTest = new Driver("Janis", "Berzins", "123456-12345", "AF12122", 2);
        String[] titles = new String[]{"SIA Labais", "SIA Labais2", "SIA Labais3", "SIA Labais4", "SIA Labais5", "SIA Labais6", "SIA Labais7", "SIA Labais8", "SIA Labais9", "SIA Labais10"};
        String[] companyRegNo = new String[]{"321456-21656", "321456-21657", "321456-21658", "321456-21659", "321456-21660", "321456-21661", "321456-21662", "321456-21663", "321456-21664", "321456-21665"};
        String[] phone = new String[]{"29666117", "29666118", "29666119", "29666120", "29666121", "29666122", "29666123", "29666124", "29666125", "29666126"};
        City[] cities = new City[]{City.Jelgava, City.Ventspils, City.Liepaja, City.Daugavpils, City.Jelgava, City.Ventspils, City.Liepaja, City.Daugavpils, City.Jelgava, City.Ventspils, };
        String[] streetOrHouseTitle = new String[]{"Brivibas", "Rigas", "Talsu", "Kurzemes", "Brivibas", "Rigas", "Talsu", "Kurzemes", "Brivibas", "Rigas"};
        ParcelSize[] sizes = new ParcelSize[]{ParcelSize.X, ParcelSize.S, ParcelSize.M, ParcelSize.L, ParcelSize.XL,ParcelSize.X, ParcelSize.S, ParcelSize.M, ParcelSize.L, ParcelSize.XL};
        boolean[] isFragile = new boolean[]{true, false, true, false, true, false, true, false, true, false};
        int[] randoms = new int[7];
        for(int i = 0; i < randoms.length; i++){
            randoms[i] = (int)(Math.random() * 10);
        }
        try {
            CustomerAsCompany customer = new CustomerAsCompany(new Address(cities[randoms[4]], streetOrHouseTitle[randoms[5]], randoms[6]), phone[randoms[3]], titles[randoms[0]], companyRegNo[randoms[2]]);
            customer.addNewParcel(new Parcel(LocalDateTime.now().plusDays(randoms[3]), sizes[randoms[0]], isFragile[randoms[1]], driverTest));
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
        }
       throw new Exception("Could not generate Business customer");
    }
}
