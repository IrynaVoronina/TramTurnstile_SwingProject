package org.example.model.entities.cards;

import org.example.model.enums.CardClassification;
import org.example.model.enums.TypeOfCard;


public abstract class Card {
    private int id;

    private CardClassification classification;
    private TypeOfCard type;

    public Card() {
    }

    protected Card(int id, CardClassification classification, TypeOfCard type) {
        this.id = id;
        this.classification = classification;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public CardClassification getClassification() {
        return classification;
    }

    public void setClassification(CardClassification classification) {
        this.classification = classification;
    }

    public TypeOfCard getType() {
        return type;
    }

    public void setType(TypeOfCard type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", classification=" + classification +
                ", type=" + type +
                '}';
    }
}
