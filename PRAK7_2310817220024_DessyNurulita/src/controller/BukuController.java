package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Buku;
import database.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BukuController {
    @FXML
    private TextField judulField;

    @FXML
    private TextField penulisField;

    @FXML
    private TextField hargaField;

    @FXML
    private TextField stokField;

    @FXML
    private TableView<Buku> tableView;

    @FXML
    private TableColumn<Buku, Integer> bukuIdColumn;

    @FXML
    private TableColumn<Buku, String> judulColumn;

    @FXML
    private TableColumn<Buku, String> penulisColumn;

    @FXML
    private TableColumn<Buku, Double> hargaColumn;

    @FXML
    private TableColumn<Buku, Integer> stokColumn;

    private ObservableList<Buku> bukuList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        bukuIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getBuku_id()));
        judulColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getJudul()));
        penulisColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPenulis()));
        hargaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getHarga()));
        stokColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getStok()));

        tableView.setItems(bukuList);
        loadDataFromDatabase();
    }

    public void handleAdd() {
        String judul = judulField.getText();
        String penulis = penulisField.getText();
        double harga;
        int stok;

        try {
            harga = Double.parseDouble(hargaField.getText());
            stok = Integer.parseInt(stokField.getText());
        } catch (NumberFormatException e) {
            showAlert("Harga dan stok harus berupa angka!");
            return;
        }

        if (judul.isEmpty() || penulis.isEmpty()) {
            showAlert("Judul dan penulis tidak boleh kosong!");
            return;
        }

        try (Connection connection = DBConnect.getConnection()) {
            String sql = "INSERT INTO buku (judul, penulis, harga, stok) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, judul);
            statement.setString(2, penulis);
            statement.setDouble(3, harga);
            statement.setInt(4, stok);
            statement.executeUpdate();

            loadDataFromDatabase();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Gagal menambahkan data.");
        }
    }

    public void handleUpdate() {
        Buku selectedBuku = tableView.getSelectionModel().getSelectedItem();
        if (selectedBuku != null) {
            String judul = judulField.getText();
            String penulis = penulisField.getText();
            double harga;
            int stok;

            try {
                harga = Double.parseDouble(hargaField.getText());
                stok = Integer.parseInt(stokField.getText());
            } catch (NumberFormatException e) {
                showAlert("Harga dan stok harus berupa angka!");
                return;
            }

            if (judul.isEmpty() || penulis.isEmpty()) {
                showAlert("Judul dan penulis tidak boleh kosong!");
                return;
            }

            try (Connection connection = DBConnect.getConnection()) {
                String sql = "UPDATE buku SET judul = ?, penulis = ?, harga = ?, stok = ? WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, judul);
                statement.setString(2, penulis);
                statement.setDouble(3, harga);
                statement.setInt(4, stok);
                statement.setInt(5, selectedBuku.getBuku_id());
                statement.executeUpdate();

                loadDataFromDatabase();
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Gagal memperbarui data.");
            }
        } else {
            showAlert("Pilih buku yang ingin di-update.");
        }
    }

    public void handleDelete() {
        Buku selectedBuku = tableView.getSelectionModel().getSelectedItem();
        if (selectedBuku != null) {
            try (Connection connection = DBConnect.getConnection()) {
                String sql = "DELETE FROM buku WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, selectedBuku.getBuku_id());
                statement.executeUpdate();

                loadDataFromDatabase();
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Gagal menghapus data.");
            }
        } else {
            showAlert("Pilih buku yang ingin dihapus.");
        }
    }

    private void loadDataFromDatabase() {
        bukuList.clear();
        try (Connection connection = DBConnect.getConnection()) {
            String sql = "SELECT * FROM buku";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int bukuId = resultSet.getInt("id");
                String judul = resultSet.getString("judul");
                String penulis = resultSet.getString("penulis");
                double harga = resultSet.getDouble("harga");
                int stok = resultSet.getInt("stok");
                bukuList.add(new Buku(bukuId, judul, penulis, harga, stok));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Gagal memuat data dari database.");
        }
    }

    private void clearFields() {
        judulField.clear();
        penulisField.clear();
        hargaField.clear();
        stokField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
