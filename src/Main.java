import logic.Logic;

public class Main {

    public static void main(String[] args) {
        new Logic();
    }
}


/*
        System.out.printf("Jugador, %s, ¿cuál es tu apuesta?\n", PLAYERS[0].getName());

        currentBet1 = KEYBOARD.readIntInRangeInclusive(1, maxBet);

        System.out.printf("Jugador, %s, ¿quieres igualar o superar la apuesta?\n", PLAYERS[1].getName());
        System.out.println("    1. Igualar");
        System.out.println("    2. Subir");

        playerElection = KEYBOARD.readIntInRangeInclusive(1, 2);

        if (playerElection == 1) {
            whoWin(currentBet1);
        } else {
            currentBet2 = KEYBOARD.readIntInRangeInclusive(currentBet1, maxBet);

            System.out.printf("Jugador, %s, ¿quieres igualar la apuesta?\n", PLAYERS[0].getName());
            System.out.println("    1. Pasar");
            System.out.println("    2. Igualar");


            playerElection = KEYBOARD.readIntInRangeInclusive(1, 2);
            switch (playerElection) {
                case 1:
                    PLAYERS[1].addMoney(currentBet1);
                    try {
                        PLAYERS[0].lossMoney(currentBet1);
                    } catch (Exception e) {
                        endGame(PLAYERS[1]);
                    }

                    showAndCleanHand();
                    initGame();
                    break;

                case 2:
                    whoWin(currentBet2);
                    break;
            }
        }
 */