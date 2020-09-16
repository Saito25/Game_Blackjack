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
        KEYBOARD.readString();

        showHand(PLAYERS[0]);
    }

    /**
     * Ese método muestra la mano de los jugadores, así como la suma de
     * su puntuación total actual.
     *
     * @param player
     */
    private void showHand(Player player) {
        System.out.printf("Mano actual del jugador %s\n", player.getName());

        player.getCurrentHand()
                .forEach(card -> System.out.printf("%s%s  ", card.getPalo(), card.getName()));

        System.out.print(": ");

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

        option = KEYBOARD.readIntInRangeInclusive(1, 2);

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

    /**
     * Este método toma una carta y comprueba si quedan o no
     * más cartas en el mazo.
     *
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
     *
     * @param player
     */
    private void changePlayerTurn(Player player) {

        if (player.equals(PLAYERS[0])) {
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
        int maxBet = Math.min(PLAYERS[0].getMoney(), PLAYERS[1].getMoney()) / 2;
        int playerElection = 0;
        int currentBet1 = 1;
        int currentBet2 = 0;


        for (Player player : PLAYERS) {
            System.out.printf("Jugador, %s, haz tu apuesta. (min: %d, máx: %d)\n",
                        player.getName(), currentBet1, maxBet);

            currentBet1 = KEYBOARD.readIntInRangeInclusive(currentBet1, maxBet);

            if(currentBet2 != currentBet1) {
                currentBet2 = currentBet1;
            }

            for (int i = 0; i < PLAYERS.length - 1; i++) {
                System.out.printf("Jugador, %s, aceptas la puja?\n");
                System.out.println("    1.- Igualar Puja");
                System.out.println("    2.- Pasar Puja");

                playerElection = KEYBOARD.readIntInRangeInclusive(1, 2);

                if() {
                    
                }
            }
        }
    }

    /**
     * El método determina que jugador ha ganado la apuesta, si es que
     * alguno ganó. Limpia las manos y retoma el juego o lo finaliza.
     *
     * @param bet
     */
    private void whoWin(int bet) {
        int scordPlayer1 = PLAYERS[0].currentScord();
        int scordPlayer2 = PLAYERS[1].currentScord();
        Player winPlayer;

        if ((scordPlayer1 > 21 && scordPlayer2 > 21)
                || (scordPlayer1 == scordPlayer2)) {

            System.out.println("Ambos jugadores empataron, nadie gana esta partida");

            showAndCleanHand();

            initGame();
        }

        if (scordPlayer1 > scordPlayer2) {

            try {

                if (scordPlayer1 == 21) {
                    PLAYERS[0].addMoney(bet * 2);
                    PLAYERS[1].lossMoney(bet * 2);
                } else {
                    PLAYERS[0].addMoney(bet);
                    PLAYERS[1].lossMoney(bet);
                }
            } catch (Exception e) {
                endGame(PLAYERS[0]);
            }

        } else {
            try {
                if (scordPlayer2 == 21) {
                    PLAYERS[1].addMoney(bet * 2);
                    PLAYERS[0].lossMoney(bet * 2);
                } else {
                    PLAYERS[1].addMoney(bet);
                    PLAYERS[0].lossMoney(bet);
                }
            } catch (Exception e) {
                endGame(PLAYERS[1]);
            }

        }

        showAndCleanHand();

        initGame();
    }


    /**
     * Este método límpia y muestra el dinero de cada jugador.
     */
    private void showAndCleanHand() {
        for (Player player : PLAYERS) {
            System.out.printf("Jugador, %s, dinero actual: %d \n", player.getName(), player.getMoney());
            player.cleanHand();
        }
    }

    /**
     * Este método finaliza una partida. Permite terminar el juego
     * o empezar uno nuevo.
     *
     * @param player
     */
    private void endGame(Player player) {
        int election;

        System.out.printf("¡Enhorabuena, %s, has ganado!\n", player.getName());

        System.out.println("Queréis la revancha?");

        election = KEYBOARD.readIntInRangeInclusive(1, 2);

        if (election == 1) {
            new Logic();
        } else {
            System.exit(0);
        }

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
