package model;

import javax.xml.transform.stream.StreamSource;
import java.util.ArrayList;

public  abstract class AbstractCustomer{
    private long cID;
    private Address address;
    private String phoneNo;
    private String customerCode;
    private ArrayList<Parcel> parcels = new ArrayList<Parcel>();
    private static long counter = 0;
    AbstractCustomer(){
        setcID();
        setAddress(null);
        setPhoneNo(null);
    }
    AbstractCustomer(Address address, String phoneNo){
        setcID();
        setAddress(address);
        setPhoneNo(phoneNo);
    }

    public long getcID() {
        return cID;
    }

    public void setcID() {
        this.cID = counter++;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        if(phoneNo != null &&
                phoneNo.matches("2\\d{7}"))
            this.phoneNo = phoneNo;
        else
            this.phoneNo = "2XXXXXXX";
    }
    public ArrayList<Parcel> getParcels() {
        return parcels;
    }

    public void setParcels(ArrayList<Parcel> parcels) {
        this.parcels = parcels;
    }
    public abstract void setCustomerCode(String customerCode);
    public String getCustomerCode(){
        return customerCode;
    }
    public void addNewParcel(Parcel parcel){
        if(!parcels.contains(parcel))
            parcels.add(parcel);
        System.out.println("Parcell already added!");
    }

    @Override
    public String toString() {
        return "AbstractCustomer{" +
                "cID=" + cID +
                ", address=" + address +
                ", phoneNo='" + phoneNo + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", parcels=" + parcels +
                '}';
    }
}
