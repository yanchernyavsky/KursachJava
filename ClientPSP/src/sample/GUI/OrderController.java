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
import sample.Tables.Order;
import sample.Tables.Product;

public class OrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private TableView<Order> OrderTable;

    @FXML
    private TableColumn<Order, Integer> OrderId;

    @FXML
    private TableColumn<Order, Integer> ClientId;

    @FXML
    private TableColumn<Order, Integer> ProductId;

    @FXML
    private TableColumn<Order, Integer> ProviderId;

    @FXML
    private TableColumn<Order, Integer> ProductNumber;

    @FXML
    private TableColumn<Order, Integer> Finalprice;

    @FXML
    private Button AddButton;

    @FXML
    private TextField IdAdd;

    @FXML
    private TextField ClientIdAdd;

    @FXML
    private TextField ProductIdAdd;

    @FXML
    private TextField ProviderIdAdd;

    @FXML
    private TextField ProductNumberAdd;

    @FXML
    private TextField FinalpriceAdd;

    @FXML
    private Button UpdateButton;

    @FXML
    private TextField IdUpdate;

    @FXML
    private TextField ClientIdUpdate;

    @FXML
    private TextField ProductIdUpdate;

    @FXML
    private TextField ProviderIdUpdate;

    @FXML
    private TextField ProductNumberUpdate;

    @FXML
    private TextField FinalpriceUpdate;

    @FXML
    private Button DeleteButton;

    @FXML
    private TextField IdDelete;

    private ObservableList<Order> data;

    @FXML
    void initialize() {
        OrderId.setCellValueFactory(
                new PropertyValueFactory<Order, Integer>("order_id"));
        ClientId.setCellValueFactory(
                new PropertyValueFactory<Order, Integer>("order_client_id"));
        ProductId.setCellValueFactory(
                new PropertyValueFactory<Order, Integer>("order_product_id"));
        ProviderId.setCellValueFactory(
                new PropertyValueFactory<Order, Integer>("order_provider_id"));
        ProductNumber.setCellValueFactory(
                new PropertyValueFactory<Order, Integer>("order_product_number"));
        Finalprice.setCellValueFactory(
                new PropertyValueFactory<Order, Integer>("order_finalprice"));
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
        data = FXCollections.observableArrayList(serv.GetOrder());
        OrderTable.setItems(data);
    }

    public void Add(){
        Integer Id = Integer.valueOf(IdAdd.getText());
        Integer ClientId = Integer.valueOf(ClientIdAdd.getText());
        Integer ProductId = Integer.valueOf(ProductIdAdd.getText());
        Integer ProviderId = Integer.valueOf(ProviderIdAdd.getText());
        Integer ProductNumber = Integer.valueOf(ProductNumberAdd.getText());
        Integer Finalprice = Integer.valueOf(FinalpriceAdd.getText());

        Order order = new Order(Id, ClientId, ProductId, ProviderId, ProductNumber, Finalprice);
        if(!Id.equals("") && !ClientId.equals("") && !ProductId.equals("") && !ProviderId.equals("")
                && !ProductNumber.equals("") && !Finalprice.equals("") ){
            Service serv = new Service();
            serv.AddOrder(order);
            IdAdd.setText("Новый");
            ClientIdAdd.setText("Заказ");
            ProductIdAdd.setText("Успешно");
            ProviderIdAdd.setText("Добавлен");
            ProductNumberAdd.setText("");
            FinalpriceAdd.setText("");
        }
        else {
            IdAdd.setText("Не должно быть");
            ClientIdAdd.setText("пустых");
            ProductIdAdd.setText("полей");
            ProviderIdAdd.setText("Введите заново!");
            ProductNumberAdd.setText("");
            FinalpriceAdd.setText("");
        }
    }

    public void Update(){
        Integer Id = Integer.valueOf(IdUpdate.getText());
        Integer ClientId = Integer.valueOf(ClientIdUpdate.getText());
        Integer ProductId = Integer.valueOf(ProductIdUpdate.getText());
        Integer ProviderId = Integer.valueOf(ProviderIdUpdate.getText());
        Integer ProductNumber = Integer.valueOf(ProductNumberUpdate.getText());
        Integer Finalprice = Integer.valueOf(FinalpriceUpdate.getText());

        Order order = new Order(Id, ClientId, ProductId, ProviderId, ProductNumber, Finalprice);
        if(!Id.equals("") && !ClientId.equals("") && !ProductId.equals("") && !ProviderId.equals("")
                && !ProductNumber.equals("") && !Finalprice.equals("") ){
            Service serv = new Service();
            serv.UpdateOrder(order);
            IdUpdate.setText("Выбранный");
            ClientIdUpdate.setText("Заказ");
            ProductIdUpdate.setText("Успешно");
            ProviderIdUpdate.setText("Изменён");
            ProductNumberUpdate.setText("");
            FinalpriceUpdate.setText("");
        }
        else {
            IdUpdate.setText("Не должно быть");
            ClientIdUpdate.setText("пустых");
            ProductIdUpdate.setText("полей");
            ProviderIdUpdate.setText("Введите заново!");
            ProductNumberUpdate.setText("");
            FinalpriceUpdate.setText("");
        }
    }

    public void Delete(){
        Integer Id = Integer.valueOf(IdDelete.getText());

        if(!Id.equals("")){
            Service serv = new Service();
            serv.DeleteOrder(Id);
            IdDelete.setText("Заказ успешно удалён");
        }
        else {
            IdDelete.setText("Поле не должно быть пустым");
        }
    }
}

