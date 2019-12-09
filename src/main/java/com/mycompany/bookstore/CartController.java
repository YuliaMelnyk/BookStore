package com.mycompany.bookstore;

import Dao.BookDao;
import Entity.Book;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Window;

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

    private static final String STYLESHEET = LoginController.class.getResource("/fxml//styles/alert.css").toExternalForm();

    @FXML
    private TableView<Book> cartTable;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, Float> priceCol;

    @FXML
    JFXButton proceedToCheckout;

    @FXML
    JFXButton deleteItem;

    @FXML
    private Label subtotalLabel;
    private BookDao bookDao;
    private Book book;
    private double total = 0;

    private Alert.AlertType type = Alert.AlertType.INFORMATION;

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

        cartTable.getSelectionModel().getSelectedItems().addListener(
                new ListChangeListener<Book>() {
                    @Override
                    public void onChanged(Change<? extends Book> change) {
                        deleteItem.setVisible(true);
                    }
                }
        );
    }

    public void proceedToCheckout() {
        createInfoAlert();
        CartBookList.clear();
        cartTable.getItems().clear();
    }

    public void deleteItem() {
        deleteItem.setOnAction(e -> {
            Book selectedItem = cartTable.getSelectionModel().getSelectedItem();
            cartTable.getItems().remove(selectedItem);
            CartBookList.remove(selectedItem);
        });
    }
    public  Alert createInfoAlert() {
        Window owner = proceedToCheckout.getScene().getWindow();
        Alert alert = new Alert(type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.getDialogPane().getStylesheets().add(STYLESHEET);
        alert.initOwner(owner);
        alert.getDialogPane().setContentText("You purchase will be delivered within 3 days");
        alert.getDialogPane().setHeaderText("Your purchase has been successful.");
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK);
        return alert;
    }
}
