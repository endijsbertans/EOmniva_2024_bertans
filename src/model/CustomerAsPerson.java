package model;

public class CustomerAsPerson extends AbstractCustomerAsPerson {
    public CustomerAsPerson(){
        super();
        setCustomerCode();
    }
    public CustomerAsPerson(String name, String surname, String personCode, Address address, String phone){
        super(name, surname, personCode, address, phone);
        setCustomerCode();
    }
    @Override
    public void setCustomerCode() {
        super.customerCode = getcID() + "_person_"+ person.getPersonCode();
    }

    @Override
    public String toString() {
        return  super.toString() + " Customer code " +
                getCustomerCode();
    }
}
