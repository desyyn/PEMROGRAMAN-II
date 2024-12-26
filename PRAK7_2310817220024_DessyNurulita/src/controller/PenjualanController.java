package controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import database.DBConnect;
import model.Pelanggan;
import model.Buku;
import model.Penjualan;

import java.sql.*;
import java.time.LocalDate;

public class PenjualanController {
    @FXML
    private TextField jumlahField;

    @FXML
    private DatePicker tanggalPicker;

    @FXML
    private ComboBox<Pelanggan> pelangganComboBox;

    @FXML
    private ComboBox<Buku> bukuComboBox;

    @FXML
    private TableView<Penjualan> tableView;

    @FXML
    private TableColumn<Penjualan, Integer> idColumn;

    @FXML
    private TableColumn<Penjualan, Integer> jumlahColumn;

    @FXML
    private TableColumn<Penjualan, Double> totalHargaColumn;

    @FXML
    private TableColumn<Penjualan, LocalDate> tanggalColumn;

    private ObservableList<Penjualan> penjualanList = FXCollections.observableArrayList();
    private ObservableList<Pelanggan> pelangganList = FXCollections.observableArrayList();
    private ObservableList<Buku> bukuList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up kolom tabel
    	 idColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
    	    jumlahColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getJumlah()).asObject());
    	    totalHargaColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getTotalHarga()).asObject());
    	    tanggalColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getTanggal()));  // Perbaiki penggunaan SimpleObjectProperty

        tableView.setItems(penjualanList);

        loadPelangganData();
        loadBukuData();
        loadPenjualanData();
    }

    // Load data pelanggan
    private void loadPelangganData() {
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
            pelangganComboBox.setItems(pelangganList);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Gagal memuat data pelanggan.");
        }
    }

    // Load data buku
    private void loadBukuData() {
        try (Connection connection = DBConnect.getConnection()) {
            String sql = "SELECT * FROM buku";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String judul = resultSet.getString("judul");
                String penulis = resultSet.getString("penulis");
                double harga = resultSet.getDouble("harga");
                int stok = resultSet.getInt("stok");
                bukuList.add(new Buku(id, judul, penulis, harga, stok));
            }
            bukuComboBox.setItems(bukuList);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Gagal memuat data buku.");
        }
    }

    // Load data penjualan
    private void loadPenjualanData() {
        penjualanList.clear();
        try (Connection connection = DBConnect.getConnection()) {
            String sql = "SELECT * FROM penjualan";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idPelanggan = resultSet.getInt("id_pelanggan");
                int idBuku = resultSet.getInt("id_buku");
                int jumlah = resultSet.getInt("jumlah");
                double totalHarga = resultSet.getDouble("total_harga");
                Date tanggal = resultSet.getDate("tanggal");
                LocalDate localTanggal = tanggal.toLocalDate();

                Pelanggan pelanggan = findPelangganById(idPelanggan);
                Buku buku = findBukuById(idBuku);

                penjualanList.add(new Penjualan(id, idPelanggan, idBuku, jumlah, totalHarga, localTanggal));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Gagal memuat data penjualan.");
        }
    }

    private Pelanggan findPelangganById(int id) {
        for (Pelanggan pelanggan : pelangganList) {
            if (pelanggan.getId() == id) {
                return pelanggan;
            }
        }
        return null;
    }

    private Buku findBukuById(int id) {
        for (Buku buku : bukuList) {
            if (buku.getBuku_id() == id) {
                return buku;
            }
        }
        return null;
    }

    public void handleAdd() {
        Pelanggan pelanggan = pelangganComboBox.getValue();
        Buku buku = bukuComboBox.getValue();
        int jumlah;

        if (pelanggan == null || buku == null) {
            showAlert("Pilih pelanggan dan buku.");
            return;
        }

        try {
            jumlah = Integer.parseInt(jumlahField.getText());
        } catch (NumberFormatException e) {
            showAlert("Jumlah harus berupa angka!");
            return;
        }

        double totalHarga = buku.getHarga() * jumlah;
        LocalDate tanggal = tanggalPicker.getValue();

        if (tanggal == null) {
            showAlert("Pilih tanggal transaksi.");
            return;
        }

        try (Connection connection = DBConnect.getConnection()) {
            String sql = "INSERT INTO penjualan (id_pelanggan, id_buku, jumlah, total_harga, tanggal) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pelanggan.getId());
            statement.setInt(2, buku.getBuku_id());
            statement.setInt(3, jumlah);
            statement.setDouble(4, totalHarga);
            statement.setDate(5, Date.valueOf(tanggal));
            statement.executeUpdate();

            loadPenjualanData(); 
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Gagal menambahkan penjualan.");
        }
    }
    
    @FXML
    public void handleDelete() {
        Penjualan selectedPenjualan = tableView.getSelectionModel().getSelectedItem();
        if (selectedPenjualan == null) {
            showAlert("Pilih penjualan yang ingin dihapus.");
            return;
        }

        try (Connection connection = DBConnect.getConnection()) {
            String sql = "DELETE FROM penjualan WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, selectedPenjualan.getId());
            statement.executeUpdate();

            loadPenjualanData(); 
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Gagal menghapus penjualan.");
        }
    }

    @FXML
    public void handleUpdate() {
        Penjualan selectedPenjualan = tableView.getSelectionModel().getSelectedItem();
        if (selectedPenjualan == null) {
            showAlert("Pilih penjualan yang ingin diperbarui.");
            return;
        }

        Pelanggan pelanggan = pelangganComboBox.getValue();
        Buku buku = bukuComboBox.getValue();
        int jumlah;

        if (pelanggan == null || buku == null) {
            showAlert("Pilih pelanggan dan buku.");
            return;
        }

        try {
            jumlah = Integer.parseInt(jumlahField.getText());
        } catch (NumberFormatException e) {
            showAlert("Jumlah harus berupa angka!");
            return;
        }

        double totalHarga = buku.getHarga() * jumlah;
        LocalDate tanggal = tanggalPicker.getValue();

        if (tanggal == null) {
            showAlert("Pilih tanggal transaksi.");
            return;
        }

        try (Connection connection = DBConnect.getConnection()) {
            String sql = "UPDATE penjualan SET id_pelanggan = ?, id_buku = ?, jumlah = ?, total_harga = ?, tanggal = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pelanggan.getId());
            statement.setInt(2, buku.getBuku_id());
            statement.setInt(3, jumlah);
            statement.setDouble(4, totalHarga);
            statement.setDate(5, Date.valueOf(tanggal));
            statement.setInt(6, selectedPenjualan.getId());
            statement.executeUpdate();

            loadPenjualanData(); 
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Gagal memperbarui penjualan.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
