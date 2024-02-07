package org.example.model.entities.cards;

import org.example.model.enums.CardClassification;
import org.example.model.enums.TypeOfCard;

public class CumulativeCard extends Card {

    private final transient double fare = 3;

    private double money;

    public CumulativeCard() {
    }

    public CumulativeCard(int id, double money) {
        super(id, CardClassification.CumulativeCard, TypeOfCard.ORDINARY);
        this.money = money;
    }

    public double getFare() {
        return fare;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


    @Override
    public String toString() {
        return "CumulativeCard{" + super.toString() +
                "money=" + money +
                '}';
    }
}
