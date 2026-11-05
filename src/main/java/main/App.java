package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/KalkulatorView.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root); // Hapus angka lebar dan tingginya
        stage.setTitle("Kalkulator Konversi Satuan");
        stage.setScene(scene);
        stage.sizeToScene(); // Otomatis pas sesuai ukuran Scene Builder
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}