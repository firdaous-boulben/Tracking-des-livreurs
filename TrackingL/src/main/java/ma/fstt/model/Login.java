package ma.fstt.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Login {
    //Vérification des données d'authentification
    public static void logIn(ActionEvent event, String login, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tracking", "root", "");
            preparedStatement = connection.prepareStatement("select password from admin where login = ?");
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            if (login.isEmpty() || password.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Erreur");
                alert.setContentText("Veuillez remplir tous les champs.");
                alert.show();
            } else if (!resultSet.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Erreur");
                alert.setContentText("Utilisateur introuvable.");
                alert.show();
            }else {
                while (resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)){
                        switchToHome(event);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Login Erreur");
                        alert.setContentText("Mot de passe incorrecte.");
                        alert.show();
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Basculer vers L'interface de Menu Principal
    public static void switchToHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new File("C:\\Users\\asus\\Desktop\\TrackingL\\src\\main\\resources\\ma\\fstt\\trackingl\\hello-view.fxml").toURI().toURL());
            Parent homeParent = loader.load();
            Scene homeScene = new Scene(homeParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(homeScene);
            window.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
