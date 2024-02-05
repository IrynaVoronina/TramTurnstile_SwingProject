package org.example.model.services;

import org.example.model.entities.cards.Card;
import org.example.audit.Audit;
import org.example.model.entities.cards.CumulativeCard;
import org.example.model.entities.cards.TransitPass;
import org.example.model.enums.AmountOfRides;
import org.example.model.enums.TypeOfCard;
import org.example.model.enums.ValidityPeriod;
import org.example.utilities.CardStorageSystem;

import java.io.IOException;
import java.text.DecimalFormat;

public class CardService {

    private final CardStorageSystem cardStorageSystem = new CardStorageSystem();

    public Card getCardByIdInTheSystemFile(int id) throws IOException, ClassNotFoundException {
        Card card = cardStorageSystem.searchCardByIdInTheSystemFile(id);
        Audit.isPresentInTheSystemFile(card);
        return card;
    }

    public void createTransitPass(int id, TypeOfCard type, ValidityPeriod period, AmountOfRides amount) throws IOException, ClassNotFoundException, IllegalArgumentException, NoSuchFieldException {
        Audit.isAbsentInTheSystemFile(cardStorageSystem.searchCardByIdInTheSystemFile(id));
        TransitPass newTransitPass = new TransitPass(id, type, period, amount);
        cardStorageSystem.writeToTheSystemFile(newTransitPass);
    }

    public void createCumulativeCard(int id, double money) throws IOException, ClassNotFoundException, IllegalArgumentException {
        Audit.isAbsentInTheSystemFile(cardStorageSystem.searchCardByIdInTheSystemFile(id));
        CumulativeCard newCumulativeCard = new CumulativeCard(id, money);
        cardStorageSystem.writeToTheSystemFile(newCumulativeCard);
    }

    public TransitPass useTransitPass(int id) throws IllegalArgumentException, IOException {
        Card card = cardStorageSystem.searchCardByIdInTheSystemFile(id);
        TransitPass updatedTS = null;
        if (card instanceof TransitPass transitPass) {
            Audit.checkValidityPeriod(transitPass);
            int previousAoR = transitPass.getCurrentAmountOfRides();
            int newAoR = previousAoR - 1;
            Audit.checkAmountOfRides(newAoR);
            transitPass.setCurrentAmountOfRides(newAoR);
            updatedTS = transitPass;
        }
        assert updatedTS != null;
        cardStorageSystem.updateInTheSystemFile(updatedTS);
        return updatedTS;
    }

    public CumulativeCard useCumulativeCard(int id) throws IOException, ClassNotFoundException {
        Card card = cardStorageSystem.searchCardByIdInTheSystemFile(id);
        CumulativeCard updatedCC = null;
        if (card instanceof CumulativeCard cumulativeCard) {
            double money = cumulativeCard.getMoney();
            double newMoney = money - cumulativeCard.getFare();
            Audit.checkMoney(newMoney);
            double roundedMoney = Double.parseDouble(new DecimalFormat("#.##").format(newMoney));
            cumulativeCard.setMoney(roundedMoney);
            updatedCC = cumulativeCard;
        }
        assert updatedCC != null;
        cardStorageSystem.updateInTheSystemFile(updatedCC);
        return updatedCC;
    }
}
