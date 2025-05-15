public class EBook extends Book {
    private double fileSize;
    private String format;

    public EBook(String title, String author, String isbnNumber, double fileSize, String format) {
        super(title, author, isbnNumber);
        this.fileSize = fileSize;
        this.format = format;
    }

    // Getters and Setters
    public double getFileSize() { return fileSize; }
    public void setFileSize(double fileSize) { this.fileSize = fileSize; }
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("File Size: " + fileSize + "MB, Format: " + format);
    }
}
