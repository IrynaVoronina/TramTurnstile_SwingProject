package org.example.controller;


import org.example.model.services.CardService;
import org.example.model.entities.cards.Card;
import org.example.model.entities.cards.CumulativeCard;
import org.example.model.entities.cards.TransitPass;
import org.example.model.enums.AmountOfRides;
import org.example.model.enums.Status;
import org.example.model.enums.TypeOfCard;
import org.example.model.enums.ValidityPeriod;
import org.example.model.services.ReportService;
import org.example.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TurnstileController {

    private final CardService cardModel;
    private final ReportService reportModel;
    private final View view;

    public TurnstileController(CardService cardModel, ReportService reportModel, View view) {
        this.cardModel = cardModel;
        this.reportModel = reportModel;
        this.view = view;

        this.view.addCheckButtonListener(new CheckButtonListener());
        this.view.addUseTSButtonListener(new UseTSButtonListener());
        this.view.addUseCCButtonListener(new UseCCButtonListener());
        this.view.addCreateTSButtonListener(new CreateTSButtonListener());
        this.view.addCreateCCButtonListener(new CreateCCButtonListener());
    }

    class UseTSButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int idFromView = view.getIdTS();
            TransitPass updatedTS;
            try {
                updatedTS = cardModel.useTransitPass(idFromView);
                int currentAmountOfRides = updatedTS.getCurrentAmountOfRides();
                view.setCurrentAoR(currentAmountOfRides);
                view.showTransitPassPanel();

                String description = String.format("The transitPass with ID %d was used to pay the fare", idFromView);
                reportModel.createReport(Status.SUCCESS, description);

            } catch (IOException | IllegalArgumentException exception) {
                try {
                    reportModel.createReport(Status.FAILURE, String.valueOf(exception.getMessage()));
                    view.displayErrorMessage(String.valueOf(exception.getMessage()));
                } catch (IOException ex) {
                    view.displayErrorMessage("Report ERROR: " + ex.getMessage());
                }
            }
        }
    }

    class UseCCButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int idFromView = view.getIdCC();
            CumulativeCard updatedCC;
            try {
                updatedCC = cardModel.useCumulativeCard(idFromView);

                double money = updatedCC.getMoney();

                view.setIdCC(idFromView);
                view.setMoney(money);

                view.showCumulativeCardPanel();

                String description = String.format("The cumulativeCard with ID %d was used to pay the fare", idFromView);
                reportModel.createReport(Status.SUCCESS, description);

            } catch (IOException | ClassNotFoundException | IllegalArgumentException exception) {
                try {
                    reportModel.createReport(Status.FAILURE, String.valueOf(exception.getMessage()));
                    view.displayErrorMessage(String.valueOf(exception.getMessage()));
                } catch (IOException ex) {
                    view.displayErrorMessage("Report ERROR: " + ex.getMessage());
                }
            }
        }
    }

    class CheckButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int idFromView = view.getIdFromMain();

            try {

                Card card = cardModel.getCardByIdInTheSystemFile(idFromView);
                TypeOfCard type = card.getType();

                if (card instanceof TransitPass transitPass) {
                    AmountOfRides amountOfRides = transitPass.getAmountOfRides();
                    ValidityPeriod validityPeriod = transitPass.getValidityPeriod();
                    int currentAmountOfRides = transitPass.getCurrentAmountOfRides();

                    view.setIdTS(idFromView);
                    view.getTypesComboBox().setSelectedItem(type);
                    view.getRidesComboBox().setSelectedItem(amountOfRides);
                    view.getPeriodsComboBox().setSelectedItem(validityPeriod);
                    view.setCurrentAoR(currentAmountOfRides);

                    view.showTransitPassPanel();

                } else if (card instanceof CumulativeCard cumulativeCard) {

                    //double fare = view.getFare();

                    double money = cumulativeCard.getMoney();

                    view.setIdCC(idFromView);
                    view.setMoney(money);

                    view.showCumulativeCardPanel();
                }

            } catch (IllegalArgumentException illegalArgumentException) {
                view.displayErrorMessage(String.valueOf(illegalArgumentException.getMessage()));
            } catch (IOException | ClassNotFoundException exception) {
                view.displayErrorMessage("IOException: " + exception.getMessage());
            }
        }
    }


    class CreateTSButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int idTS = view.getIdTS();
            TypeOfCard type = (TypeOfCard) view.getTypesComboBox().getSelectedItem();
            AmountOfRides amount = (AmountOfRides) view.getRidesComboBox().getSelectedItem();
            ValidityPeriod period = (ValidityPeriod) view.getPeriodsComboBox().getSelectedItem();

            try {
                cardModel.createTransitPass(idTS, type, period, amount);
                reportModel.createReport(Status.SUCCESS, "TransitPass was created !!! ID: " + idTS);
            } catch (IOException | ClassNotFoundException | IllegalArgumentException |
                     NoSuchFieldException illegalArgumentException) {
                view.displayErrorMessage(String.valueOf(illegalArgumentException.getMessage()));
            }
        }
    }

    class CreateCCButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int idCC = view.getIdCC();
            double money = view.getMoney();

            try {
                cardModel.createCumulativeCard(idCC, money);
                reportModel.createReport(Status.SUCCESS, "CumulativeCard was created !!! ID: " + idCC);
            } catch (IOException | ClassNotFoundException ignored) {
            } catch (IllegalArgumentException illegalArgumentException) {
                view.displayErrorMessage(String.valueOf(illegalArgumentException.getMessage()));
            }
        }
    }
}