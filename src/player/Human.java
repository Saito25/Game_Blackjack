package player;

import deck.Deck;
import utility.ConsoleInput;

import java.util.Scanner;

/**
 * Clase jugador Humano, la cual se usará para jugar un humano.
 * Esta clase implementa códigos que permite seleccionar las acciones.
 */
public class Human extends Player{

    ConsoleInput consoleInput = new ConsoleInput(new Scanner(System.in));

    /**
     * Construye a un jugador
     *
     * @param name
     * @param money
     */
    public Human(String name, int money) {
        super(name, money);
    }

    @Override
    public void whatDo(Deck deck) {

        int election = 0;
        int scored = currentScord();

        while (scored <= 20) {
            System.out.printf("¿%s, Quieres coger carta o pasar?\n", name);
            System.out.println("    1. Coger carta");
            System.out.println("    2. Pasar");

            election = consoleInput.readIntInRangeInclusive(1, 2);

            if(election == 1) {
                takeCard(deck.drawCard());

                showHandAndValue();
                scored = currentScord();

                if(scored > 21) {
                    System.out.printf("%s, se ha pasado de 21. Pierde la ronda\n", name);
                    break;
                }
            } else {
                System.out.printf("%s, pasa\n", name);
                break;
            }
        }

    }
} // Class end
