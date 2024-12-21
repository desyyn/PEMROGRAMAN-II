package PRAKTIKUM6;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPrak6 extends Application {
    @Override
    public void start(Stage primaryStage) {
        TableView<Mahasiswa> table = new TableView<>();

        TableColumn<Mahasiswa, String> nimColumn = new TableColumn<>("NIM");
        nimColumn.setCellValueFactory(new PropertyValueFactory<>("nim"));

        TableColumn<Mahasiswa, String> namaColumn = new TableColumn<>("Nama");
        namaColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));

        table.getColumns().add(nimColumn);
        table.getColumns().add(namaColumn);

        ObservableList<Mahasiswa> data = FXCollections.observableArrayList(
            new Mahasiswa(1, "Dessy Nurulita", "2310817220024"),
            new Mahasiswa(2, "Eci Sisi", "2345176587011"),
            new Mahasiswa(3, "Ariana Anne", "2398765120874"),
            new Mahasiswa(4, "Jane Camila", "2314532098724"),
            new Mahasiswa(5, "Issa Caca", "2309556712398"),
            new Mahasiswa(6, "Shofa Nana", "2306845123487"),
            new Mahasiswa(7, "Sapna Anna", "2309458712542"),
            new Mahasiswa(8, "Dyah Dya", "2305673456121"),
            new Mahasiswa(9, "Kezia Kei", "2388543132435"),
            new Mahasiswa(10, "Justin Tobi", "2301625428764")
        );
        table.setItems(data);

        VBox vbox = new VBox(table);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tabel Data Mahasiswa");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
