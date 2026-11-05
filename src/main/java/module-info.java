module KalkulatorKonversi {
    requires javafx.controls;
    requires javafx.fxml;

    opens controller to javafx.fxml; // Wajib agar Scene Builder / FXML bisa akses Controller
    exports main;
}