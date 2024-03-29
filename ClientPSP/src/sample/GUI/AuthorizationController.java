package sample.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Service;
import sample.Tables.User;

public class AuthorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginSighUpButton;

    @FXML
    private Button authSighInButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    void initialize() {
        authSighInButton.setOnAction(event -> {
            loginUser();
        });


        loginSighUpButton.setOnAction(event -> {
           openNewScene("/sample/GUI/RegistrationGUI.fxml");
        });
    }

            private void loginUser() {
                String login = login_field.getText().trim();
                String password = password_field.getText().trim();
                User user = new User(login, password);
                boolean userExistence;
                if(!login.equals("") && !password.equals("")){
                    Service serv = new Service();
                    userExistence=serv.SighUp(user).booleanValue();
                    if(userExistence){
                        openNewScene("MenuGUI.fxml");
                    }
                    else
                    {
                        login_field.setText("Пользователь не найден");
                        password_field.setText("");
                    }
                }
                else {
                    login_field.setText("Необходимо заполнить все поля");
                    password_field.setText("");
                }
            }
            public void openNewScene(String window) {
                loginSighUpButton.getScene().getWindow().hide();
                FXMLLoader loaderGetIn = new FXMLLoader();
                loaderGetIn.setLocation(getClass().getResource(window));
                try {
                    loaderGetIn.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent rootGetIn = loaderGetIn.getRoot();
                Stage stageGetIn = new Stage();
                stageGetIn.setScene(new Scene(rootGetIn));
                stageGetIn.show();
    }
}

