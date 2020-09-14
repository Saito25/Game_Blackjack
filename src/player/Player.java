package player;

import card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final int numberPlayer;
    private int currentScord;
    private int Money;
    private List<Card> currentCards = new ArrayList<>();


    public Player(int numberPlayer) {
        this.numberPlayer = numberPlayer;
        this.currentScord = 0;
        Money = 100;
    }

    public List<Card> getCurrentCards() {
        return currentCards;
    }

    public void addCard(Card card) {
        currentCards.add(card);
    }

    public int getNumberPlayer() {
        return numberPlayer;
    }

    public int getCurrentScord() {
        return currentScord;
    }

    public void setCurrentScord(int currentScord) {
        this.currentScord = currentScord;
    }

    public int getMoney() {
        return Money;
    }

    public void setMoney(int money) {
        Money = money;
    }
}
