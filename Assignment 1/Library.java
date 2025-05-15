import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) { books.add(book); }

    public void checkOutBook(String isbnNumber) {
        for (Book book : books) {
            if (book.getIsbnNumber().equals(isbnNumber) && book.isAvailable()) {
                book.setAvailable(false);
                System.out.println(book.getTitle() + " has been checked out.");
                return;
            }
        }
        System.out.println("Book not available or not found.");
    }

    public void returnBook(String isbnNumber) {
        for (Book book : books) {
            if (book.getIsbnNumber().equals(isbnNumber) && !book.isAvailable()) {
                book.setAvailable(true);
                System.out.println(book.getTitle() + " has been returned.");
                return;
            }
        }
        System.out.println("Error in returning book or book not found.");
    }

    public void displayBooks() {
        for (Book book : books) {
            book.displayDetails();
        }
    }

    public Book findBookByISBN(String isbn) {
        for (Book book : books) {
            if (book.getIsbnNumber().equals(isbn)) {
                return book;
            }
        }
        return null; // Book not found
    }
    
}
