package model;

public enum ParcelSize {
    X(1), S(2), M(3), L(4), XL(5);

    private final int value;

    ParcelSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
