package labapp;

import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sample.Tables.*;

public class ServerTCP extends Thread{

    Socket socket;
    int num;

    public static void main(String[] args) {
        try
        {
            int i = 0; // счётчик подключений

            ServerSocket server = new ServerSocket(3128, 0,
                    InetAddress.getByName("localhost"));
            System.out.println("Сервер запущен");

            // слушаем порт
            while(true)
            {
                // ждём нового подключения, после чего запускаем обработку клиента
                // в новый вычислительный поток и увеличиваем счётчик на единичку
                new ServerTCP(i, server.accept());
                i++;
            }
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }

    public ServerTCP(int num, Socket socket) {
        // копируем данные
        this.num = num;
        this.socket = socket;
        // и запускаем новый вычислительный поток (см. ф-ю run())
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    public void run()
    {
        try
        {
            System.out.println("Начало передачи...");
            ObjectInputStream  Inputstream   = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream Outputstream   = new ObjectOutputStream(socket.getOutputStream());
            String command = new String();
            command = (String)Inputstream.readObject();
            Outputstream.writeObject(command);
            DatabaseHandler dbh = new DatabaseHandler();
            switch (command) {
                case "Authorization":
                {
                    System.out.println("Авторизация пользователя...");
                    User user=new User((User)Inputstream.readObject());
                    ResultSet result = dbh.getUser(user);
                    int count=0;
                    try{
                        while(result.next()){
                            count++;
                        }
                    } catch(SQLException e){
                        e.printStackTrace();
                    }
                    if(count>=1)
                        Outputstream.writeObject(new Boolean(true));
                    else
                        Outputstream.writeObject(new Boolean(false));
                }
                break;

                case "Registration":
                {
                    System.out.println("Регистрация пользователя...");
                    User user=new User((User)Inputstream.readObject());
                    ResultSet result = dbh.getUser(user);
                    int count=0;
                    try{
                        while(result.next()){
                            count++;
                        }
                    } catch(SQLException e){
                        e.printStackTrace();
                    }
                    if(count>=1)
                        Outputstream.writeObject(new Boolean(false));
                    else{
                        dbh.signUpUser(user);
                        Outputstream.writeObject(new Boolean(true));
                    }
                }
                break;

                case "GiveProviders":
                {
                        System.out.println("Отправка данных о поставщиках...");
                        Outputstream.writeObject(GiveProviders());
                }
                break;

                case "AddProvider":
                {
                    System.out.println("Добавление поставщика...");
                    Provider provider=new Provider((Provider)Inputstream.readObject());
                    dbh.AddProvider(provider);
                }
                break;

                case "UpdateProvider":
                {
                    System.out.println("Изменение поставщика...");
                    Provider provider=new Provider((Provider)Inputstream.readObject());
                    dbh.UpdateProvider(provider);
                }
                break;

                case "DeleteProvider":
                {
                    System.out.println("Удаление поставщика...");
                    Integer Id=((Integer)Inputstream.readObject());
                    dbh.DeleteProvider(Id);
                }
                break;

                case "GiveCategories":
                {
                    System.out.println("Отправка данных о категориях...");
                    Outputstream.writeObject(GiveCategories());
                }
                break;

                case "AddCategory":
                {
                    System.out.println("Добавление поставщика...");
                    Category category=new Category((Category)Inputstream.readObject());
                    dbh.AddCategory(category);
                }
                break;

                case "UpdateCategory":
                {
                    System.out.println("Изменение поставщика...");
                    Category category=new Category((Category)Inputstream.readObject());
                    dbh.UpdateCategory(category);
                }
                break;

                case "DeleteCategory":
                {
                    System.out.println("Удаление поставщика...");
                    String CathName=((String)Inputstream.readObject());
                    dbh.DeleteCategory(CathName);
                }
                break;

                case "GiveProducts":
                {
                    System.out.println("Отправка данных о товарах...");
                    Outputstream.writeObject(GiveProducts());
                }
                break;

                case "AddProduct":
                {
                    System.out.println("Добавление поставщика...");
                    Product product=new Product((Product)Inputstream.readObject());
                    dbh.AddProduct(product);
                }
                break;

                case "UpdateProduct":
                {
                    System.out.println("Изменение поставщика...");
                    Product product=new Product((Product)Inputstream.readObject());
                    dbh.UpdateProduct(product);
                }
                break;

                case "DeleteProduct":
                {
                    System.out.println("Удаление поставщика...");
                    Integer Id=((Integer)Inputstream.readObject());
                    dbh.DeleteProduct(Id);
                }
                break;

                case "GiveClients":
                {
                    System.out.println("Отправка данных о клиетнах...");
                    Outputstream.writeObject(GiveClients());
                }
                break;

                case "AddClient":
                {
                    System.out.println("Добавление поставщика...");
                    Client client=new Client((Client)Inputstream.readObject());
                    dbh.AddClient(client);
                }
                break;

                case "UpdateClient":
                {
                    System.out.println("Изменение поставщика...");
                    Client client=new Client((Client)Inputstream.readObject());
                    dbh.UpdateClient(client);
                }
                break;

                case "DeleteClient":
                {
                    System.out.println("Удаление поставщика...");
                    Integer Id=((Integer)Inputstream.readObject());
                    dbh.DeleteClient(Id);
                }
                break;

                case "GiveOrders":
                {
                    System.out.println("Отправка данных о заказах...");
                    Outputstream.writeObject(GiveOrders());
                }
                break;

                case "AddOrder":
                {
                    System.out.println("Добавление поставщика...");
                    Order order=new Order((Order)Inputstream.readObject());
                    dbh.AddOrder(order);
                }
                break;

                case "UpdateOrder":
                {
                    System.out.println("Изменение поставщика...");
                    Order order=new Order((Order)Inputstream.readObject());
                    dbh.UpdateOrder(order);
                }
                break;

                case "DeleteOrder":
                {
                    System.out.println("Удаление поставщика...");
                    Integer Id=((Integer)Inputstream.readObject());
                    dbh.DeleteOrder(Id);
                }
                break;

                default:
                    System.out.println("Неправильная комманда");
                    break;
            }
            socket.close();
            System.out.println("Конец передачи...");
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }

    public ArrayList<Provider> GiveProviders(){
        DatabaseHandler dbh = new DatabaseHandler();
        ArrayList<Provider> data = new ArrayList<Provider>();
        ResultSet result = dbh.getAll(Const.PROVIDER_TABLE);
        try {
            while (result.next()) {
                Provider object = new Provider();
                object.setProvider_id(result.getInt(Const.PROVIDER_ID));
                object.setProvider_name(result.getString(Const.PROVIDER_NAME));
                object.setProvider_address(result.getString(Const.PROVIDER_ADDRESS));
                object.setProvider_phonenumber(result.getString(Const.PROVIDER_PHONE));
                //System.out.println((object.provider_id).toString() + object.provider_name.toString()
                //      + object.provider_address.toString() + object.provider_phonenumber.toString());
                data.add(object);
                }
            } catch (SQLException e) {
                    e.printStackTrace();
                }
                return data;
    }

    public ArrayList<Category> GiveCategories(){
        DatabaseHandler dbh = new DatabaseHandler();
        ArrayList<Category> data = new ArrayList<Category>();
        ResultSet result = dbh.getAll(Const.CATEGORY_TABLE);
        try {
            while (result.next()) {
                Category object = new Category();
                object.setCategory_name(result.getString(Const.CATEGORY_NAME));
                object.setCategory_number(result.getInt(Const.CATEGORY_PRODUCT_NUMBER));
                object.setCategory_description(result.getString(Const.CATEGORY_DESCRIPTION));
                object.setCategory_availability(result.getString(Const.CATEGORY_AVAILABILITY));
                data.add(object);
                }
            } catch (SQLException e) {
                    e.printStackTrace();
                }
                return data;
    }

    public ArrayList<Product> GiveProducts(){
        DatabaseHandler dbh = new DatabaseHandler();
        ArrayList<Product> data = new ArrayList<Product>();
        ResultSet result = dbh.getAll(Const.PRODUCT_TABLE);
        try {
            while (result.next()) {
                Product object = new Product();
                object.setProduct_id(result.getInt(Const.PRODUCT_ID));
                object.setProduct_provider_id(result.getInt(Const.PRODUCT_PROVIDER_ID));
                object.setProduct_name(result.getString(Const.PRODUCT_NAME));
                object.setProduct_number(result.getInt(Const.PRODUCT_NUMBER));
                object.setProduct_price(result.getInt(Const.PRODUCT_PRICE));
                object.setProduct_category_name(result.getString(Const.PRODUCT_CATEGORY_NAME));
                data.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<Client> GiveClients(){
        DatabaseHandler dbh = new DatabaseHandler();
        ArrayList<Client> data = new ArrayList<Client>();
        ResultSet result = dbh.getAll(Const.CLIENT_TABLE);
        try {
            while (result.next()) {
                Client object = new Client();
                object.setClient_id(result.getInt(Const.CLIENT_ID));
                object.setClient_name(result.getString(Const.CLIENT_NAME));
                object.setClient_surname(result.getString(Const.CLIENT_SURNAME));
                object.setClient_lastname(result.getString(Const.CLIENT_LAST_NAME));
                object.setClient_phone(result.getString(Const.CLIENT_PHONE));
                data.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<Order> GiveOrders(){
        DatabaseHandler dbh = new DatabaseHandler();
        ArrayList<Order> data = new ArrayList<Order>();
        ResultSet result = dbh.getAll(Const.ORDER_TABLE);
        try {
            while (result.next()) {
                Order object = new Order();
                object.setOrder_id(result.getInt(Const.ORDER_ID));
                object.setOrder_client_id(result.getInt(Const.ORDER_CLIENT_ID));
                object.setOrder_product_id(result.getInt(Const.ORDER_PRODUCT_ID));
                object.setOrder_provider_id(result.getInt(Const.ORDER_PROVIDER_ID));
                object.setOrder_product_number(result.getInt(Const.ORDER_PRODUCT_NUMBER));
                object.setOrder_finalprice(result.getInt(Const.ORDER_FINAL_PRICE));
                data.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

}