package edu.itstep.hw20220915.blackjack.deck;

public class Card {
    private Suit suit; //масть
    private Value value; //значення (достоинство)

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Card= " + suit.getImg() + " " + value;
        //return "Card= " + suit.getImg() + " " + value + " - " + value.getNominal();
    }
}
