package edu.itstep.hw20220915.blackjack.game;

import edu.itstep.hw20220915.blackjack.deck.Deck;
import edu.itstep.hw20220915.blackjack.player.Dealer;
import edu.itstep.hw20220915.blackjack.player.User;

import java.util.Scanner;

public class Game {
    private Dealer dealer = new Dealer();
    private User user = new User();
    private Deck deck = new Deck();
    public final int winnerScore = 21;
    public final int dealerMinScore = 17;
    public final int GamesWithOneDeck = 4;

    public void start() {
        // задати банк юзера
        System.out.print("Enter User bank, $ - ");
        Scanner scanner = new Scanner(System.in);
        user.setUserBank(scanner.nextInt());

        String choice = scanner.nextLine();
        int gamesDeck = GamesWithOneDeck;
        do {
            // ставка гравця
            userBet();

            // партії з однієї колода - max=4
            gamesDeck = ShuffleTheDeck(gamesDeck);

            // початок гри (гравець та ділер отримують по 2 карти)
            beginGame();

            // user бере карти
            userTakeCards();

            // dealer бере карти
            dealerTakeCards();

            // результат партії
            showWinner();

            // пустий банк гравця
            if (user.getUserBank() == 0) {
                System.out.println("\nThe player’s bank is empty. We will continue next time. \nAnd now the game is over");
                break;
            }

            // наступна партія
            System.out.println();
            System.out.print("Play again? y/n ");
            choice = scanner.nextLine();

        } while (choice.equals("y") || choice.equals("yes"));
    }

    public int ShuffleTheDeck(int gamesDeck){
        System.out.println();
        if (++gamesDeck > GamesWithOneDeck) {
            newDeck();
            gamesDeck = 1;
            System.out.println("The deck of cards is shuffled");
        }
        System.out.println("Number of parties with one deck (max = 4) - " + gamesDeck + "\n");
        return gamesDeck;
    }

    public void userBet() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Enter User bet in this game (" + user.getUserBank() + " - " + "1) - ");
            user.setUserBet(scanner.nextInt());
            if (user.getUserBet() > user.getUserBank() || user.getUserBet() <= 0) {
                System.out.println("ATTENTION! You introduced incorrect data.");
                //System.out.println("Enter the correct data");
            }
        } while (user.getUserBet() > user.getUserBank() || user.getUserBet() <= 0);
        user.setUserBank(user.getUserBank() - user.getUserBet());
        String temp = scanner.nextLine();
    }

    public void beginGame() {
        System.out.println("!!!!!!!!!!!!!!!!!!!! NEW GAME !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        newDeck();
        getFirstCards();
    }

    public void newDeck() {
        deck.create();
        deck.shuffle();
    }

    public void getFirstCards() {
        dealer.getCards().clear();
        user.getCards().clear();
        user.getCardsAndNominals().clear();

        dealer.addCard(deck.getCard());
        dealer.addCard(deck.getCard());
        user.addCard(deck.getCard());
        user.addCard(deck.getCard());
    }

    public void userTakeCards() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        while (user.deckCurrentNominal(winnerScore) < winnerScore) {
            showDealerCards(true);

            showUserCards();

            System.out.print("Want to get another card? y/n ");
            choice = scanner.nextLine();
            if (choice.equals("n") || choice.equals("no")) {
                break;
            }
            user.addCard(deck.getCard());
        }
    }

    public void dealerTakeCards() {
        while (user.deckCurrentNominal(winnerScore) < winnerScore && dealer.deckNominal() < dealerMinScore) {
            dealer.addCard(deck.getCard());
        }
        //showDealerCards(false);
    }

    private void showUserCards() {
        System.out.println("----------- User cards --------------------");
        System.out.println("Count User cards = " + user.getCards().size());
        user.showCardsCurrentNominals();
        System.out.println("User cards score = " + user.deckCurrentNominal(winnerScore));
    }

    private void showDealerCards(boolean showFirstCard) {
        System.out.println("----------- Dealer cards --------------------");
        System.out.println("Count Dealer cards = " + dealer.getCards().size());
        if (showFirstCard) {
            dealer.showFirstCard();
        } else {
            dealer.showCards();
            System.out.println("Dealer cards score = " + dealer.deckNominal());
        }
    }

    public void showWinner() {
        //System.out.println("-------------- Show Winner ------------------------");
        System.out.println("============== GAME IS OVER ========================");
        final int userScore = user.deckCurrentNominal(winnerScore);
        final int dealerScore = dealer.deckNominal();

        if (userScore > winnerScore) {
            System.out.println("Dealer winn");
        } else if (dealerScore > winnerScore) {
            System.out.println("User winn");
            user.setUserBank(user.getUserBank() + (2 * user.getUserBet()));
        } else if (userScore == winnerScore) {
            System.out.println("User winn");
            user.setUserBank(user.getUserBank() + (2 * user.getUserBet()));
        } else if (dealerScore == winnerScore) {
            System.out.println("Dealer winn");
        } else if (userScore > dealerScore) {
            System.out.println("User winn");
            user.setUserBank(user.getUserBank() + (2 * user.getUserBet()));
        } else if (userScore < dealerScore) {
            System.out.println("Dealer winn");
        } else {
            System.out.println("DEAD HEAT");
            user.setUserBank(user.getUserBank() + user.getUserBet());
        }
        gameScore();
    }

    private void gameScore() {
        showDealerCards(false);
        showUserCards();
        System.out.println("-------------- GAME SCORE ------------------------");
        System.out.println("Dealer cards score = " + dealer.deckNominal());
        System.out.println("User cards score = " + user.deckCurrentNominal(winnerScore));
        user.showUserBank();
    }

}
