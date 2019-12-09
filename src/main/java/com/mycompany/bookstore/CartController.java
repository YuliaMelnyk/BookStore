package com.mycompany.bookstore;

import Dao.BookDao;
import Entity.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.mycompany.bookstore.MainApp.CartBookList;

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
            CartBookList.add(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String currencyPrice = currencyFormatter.format(total);
        subtotalLabel.setText(currencyPrice);

        titleCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        cartTable.getItems().setAll(CartBookList);
    }

    public void proceedToCheckout() {

    }
}
