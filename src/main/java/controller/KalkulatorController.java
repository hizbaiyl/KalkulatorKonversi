package controller;

import model.KonversiModel;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class KalkulatorController implements Initializable {

    @FXML
    private TextField inputNilai;

    @FXML
    private ComboBox<String> comboAsal;

    @FXML
    private ComboBox<String> comboTujuan;

    @FXML
    private Label labelHasil;

    // Menghubungkan ke Model
    private final KonversiModel model = new KonversiModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Daftar 4 satuan konversi
        ObservableList<String> daftarSatuan = FXCollections.observableArrayList(
            "Meter", "Feet", "Yard", "Miles"
        );

        // Memasukkan daftar ke dalam ComboBox
        comboAsal.setItems(daftarSatuan);
        comboTujuan.setItems(daftarSatuan);

        // Nilai bawaan (default) saat pertama kali dibuka
        comboAsal.setValue("Meter");
        comboTujuan.setValue("Feet");
    }

    @FXML
    private void handleKonversi(ActionEvent event) {
        String teksInput = inputNilai.getText().trim();

        // 1. Validasi: jika input masih kosong
        if (teksInput.isEmpty()) {
            labelHasil.setStyle("-fx-text-fill: red;");
            labelHasil.setText("Peringatan: Masukkan angka terlebih dahulu!");
            return;
        }

        try {
            // Pengguna bisa menginput menggunakan koma atau titik
            double nilai = Double.parseDouble(teksInput.replace(",", "."));

            // Validasi: satuan ukuran panjang tidak boleh negatif
            if (nilai < 0) {
                labelHasil.setStyle("-fx-text-fill: red;");
                labelHasil.setText("Peringatan: Ukuran panjang tidak boleh bernilai negatif!");
                return;
            }

            String asal = comboAsal.getValue();
            String tujuan = comboTujuan.getValue();

            // 2. Hitung melalui MODEL
            double hasil = model.konversi(nilai, asal, tujuan);

            // 3. Format angka menggunakan fungsi helper formatAngka()
            String teksNilai = formatAngka(nilai);
            String teksHasil = formatAngka(hasil);

            labelHasil.setStyle("-fx-text-fill: #1b5e20; -fx-font-weight: bold;");
            labelHasil.setText(teksNilai + " " + asal + " = " + teksHasil + " " + tujuan);

        } catch (NumberFormatException e) {
            labelHasil.setStyle("-fx-text-fill: red;");
            labelHasil.setText("Peringatan: Masukkan angka yang valid!");
        }
    }

    @FXML
    private void handleReset(ActionEvent event) {
        inputNilai.clear();
        labelHasil.setText("");
        comboAsal.setValue("Meter");
        comboTujuan.setValue("Feet");
    }

    /**
     * Fungsi pembantu untuk memformat angka:
     * - Menggunakan pemisah koma (,)
     * - Jika bulat (misal 10.0), menghasilkan "10" (tanpa koma)
     * - Jika desimal (misal 3.28084), menghasilkan "3,2808" (maksimal 4 desimal)
     */
    private String formatAngka(double angka) {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols();
        simbol.setDecimalSeparator(','); // Gunakan koma untuk desimal

        // Pola '0.####' berarti jika angka di belakang koma 0, koma tidak ditampilkan
        DecimalFormat df = new DecimalFormat("0.####", simbol);
        return df.format(angka);
    }
}