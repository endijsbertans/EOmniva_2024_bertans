package model;

public class Address {
    private City city;
    private String streetOrHouseTitle;
    private int houseNo;

    Address(){
        setCity(City.notSet);
        setStreetOrHouseTitle(null);
        setHouseNo(-1);
    }
    Address(City city, String streetOrHouseTitle, int houseNo){
        setCity(city);
        setStreetOrHouseTitle(streetOrHouseTitle);
        setHouseNo(houseNo);
    }
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStreetOrHouseTitle() {
        return streetOrHouseTitle;
    }

    public void setStreetOrHouseTitle(String streetOrHouseTitle) {
        if(streetOrHouseTitle != null &&
                streetOrHouseTitle.matches("[A-ZĒŪĪĻĶĢŠĀČŅ]{1}[a-zēūīļķģšāžčņ]"))
            this.streetOrHouseTitle = streetOrHouseTitle;
        else
            this.streetOrHouseTitle = "---";
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        if(houseNo > 0)
            this.houseNo = houseNo;
        else
            this.houseNo = -1;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city=" + city +
                ", streetOrHouseTitle='" + streetOrHouseTitle + '\'' +
                ", houseNo=" + houseNo +
                '}';
    }
}
