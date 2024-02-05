package org.example.model.enums;

public enum AmountOfRides {
    FIVE(5),
    TEN(10);

    private final int amount;

    private AmountOfRides(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
