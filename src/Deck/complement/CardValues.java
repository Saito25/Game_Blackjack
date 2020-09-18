package deck.complement;

/**
 * Enum que representa a las 3 figuras de la baraja
 */
public enum CardValues {
    TWO("dos", 2),
    THREE("Tres", 3),
    FOUR("Cuatro", 4),
    FIVE("Cinco", 5),
    SIX("Seis", 6),
    SEVEN("Siete", 7),
    EIGHT("Ocho", 8),
    NINE("Nueve", 9),
    TEN("Diez", 10),
    JACK("Jota", 11),
    QUEEN("Reina", 12),
    KING("Rey", 13),
    ACE("As", 1);

    /**
     * Nombre en español
     */
    private String nameEs;

    /**
     * Valor real de la carta
     */
    private int value;

    /**
     * Constructor del Enum.
     *
     * @param nameEs Nombre en español
     * @param value Valor real de la carta
     */
    CardValues(String nameEs, int value) {
        this.nameEs = nameEs;
        this.value = value;
    }

    public String getNameEs() {
        return nameEs;
    }

    public int getValue() {
        return value;
    }
}
