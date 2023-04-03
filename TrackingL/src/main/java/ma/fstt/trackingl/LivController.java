package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ma.fstt.model.Livreur;
import ma.fstt.model.LivreurDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LivController implements Initializable {
    @FXML
    private Button btnSave;

    @FXML
    private VBox liv;

    @FXML
    private TextField nom ;
    @FXML
    private TextField tele ;
    @FXML
    private TextField cmd;

    @FXML
    private TableView<Livreur> mytable ;
    Long id;

    @FXML
    private TableColumn<Livreur ,Long> col_id ;
    @FXML
    private TableColumn <Livreur ,String> col_nom ;
    @FXML
    private TableColumn <Livreur ,String> col_tele ;
    @FXML
    private TableColumn<Livreur, Integer> col_cmd ;

    @FXML
    protected void onSaveButtonClick() {
        // access a la bdd
        try {
            LivreurDAO livreurDAO = new LivreurDAO();
            Livreur liv = new Livreur(0l , nom.getText() , tele.getText() , Integer.parseInt(cmd.getText()));
            livreurDAO.save(liv);

            UpdateTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onUpdateButtonClick() {
        // access a la bdd
        try {
            LivreurDAO livreurDAO = new LivreurDAO();
            Livreur liv = new Livreur(0l , nom.getText() , tele.getText() , Integer.parseInt(cmd.getText()));
            livreurDAO.update(liv, id);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onDeleteButtonClick() {
        // access a la bdd
        try {
            LivreurDAO livreurDAO = new LivreurDAO();
            Livreur liv = new Livreur(0l , nom.getText() , tele.getText() , Integer.parseInt(cmd.getText()) );
            livreurDAO.delete(liv, id);

            UpdateTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onClearButtonClick() {
        nom.setText(null);
        tele.setText(null);
        cmd.setText(null);
        btnSave.setDisable(false);
    }

    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Livreur,Long>("id_livreur"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Livreur,String>("nom"));
        col_tele.setCellValueFactory(new PropertyValueFactory<Livreur,String>("telephone"));
        col_cmd.setCellValueFactory(new PropertyValueFactory<Livreur,Integer>("nb_commande"));

        mytable.setItems(this.getDataLivreurs());
    }

    public static ObservableList<Livreur> getDataLivreurs(){
        LivreurDAO livreurDAO = null;

        ObservableList<Livreur> listfx = FXCollections.observableArrayList();

        try {
            livreurDAO = new LivreurDAO();
            for (Livreur ettemp : livreurDAO.getAll())
                listfx.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    }

    public void getData(javafx.scene.input.MouseEvent mouseEvent) {
        Livreur livreur = mytable.getSelectionModel().getSelectedItem();

        id = livreur.getId_livreur();
        nom.setText(livreur.getNom());
        tele.setText(livreur.getTelephone());
        cmd.setText(String.valueOf(livreur.getNb_commande()));

        btnSave.setDisable(true);
    }

    @FXML
    void switchToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent homeParent = loader.load();
        Scene homeScene = new Scene(homeParent);
        Stage window = (Stage) liv.getScene().getWindow();
        window.setScene(homeScene);
        window.show();
    }
}