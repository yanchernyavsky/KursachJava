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
import sample.Tables.Product;

public class ProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private TableView<Product> ProductTable;

    @FXML
    private TableColumn<Product, Integer> ProductId;

    @FXML
    private TableColumn<Product, Integer> ProviderId;

    @FXML
    private TableColumn<Product, String> ProductName;

    @FXML
    private TableColumn<Product, Integer> ProductNumber;

    @FXML
    private TableColumn<Product, Integer> ProductPrice;

    @FXML
    private TableColumn<Product, String> CategoryName;

    @FXML
    private Button CategoryButton;

    @FXML
    private Button AddButton;

    @FXML
    private TextField IdAdd;

    @FXML
    private TextField ProductProviderIdAdd;

    @FXML
    private TextField NameAdd;

    @FXML
    private TextField NumberAdd;

    @FXML
    private TextField PriceAdd;

    @FXML
    private TextField ProductCategoryNameAdd;

    @FXML
    private Button UpdateButton;

    @FXML
    private TextField IdUpdate;

    @FXML
    private TextField ProductProviderIdUpdate;

    @FXML
    private TextField NameUpdate;

    @FXML
    private TextField NumberUpdate;

    @FXML
    private TextField PriceUpdate;

    @FXML
    private TextField ProductCategoryNameUpdate;

    @FXML
    private Button DeleteButton;

    @FXML
    private TextField IdDelete;

    private ObservableList<Product> data;

    @FXML
    void initialize() {
        ProductId.setCellValueFactory(
                new PropertyValueFactory<Product, Integer>("product_id"));
        ProviderId.setCellValueFactory(
                new PropertyValueFactory<Product,Integer>("product_provider_id"));
        ProductName.setCellValueFactory(
                new PropertyValueFactory<Product,String>("product_name"));
        ProductNumber.setCellValueFactory(
                new PropertyValueFactory<Product,Integer>("product_number"));
        ProductPrice.setCellValueFactory(
                new PropertyValueFactory<Product,Integer>("product_price"));
        CategoryName.setCellValueFactory(
                new PropertyValueFactory<Product,String>("product_category_name"));
        UpdateTableInformation();
        BackButton.setOnAction(event -> {
            openNewScene("/sample/GUI/MenuGUI.fxml");
        });

        CategoryButton.setOnAction(event -> {
            openNewScene("/sample/GUI/CategoryGUI.fxml");
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
        data = FXCollections.observableArrayList(serv.GetProduct());
        ProductTable.setItems(data);
    }

    public void Add(){
        Integer Id = Integer.valueOf(IdAdd.getText());
        Integer ProviderId = Integer.valueOf(ProductProviderIdAdd.getText());
        String Name = NameAdd.getText().trim();
        Integer Number = Integer.valueOf(NumberAdd.getText());
        Integer Price = Integer.valueOf(PriceAdd.getText());
        String CategoryName = ProductCategoryNameAdd.getText().trim();

        Product product = new Product(Id, ProviderId, Name, Number, Price, CategoryName);
        if(!Id.equals("") && !ProviderId.equals("") && !Name.equals("") && !Number.equals("") && !Price.equals("") && !CategoryName.equals("")){
            Service serv = new Service();
            serv.AddProduct(product);
            IdAdd.setText("Новый");
            ProductProviderIdAdd.setText("Товар");
            NameAdd.setText("Успешно");
            NumberAdd.setText("Добавлен");
            PriceAdd.setText("");
            ProductCategoryNameAdd.setText("");

        }
        else {
            IdAdd.setText("Не должно");
            ProductProviderIdAdd.setText("быть");
            NameAdd.setText("пустых");
            NumberAdd.setText("полей");
            PriceAdd.setText("Введите заново!");
            ProductCategoryNameAdd.setText("");
        }
    }

    public void Update(){
        Integer Id = Integer.valueOf(IdUpdate.getText());
        Integer ProviderId = Integer.valueOf(ProductProviderIdUpdate.getText());
        String Name = NameUpdate.getText().trim();
        Integer Number = Integer.valueOf(NumberUpdate.getText());
        Integer Price = Integer.valueOf(PriceUpdate.getText());
        String CategoryName = ProductCategoryNameUpdate.getText().trim();

        Product product = new Product(Id, ProviderId, Name, Number, Price, CategoryName);
        if(!Id.equals("") && !ProviderId.equals("") && !Name.equals("") && !Number.equals("") && !Price.equals("") && !CategoryName.equals("")){
            Service serv = new Service();
            serv.UpdateProduct(product);
            IdUpdate.setText("Выьранный");
            ProductProviderIdUpdate.setText("Товар");
            NameUpdate.setText("Успешно");
            NumberUpdate.setText("Изменён");
            PriceUpdate.setText("");
            ProductCategoryNameUpdate.setText("");

        }
        else {
            IdUpdate.setText("Не должно");
            ProductProviderIdUpdate.setText("быть");
            NameUpdate.setText("пустых");
            NumberUpdate.setText("полей");
            PriceUpdate.setText("Введите заново!");
            ProductCategoryNameUpdate.setText("");
        }
    }

    public void Delete(){
        Integer Id = Integer.valueOf(IdDelete.getText());

        if(!Id.equals("")){
            Service serv = new Service();
            serv.DeleteProduct(Id);
            IdDelete.setText("Товар успешно удалён");
        }
        else {
            IdDelete.setText("Поле не должно быть пустым");
        }
    }
}


