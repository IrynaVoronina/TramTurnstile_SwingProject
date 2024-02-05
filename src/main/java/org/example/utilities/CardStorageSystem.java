package org.example.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.entities.cards.Card;
import org.example.model.entities.cards.CumulativeCard;
import org.example.model.entities.cards.TransitPass;
import org.example.model.enums.CardClassification;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class CardStorageSystem {

    private Class<? extends Card> getCardClass(CardClassification classifiedStorage) {
        return switch (classifiedStorage) {
            case TransitPass -> TransitPass.class;
            case CumulativeCard -> CumulativeCard.class;
        };
    }

    /*
    private Class<? extends Card> getCardClassFromJson(JsonReader jsonReader) {
        Class<? extends Card> cardClass = null;
        try {
            jsonReader.beginObject();

            String classification = null;

            while (jsonReader.hasNext()) {
                String name = jsonReader.nextName();
                if ("classification".equals(name)) {
                    classification = jsonReader.nextString();
                    break;
                } else {
                    jsonReader.skipValue();
                }
            }

            if (classification != null) {
                cardClass = switch (classification) {
                    case "TransitPass" -> TransitPass.class;
                    case "CumulativeCard" -> CumulativeCard.class;
                    default -> null;
                };
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cardClass;
    }
 */

    public Card searchCardByIdInTheSystemFile(int id) throws IOException {
        Card res = null;
        for (CardClassification classifiedStorage : CardClassification.values()) {
            String fileName = classifiedStorage.getFileName();
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                Gson gson = new Gson();
                String jsonObjLine;
                while ((jsonObjLine = reader.readLine()) != null) {
                    Card card = gson.fromJson(jsonObjLine, getCardClass(classifiedStorage));
                    if (card.getId() == id) {
                        res = card;
                        break;
                    }
                }
            }
        }
        return res;

/*  через JsonReader
        try (JsonReader jsonReader = new JsonReader(new FileReader(fileName))) {
            Gson gson = new Gson();
            jsonReader.setLenient(true);

            while (jsonReader.hasNext()) {

                Card card = gson.fromJson(jsonReader, TransitPass.class);
                if (card.getId() == id) {
                    System.out.println("search: " + card);
                    res = card;
                    break;
                }
            }
        }
 */

    }

    public void writeToTheSystemFile(Card card) throws IOException {
        String fileName = card.getClassification().getFileName();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Card.class, new CardAdapter())
                    //.setPrettyPrinting()
                    .create();
            gson.toJson(card, writer);
            writer.newLine();
        }
    }

    public void updateInTheSystemFile(Card updatedCard) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Card.class, new CardAdapter())
                //.setPrettyPrinting()
                .create();

        String fileName = updatedCard.getClassification().getFileName();
        String tempFileName = fileName + ".temp";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName))) {

            String jsonObjLine;
            while ((jsonObjLine = reader.readLine()) != null) {
                Card card = gson.fromJson(jsonObjLine, getCardClass(updatedCard.getClassification()));
                if (card.getId() == updatedCard.getId()) {
                    jsonObjLine = gson.toJson(updatedCard);
                }
                writer.write(jsonObjLine);
                writer.newLine();
            }
        } catch (IOException ignored) {
        }

        try {
            Files.move(Path.of(tempFileName), Path.of(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
