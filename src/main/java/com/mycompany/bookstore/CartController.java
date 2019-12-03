package com.mycompany.bookstore;

import Dao.BookDao;
import Entity.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.mycompany.bookstore.MainApp.CurrentBookISBN;

/**
 * @author andrescabrera, yuliiamelnyk
 */

public class CartController implements Initializable {

    @FXML
    private TableView<Book> cartTable;
    //@FXML
    //private TableColumn<Book, Book> coverCol;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, Integer> quantityCol;
    @FXML
    private TableColumn<Book, Float> priceCol;

    @FXML
    private Label subtotalLabel;
    private BookDao bookDao;
    private Book book;
    private double total = 0;
    private List<Book> bookList;
    private ObservableList<Book> activeSession = FXCollections.observableArrayList();

    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bookDao = new BookDao();
        try {
            book = bookDao.getBookbyISBN(MainApp.CurrentBookISBN);
        } catch (SQLException e) {
            e.printStackTrace();
        }

/*
        for (Book book : cartTable.getItems()) {
            total = total + book.getPrice();
        }*/
        String currencyPrice = currencyFormatter.format(total);
        subtotalLabel.setText(currencyPrice);

        //cartTable.setPlaceholder(new Label("Your shopping cart is empty..."));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        titleCol.setCellFactory(col -> new TableCell<Book, String>() {
            @Override
            public void updateItem(String title, boolean empty) {
                super.updateItem(title, empty);
                if (empty) {
                    setGraphic(null);
                } else{
                    Label titleLabel = new Label(book.getName());
                    setGraphic(titleLabel);
                }
            }
        });
       quantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
       quantityCol.setCellFactory(col -> new TableCell<Book, Integer>(){
            @Override
            public void updateItem(Integer quantity, boolean empty) {
                super.updateItem(quantity, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Label quantytiLabel = new Label("1");
                    setGraphic(quantytiLabel);
                }
            }
        });
        priceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        priceCol.setCellFactory(col -> new TableCell<Book, Float>() {
            @Override
            public void updateItem(Float price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    String currencyPrice = currencyFormatter.format(price);
                    Label priceLabel = new Label(currencyPrice);
                    setGraphic(priceLabel);
                }
            }
        });
        try {
            cartTable.getItems().setAll(bookDao.getBookbyISBN(CurrentBookISBN));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    // parse and construct User datamodel list by looping your ResultSet rs
    // and return the list



    public void continueShopping() {

    }

    public void proceedToCheckout() {

    }

    public void onBackcToHomelicked() {

    }
}
