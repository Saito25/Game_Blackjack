package Deck.complement;

/**
 * Enum que contiene los iconos de los 4 palos
 * para las diferentes cartas.
 */
public enum Type {

    // Enums
    PICA("\u2660"),
    DIAMANTE("\u2666"),
    TREBOL("\u2663"),
    CORAZON("\u2665");

    /**
     * Atributo que contiene el símbolo de cada tipo.
     */
    private String type;

    /**
     * Constructor de la clase.
     * @param type
     * @since 1
     */
    Type(String type) {
        this.type = type;
    }

    // GETTERS

    /**
     * Devuelve un String con el símbolo en formáto númérico.
     *
     * @return el String que contiene el símbolo.
     */
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
