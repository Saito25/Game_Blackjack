package card;

import card.Card;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Deck {

    // Atributos
    private Card[] cards = new Card[52];
    private int[] randomDraw = new int[52];
    private int index = 0;

    public Deck() {
        makeDeck();
        randomDraw();
    }

    public Card getRandomCard() {
        if(index > 51) {
            throw new RuntimeException("Se acabó el mazo");
        }
        return cards[randomDraw[index++]];
    }


    // Creación de la clase.
    private void makeDeck() {
        Arrays.stream(Type.values()).forEach(this::addCard);
    }

    private void addCard(Type type) {
        cards[index] = new Card(type, 1, "As");

        for(Integer i = 2; i <= 10; i++) {

            cards[++index] = new Card(type, i, i.toString());

        }

        Arrays.stream(Figure.values()).forEach(figure -> cards[++index] = new Card(type, 10, figure.toString()));
        index++;
    }

    // Creación de números aleatorios
    private void randomDraw() {
        Set<Integer> draw = new HashSet<>();
        index = 0;

        for (int i = 0; i < 52; i++) {
            draw.add(i);
        }

        draw.parallelStream().forEach(value -> randomDraw[index++] = value);

        index = 0;
    }
}
