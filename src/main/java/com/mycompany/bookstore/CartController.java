package com.mycompany.bookstore;

import Dao.BookDao;
import Entity.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.mycompany.bookstore.MainApp.CurrentBookISBN;

/**
 * @author andrescabrera, yuliiamelnyk
 */

public class CartController extends BackToHome implements Initializable {

    @FXML
    private TableView<Book> cartTable;
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
    private ObservableList<Book> activeSession = FXCollections.observableArrayList();

    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.GERMANY);

    @Override
    public void onBackToHomelicked() throws IOException {
        super.onBackToHomelicked();
    }

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
                    Label quantityLabel = new Label("1");
                    setGraphic(quantityLabel);
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

/*        TableColumn<Book, String> column1 = new TableColumn<>("Title");
        column1.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> column2 = new TableColumn<>("Quantity");
        column2.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        TableColumn<Book, Float> column3 = new TableColumn<>("Price");
        column3.setCellValueFactory(new PropertyValueFactory<>("orice"));

        cartTable.getColumns().add(column1);
        cartTable.getColumns().add(column2);
        cartTable.getColumns().add(column3);

        cartTable.getItems().setAll(new Book(book.getName(), 1, book.getPrice()));*/
    }

    public void proceedToCheckout() {

    }
}
