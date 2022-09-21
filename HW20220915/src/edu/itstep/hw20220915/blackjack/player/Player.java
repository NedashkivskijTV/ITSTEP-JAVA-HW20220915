package edu.itstep.hw20220915.blackjack.player;

import edu.itstep.hw20220915.blackjack.deck.Card;
import edu.itstep.hw20220915.blackjack.deck.Suit;
import edu.itstep.hw20220915.blackjack.deck.Value;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Player.ParaCardCurrentNominal> cardsAndNominals = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
        cardsAndNominals.add(new ParaCardCurrentNominal(card));
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<ParaCardCurrentNominal> getCardsAndNominals() {
        return cardsAndNominals;
    }

    public int deckNominal() {
        int nominal = 0;
        for (Card card : cards) {
            nominal += card.getValue().getNominal();
        }
        return nominal;
    }

    public int deckCurrentNominal(int winnerScore) {
        int nominal;
        do {
            nominal = 0;
            for (ParaCardCurrentNominal cardAndNominal : cardsAndNominals) {
                nominal += cardAndNominal.getCurrentNominal();
            }
        } while (changeCurrentNominal(nominal, winnerScore));
        return nominal;
    }

    private boolean changeCurrentNominal(int nominal, int winnerScore) {
        if (nominal > winnerScore) {
            for (ParaCardCurrentNominal cardAndNominal : cardsAndNominals) {
                if (cardAndNominal.getCard().getValue().toString().equals(Value.ACE.toString()) &&
                        cardAndNominal.getCurrentNominal() == Value.ACE.getNominal()) {
                    cardAndNominal.setCurrentNominal(1);
                    return true;
                }
            }
        }
        return false;
    }

    public void showCards() {
        for (Card card : getCards()) {
            System.out.print(card);
            System.out.println(" - " + card.getValue().getNominal());
        }
    }

    public void showCardsCurrentNominals() {
        for (ParaCardCurrentNominal cardAndNominal : cardsAndNominals) {
            System.out.println(cardAndNominal);
        }
    }

    // допоміжний внутрішній клас
    private class ParaCardCurrentNominal {
        private Card card;
        private int currentNominal;

        public ParaCardCurrentNominal(Card card) {
            this.card = card;
            this.currentNominal = card.getValue().getNominal();
        }

        public Card getCard() {
            return card;
        }

        public int getCurrentNominal() {
            return currentNominal;
        }

        public void setCurrentNominal(int currentNominal) {
            this.currentNominal = currentNominal;
        }

        @Override
        public String toString() {
            return "CardCurrentNominal= " + card + " - " + currentNominal;
        }
    }

}
