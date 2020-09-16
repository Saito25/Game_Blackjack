package logic;

import Deck.Deck;
import player.Human;
import player.Player;

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

    private final Scanner KEYBOARD = new Scanner(System.in);

    // Constructors

    /**
     * Constructor de la clase. Llama a los métodos necesarios para construir
     * a los jugadores y empezar el juego.
     */
    public Logic() {
        //initPlayers();
        testgame();

        initGame();
    }

    /**
     * Este método inicializa el juego, una vez los jugadores estén
     * seleccionados.
     * Empieza repartiendo 2 cartas a cada jugador.
     */
    private void initGame() {
        for (int i = 0; i < PLAYERS.length; i++) {
            takeNewCard(PLAYERS[i]);
            takeNewCard(PLAYERS[i]);
        }

        System.out.println("A continuación se mostrará la mano del jugador 1 (pulta enter para continuar): ");
        KEYBOARD.nextLine();

        showHand(PLAYERS[0]);
    }

    /**
     * Ese método muestra la mano de los jugadores, así como la suma de
     * su puntuación total actual.
     * @param player
     */
    private void showHand(Player player) {
        System.out.printf("Mano actual del jugador %s\n", player.getName());

        player.getCurrentHand().stream()
                .forEach(card -> System.out.printf("%s%s  ", card.getPalo(), card.getName()));

        System.out.printf(": ");

        System.out.println(player.currentScord());

        showOptions(player);
    }

    /**
     * Esta clase hace de menú y muestra las opciones que tiene el jugador,
     * pasar o coger otra carta.
     *
     * @param player
     */
    private void showOptions(Player player) {
        int option = 0;

        System.out.println("¿Qué quieres hacer?");
        System.out.println("    1. Coger otra carta");
        System.out.println("    2. pasar");

        while (true) {
            try {
                option = KEYBOARD.nextInt();
                KEYBOARD.nextLine();
            } catch (Exception e) {

            } finally {
                switch (option) {
                    case 1:
                        takeNewCard(player);
                        showHand(player);
                        break;
                    case 2:
                        changePlayerTurn(player);
                        break;
                }
            }

        }
    }

    /**
     * Este método toma una carta y comprueba si quedan o no
     * más cartas en el mazo.
     * @param player
     */
    private void takeNewCard(Player player) {
        try {
            player.takeCard(DECK.drawCard());
        } catch (Exception e) {
            System.out.println("No hay más cartas");
        }
    }

    /**
     * Esta clase cambia los turnos de los jugadores, para que ambos puedan
     * pedir carta. Cuando ambos pasen, se les permite apostar, hasta la mitad
     * del que tenga menos dinero.
     * @param player
     */
    private void changePlayerTurn(Player player) {

        if(player.equals(PLAYERS[0])) {
            showHand(PLAYERS[1]);
        } else {
            playersBet();
        }
    }

    /**
     * Permite a los jugadores apostar el dinero que tengan, como máximo
     * la mitad del que tenga menos
     */
    private void playersBet() {
        int maxBet = Math.max(PLAYERS[0].getMoney(), PLAYERS[1].getMoney()) / 2;
        int playerBet = 0;
        int currentBet = 0;

        while(true) {
            System.out.printf("¿Jugador, %s, cuánto apuesta?\n", PLAYERS[0].getName());

            try {
                playerBet = KEYBOARD.nextInt();
                break;
            } catch (Exception e) {

            } finally {
                KEYBOARD.nextLine();
            }
        }

        System.out.printf("Jugador, %s, ¿quieres igualar o superar la apuesta?\n", PLAYERS[1].getName());


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
            name = KEYBOARD.nextLine();


            while (true) {
                System.out.printf("¿Dinero inicial de %s? ", name);
                try {
                    gold = KEYBOARD.nextInt();
                    break;
                } catch (Exception e) {

                }
                finally {
                    KEYBOARD.nextLine();
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
