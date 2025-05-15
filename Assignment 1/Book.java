public class Book {
    private String title;
    private String author;
    private String isbnNumber;
    private boolean isAvailable;

    public Book(String title, String author, String isbnNumber) {
        this.title = title;
        this.author = author;
        this.isbnNumber = isbnNumber;
        this.isAvailable = true;
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getIsbnNumber() { return isbnNumber; }
    public void setIsbnNumber(String isbnNumber) { this.isbnNumber = isbnNumber; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public void displayDetails() {
        System.out.println("Title: " + title + ", Author: " + author + ", ISBN: " + isbnNumber + ", Available: " + isAvailable);
    }
}
