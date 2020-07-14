package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class HighScoresModel {

    static List<String> hsList;
    Path path = Paths.get("..\\Projekt3\\src\\com\\company\\highscores.txt");

    public HighScoresModel() {

        try {
            hsList = Files.lines(path).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

