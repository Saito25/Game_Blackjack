package game;

import card.Card;
import card.Deck;
import player.Player;

public class Game {

    private final Menu menu = new Menu();
    private final Player[] players = new Player[2];
    private final Deck deck = new Deck();

    private int turn = 1;

    public Game() {
        players[0] = new Player(1);
        players[1] = new Player(2);

        play(players[0]);
    }

    private void play(Player player) {
        giveCard(player, 2);
    }

    private void giveCard(Player player, int cant) {
        for (int i = 0; i < cant; i++) {
            player.addCard(deck.getRandomCard());
        }

        System.out.printf("Jugador %d, ha sacado: \n", player.getNumberPlayer());

        player.getCurrentCards().stream().forEach(value -> System.out.printf("%s \n", value));

        System.out.printf("Total: %d", totalPoints(player));
    }

    public int totalPoints(Player player) {
        int total = player.getCurrentCards().stream().mapToInt(value -> (int) value.getScored()).sum();

        if (total > 21 && player.getCurrentCards().stream().anyMatch(value -> value.getName().equalsIgnoreCase("As"))) {
            total = 0;

            for (Card card : player.getCurrentCards()) {
                total += checkAs(card);
            }

            return total;

        } else {
            player.setCurrentScord(total);
            return player.getCurrentScord();
        }

    }

    private int checkAs(Card card) {
        return card.getName().equalsIgnoreCase("As") ? 1 : card.getScored();
    }


}
