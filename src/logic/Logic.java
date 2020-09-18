package logic;

import deck.Card;
import deck.Deck;
import player.Crupier;
import player.Human;
import player.IAPlayer;
import player.Player;
import utility.ConsoleInput;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private Player[] activePlayers;

    /**
     * Determina la apuesta por turno que se determina para una partida.
     *
     * Debe ser de acuerdo al mínimo del dinero del jugador que tenga menos dinero.
     */
    private final int BET;

    /**
     * Dinero total apostado en una ronda. Se repartirá entre los ganadores de la misma.
     */
    private int currentBet = 0;

    /**
     * Crupier del juego, el dealer. Mucho dinero y muchas chispas.
     */
    private final Player crupier = new Crupier();

    /**
     * Lista de ganadores, perdedores y los que han empetado en cada ronda.
     */
    List<Player> winners = new ArrayList<>();
    List<Player> losers = new ArrayList<>();
    List<Player> tier = new ArrayList<>();
    List<Player> blackJackWinners = new ArrayList<>();

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

        betRound();
    }

    // Game Implementation Methods

    /**
     * Ronda de apuesta. Todos los jugadores que queden activos deberán apostar
     * lo mínimo imprescindible para poder seguir jugando.
     *
     * Encadena el siguiente paso, mostrar la primera carta.
     */
    public void betRound() {
        System.out.println("Ronda de apuestas. Todo el mundo debe de apostar " + BET + " €");

        for (Player player: activePlayers) {
            /*
            Restamos a cada jugador que siga activo, una cantidad fija de dinero. Que será la apuesta
            mínima permitida.
             */

            player.lossMoney(BET);
            currentBet += BET;
        }

        System.out.println("Apuesta total en mesa: " + BET + " €");

        initialHands();
    }

    /**
     * Mano inicial repartida a cada jugador. Todos los jugadores tendrán
     * dos cartas descubiertas. El Crupier solo tendrá 1.
     *
     * Inicializa el turno de cada jugador.
     */
    private void initialHands()  {

        crupier.takeCard(DECK.drawCard()); // El crupier solo roba una carta.

        crupier.showHandAndValue();

        for (Player player : activePlayers) {
            player.takeCard(DECK.drawCard());
            player.takeCard(DECK.drawCard());
            player.showHandAndValue();
        }

        playersTurn();
    }

    /**
     * Este método interactúa con los jugadores. Permitíendoles robar carta o pasar turno.
     *
     * Luego se decide quien gana y cuánto gana.
     */
    private void playersTurn() {
        for (Player player : activePlayers) {
            player.whatDo(DECK);
        }

        crupier.whatDo(DECK);

        whoWhin();
    }

    /**
     * Este método determina que jugadores ganan o pierden dinero.
     *
     * Además, limpiará de la mesa a los jugadores que no tengan dinero.
     */
    private void whoWhin() {
        int crupierScord = crupier.currentScord();
        int playerScord = 0;
        boolean crupierBlackJack = crupier.isBlackJack();
        boolean playerBlackJack;

        for(Player player : activePlayers) {
            playerScord = player.currentScord();
            playerBlackJack = player.isBlackJack();

            if(playerScord > 21) {
                if(crupierScord > 21) {
                    tier.add(player);
                } else {
                    losers.add(player);
                }
            } else {
                if(crupierScord <= 21) {

                    if(crupierBlackJack && playerBlackJack) {
                        tier.add(player);
                    } else if(crupierBlackJack) {
                        losers.add(player);
                    } else if(playerBlackJack) {
                        blackJackWinners.add(player);
                    } else {

                        if(crupierScord < playerScord) {
                            winners.add(player);
                        } else if (crupierScord > playerScord){
                            losers.add(player);
                        } else {
                            tier.add(player);
                        }
                    }
                } else {
                    if(playerBlackJack) {
                        blackJackWinners.add(player);
                    } else {
                        winners.add(player);
                    }
                }
            }
        }

        howMoney();
    }

    /**
     * Este método determina cuánto dinero gana cada jugador según
     */
    private void howMoney() {

        int money = BET / 2 * 3 + BET;
        for (Player player: blackJackWinners) {
            System.out.printf("%s, ha ganado: %d\n", player.getName(), money);
            player.addMoney(money);
        }

        money = BET * 2;
        for(Player player: winners) {
            System.out.printf("%s, ha ganado: %d\n", player.getName(), money);
            player.addMoney(money);
        }

        for(Player player: losers) {
            System.out.printf("%s, ha perdido: %d\n", player.getName(), BET);
        }

        for(Player player: tier) {
            System.out.printf("%s, ha recuperado su apuesta\n", player.getName());
            player.addMoney(BET);
        }

        cleanList();
    }

    /**
     * Reinicializa las listas eliminando a todos los participantes de las mismas.
     *
     * Llama a los métodos para la transacción del dinero y para limpiar la mano de cada jugador.
     */
    private void cleanList() {
        winners.clear();
        losers.clear();
        blackJackWinners.clear();
        tier.clear();

        cleanHand();

        eliminateLoserPlayer();
    }

    /**
     * Elimina a los jugadores que no tengan dinero.
     */
    private void eliminateLoserPlayer() {
        Map<Boolean, List<Player>> result = Arrays.stream(activePlayers).collect(Collectors.partitioningBy(player -> player.getMoney() > 0));

        result.get(false).forEach(player -> System.out.printf("%s, no tiene dinero para seguir jugando. Pierde la partida\n", player.getName()));

        result.get(true).toArray(activePlayers);

        showMoney();

        endGame();
    }

    /**
     * Este método muestra el dinero que tienen los jugadores activos, pues los no activo
     * se consideran que han perdido todo su dinero.
     */
    private void showMoney() {
        for (Player player : players) {
            System.out.printf("%s, tiene %d € actualmente\n", player.getName(), player.getMoney());
        }
    }

    /**
     * Este método comprueba si sigue habiendo jugadores con dinero en mesa y si estos quieren
     * seguir jugando. También permite salir de la mesa e irse con el dinero obtenido.
     *
     * NOTA: para dejar el juego, tienen que dejarlo todos los jugadores activos.
     */
    private void endGame() {

        int election = 0;

        if(activePlayers.length > 0) {
            System.out.println("¿Otra ronda?");
            System.out.println("    1.- Sí");
            System.out.println("    1.- No");

            election = KEYBOARD.readIntInRangeInclusive(1, 2);

            if(election == 1) {
                DECK.shuffleDeck();
                initialHands();
            } else {
                finishGame(true);
            }

        } else {
            finishGame(false);
        }
    }

    /**
     * Comprueba si el juego ha terminado con algún ganador o gana la banca.
     * @param condition
     */
    private void finishGame(boolean condition) {

        if (condition) {
            selectWinner();
        } else {
            System.out.println("Ha ganado la banca. Vuelvan a nuesro casino! Pronto más y mejor!");
        }

        newGame();
    }

    /**
     * Este método selecciona a los ganadores del juego, según el que tenga más dinero:
     */
    private void selectWinner() {

        OptionalInt result = Arrays.stream(activePlayers).mapToInt(Player::getMoney).max();

        System.out.println("El ganador o los ganadores de esta partida son:");

        Arrays.stream(activePlayers)
                .filter(player -> player.getMoney() >= result.getAsInt())
                .collect(Collectors.toList())
                .forEach(player -> System.out.printf("%s\n", player.getName()));
    }

    private void cleanHand() {
        for(Player player : activePlayers) {
            player.cleanHand();
        }
        crupier.cleanHand();
    }

    /**
     * Este método ofrece la posibilidad de empezar un nuevo juego o cerrar el programa.
     */
    private void newGame() {
        int election = 0;

        if(activePlayers.length > 0) {
            System.out.println("¿Quieres jugar de nuevo?");
            System.out.println("    1.- Sí");
            System.out.println("    1.- No");

            election = KEYBOARD.readIntInRangeInclusive(1, 2);

            if (election == 1) {
                new Logic();
            } else {
                System.exit(0);
            }
        }
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
        int totalPlayers = players.length;

        if(totalPlayers > 1) {
            System.out.printf("¿Cuántos de ellos serán controlados por la máquina? (Máximo %d)\n", PLAYERS_LIMIT - 1);
            election = KEYBOARD.readIntInRangeInclusive(0, totalPlayers - 1);
        }

        if(election > 0) {
            /*
                Comprobamos si se ha seleccionado algún jugador controlado por la máquina
                y los inicializamos.
             */

            System.out.println("¿Con cuánto dinero jugará la máquina? (mínimo 100 €)");
            gold = KEYBOARD.readIntGreaterOrEqualThan(100);

            for (int i = 0; i < election; i++) {
                players[i] = new IAPlayer("IA " + i, gold);
            }
        }

        for (int i = election; i < totalPlayers; i++) {
            /*
            Inicializamos a los jugadores humanos. Escogemos el nombre
            y el dinero con el que empiezan.
             */

            System.out.println("Escoge el nombre para el jugador humano:");
            name = KEYBOARD.readString();

            System.out.println("¿Cuánto dinero tendrá este jugador? (mínimo 100 €)");
            gold = KEYBOARD.readIntGreaterOrEqualThan(100);

            players[i] = new Human(name, gold);
        }

        activePlayers = Arrays.copyOf(players, players.length);
    }

    /**
     * Ese método sirve para determina cuál será la apuesta entre los jugadores, que se hará cada turno.
     *
     * @return un entero.
     */
    private int chooseBet() {

        int maxBet = Arrays.stream(players).map(Player::getMoney).min(Comparator.naturalOrder()).get() / 2;

        System.out.printf("Seleccione la apuesta por turno. Recuerde que no podrá ser cambiado " +
                "a lo largo de la partida, por lo que se recomiendan apuestas acorde al dinero de cada jugador." +
                " Apuesta mínima 1. Apuesta máxima %d\n", maxBet);

        return KEYBOARD.readIntInRangeInclusive(1, maxBet);
    }

} // Fin clase
