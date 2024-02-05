package org.example.view;

import org.example.model.enums.AmountOfRides;
import org.example.model.enums.TypeOfCard;
import org.example.model.enums.ValidityPeriod;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.example.model.enums.AmountOfRides.FIVE;
import static org.example.model.enums.AmountOfRides.TEN;
import static org.example.model.enums.TypeOfCard.*;
import static org.example.model.enums.ValidityPeriod.*;

public class View extends JFrame {

    CardLayout cardLayout = new CardLayout();
    JPanel cardPanel = new JPanel(cardLayout);

    // button menu
    private JButton showMainPanel = new JButton("Main");
    private JButton showTransitPassPanel = new JButton("TransitPass");
    private JButton showCumulativeCardPanel = new JButton("CumulativeCard");


    // for main
    private JLabel idLabelMain = new JLabel("Enter ID: ");
    private JTextField idInputMain = new JTextField(20);

    //private JLabel fareLabel = new JLabel("Fare = ");
    //private JTextField fareInput = new JTextField(10);

    private JButton checkButton = new JButton("CHECK");


    // for TransitPass
    private JLabel idLabelTS = new JLabel("ID: ");
    private JTextField idInputTS = new JTextField(10);

    private JLabel labelForTypeTS = new JLabel("Type:");
    TypeOfCard[] types = {ORDINARY,
            SCHOOL,
            STUDENT};

    JComboBox<TypeOfCard> typesComboBox = new JComboBox<>(types);

    private JLabel labelForPiriod = new JLabel("Validity period:");

    ValidityPeriod[] periods = {DAY,
            WEEK, MONTH};
    JComboBox<ValidityPeriod> periodsComboBox = new JComboBox<>(periods);

    private JLabel labelForRides = new JLabel("Amount of rides:");

    AmountOfRides[] rides = {FIVE, TEN};
    JComboBox<AmountOfRides> ridesComboBox = new JComboBox<>(rides);

    private JLabel currentAoRLabel = new JLabel("Current amount of rides: ");
    private JTextField currentAoR = new JTextField(5);

    private JButton useTSButton = new JButton("Use");
    private JButton createTSButton = new JButton("Create TransitPass");


    // for CumulativeCard
    private JLabel idLabelCC = new JLabel("ID: ");
    private JTextField idInputCC = new JTextField(10);
    private JLabel labelForTypeCC = new JLabel("Type:");
    TypeOfCard[] typeCC = {ORDINARY};
    JComboBox<TypeOfCard> typeCCComboBox = new JComboBox<>(typeCC);
    private JLabel labelForMoney = new JLabel("money: ");

    private JTextField moneyInputCC = new JTextField(10);

    private JButton useCCButton = new JButton("Use");
    private JButton createCCButton = new JButton("Create CumulativeCard");


    public View() {

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(460, 300);
        this.setLocationRelativeTo(null);

        // main панель
        JPanel mainPanel = new JPanel();
        mainPanel.add(idLabelMain);
        mainPanel.add(idInputMain);

        idLabelMain.setFont(new Font("Arial", Font.BOLD, 16));


        //mainPanel.add(fareLabel);
        //mainPanel.add(fareInput);


        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        mainPanel.add(checkButton);
        cardPanel.add(mainPanel, "mainPanel");


// Панель for TransitPass
        JPanel panelForTransitPass = createPanelForCard(
                idLabelTS, idInputTS,
                labelForTypeTS, typesComboBox,
                labelForPiriod, periodsComboBox,
                labelForRides, ridesComboBox,
                currentAoRLabel, currentAoR,
                useTSButton, createTSButton);

// Панель for CumulativeCard
        JPanel panelForCumulativeCard = createPanelForCard(
                idLabelCC, idInputCC,
                labelForTypeCC, typeCCComboBox,
                labelForMoney, moneyInputCC,
                useCCButton, createCCButton);

        panelForCumulativeCard.setBackground(Color.decode("#dcf2f5"));
        panelForCumulativeCard.setBorder(new EmptyBorder(10, 10, 10, 10));

        cardPanel.add(panelForTransitPass, "panelForTransitPass");
        cardPanel.add(panelForCumulativeCard, "panelForCumulativeCard");

        // Button панель
        JPanel barButtonPanel = new JPanel();
        barButtonPanel.add(showMainPanel);
        barButtonPanel.add(showTransitPassPanel);
        barButtonPanel.add(showCumulativeCardPanel);

        barButtonPanel.setBackground(Color.decode("#0a94a3"));
        barButtonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));


        showMainPanel.addActionListener(e -> cardLayout.show(cardPanel, "mainPanel"));
        showTransitPassPanel.addActionListener(e -> showTransitPassPanel());
        showCumulativeCardPanel.addActionListener(e -> showCumulativeCardPanel());

        this.add(cardPanel, BorderLayout.CENTER);
        this.add(barButtonPanel, BorderLayout.SOUTH);
    }

    private JPanel createPanelForCard(Component... components) {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(1, 5, 1, 5);

        for (int i = 0; i < components.length; i++) {
            panel.add(components[i], gbc);

            if (i % 2 != 0) {
                gbc.gridx = 0;
                gbc.gridy += 1;
            } else {
                gbc.gridx = 1;
            }
        }

        gbc.gridy++;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(15, 5, 1, 5);
        panel.add(components[components.length - 1], gbc);

        panel.setBackground(Color.decode("#dcf2f5"));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        return panel;
    }


    public int getIdFromMain() {
        return Integer.parseInt(idInputMain.getText());
    }

    public JComboBox<TypeOfCard> getTypesComboBox() {
        return typesComboBox;
    }

    public JComboBox<AmountOfRides> getRidesComboBox() {
        return ridesComboBox;
    }

    public JComboBox<ValidityPeriod> getPeriodsComboBox() {
        return periodsComboBox;
    }

    public int getIdTS() {
        return Integer.parseInt(idInputTS.getText());
    }

    public void setIdTS(int value) {
        idInputTS.setText(Integer.toString(value));
    }

    public int getIdCC() {
        return Integer.parseInt(idInputCC.getText());
    }

    public void setIdCC(int value) {
        idInputCC.setText(Integer.toString(value));
    }

    public double getMoney() {
        return Double.parseDouble(moneyInputCC.getText());
    }

    public void setMoney(double value) {
        moneyInputCC.setText(Double.toString(value));
    }


    public void setCurrentAoR(int value) {
        currentAoR.setText(Integer.toString(value));
    }
/*
    public double getFare() {
        return Double.parseDouble(fareInput.getText());
    }

 */

    public void showTransitPassPanel() {
        cardLayout.show(cardPanel, "panelForTransitPass");
    }

    public void showCumulativeCardPanel() {
        cardLayout.show(cardPanel, "panelForCumulativeCard");
    }

    public void addIdInputListener(DocumentListener documentListener) {
        idInputMain.getDocument().addDocumentListener(documentListener);
    }

    public void addCheckButtonListener(ActionListener checkButtonListener) {
        checkButton.addActionListener(checkButtonListener);
    }

    public void addUseTSButtonListener(ActionListener useTSButtonListener) {
        useTSButton.addActionListener(useTSButtonListener);
    }

    public void addCreateTSButtonListener(ActionListener createButtonListener) {
        createTSButton.addActionListener(createButtonListener);
    }

    public void addUseCCButtonListener(ActionListener useCCButtonListener) {
        useCCButton.addActionListener(useCCButtonListener);
    }

    public void addCreateCCButtonListener(ActionListener createButtonListener) {
        createCCButton.addActionListener(createButtonListener);
    }


    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
