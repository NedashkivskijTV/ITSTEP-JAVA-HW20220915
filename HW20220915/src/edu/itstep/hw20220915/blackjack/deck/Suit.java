package edu.itstep.hw20220915.blackjack.deck;

public enum Suit {
    CLUBS("♣"), //трефы
    DIAMONDS("♦"), //бубны
    HEARTS("♥"), //червы
    SPADES("♠"); //пики

    private String img;

    Suit(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }
}
