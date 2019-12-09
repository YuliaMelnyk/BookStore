package Entity;

import java.util.LinkedList;
import java.util.List;

/**
 * @author andrescabrera, yuliiamelnyk
 */
public class Book {
    String ISBN;
    String name;
    String genre;
    float price;
    String description;
    byte[] image;
    String author;
    String publisher;
    String year;
    String language;
    private List<String> images;

    public Book(){
        images = new LinkedList<>();
    }

    public Book(String isbn, String name, String genre, float price,
                String description, byte[] image, String author,
                String publisher, String year, String language) {
        this.ISBN = isbn;
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.description = description;
        this.image = image;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.language = language;
    }

    //getters and setters

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> image) {
        this.images = image;
    }


}
