package model;

import java.time.LocalDateTime;

public class Parcel {
    private LocalDateTime orderCreated;
    private LocalDateTime plannedDelivery;
    private ParcelSize size;
    private float price;
    private boolean isFragile;
    private Driver driver;
    public Parcel(){
        setOrderCreated();
        setPlannedDelivery(LocalDateTime.now().plusDays(1));
        setSize(ParcelSize.S);
        setPrice();
        setFragile(false);
        setDriver(new Driver());
    }
    public Parcel(ParcelSize size, boolean isFragile, Driver driver){
        setOrderCreated();
        setPlannedDelivery(LocalDateTime.now().plusDays(1));
        setSize(size);
        setPrice();
        setFragile(isFragile);
        setDriver(driver);
    }

    public LocalDateTime getOrderCreated() {
        return orderCreated;
    }

    public void setOrderCreated() {
        this.orderCreated = LocalDateTime.now();
    }

    public LocalDateTime getPlannedDelivery() {
        return plannedDelivery;
    }
    // TODO  make a check for minimum delivery time 1 day
    public void setPlannedDelivery(LocalDateTime plannedDelivery) {
        if(plannedDelivery.isAfter(LocalDateTime.now()))
            this.plannedDelivery = plannedDelivery;
        else
            this.plannedDelivery = LocalDateTime.now();
    }

    public ParcelSize getSize() {
        return size;
    }

    public void setSize(ParcelSize size) {
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice() {
        this.price = (float) ((size.getValue() * 1.99) + (isFragile ? 2.99 : 0));
    }

    public boolean isFragile() {
        return isFragile;
    }

    public void setFragile(boolean fragile) {
        isFragile = fragile;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "orderCreated=" + orderCreated +
                ", plannedDelivery=" + plannedDelivery +
                ", size=" + size +
                ", price=" + price +
                ", isFragile=" + isFragile +
                ", driver=" + driver +
                '}';
    }
}
