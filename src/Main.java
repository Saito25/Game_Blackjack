import Deck.Deck;
import Deck.complement.Type;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Arrays.stream(new Deck().getCards())
                .forEach(card -> System.out.printf("%s %s : %d\n", card.getPalo(),
                                                                    card.getName(),
                                                                    card.getValue()));

    }
}
