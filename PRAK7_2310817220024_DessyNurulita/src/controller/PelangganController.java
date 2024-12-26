package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Pelanggan;
import database.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PelangganController {
    @FXML
    private TextField namaField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField teleponField;

    @FXML
    private TableView<Pelanggan> tableView;

    @FXML
    private TableColumn<Pelanggan, Integer> idColumn;

    @FXML
    private TableColumn<Pelanggan, String> namaColumn;

    @FXML
    private TableColumn<Pelanggan, String> emailColumn;

    @FXML
    private TableColumn<Pelanggan, String> teleponColumn;

    private ObservableList<Pelanggan> pelangganList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getId()));
        namaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNama()));
        emailColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEmail()));
        teleponColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTelepon()));

        tableView.setItems(pelangganList);
        loadDataFromDatabase();
    }

    public void handleAdd() {
        String nama = namaField.getText();
        String email = emailField.getText();
        String telepon = teleponField.getText();

        if (nama.isEmpty() || email.isEmpty() || telepon.isEmpty()) {
            showAlert("Semua field harus diisi!");
            return;
        }

        try (Connection connection = DBConnect.getConnection()) {
            String sql = "INSERT INTO pelanggan (nama, email, telepon) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nama);
            statement.setString(2, email);
            statement.setString(3, telepon);
            statement.executeUpdate();

            loadDataFromDatabase();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Gagal menambahkan data.");
        }
    }

    public void handleUpdate() {
        Pelanggan selectedPelanggan = tableView.getSelectionModel().getSelectedItem();
        if (selectedPelanggan != null) {
            String nama = namaField.getText();
            String email = emailField.getText();
            String telepon = teleponField.getText();

            if (nama.isEmpty() || email.isEmpty() || telepon.isEmpty()) {
                showAlert("Semua field harus diisi!");
                return;
            }

            try (Connection connection = DBConnect.getConnection()) {
                String sql = "UPDATE pelanggan SET nama = ?, email = ?, telepon = ? WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, nama);
                statement.setString(2, email);
                statement.setString(3, telepon);
                statement.setInt(4, selectedPelanggan.getId());
                statement.executeUpdate();

                loadDataFromDatabase();
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Gagal memperbarui data.");
            }
        } else {
            showAlert("Pilih pelanggan yang ingin di-update.");
        }
    }

    public void handleDelete() {
        Pelanggan selectedPelanggan = tableView.getSelectionModel().getSelectedItem();
        if (selectedPelanggan != null) {
            try (Connection connection = DBConnect.getConnection()) {
                String sql = "DELETE FROM pelanggan WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, selectedPelanggan.getId());
                statement.executeUpdate();

                loadDataFromDatabase();
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Gagal menghapus data.");
            }
        } else {
            showAlert("Pilih pelanggan yang ingin dihapus.");
        }
    }

    private void loadDataFromDatabase() {
        pelangganList.clear();
        try (Connection connection = DBConnect.getConnection()) {
            String sql = "SELECT * FROM pelanggan";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                String email = resultSet.getString("email");
                String telepon = resultSet.getString("telepon");
                pelangganList.add(new Pelanggan(id, nama, email, telepon));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Gagal memuat data dari database.");
        }
    }

    private void clearFields() {
        namaField.clear();
        emailField.clear();
        teleponField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
