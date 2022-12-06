package fr.f1parking.ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static java.lang.Long.MAX_VALUE;


public class Game_Interface {

    private Scene game_scene;


    private Stage game_stage;


    public Game_Interface(final Coordinator d){



        //initialize scene

        GridPane first_gridpane = new GridPane();
        first_gridpane.setStyle("-fx-background-color: #333333ff");
        ColumnConstraints first_gridpane_left_column = new ColumnConstraints();
        first_gridpane_left_column.setPercentWidth(20);
        ColumnConstraints first_gridpane_center_column = new ColumnConstraints();
        first_gridpane_center_column.setPercentWidth(60);
        ColumnConstraints first_gridpane_right_column = new ColumnConstraints();
        first_gridpane_right_column.setPercentWidth(20);

        RowConstraints first_gridpane_top_row = new RowConstraints();
        first_gridpane_top_row.setPercentHeight(5);
        RowConstraints first_gridpane_center_row = new RowConstraints();
        first_gridpane_center_row.setPercentHeight(90);
        RowConstraints first_gridpane_bottom_row = new RowConstraints();
        first_gridpane_bottom_row.setPercentHeight(5);

        // gridpane to set button on the left

        GridPane left_gridpane = new GridPane();
        left_gridpane.setGridLinesVisible(true);

        // column used in left gridpane

        ColumnConstraints left = new ColumnConstraints();
        left.setPercentWidth(33);
        ColumnConstraints center = new ColumnConstraints();
        center.setPercentWidth(33);
        ColumnConstraints right = new ColumnConstraints();
        right.setPercentWidth(33);

        // row used in left gridpane

        RowConstraints left_top = new RowConstraints();
        left_top.setPercentHeight(50);
        RowConstraints left_center = new RowConstraints();
        left_center.setPercentHeight(20);
        RowConstraints left_bottom = new RowConstraints();
        left_bottom.setPercentHeight(30);

        left_gridpane.getRowConstraints().addAll(left_top, left_center, left_bottom);
        left_gridpane.getColumnConstraints().addAll(left, center, right);
        first_gridpane.add(left_gridpane, 0,1);



        first_gridpane.getColumnConstraints().addAll(first_gridpane_left_column,first_gridpane_center_column, first_gridpane_right_column);
        first_gridpane.getRowConstraints().addAll(first_gridpane_top_row, first_gridpane_center_row, first_gridpane_bottom_row);


        //initialize inside game
        GridPane game_root = new GridPane();
        game_root.setGridLinesVisible(true);
        game_root.setAlignment(Pos.CENTER);
        ColumnConstraints game_column1 = new ColumnConstraints();
        //game_column1.setPrefWidth(flowtest.getWidth()/6);
        game_column1.setPercentWidth(16);
        ColumnConstraints game_colun2 = new ColumnConstraints();
        //game_colun2.setPrefWidth(flowtest.getWidth()/6);
        game_colun2.setPercentWidth(16);
        ColumnConstraints game_colun3 = new ColumnConstraints();
        //game_colun3.setPrefWidth(flowtest.getWidth()/6);
        game_colun3.setPercentWidth(16);
        ColumnConstraints game_colun4 = new ColumnConstraints();
        //game_colun4.setPrefWidth(flowtest.getWidth()/6);
        game_colun4.setPercentWidth(16);
        ColumnConstraints game_colun5 = new ColumnConstraints();
        //game_colun5.setPrefWidth(flowtest.getWidth()/6);
        game_colun5.setPercentWidth(16);
        ColumnConstraints game_colun6 = new ColumnConstraints();
        //game_colun6.setPrefWidth(flowtest.getWidth()/6);
        game_colun6.setPercentWidth(16);
        game_root.setStyle("-fx-background-color: #6c42f5");




        //row matrix

        RowConstraints game_rowA = new RowConstraints();
        //game_rowA.setPrefHeight(flowtest.getHeight()/6);
        game_rowA.setPercentHeight(16);
        RowConstraints game_rowB = new RowConstraints();
        //game_rowB.setPrefHeight(flowtest.getHeight()/6);
        game_rowB.setPercentHeight(16);
        RowConstraints game_rowC = new RowConstraints();
        //game_rowC.setPrefHeight(flowtest.getHeight()/6);
        game_rowC.setPercentHeight(16);
        RowConstraints game_rowD = new RowConstraints();
        //game_rowD.setPrefHeight(flowtest.getHeight()/6);
        game_rowD.setPercentHeight(16);
        RowConstraints game_rowE = new RowConstraints();
        //game_rowE.setPrefHeight(flowtest.getHeight()/6);
        game_rowE.setPercentHeight(16);
        RowConstraints game_rowF = new RowConstraints();
        //game_rowF.setPrefHeight(flowtest.getHeight()/6);
        game_rowF.setPercentHeight(16);
        game_root.getRowConstraints().addAll(game_rowA, game_rowB, game_rowC, game_rowD, game_rowE, game_rowF);
        game_root.getColumnConstraints().addAll(game_column1,game_colun2,game_colun3,game_colun4,game_colun5,game_colun6);
        first_gridpane.add(game_root, 1,1);

        //button

        Button Z = new Button("Z");
        button_style(Z);
        left_gridpane.add(Z, 1,1);
        GridPane.setHalignment(Z, HPos.CENTER);
        btnHover(Z);
        Button Q = new Button("Q");
        button_style(Q);
        left_gridpane.add(Q, 0,2);
        GridPane.setHalignment(Q, HPos.CENTER);
        GridPane.setValignment(Q, VPos.TOP);
        btnHover(Q);
        Button S = new Button("S");
        button_style(S);
        left_gridpane.add(S, 1,2);
        GridPane.setHalignment(S, HPos.CENTER);
        GridPane.setValignment(S, VPos.TOP);
        btnHover(S);
        Button D = new Button("D");
        button_style(D);
        left_gridpane.add(D, 2,2);
        GridPane.setHalignment(D, HPos.CENTER);
        GridPane.setValignment(D, VPos.TOP);
        btnHover(D);

        Button exit = new Button("exit");
        btnHover(exit);
        exit.setPrefSize(150, 60);
        exit.setAlignment(Pos.CENTER);
        exit.setStyle("-fx-background-color: #B2BABB ;-fx-background-radius: 15px");
        exit.setOnAction(event -> {
            d.change_scene(2);
        });
        first_gridpane.add(exit, 2, 0);
        GridPane.setHalignment(exit, HPos.CENTER);

        Button btn = new Button();
        btn.setText("Open Dialog");
        btn.setOnAction(
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {
                        final Stage dialog = new Stage();
                        dialog.initModality(Modality.APPLICATION_MODAL);
                        dialog.initOwner(game_stage);
                        VBox dialogVbox = new VBox(20);
                        HBox top = new HBox();
                        HBox bottom = new HBox();
                        Label congrats = new Label("congratulation, you won");
                        //congrats.setStyle();
                        Button return_menu = new Button("click to return");
                        return_menu.setOnAction(event1 -> {
                            d.change_scene(2);
                        });
                        Button exit = new Button("exit game");
                        exit.setOnAction(event1 -> {
                            Platform.exit();

                        });
                        top.getChildren().add(congrats);
                        bottom.getChildren().addAll(return_menu, exit);
                        dialogVbox.getChildren().addAll(top, bottom);
                        Scene dialogScene = new Scene(dialogVbox, 500, 400);
                        dialog.setScene(dialogScene);
                        dialog.show();
                    }
                });
        first_gridpane.add(btn, 2,2);
        GridPane.setValignment(btn, VPos.CENTER);









        game_scene = new Scene(first_gridpane, d.getWIDTH(), d.getHEIGHT());

       // MenuBar Game_menuebar = new MenuBar()


    }
    public Scene getGame_scene() {
        return game_scene;
    }
    public Button button_style (Button bouton){
        bouton.setPrefSize(80, 80);
        bouton.setStyle("-fx-background-color: #B2BABB ;-fx-background-radius: 15px; -fx-font-size: 20px ");
        return bouton;
    }
    public void btnHover(Button btn) {
        btn.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                btn.setStyle("-fx-background-color: #424040; -fx-text-fill: white; -fx-font-size: 20px;-fx-background-radius: 20px");
                btn.setEffect(new javafx.scene.effect.DropShadow(10, Color.web("#000000")));
                game_scene.setCursor(javafx.scene.Cursor.HAND);
            } else {
                btn.setStyle(null);
                btn.setEffect(null);
                btn.setPrefSize(80, 80);
                btn.setStyle("-fx-background-color: #B2BABB ;-fx-background-radius: 15px; -fx-font-size: 20px ");
                game_scene.setCursor(javafx.scene.Cursor.DEFAULT);
            }
        });


    }
}
