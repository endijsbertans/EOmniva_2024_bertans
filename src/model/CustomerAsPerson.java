package model;

public class CustomerAsPerson extends AbstractCustomerAsPerson {
    CustomerAsPerson(){
        super();
    }
    CustomerAsPerson(String name, String surname, String personCode, Address address, String phone){
        super();
    }
    @Override
    public void setCustomerCode(String customerCode) {
        setCustomerCode(getcID() + "_person_"+ person.getPersonCode());
    }

    @Override
    public String toString() {
        return  super.toString() + " Customer code " +
                getCustomerCode();
    }
}
