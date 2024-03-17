package model;
import model.Person;
public abstract class AbstractCustomerAsPerson extends AbstractCustomer {
    protected Person person;
    AbstractCustomerAsPerson(){
        super();
        new Person();
    }
    AbstractCustomerAsPerson(String name, String surname, String personCode, Address address, String phoneNO){
        super();
        new Person(name, surname, personCode);
    }

    @Override
    public String toString() {
        return   super.toString() +
                "AbstractCustomerAsPerson{" +
                "person=" + person +
                '}';
    }
}
