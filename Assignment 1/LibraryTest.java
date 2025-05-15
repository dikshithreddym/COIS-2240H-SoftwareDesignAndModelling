public class LibraryTest {
    public static void main(String[] args) {
        Library library = new Library();

        // Test 1: Add a new book and display the library
        System.out.println("Test 1: Add a new book and display the library");
        library.addBook(new Book("Effective Java", "Joshua Bloch", "1122334455"));
        System.out.println("Expected output: The library should list 'Effective Java' among the books.");
        library.displayBooks();

        // Test 2: Check out an available book and verify its status
        System.out.println("\nTest 2: Check out an available book and verify its status");
        library.checkOutBook("1122334455");
        System.out.println("Expected output: 'Effective Java' should be marked as not available.");
        Book checkedOutBook = library.findBookByISBN("1122334455"); // Implement this method in Library
        if (checkedOutBook != null) {
            System.out.println("Book availability: " + (checkedOutBook.isAvailable() ? "Available" : "Not Available"));
        } else {
            System.out.println("Book not found");
        }

        // Test 3: Return a book that has been checked out
        System.out.println("\nTest 3: Return a book that has been checked out");
        library.returnBook("1122334455");
        System.out.println("Expected output: 'Effective Java' should be marked as available after being returned.");
        Book returnedBook = library.findBookByISBN("1122334455");
        if (returnedBook != null) {
            System.out.println("Book availability: " + (returnedBook.isAvailable() ? "Available" : "Not Available"));
        } else {
            System.out.println("Book not found");
        }

        // Test 4: Attempt to return a book that has never been checked out
        System.out.println("\nTest 4: Attempt to return a book that has never been checked out");
        library.returnBook("1122334455"); // Attempting to return the same book again without checking it out
        System.out.println("Expected output: The attempt should fail with a message indicating that the book was not checked out.");
    
    }
}
