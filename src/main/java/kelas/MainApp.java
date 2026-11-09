package kelas;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Navigator nav = new Navigator(stage);
        nav.bukaDaftar();
    }

    public static void main(String[] args) {
        launch(args);
    }
}