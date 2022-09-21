package edu.itstep.hw20220915.blackjack.player;

public class Dealer extends Player{
    public void showFirstCard(){
        System.out.println(getCards().get(0));
    }
}
