package com.pluralsight;

import com.pluralsight.model.Card;
import com.pluralsight.model.Deck;
import com.pluralsight.model.Hand;
import com.pluralsight.ui.Console;

import java.util.Random;

public class Main {
    static void main() {
        Deck deck = new Deck();

        String m = """
                \t Welcome to Temple Casino (Def not rigged)!
                \t [You are now playing blackjack]
                \t How many players?
                \t
                \t Press x to terminate.
                \t 
                Your Input:""";

        boolean completedInitialStartup = false;
        String userinput;
        int numOfPlayers;

        while(!completedInitialStartup) {
            userinput = Console.askForString(m);

            if(userinput.equalsIgnoreCase("x")){
                return;
            }
            try {
                numOfPlayers = Integer.parseInt(userinput);
                System.out.printf("Starting blackjack with %d player\n", numOfPlayers);
                completedInitialStartup = true;
                deck.prepareDeck();
            } catch (NumberFormatException e){
                System.out.printf("Could not parse your value to int. %s", e.getMessage());
            }


        }

        Hand p1 = new Hand();
        Hand p2 = new Hand();
        Card card1 = deck.deal();
        Card card2 = deck.deal();
        Card card3 = deck.deal();
        Card card4 = deck.deal();
        p1.deal(card1);
        p1.deal(card2);

        p2.deal(card3);
        p2.deal(card4);



        System.out.printf("Your current hand: %s and %s. Total: %s\n",card1 ,card2, p1.getValue());


        String msg = """
                \n\t [1. Hit] [2. Stand]
                Your Input:""";
        boolean isPlaying = true;

        while (isPlaying) {
            if(p1.getValue() <= 21){
                int userInputOption = Console.askForInt(msg, 1, 2);

                switch (userInputOption){
                    case 1 -> {
                        optionHit(deck, p1);
                        if(p1.getValue() == 21){
                            isPlaying = false;
                        }
                    }
                    case 2 -> {
                        stand(deck, p2);
                        isPlaying = false;
                    }
                }

                System.out.printf("Your current hand: %s. Total: %s\n", p1, p1.getValue());


            } else {
                System.out.println("Bust!");
                System.out.println("Thank you for playing!");
                return;
            }


        }

        if(!isPlaying){
            System.out.println("Moment of truth...");
            for(int i = 3; i > 0; i--){
                try{
                    Thread.sleep(1000);
                    System.out.println(i);
                } catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Your hand: " + p1 + " Total: " + p1.getValue());
            System.out.println("Bot hand: " + p2 + " Total: " + p2.getValue());

            if (p1.getValue() > 21) {
                System.out.println("You busted. Bot wins.");
            } else if (p2.getValue() > 21) {
                System.out.println("Bot busted. You win!");
            } else if (p1.getValue() > p2.getValue()) {
                System.out.println("You win!");
            } else if (p1.getValue() < p2.getValue()) {
                System.out.println("Bot wins.");
            } else {
                System.out.println("Draw!");
            }
        }
    }

    public static Card optionHit(Deck deck, Hand player){
        Card newCard = deck.deal();
        System.out.println("Drawed card: " + newCard);
        player.deal(newCard);
        return newCard;
    }

    public static void stand(Deck deck, Hand bot){
        Card newCard = deck.deal();
        boolean isHit = botEvaluate(bot);

      while (true){
          if(!isHit){
              System.out.println("Bot stood.");
              break;
          }

          bot.deal(newCard);
          System.out.printf("The bot draws %s. Total: %s", newCard, bot.getValue());

          if(bot.getValue() > 21){
              System.out.println("The bot busted");
              break;
          }

      }
    }


    public static boolean botEvaluate(Hand bot){
        Random random = new Random();
        // The bot always hit if val is under 10
        if(bot.getValue() <= 12){
            return true;
        }
        if(bot.getValue() < 16){
            return random.nextBoolean(); // bot will take a gamble
        }

        return false;
    }
}

