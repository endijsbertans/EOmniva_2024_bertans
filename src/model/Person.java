package model;

public class Person {
    private String name;
    private String surname;
    private String personCode;
    public Person(){
        setName(null);
        setSurname(null);
        setPersonCode(null);
    }
    public Person(String name, String surname, String personCode){
        setName(name);
        setSurname(surname);
        setPersonCode(personCode);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null &&
                name.matches("[A-ZĒŪĪĻĶĢŠĀČŅ]{1}[a-zēūīļķģšāžčņ]"))
            this.name = name;
        else
            this.name = "----";
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(surname != null &&
                surname.matches("[A-ZĒŪĪĻĶĢŠĀČŅ]{1}[a-zēūīļķģšāžčņ]"))
            this.surname = surname;
        else
            this.surname = "----";
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        if(personCode != null &&
                personCode.matches("\\d{5,6}-\\d{5}")) //210202-21656
            this.personCode = personCode;
        else
            this.personCode = "XXXXXX-XXXXXX";
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", personCode='" + personCode + '\'' +
                '}';
    }
}
