package card;

public class Card {
    // Atributos
    private final Type type;
    private final int scored;
    private final String name;

    public Card(Type type, int scored, String name) {
        this.type = type;
        this.scored = scored;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public int getScored() {
        return scored;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s %s", type, name);
    }
}
