package org.example.audit;

import org.example.model.entities.cards.Card;
import org.example.model.entities.cards.TransitPass;

import java.time.LocalDate;

public class Audit {

    public static void isPresentInTheSystemFile(Card card) throws IllegalArgumentException {
        if (card == null) {
            throw new IllegalArgumentException("!! There is NO card with this ID !!");
        }
    }

    public static void isAbsentInTheSystemFile(Card card) throws IllegalArgumentException {
        if (card != null) {
            throw new IllegalArgumentException("!!! There is a card with this ID !!!");
        }
    }

    public static void checkAmountOfRides(int amountOfRides) throws IllegalArgumentException {
        if (amountOfRides < 0) {
            throw new IllegalArgumentException("!! INVALID Amount Of Rides !!");
        }
    }

    public static void checkMoney(double money) throws IllegalArgumentException {
        if (money < 0) {
            throw new IllegalArgumentException("!! NOT enough money !!");
        }
    }

    public static void checkValidityPeriod(TransitPass transitPass) throws IllegalArgumentException {
        LocalDate endDate = transitPass.getEndDate();
        LocalDate now = LocalDate.now();

        if (endDate.isBefore(now)) {
            throw new IllegalArgumentException("!! Transit pass has expired !!");
        }
    }
}
