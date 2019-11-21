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
import sample.Tables.Category;

public class CategoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private TableView<Category> CategoryTable;

    @FXML
    private TableColumn<Category, String> CategoryName;

    @FXML
    private TableColumn<Category, Integer> CategoryProductNumber;

    @FXML
    private TableColumn<Category, String> CategoryDescription;

    @FXML
    private TableColumn<Category, String> CategoryAvailability;

    @FXML
    private Button AddButton;

    @FXML
    private TextField NameAdd;

    @FXML
    private TextField ProductNumberAdd;

    @FXML
    private TextField DescriptionAdd;

    @FXML
    private TextField AvailabilityAdd;

    @FXML
    private Button UpdateButton;

    @FXML
    private TextField NameUpdate;

    @FXML
    private TextField ProductNumberUpdate;

    @FXML
    private TextField DescriptionUpdate;

    @FXML
    private TextField AvailabilityUpdate;

    @FXML
    private Button DeleteButton;

    @FXML
    private TextField NameDelete;

    private ObservableList<Category> data;

    @FXML
    void initialize() {
        CategoryName.setCellValueFactory(
                new PropertyValueFactory<Category, String>("category_name"));
        CategoryProductNumber.setCellValueFactory(
                new PropertyValueFactory<Category,Integer>("category_number"));
        CategoryDescription.setCellValueFactory(
                new PropertyValueFactory<Category,String>("category_description"));
        CategoryAvailability.setCellValueFactory(
                new PropertyValueFactory<Category,String>("category_availability"));
        UpdateTableInformation();
        BackButton.setOnAction(event -> {
            openNewScene("/sample/GUI/ProductGUI.fxml");
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
        data = FXCollections.observableArrayList(serv.GetCategory());
        CategoryTable.setItems(data);
    }

    public void Add(){
        String Name = NameAdd.getText();
        Integer CategoryNumber = Integer.valueOf(ProductNumberAdd.getText());
        String Description = DescriptionAdd.getText().trim();
        String Availability = AvailabilityAdd.getText().trim();

        Category category = new Category(Name,CategoryNumber,Description, Availability);
        if(!Name.equals("") && !CategoryNumber.equals("") && !Description.equals("") && !Availability.equals("")){
            Service serv = new Service();
            serv.AddCategory(category);
            NameAdd.setText("Новая");
            ProductNumberAdd.setText("Категория");
            DescriptionAdd.setText("Успешно");
            AvailabilityAdd.setText("Добавлена");
        }
        else {
            NameAdd.setText("Не должно быть");
            ProductNumberAdd.setText("пустых");
            DescriptionAdd.setText("полей");
            AvailabilityAdd.setText("Введите заново!");
        }
    }

    public void Update(){
        String Name = NameUpdate.getText();
        Integer CategoryNumber = Integer.valueOf(ProductNumberUpdate.getText());
        String Description = DescriptionUpdate.getText().trim();
        String Availability = AvailabilityUpdate.getText().trim();

        Category category = new Category(Name,CategoryNumber,Description, Availability);
        if(!Name.equals("") && !CategoryNumber.equals("") && !Description.equals("") && !Availability.equals("")){
            Service serv = new Service();
            serv.UpdateCategory(category);
            NameUpdate.setText("Новая");
            ProductNumberUpdate.setText("Категория");
            DescriptionUpdate.setText("Успешно");
            AvailabilityUpdate.setText("Изменена");
        }
        else {
            NameUpdate.setText("Не должно быть");
            ProductNumberUpdate.setText("пустых");
            DescriptionUpdate.setText("полей");
            AvailabilityUpdate.setText("Введите заново!");
        }
    }

    public void Delete(){
        String CathName = NameDelete.getText();

        if(!CathName.equals("")){
            Service serv = new Service();
            serv.DeleteCategory(CathName);
            NameDelete.setText("Категория успешно удалён");
        }
        else {
            NameDelete.setText("Поле не должно быть пустым");
        }
    }
}

