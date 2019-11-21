package sample;

import sample.Tables.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientTCP extends Thread{

    Socket socket;
    ObjectOutputStream Outputstream;
    ObjectInputStream  Inputstream;

    public void InitSocketAndStreams(){
        try
        {
            // открываем сокет и коннектимся к localhost:3128
            // получаем сокет сервера
            socket = new Socket("localhost", 3128);
            Outputstream = new ObjectOutputStream(socket.getOutputStream());
            Inputstream = new ObjectInputStream(socket.getInputStream());
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }
    public void CloseStreamsAndSocket(){
        try {
            Outputstream.close();//закрытие потока вывода
            Inputstream.close();//закрытие потока ввода
            socket.close();//закрытие сокета
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений)
    }

    public Boolean Send(String command, User object) {
        Boolean answer = new Boolean(false);
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(object);
            answer = (Boolean) Inputstream.readObject();
        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
        return answer;
        }

    public void Send(String command, Provider object) {
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
    }

    public void Send(String command, Category object) {
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
    }

    public void Send(String command, Product object) {
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
    }

    public void Send(String command, Client object) {
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
    }

    public void Send(String command, Order object) {
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
    }

    public void Send(String command, Integer Id) {
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(Id);
        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
    }

    public void Send(String command, String CathName) {
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(CathName);
        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
    }

    public ArrayList<Provider> SendArrayProvider(String command){
        ArrayList<Provider> array=new ArrayList<Provider>();
        InitSocketAndStreams();
        try{
            Outputstream.writeObject(command);
            Inputstream.readObject();
            array=(ArrayList<Provider>)Inputstream.readObject();
        }
        catch(Exception e){
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
        return array;
    }

    public ArrayList<Category> SendArrayCategory(String command){
        ArrayList<Category> array=new ArrayList<Category>();
        InitSocketAndStreams();
        try{
            Outputstream.writeObject(command);
            Inputstream.readObject();
            array=(ArrayList<Category>)Inputstream.readObject();
        }
        catch(Exception e){
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
        return array;
    }

    public ArrayList<Product> SendArrayProduct(String command){
        ArrayList<Product> array=new ArrayList<Product>();
        InitSocketAndStreams();
        try{
            Outputstream.writeObject(command);
            Inputstream.readObject();
            array=(ArrayList<Product>)Inputstream.readObject();
        }
        catch(Exception e){
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
        return array;
    }

    public ArrayList<Client> SendArrayClient(String command){
        ArrayList<Client> array=new ArrayList<Client>();
        InitSocketAndStreams();
        try{
            Outputstream.writeObject(command);
            Inputstream.readObject();
            array=(ArrayList<Client>)Inputstream.readObject();
        }
        catch(Exception e){
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
        return array;
    }

    public ArrayList<Order> SendArrayOrder(String command){
        ArrayList<Order> array=new ArrayList<Order>();
        InitSocketAndStreams();
        try{
            Outputstream.writeObject(command);
            Inputstream.readObject();
            array=(ArrayList<Order>)Inputstream.readObject();
        }
        catch(Exception e){
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
        return array;
    }

}
