package com.benjaminkiesling.zauberkugel;

import java.util.Random;

public class Zauberkugel {
    //Member variables
    public String[] mAnswers = {
            "Die Geister sagen ja...",
            "Das Schicksal liegt auf deiner Seite",
            "Überdenke die Konsequenzen",
            "Deine Tat bringt mehr als du denkst",
            "Undeutliches,wird klarer werden.",
            "Geniesse erstmal eine Erfrischung",
            "Ruhe und sammel deine Energie",
            "Das Jenseits ist fern...",
            "Der Preis überwiegt den Wert",
            "Dein zukünftiges ich ist entäuscht von dir",
            "Die Last wird groß sein",
            "Dieser Weg ist steinig geflastert",
            "Die Geister sind unstimmig",
            "Die Folgen sind verheerend"
            };
    
    //no need for explicit Constructor, standard Constructor is sufficient
    
    //Methods
    
    public String getAnswer(){


        Random rnd = new Random();
        String answer ="Network connection problems";
        int prediction = rnd.nextInt(mAnswers.length);
        answer = mAnswers[prediction];
        return answer;
        }

}
