package edu.itstep.hw20220915.blackjack.player;

import edu.itstep.hw20220915.blackjack.deck.Card;

public class User extends Player{
    private int userBank = 0;
    private int userBet = 0;

    public int getUserBank() {
        return userBank;
    }

    public void setUserBank(int userBank) {
        this.userBank = userBank;
    }

    public int getUserBet() {
        return userBet;
    }

    public void setUserBet(int userBet) {
        this.userBet = userBet;
    }

    public void showUserBank(){
        System.out.println("User bank, $ = " + getUserBank());
    }
}
