package org.example;

import org.example.controller.TurnstileController;
import org.example.model.services.CardService;
import org.example.model.services.ReportService;
import org.example.view.View;

public class Main {
    public static void main(String[] args) {

        View swingView = new View();
        CardService cardModel = new CardService();
        ReportService reportModel = new ReportService();

        new TurnstileController(cardModel, reportModel, swingView);

    }
}