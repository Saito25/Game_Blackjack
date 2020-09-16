package player;

import deck.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private int money = 0;

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

    /**
     * Este método borra todas las manos de los jugadores.
     */
    public void cleanHand() {
        currentHand.clear();
    }

    /**
     * Este método devuelve la puntuación total de la mano del
     * jugador. Convirtiendo un As en un 11 cuando sea necesario.
     *
     * @return
     */
    public int currentScord() {
        int scord = 0;
        int asCounter = 0;
        Card currentCard;

        for(int i = 0; i < currentHand.size(); i++) {
            currentCard = currentHand.get(i);
            scord += currentCard.getValue();

            if(currentCard.getName().equalsIgnoreCase("As")) {
                asCounter++;
            }
        }

        for(int i = 1; i <= asCounter; i++) {
            if(scord <= 11) {
                scord += 10;
            } else {
                break;
            }
        }

        return scord;
    }

    /**
     * Añade dinero a un jugador.
     * @param money
     */
    public void addMoney(int money) {
        this.money += money;
    }

    /**
     * Reduce el dinero de un jugador, si este se queda a 0 o menos,
     * lanza una excepción
     * @param money
     */
    public void lossMoney(int money) throws IllegalArgumentException {
        if (this.money <= money) {
            throw new IllegalArgumentException("Game over, jugador " + name);
        }
        this.money -= money;
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


    // Object Methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return money == player.money &&
                name.equals(player.name) &&
                Objects.equals(currentHand, player.currentHand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money, currentHand);
    }
}
