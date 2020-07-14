package com.company;

import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Gui extends Application {

    int score = 0;
    int brokenEggCounter = 0;

    //initial speed of falling eggs

    int durationTransition = 10;

    @Override
    public void start(Stage stage) {


        //setting the action scene with a pane as root
        Pane gameplayPane = new Pane();
        Scene actionScene = new Scene(gameplayPane, 890, 700);
        stage.setResizable(false);
        //the wolf image in the main menu
        Image wolfImage = new Image("file:../Projekt3/src/wolf.png");
        ImageView wolfImagewiew = new ImageView(wolfImage);

        //setting the rectangles that will be used to detect collision Egg-Basket
        Rectangle rectangle = new Rectangle(40, 40);
        rectangle.setLayoutX(190);
        rectangle.setLayoutY(320);
        rectangle.setFill(Color.TRANSPARENT);

        Rectangle rectangle1 = new Rectangle(40, 40);
        rectangle1.setLayoutX(170);
        rectangle1.setLayoutY(450);
        rectangle1.setFill(Color.TRANSPARENT);

        Rectangle rectangle2 = new Rectangle(40, 40);
        rectangle2.setLayoutX(650);
        rectangle2.setLayoutY(320);
        rectangle2.setFill(Color.TRANSPARENT);

        Rectangle rectangle3 = new Rectangle(40, 40);
        rectangle3.setLayoutX(680);
        rectangle3.setLayoutY(450);
        rectangle3.setFill(Color.TRANSPARENT);

        //creating the one and only egg
        Ellipse ellipse = new Ellipse(10, 20);
        ellipse.setFill(Color.GOLD);

        //the upper right black broken eggs
        Image eggCounterImage = new Image("file:../Projekt3/src/eggCounter.png");

        ImageView eggCounterView = new ImageView(eggCounterImage);
        eggCounterView.setLayoutX(260);
        eggCounterView.setLayoutY(-300);
        eggCounterView.setVisible(false);

        ImageView eggCounterView1 = new ImageView(eggCounterImage);
        eggCounterView1.setLayoutX(190);
        eggCounterView1.setLayoutY(-300);
        eggCounterView1.setVisible(false);

        ImageView eggCounterView2 = new ImageView(eggCounterImage);
        eggCounterView2.setLayoutX(120);
        eggCounterView2.setLayoutY(-300);
        eggCounterView2.setVisible(false);


        //the lower yellow broken eggs
        Image brokenEgg = new Image("file:../Projekt3/src/brokenegg.png");

        ImageView brokeneggView = new ImageView(brokenEgg);
        brokeneggView.setLayoutX(650);
        brokeneggView.setLayoutY(500);
        brokeneggView.setVisible(false);

        ImageView brokeneggView1 = new ImageView(brokenEgg);
        brokeneggView1.setLayoutX(150);
        brokeneggView1.setLayoutY(500);
        brokeneggView1.setVisible(false);


        //the background picture with the chickens and rabit
        ImageView chickensView = new ImageView(new Image("file:../Projekt3/src/chickens.png"));

        //the wolf and its 4 different positions
        ImageView uprightView = new ImageView(new Image("file:../Projekt3/src/upright1.png"));
        ImageView downrightView = new ImageView(new Image("file:../Projekt3/src/downright1.png"));
        ImageView upleftView = new ImageView(new Image("file:../Projekt3/src/upleft1.png"));
        ImageView downleftView = new ImageView(new Image("file:../Projekt3/src/downleft1.png"));

        //the total score
        Label punkty = new Label();
        punkty.setFont(Font.font("Serif", 50));
        punkty.setLayoutX(720);
        punkty.setLayoutY(0);

        //The upper left falling egg animation:
        PathTransition eggPathTransition = new PathTransition(Duration.seconds(durationTransition), ellipse);
        Path path = new Path();
        path.getElements().add(new MoveTo(30, 245));
        path.getElements().add(new LineTo(240, 340));
        eggPathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        eggPathTransition.setPath(path);
        eggPathTransition.setNode(ellipse);
        RotateTransition eggRotateTransition = new RotateTransition(Duration.millis(1000), ellipse);
        eggRotateTransition.setByAngle(360);
        eggRotateTransition.setCycleCount(100);
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(eggPathTransition, eggRotateTransition);
        parallelTransition.setCycleCount(Animation.INDEFINITE);

        //The lower left falling egg animation
        PathTransition eggPathTransition1 = new PathTransition(Duration.seconds(durationTransition), ellipse);
        Path path1 = new Path();
        path1.getElements().add(new MoveTo(30, 358));
        path1.getElements().add(new LineTo(240, 460));
        eggPathTransition1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        eggPathTransition1.setPath(path1);
        eggPathTransition1.setNode(ellipse);
        RotateTransition eggRotateTransition1 = new RotateTransition(Duration.millis(1000), ellipse);
        eggRotateTransition1.setByAngle(360);
        eggRotateTransition1.setCycleCount(100);
        ParallelTransition parallelTransition1 = new ParallelTransition();
        parallelTransition1.getChildren().addAll(eggPathTransition1, eggRotateTransition1);
        parallelTransition1.setCycleCount(Animation.INDEFINITE);

        //the upper right falling egg animation
        PathTransition eggPathTransition2 = new PathTransition(Duration.seconds(durationTransition), ellipse);
        Path path2 = new Path();
        path2.getElements().add(new MoveTo(850, 230));
        path2.getElements().add(new LineTo(600, 380));
        eggPathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        eggPathTransition2.setPath(path2);
        eggPathTransition2.setNode(ellipse);
        RotateTransition eggRotateTransition2 = new RotateTransition(Duration.millis(1000), ellipse);
        eggRotateTransition2.setByAngle(-360);
        eggRotateTransition2.setCycleCount(100);
        ParallelTransition parallelTransition2 = new ParallelTransition();
        parallelTransition2.getChildren().addAll(eggPathTransition2, eggRotateTransition2);
        parallelTransition2.setCycleCount(Animation.INDEFINITE);

        //the lower right falling egg animation
        PathTransition eggPathTransition3 = new PathTransition(Duration.seconds(durationTransition), ellipse);
        Path path3 = new Path();
        path3.getElements().add(new MoveTo(850, 350));
        path3.getElements().add(new LineTo(600, 500));
        eggPathTransition3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        eggPathTransition3.setPath(path3);
        eggPathTransition3.setNode(ellipse);
        RotateTransition eggRotateTransition3 = new RotateTransition(Duration.millis(1000), ellipse);
        eggRotateTransition3.setByAngle(-360);
        eggRotateTransition3.setCycleCount(100);
        ParallelTransition parallelTransition3 = new ParallelTransition();
        parallelTransition3.getChildren().addAll(eggPathTransition3, eggRotateTransition3);
        parallelTransition3.setCycleCount(Animation.INDEFINITE);


        //the javafx thread 60fps
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {


                punkty.textProperty().bind(new SimpleIntegerProperty(score).asString());//line of code taken from https://stackoverflow.com/questions/33146167/javafx-binding-label-with-int-value

                //4 fallen eggs, you lose
                if (brokenEggCounter >= 4) {
                    parallelTransition.stop();
                    parallelTransition1.stop();
                    parallelTransition2.stop();
                    parallelTransition3.stop();
                    gameplayPane.getChildren().add(GameOver());
                    this.stop();
                }


                //collision detection (code taken from https://stackoverflow.com/questions/49960670/animationtimer-for-collision-detection)

                Shape intersect = Shape.intersect(ellipse, rectangle);

                if (intersect.getBoundsInLocal().getWidth() != -1) {
                    //if egg colides with the upperleft rectange and the wolf is with basket up left:
                    if (upleftView.isVisible()) {
                        score++;
                        if (durationTransition > 1) //Increasing the speed of the falling eggss
                            durationTransition--;
                        wybracPath(parallelTransition, parallelTransition1, parallelTransition2, parallelTransition3); //choosing a random next animation
                        brokeneggView.setVisible(false); //cleaning up the screen
                        brokeneggView1.setVisible(false);

                        //updating the speed of the path transition
                        eggPathTransition.setDuration(Duration.seconds(durationTransition));
                        eggPathTransition1.setDuration(Duration.seconds(durationTransition));
                        eggPathTransition2.setDuration(Duration.seconds(durationTransition));
                        eggPathTransition3.setDuration(Duration.seconds(durationTransition));

                    } else {
                        brokenEggCounter++;

                        setImageEgg(eggCounterView, eggCounterView1, eggCounterView2);

                        brokeneggView1.setVisible(true);

                        wybracPath(parallelTransition, parallelTransition1, parallelTransition2, parallelTransition3);

                    }

                }


                Shape intersect1 = Shape.intersect(ellipse, rectangle1);
                if (intersect1.getBoundsInLocal().getWidth() != -1) {

                    if (downleftView.isVisible()) {
                        score++;
                        if (durationTransition > 1)
                            durationTransition--;

                        wybracPath(parallelTransition, parallelTransition1, parallelTransition2, parallelTransition3);

                        brokeneggView.setVisible(false);
                        brokeneggView1.setVisible(false);

                        eggPathTransition.setDuration(Duration.seconds(durationTransition));
                        eggPathTransition1.setDuration(Duration.seconds(durationTransition));
                        eggPathTransition2.setDuration(Duration.seconds(durationTransition));
                        eggPathTransition3.setDuration(Duration.seconds(durationTransition));


                    } else {

                        brokenEggCounter++;
                        setImageEgg(eggCounterView, eggCounterView1, eggCounterView2);

                        brokeneggView1.setVisible(true);
                        wybracPath(parallelTransition, parallelTransition1, parallelTransition2, parallelTransition3);
                    }

                }


                Shape intersect2 = Shape.intersect(ellipse, rectangle2);

                if (intersect2.getBoundsInLocal().getWidth() != -1) {

                    if (uprightView.isVisible()) {
                        score++;
                        if (durationTransition > 1)
                            durationTransition--;

                        wybracPath(parallelTransition, parallelTransition1, parallelTransition2, parallelTransition3);
                        brokeneggView.setVisible(false);
                        brokeneggView1.setVisible(false);
                        eggPathTransition.setDuration(Duration.seconds(durationTransition));
                        eggPathTransition1.setDuration(Duration.seconds(durationTransition));
                        eggPathTransition2.setDuration(Duration.seconds(durationTransition));
                        eggPathTransition3.setDuration(Duration.seconds(durationTransition));

                    } else {
                        brokenEggCounter++;
                        setImageEgg(eggCounterView, eggCounterView1, eggCounterView2);

                        brokeneggView.setVisible(true);
                        wybracPath(parallelTransition, parallelTransition1, parallelTransition2, parallelTransition3);

                    }
                }


                Shape intersect3 = Shape.intersect(ellipse, rectangle3);

                if (intersect3.getBoundsInLocal().getWidth() != -1) {
                    if (downrightView.isVisible()) {
                        score++;
                        if (durationTransition > 1)
                            durationTransition--;

                        wybracPath(parallelTransition, parallelTransition1, parallelTransition2, parallelTransition3);
                        brokeneggView.setVisible(false);
                        brokeneggView1.setVisible(false);
                        eggPathTransition.setDuration(Duration.seconds(durationTransition));
                        eggPathTransition1.setDuration(Duration.seconds(durationTransition));
                        eggPathTransition2.setDuration(Duration.seconds(durationTransition));
                        eggPathTransition3.setDuration(Duration.seconds(durationTransition));

                    } else {

                        brokenEggCounter++;
                        setImageEgg(eggCounterView, eggCounterView1, eggCounterView2);
                        brokeneggView.setVisible(true);
                        wybracPath(parallelTransition, parallelTransition1, parallelTransition2, parallelTransition3);

                    }
                }
            }

        };

        //adding every component to the game play pane:
        gameplayPane.getChildren().addAll(chickensView, uprightView, ellipse, downrightView, upleftView, downleftView, rectangle, rectangle1,
                rectangle2, rectangle3, brokeneggView, brokeneggView1, punkty, eggCounterView, eggCounterView1, eggCounterView2);

        // setting the wolf in it's initial position
        downleftView.setVisible(true);
        downrightView.setVisible(false);
        upleftView.setVisible(false);
        uprightView.setVisible(false);

        Stage actionStage = new Stage();
        actionStage.initModality(Modality.APPLICATION_MODAL);
        actionStage.setResizable(false);
        actionStage.setOnCloseRequest(t -> Platform.exit());

                actionStage.setScene(actionScene);
                actionStage.setTitle("Nupogodi!");

                //defining the movement
                actionScene.setOnKeyPressed(event -> {

                    if (event.isControlDown() && event.isShiftDown() && (event.getCode() == KeyCode.Q)) {
                        score = 0;
                        brokenEggCounter = 0;
                        timer.stop();
                        eggCounterView.setVisible(false);
                        eggCounterView1.setVisible(false);
                        eggCounterView2.setVisible(false);

                        actionStage.close();


                    }
                    switch (event.getCode()) {
                        case S:
                            System.out.println("down left");
                            downleftView.setVisible(true);
                            downrightView.setVisible(false);
                            upleftView.setVisible(false);
                            uprightView.setVisible(false);


                            break;
                        case W:
                            System.out.println("up left");
                            upleftView.setVisible(true);
                            downleftView.setVisible(false);
                            downrightView.setVisible(false);
                            uprightView.setVisible(false);

                            break;
                        case E:
                            System.out.println("up right");
                            uprightView.setVisible(true);
                            upleftView.setVisible(false);
                            downleftView.setVisible(false);
                            downrightView.setVisible(false);


                            break;
                        case D:
                            System.out.println("down right");
                            downrightView.setVisible(true);
                            uprightView.setVisible(false);
                            upleftView.setVisible(false);
                            downleftView.setVisible(false);


                            break;

                    }
                });


                //fetching the highscore data from the class HighscoresModel
                ObservableList<String> items = FXCollections.observableArrayList(HighScoresModel.hsList
                );

                //putting the data in the list
                ListView<String> list = new ListView<>(items);

                Button highScoresButton = new Button("High Scores");
                highScoresButton.setStyle("-fx-background-color: #fdff5e; ");
                highScoresButton.setPrefSize(350, 100);
                highScoresButton.setFont(Font.font("Serif", 50));

                //putting the list as a root element on the highscores scene
                Scene highScores = new Scene(list, 200, 400);
                Stage stageHighScores = new Stage();
                stageHighScores.setScene(highScores);
                stageHighScores.setTitle("HighScores");

                highScoresButton.setOnAction(actionEvent -> {


                    stageHighScores.show();


                });

                Button exitButton = new Button("Exit");
                exitButton.setStyle("-fx-background-color: #fdff5e; ");
                exitButton.setPrefSize(200, 100);
                exitButton.setFont(Font.font("Serif", 50));
                exitButton.setOnAction(actionEvent -> {
                    Platform.exit();
                });


                Button startButton = new Button("Start");
                startButton.setPrefSize(200, 100);
                startButton.setFont(Font.font("Serif", 50));
                startButton.setStyle("-fx-background-color: #fdff5e; ");
                startButton.setOnAction(actionEvent -> {

                    actionStage.show();
                    timer.start();
                    wybracPath(parallelTransition, parallelTransition1, parallelTransition2, parallelTransition3);

                });

                VBox buttonVbox = new VBox(10);
                buttonVbox.setMaxSize(500, 600);
                buttonVbox.getChildren().addAll(startButton, highScoresButton, exitButton);
                buttonVbox.setAlignment(Pos.BOTTOM_CENTER);

                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(wolfImagewiew);
                stackPane.getChildren().add(buttonVbox);

                Scene sceneMainMenu = new Scene(stackPane, 850, 630);
                stage.setScene(sceneMainMenu);
                stage.setTitle("NU POGODI!");
                stage.show();

            }


            public void wybracPath(ParallelTransition a, ParallelTransition b, ParallelTransition c, ParallelTransition d) {
                double random = Math.random();
                if (random <= 0.25)
                    a.playFromStart();
                else if (random <= 0.50)
                    b.playFromStart();
                else if (random <= 0.75)
                    c.playFromStart();
                else
                    d.playFromStart();
            }

            public void setImageEgg(ImageView a, ImageView b, ImageView c) {
                if (brokenEggCounter == 1)
                    a.setVisible(true);
                if (brokenEggCounter == 2)
                    b.setVisible(true);
                if (brokenEggCounter == 3)
                    c.setVisible(true);

            }


            public void appendStrToFile(String fileName, // appendStrToFile method taken from https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
                                        String str) {
                try {
                    BufferedWriter out = new BufferedWriter(
                            new FileWriter(fileName, true));
                    out.write(str);
                    out.close();
                } catch (IOException e) {
                    System.out.println("exception occoured" + e);
                }
            }


            public Group GameOver() {

                Label gameOver = new Label("Game over!");
                gameOver.setFont(Font.font("Serif", 100));
                gameOver.setStyle("-fx-text-fill: #ff0d00;");
                gameOver.setLayoutX(-70);
                gameOver.setLayoutY(-100);
                Label label1 = new Label("Name:");
                label1.setFont(Font.font("Serif", 25));
                TextField textField = new TextField();
                textField.setPromptText("Enter your name:");
                Button submit = new Button("Submit");

                submit.setOnAction(e -> {
                    String userName = textField.getText();
                    System.out.println(textField.getText());
                    String fileName = "..\\Projekt3\\src\\com\\company\\highscores.txt";
                    String str = userName + " : " + score + System.lineSeparator();
                    appendStrToFile(fileName, str);
                });

                HBox hb = new HBox();
                hb.getChildren().addAll(label1, textField, submit);
                hb.setSpacing(10);
                Group group = new Group(hb, gameOver);
                group.setLayoutX(300);
                group.setLayoutY(400);
                return group;
            }
        }

