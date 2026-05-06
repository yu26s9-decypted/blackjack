package com.pluralsight.model;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand(){
        cards = new ArrayList<>();
    }

    public void deal(Card card){
        cards.add(card);
    }

    public int getSize(){
        return cards.size();
    }


    public int getValue(){
        int value = 0;

        for(Card card: cards){
            card.flip();
            value += card.getPointValue();
            card.flip();
        }

        return value;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Card card: cards){
           s.append(card.toString()).append(" ");
       }
       return s.toString();
    }
}
