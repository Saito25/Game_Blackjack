package player;

import deck.Card;
import deck.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    protected final String name;

    /**
     * Dinero que tiene el jugador para apostar.
     */
    protected int money = 0;

    /**
     * Mano actual de cada jugador. Se compone de cartas.
     */
    protected final List<Card> currentHand = new ArrayList<>();

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

    // ABSTRACT METHODS

    /**
     * Este método deberá ser implementado en los hijos. Determina
     * el comportamiento de cada jugador, diferenciando entre los jugadores
     * controlados por la máquina y los humanos.
     */
    public abstract void whatDo(Deck deck);

    // CLASS METHODS
    public void showHandAndValue() {
        String hand;

        System.out.println(name);
        hand = currentHand.stream().map(Card::toString).collect(Collectors.joining(" + ", "", ":"));

        System.out.printf("%s %d\n", hand, currentScord());
    }

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
        int value = 0;

        for(Card card : currentHand) {
            /*
                Recorremos la mano actual y vamos sumando sus valores.
                Para valores entre 2 y 10, los sumanos tal cual. Pero para los valores
                del 11 al 13, sumanos 10. El As siempre suma uno, pero puede llegar a sumar 11.
             */

            value = card.getCARD_VALUES().getValue(); // Desencapsulamos el valor int de la carta

            if(value >= 2 && value <= 10) {
                scord += value;
            } else {
                switch (value) {
                    case 11:
                    case 12:
                    case 13:
                        scord += 10;
                        break;

                    case 1:
                        scord += 1;
                        asCounter += 1;
                        break;
                }
            }


        }

        for(int i = 1; i <= asCounter; i++) {
            /*
            Comprobamos si existe algún As en la mano y si la puntuación nos permite
            transformarlo.
             */

            if(scord <= 11) {
                scord += 10;
            } else {
                break;
            }
        }

        return scord;
    }

    /**
     * Determina si el jugador tiene o no blackjack
     * @return
     */
    public boolean isBlackJack() {
        return currentScord() == 21 && currentHand.size() == 2;
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
        /*
        Hay que determinar cuándo un jugador ha perdido
         */
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
