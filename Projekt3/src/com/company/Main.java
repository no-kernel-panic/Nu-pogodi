package com.company;

import javafx.application.Application;


public class Main {


    public static void main(String[] args) {

        HighScoresModel model = new HighScoresModel();
        Application.launch(Gui.class, args);


    }

}