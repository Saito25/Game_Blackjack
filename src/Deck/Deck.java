package deck;

import deck.complement.CardValues;
import deck.complement.Type;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Clase que contiene las cartas necesarias para cada partida.
 * Se compone de un total de 52 cartas: 13 cartas X 4 palos.
 *
 * @author Manuel
 * @since 1
 */
public class Deck {

    // CLASS ATRIBUTTE

    /**
     * Array que contiene las 52 cartas del juego.
     */
    private final Card[] cards = new Card[52];

    /**
     * Índice usado por varios métodos de la clase Deck.
     */
    private int index = 0;

    // CONSTRUCTORES

    /**
     * Constructor de la clase. No recibe ningún parámetro dado que
     * el mismo sabe cómo construir las cartas.
     */
    public Deck() {

        // Creamos el deck ordenado
        Arrays.stream(Type.values()).forEach(this::makeDeck);

        // Barajamos el deck
        shuffleDeck();
    }

    // ClASS METHODS

    /**
     * Devuelve una carta del mazo cada vez que es llamado.
     * Si se alcanza el número máximo de cartas, lanza una excepción.
     *
     * @return Una carta del mazo
     * @throws Exception
     */
    public Card drawCard() {
        return cards[index++];
    }

    // Getters

    /**
     * Devuelve todo el array de cartas.
     *
     * @return Todo el conjunto de cartas.
     */
    public Card[] getCards() {
        return cards;
    }


    // Métodos para el constructor de la clase

    /**
     * Método privado solo utilizado por el constructor de la clase.
     * Recibe el palo de una familia de cartas y genera dicha familia,
     * las agrega al array de la clase "cards"
     * @param type
     */
    private void makeDeck(Type type) {

        for(CardValues cardValues : CardValues.values()) {
            cards[index++] = new Card(cardValues, type);
        }
    }

    /**
     * Método que recicla el deck, lo vuelve a barajar.
     */
    public void shuffleDeck() {
        // Variable que usaremos para desordenar las cartas.
        List<Card> shuffleCards;

        // Desordenamos el deck
        shuffleCards = Arrays.asList(cards);
        Collections.shuffle(shuffleCards);
        shuffleCards.toArray(cards);

        // reset del contador
        index = 0;
    }

}
