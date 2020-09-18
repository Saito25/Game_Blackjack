package player;

import deck.Deck;

/**
 * Jugador especial, el Crupier. Actúa diferente a un jugador controlado por la IA.
 *
 * @author Manuel
 * @version 1
 * @since 1
 */
public class Crupier extends IAPlayer {

    public Crupier() {
        super("Crupier", 100000000);
    }

    public void whatDo(Deck deck) {
        while (currentScord() <= 16) {
            System.out.printf("%s, pide una carta\n", name);
            takeCard(deck.drawCard());
            showHandAndValue();
        }

        System.out.printf("%s, se planta\n", name);
    }
} // Class end
