package com.pluralsight.model;

public class Card {
    private String suit;
    private String value;
    private boolean isFaceUp;

    public Card(String suit, String value){
        this.suit = suit;
        this.value = value;
        this.isFaceUp = false;
    }

    public String getSuit(){
        if(isFaceUp){
            return suit;
        } else {
            return "#";
        }
    }

    public String getValue(){
        if(isFaceUp){
            return value;
        } else {
            return "#";
        }
    }

    public int getPointValue(){
        if(isFaceUp){
            if(value.equals("A")) return 11;
            if(value.equals("K") || value.equals("Q") || value.equals("J")) return 10;
            return Integer.parseInt(this.value);

        } else {
            return 0;
        }
    }



    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setFaceUp(boolean faceUp) {
        isFaceUp = faceUp;
    }

    public boolean isFaceUp(){
        return  isFaceUp;
    }

    public boolean flip(){
        return ((isFaceUp = !isFaceUp));
    }

    @Override
    public String toString() {
        return "[" + value + "]";
    }
}
