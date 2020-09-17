package deck;

import deck.complement.CardValues;
import deck.complement.Type;

import java.util.Objects;

/**
 * Clase que simula las propiedades de una carta. Es utilizada en los mazos de carta.
 *
 * @author Manuel
 * @version 1
 */
public class Card {

    // CLASS ATRIBUTTES
    /**
     * Valor y nombre de la carta, a partir de un Enum
     */
    private final CardValues CARD_VALUES;

    /**
     * El palo de la carta corresponde con el símbolo que representa
     * a su familia.
     */
    private final Type TYPE;

    /**
     * Constructor de la clase. Recibe los datos apropiados para crear
     * una carta.
     *
     * @param cardValues valor de la carta (nombre y entero).
     * @param palo palo, familia, de la carta
     * @since 1
     */
    public Card(CardValues cardValues, Type type) {

        Objects.requireNonNull(cardValues, "El valor de la carta no puede ser nulo");
        Objects.requireNonNull(type, "El palo no puede ser nulo");

        CARD_VALUES = cardValues;
        TYPE = type;
    }



    // Getters

    public CardValues getCARD_VALUES() {
        return CARD_VALUES;
    }

    public Type getTYPE() {
        return TYPE;
    }
}
