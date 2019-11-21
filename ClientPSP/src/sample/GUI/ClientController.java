package sample.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Service;
import sample.Tables.Client;

public class ClientController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private TableView<Client> ClientTable;

    @FXML
    private TableColumn<Client, Integer> ClientId;

    @FXML
    private TableColumn<Client, String> ClientName;

    @FXML
    private TableColumn<Client, String> ClientSurname;

    @FXML
    private TableColumn<Client, String> ClientLastname;

    @FXML
    private TableColumn<Client, String> ClientPhone;

    @FXML
    private Button AddButton;

    @FXML
    private TextField IdAdd;

    @FXML
    private TextField NameAdd;

    @FXML
    private TextField SurnameAdd;

    @FXML
    private TextField LastnameAdd;

    @FXML
    private TextField PhoneAdd;

    @FXML
    private Button UpdateButton;

    @FXML
    private TextField IdUpdate;

    @FXML
    private TextField NameUpdate;

    @FXML
    private TextField SurnameUpdate;

    @FXML
    private TextField LastnameUpdate;

    @FXML
    private TextField PhoneUpdate;

    @FXML
    private Button DeleteButton;

    @FXML
    private TextField IdDelete;

    private ObservableList<Client> data;

    @FXML
    void initialize() {
        ClientId.setCellValueFactory(
                new PropertyValueFactory<Client, Integer>("client_id"));
        ClientName.setCellValueFactory(
                new PropertyValueFactory<Client, String>("client_name"));
        ClientSurname.setCellValueFactory(
                new PropertyValueFactory<Client, String>("client_surname"));
        ClientLastname.setCellValueFactory(
                new PropertyValueFactory<Client, String>("client_lastname"));
        ClientPhone.setCellValueFactory(
                new PropertyValueFactory<Client, String>("client_phone"));
        UpdateTableInformation();
        BackButton.setOnAction(event -> {
            openNewScene("/sample/GUI/MenuGUI.fxml");
        });

        AddButton.setOnAction(event -> {
            Add();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UpdateTableInformation();
        });

        UpdateButton.setOnAction(event -> {
            Update();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UpdateTableInformation();
        });

        DeleteButton.setOnAction(event -> {
            Delete();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UpdateTableInformation();
        });
    }
        public void openNewScene(String window) {
            BackButton.getScene().getWindow().hide();
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

        public void UpdateTableInformation() {
            Service serv = new Service();
            data = FXCollections.observableArrayList(serv.GetClient());
            ClientTable.setItems(data);

        }

        public void Add(){
            Integer Id = Integer.valueOf(IdAdd.getText());
            String Name = NameAdd.getText().trim();
            String Surname = SurnameAdd.getText().trim();
            String Lastname = LastnameAdd.getText().trim();
            String Phone = PhoneAdd.getText().trim();

            Client client = new Client(Id,Name,Surname,Lastname,Phone);
            if(!Id.equals("") && !Name.equals("") && !Surname.equals("") && !Lastname.equals("") && !Phone.equals("")){
                Service serv = new Service();
                serv.AddClient(client);
                IdAdd.setText("Новый");
                NameAdd.setText("Клиент");
                SurnameAdd.setText("Успешно");
                LastnameAdd.setText("Добавлен");
                PhoneAdd.setText("");
            }
            else {
                IdAdd.setText("Не должно быть");
                NameAdd.setText("пустых");
                SurnameAdd.setText("полей");
                LastnameAdd.setText("Введите заново!");
                PhoneAdd.setText("");
            }
        }

        public void Update(){
            Integer Id = Integer.valueOf(IdUpdate.getText());
            String Name = NameUpdate.getText().trim();
            String Surname = SurnameUpdate.getText().trim();
            String Lastname = LastnameUpdate.getText().trim();
            String Phone = PhoneUpdate.getText().trim();

            Client client = new Client(Id,Name,Surname,Lastname,Phone);
            if(!Id.equals("") && !Name.equals("") && !Surname.equals("") && !Lastname.equals("") && !Phone.equals("")){
                Service serv = new Service();
                serv.UpdateClient(client);
                IdUpdate.setText("Выбранный");
                NameUpdate.setText("Клиент");
                SurnameUpdate.setText("Успешно");
                LastnameUpdate.setText("Изменён");
                PhoneUpdate.setText("");
            }
            else {
                IdUpdate.setText("Не должно быть");
                NameUpdate.setText("пустых");
                SurnameUpdate.setText("полей");
                LastnameUpdate.setText("Введите заново!");
                PhoneUpdate.setText("");
            }
        }

        public void Delete(){
            Integer Id = Integer.valueOf(IdDelete.getText());

            if(!Id.equals("")){
                Service serv = new Service();
                serv.DeleteClient(Id);
                IdDelete.setText("Клиент успешно удалён");
            }
            else {
                IdDelete.setText("Поле не должно быть пустым");
            }
        }
    }


