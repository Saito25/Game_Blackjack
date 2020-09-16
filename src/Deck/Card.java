package deck;

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
     * Valor base numérico de la carta.
     */
    private final int value;

    /**
     * Es el nombre de la carta. Por ejemplo, el rey vale 10 puntos,
     * pero sigue llamándose rey.
     */
    private final String name;

    /**
     * El palo de la carta corresponde con el símbolo que representa
     * a su familia.
     */
    private final Type palo;

    /**
     * Constructor de la clase. Recibe los datos apropiados para crear
     * una carta.
     *
     * @param value valor numérico de la carta
     * @param name nombre de la carta
     * @param palo palo, familia, de la carta
     * @since 1
     */
    public Card(int value, String name, Type palo) {
        if(value < 1 || value > 10) {
            throw new IllegalArgumentException("El valor no está dentro del rango adecuado");
        }
        Objects.requireNonNull(name, "El nombre no puede ser nulo");
        Objects.requireNonNull(palo, "El palo no puede ser nulo");

        this.value = value;
        this.name = name;
        this.palo = palo;
    }

        // Getters
    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public Type getPalo() {
        return palo;
    }
}
