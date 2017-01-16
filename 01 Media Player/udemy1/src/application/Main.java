package application;

/**
 * Created by HAWK-VAIO on 1/15/2017.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    Player player;
    FileChooser fileChooser;

    @Override
    public void start(Stage primaryStage) throws Exception{

        /*add menu to open new files*/
        MenuItem open = new MenuItem("Open");
        Menu file = new Menu("file");
        MenuBar menu = new MenuBar();

        file.getItems().add(open);
        menu.getMenus().add(file);

        /*Add action when open file menu is clicked*/
        fileChooser = new FileChooser();
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.player.pause();
                File file = fileChooser.showOpenDialog(primaryStage);
                if(file != null){
                    try {
                        player = new Player(file.toURI().toURL().toExternalForm());
                        Scene scene = new Scene(player, 720, 535, Color.BLACK);
                        primaryStage.setScene(scene);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });


        player = new Player("file:///C://Users//HAWK-VAIO//Downloads//MediaPlayerS1.mp4");

        /*set open file menu to be at the top*/
        player.setTop(menu);

        Scene scene = new Scene(player, 720, 535, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
