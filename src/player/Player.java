package player;

import Deck.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta padre de todo jugador.
 * Tiene los atributos básicos afines a cualquier jugador.
 *
 * @author Manuel
 * @since 1
 */
public abstract class Player {

    // CLASS ATRIBUTTES
    /**
     * Nombre del jugador.
     */
    private final String name;

    /**
     * Dinero que tiene el jugador para apostar.
     */
    private int money;

    /**
     * Mano actual de cada jugador. Se compone de cartas
     */
    private final List<Card> currentHand = new ArrayList<>();

    // CONSTRUCTOR

    /**
     * Construye a un jugador
     * @param name
     * @param money
     */
    public Player(String name, int money) {
        this.name = name;
        this.money = money;
    }

    // CLASS METHODS

    /**
     * Cada vez que el método es invocado, se añade una carta
     * a la mano del jugador.
     * @param card
     */
    public void takeCard(Card card) {
        currentHand.add(card);
    }



    // GETTERS

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public List<Card> getCurrentHand() {
        return currentHand;
    }
}
