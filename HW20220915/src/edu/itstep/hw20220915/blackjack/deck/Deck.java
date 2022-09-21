package edu.itstep.hw20220915.blackjack.deck;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards = new ArrayList<>();
    
    public void create(){
        cards.clear();
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                cards.add(new Card(suit, value));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card getCard(){
        return cards.remove(cards.size() - 1);
    }

    public int getSize(){
        return cards.size();
    }

}
