package org.example.utilities;

import com.google.gson.*;
import org.example.model.entities.cards.Card;
import org.example.model.entities.cards.CumulativeCard;
import org.example.model.entities.cards.TransitPass;
import org.example.model.enums.CardClassification;
import org.example.model.enums.TypeOfCard;
import org.example.model.enums.ValidityPeriod;

import java.lang.reflect.Type;


public class CardAdapter implements JsonDeserializer<Card>, JsonSerializer<Card> {

    @Override
    public JsonElement serialize(Card src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("classification", src.getClassification().name());
        jsonObject.addProperty("type", src.getType().name());

        if (src instanceof TransitPass) {
            jsonObject.add("validityPeriod", context.serialize(((TransitPass) src).getValidityPeriod()));
            jsonObject.addProperty("currentAmountOfRides", ((TransitPass) src).getCurrentAmountOfRides());
        } else if (src instanceof CumulativeCard) {
            jsonObject.addProperty("money", ((CumulativeCard) src).getMoney());
        }

        jsonObject.add("data", context.serialize(src));
        return jsonObject;
    }

    @Override
    public Card deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String classification = jsonObject.get("classification").getAsString();
        String type = jsonObject.get("type").getAsString();
        //JsonElement data = jsonObject.get("data");

        try {
            Card card;
            CardClassification cardClassification = CardClassification.valueOf(classification);
            TypeOfCard cardType = TypeOfCard.valueOf(type);

            if (cardClassification == CardClassification.TransitPass) {
                card = new TransitPass();
                ((TransitPass) card).setValidityPeriod(context.deserialize(jsonObject.get("validityPeriod"), ValidityPeriod.class));
                ((TransitPass) card).setCurrentAmountOfRides(jsonObject.get("currentAmountOfRides").getAsInt());
            } else if (cardClassification == CardClassification.CumulativeCard) {
                card = new CumulativeCard();
                ((CumulativeCard) card).setMoney(jsonObject.get("money").getAsDouble());
            } else {
                throw new JsonParseException("Unsupported Card Classification: " + classification);
            }

            card.setClassification(cardClassification);
            card.setType(cardType);

            return card;
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }
}

