package player;

import deck.Deck;

/**
 * Clase que simula un jugador controlado por la máquina.
 * Apuesta por sí sola.
 *
 * @author Manuel
 * @since 1
 */
public class IAPlayer extends Player{

    /**
     * Construye a un jugador
     *
     * @param name
     * @param money
     */
    public IAPlayer(String name, int money) {
        super(name, money);
    }

    // ABSTRACT METHODS IMPLEMENT

    /**
     * Pide carta en caso de creerlo necesario.
     *
     * Se debe mejorar.
     * @param deck
     */
    @Override
    public void whatDo(Deck deck) {

        while (currentScord() <= 15) {
            System.out.printf("%s, pide una carta\n", name);
            takeCard(deck.drawCard());
            showHandAndValue();
        }

        System.out.printf("%s, se planta\n", name);
    }

}
