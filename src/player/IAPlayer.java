package player;

/**
 * Clase que simula un jugador controlado por la máquina.
 * Apuesta por sí sola.
 *
 * @author Manuel
 * @since 1
 */
public class IAPlayer extends Player{

    /**
     * Construye a un jugador
     *
     * @param name
     * @param money
     */
    public IAPlayer(String name, int money) {
        super(name, money);
    }
}
