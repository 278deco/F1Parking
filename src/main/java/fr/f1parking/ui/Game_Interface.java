package fr.f1parking.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


public class Game_Interface {

    private Scene game_scene;


    private Stage game_stage;


    public Game_Interface(final Coordinator d){

        //initialize scene
        GridPane root = new GridPane();
        ColumnConstraints game_column10 = new ColumnConstraints();
        game_column10.setPrefWidth(d.getWIDTH()/3);
        ColumnConstraints game_column12 = new ColumnConstraints();
        game_column12.setPrefWidth(470);
        ColumnConstraints game_column13 = new ColumnConstraints();
        game_column13.setPrefWidth(d.getWIDTH()/3);
        RowConstraints wallah = new RowConstraints();
        wallah.setPercentHeight(20);


        FlowPane flowtest = new FlowPane();
        flowtest.setPadding(new Insets(250,0,200,0));
        flowtest.setStyle("-fx-background-color: #B2BABB");
        flowtest.setAlignment(Pos.CENTER);
        root.getColumnConstraints().addAll(game_column10,game_column12, game_column13);
        root.getRowConstraints().add(wallah);
        root.add(flowtest,1,1);

        //initialize inside game
        GridPane game_root = new GridPane();
        ColumnConstraints game_column1 = new ColumnConstraints();
        game_column1.setPercentWidth(d.getWIDTH()/6);
        ColumnConstraints game_colun2 = new ColumnConstraints();
        game_colun2.setPercentWidth(d.getWIDTH()/6);
        ColumnConstraints game_colun3 = new ColumnConstraints();
        game_colun3.setPercentWidth(d.getWIDTH()/6);
        ColumnConstraints game_colun4 = new ColumnConstraints();
        game_colun4.setPercentWidth(d.getWIDTH()/6);
        ColumnConstraints game_colun5 = new ColumnConstraints();
        game_colun5.setPercentWidth(d.getWIDTH()/6);
        ColumnConstraints game_colun6 = new ColumnConstraints();
        game_colun6.setPercentWidth(d.getWIDTH()/6);

        //row matrix

        RowConstraints game_rowA = new RowConstraints();
        game_rowA.setPercentHeight(d.getHEIGHT()/6);
        RowConstraints game_rowB = new RowConstraints();
        game_rowB.setPercentHeight(d.getHEIGHT()/6);
        RowConstraints game_rowC = new RowConstraints();
        game_rowC.setPercentHeight(d.getHEIGHT()/6);
        RowConstraints game_rowD = new RowConstraints();
        game_rowD.setPercentHeight(d.getHEIGHT()/6);
        RowConstraints game_rowE = new RowConstraints();
        game_rowE.setPercentHeight(d.getHEIGHT()/6);
        RowConstraints game_rowF = new RowConstraints();
        game_rowF.setPercentHeight(d.getHEIGHT()/6);
        game_root.getRowConstraints().addAll(game_rowA, game_rowB, game_rowC, game_rowD, game_rowE, game_rowF);
        game_root.getColumnConstraints().addAll(game_column1,game_colun2,game_colun3,game_colun4,game_colun5,game_colun6);
        flowtest.getChildren().add(game_root);





        game_scene = new Scene(root, d.getWIDTH(), d.getHEIGHT());

       // MenuBar Game_menuebar = new MenuBar()


    }

    public Stage getGame_stage() {
        return game_stage;
    }
    public Scene getGame_scene() {
        return game_scene;
    }








}
