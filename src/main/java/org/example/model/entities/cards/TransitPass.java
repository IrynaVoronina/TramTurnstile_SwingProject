package org.example.model.entities.cards;

import java.time.LocalDate;

import org.example.model.enums.AmountOfRides;
import org.example.model.enums.CardClassification;
import org.example.model.enums.ValidityPeriod;
import org.example.model.enums.TypeOfCard;

public class TransitPass extends Card {

    private ValidityPeriod validityPeriod;
    private AmountOfRides amountOfRides;
    private int currentAmountOfRides;
    private LocalDate startDate;
    private transient LocalDate endDate;

    public TransitPass() {
        super();
    }

    public TransitPass(int id, TypeOfCard type, ValidityPeriod period, AmountOfRides amount) {
        super(id, CardClassification.TransitPass, type);
        this.validityPeriod = period;
        this.amountOfRides = amount;
        this.currentAmountOfRides = amount.getAmount();
        this.startDate = LocalDate.now();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public AmountOfRides getAmountOfRides() {
        return amountOfRides;
    }

    public ValidityPeriod getValidityPeriod() {
        return validityPeriod;
    }


    public int getCurrentAmountOfRides() {
        return currentAmountOfRides;
    }

    public void setCurrentAmountOfRides(int currentAmountOfRides) {
        this.currentAmountOfRides = currentAmountOfRides;
    }

    public void setValidityPeriod(ValidityPeriod validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public void setAmountOfRides(AmountOfRides amountOfRides) {
        this.amountOfRides = amountOfRides;
    }


    public LocalDate getEndDate() {
        switch (validityPeriod) {
            case DAY:
                endDate = startDate.plusDays(1);
                break;
            case WEEK:
                endDate = startDate.plusWeeks(1);
                break;
            case MONTH:
                endDate = startDate.plusMonths(1);
                break;
        }
        return endDate;
    }

    @Override
    public String toString() {
        return "TransitPass{" + super.toString() +
                "validityPeriod=" + validityPeriod +
                ", amountOfRides=" + amountOfRides +
                ", currentAmountOfRides=" + currentAmountOfRides +
                ", startDate=" + startDate +
                '}';
    }
}