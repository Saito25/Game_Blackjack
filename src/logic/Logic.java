package logic;

import deck.Deck;
import player.Human;
import player.Player;
import utility.ConsoleInput;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

/**
 * Clase que inicializa el juego. Contiene las acciones, "reglas",
 * del mismo.
 */
public class Logic {

    // CLASS ATRIBBUTES

    /**
     * Mazo de 52 cartas con el que interactúa cada jugador.
     */
    private final Deck DECK = new Deck();

    /**
     * Determina el número máximo de jugadores que podrán jugar
     * una partida.
     */
    private final int PLAYERS_LIMIT = 5;

    /**
     * Límite de jugadores 2. Contiene a los jugadores de
     * la partida.
     */
    private Player[] players;

    private final int BET;

    /**
     * Atributo de utilidad. Necesario para las lecturas correctas de teclado.
     */
    private final ConsoleInput KEYBOARD = new ConsoleInput(new Scanner(System.in));

    // Constructors

    /**
     * Constructor de la clase. Llama a los métodos necesarios para construir
     * a los jugadores y empezar el juego.
     */
    public Logic() {
        players = new Player[howPlayers()];

        initPlayers();

        BET = chooseBet();
    }



    // CONSTRUCTOR METHODS

    /**
     * Este método determina cuantos jugadores habrá en la partida
     * y cuántos de ellos habrá controlados por la máquina.
     */
    private int howPlayers() {
        System.out.printf("Selecciona el número de jugadores sin incluir al crupier (máximo %d)\n", PLAYERS_LIMIT);

        return KEYBOARD.readIntInRangeInclusive(1, PLAYERS_LIMIT);
    }

    /**
     * Método que inicializa a los jugadores. Por defecto, este método
     * solo inicializa a jugadores humanos. Pregunta nombre y dinero inicial.
     */
    private void initPlayers() {

        String name;
        int gold = 0;
        int election = 0;

        System.out.printf("¿Cuántos de ellos serán controlados por la máquina? (Máximo %d)\n", PLAYERS_LIMIT - 1);
        election = KEYBOARD.readIntInRangeInclusive(0, PLAYERS_LIMIT - 1);


    }

    /**
     * Ese método sirve para determina cual será la apuesta entre los jugadores que se hará cada turno.
     *
     * @return un entero.
     */
    private int chooseBet() {

        Integer maxBet = Arrays.stream(players).map(Player::getMoney).min(Comparator.naturalOrder()).get() / 2;

        System.out.printf("Seleccione la apuesta por turno. Recuerde que no podrá ser cambiado " +
                "a lo largo de la partida, por lo que se recomiendan apuestas acorde al dinero de cada jugador." +
                " Apuesta mínima 1. Apuesta máxima %d\n", maxBet);

        return KEYBOARD.readIntInRangeInclusive(1, maxBet);
    }
} // Fin clase
