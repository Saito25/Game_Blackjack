package logic;

import deck.Deck;
import player.Human;
import player.Player;
import utility.ConsoleInput;

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
     * Límite de jugadores 2. Contiene a los jugadores de
     * la partida.
     */
    private final Player[] PLAYERS = new Player[2];

    private final ConsoleInput KEYBOARD = new ConsoleInput(new Scanner(System.in));

    // Constructors

    /**
     * Constructor de la clase. Llama a los métodos necesarios para construir
     * a los jugadores y empezar el juego.
     */
    public Logic() {
        //initPlayers();
        testgame();
        
    }



    // CONSTRUCTOR METHODS

    /**
     * Método que inicializa a los jugadores. Por defecto, este método
     * solo inicializa a jugadores humanos. Pregunta nombre y dinero inicial.
     */
    private void initPlayers() {

        String name;
        int gold = 0;

        for (int i = 0; i < PLAYERS.length; i++) {
            System.out.printf("¿Nombre del %d jugador?\n", i + 1);
            name = KEYBOARD.readString();


            while (true) {
                System.out.printf("¿Dinero inicial de %s? ", name);
                try {
                    gold = KEYBOARD.readInt();
                    break;
                } catch (Exception e) {

                } finally {
                    KEYBOARD.readInt();
                }
            }

            PLAYERS[i] = new Human(name, gold);
        }
    }

    private void testgame() {
        PLAYERS[0] = new Human("Manuel", 100);
        PLAYERS[1] = new Human("Juan", 100);
    }

} // Fin clase
