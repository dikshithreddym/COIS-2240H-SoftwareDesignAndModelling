public class AudioBook extends Book {
    private double duration;
    private String audioFormat;

    public AudioBook(String title, String author, String isbnNumber, double duration, String audioFormat) {
        super(title, author, isbnNumber);
        this.duration = duration;
        this.audioFormat = audioFormat;
    }

    // Getters and Setters
    public double getDuration() { return duration; }
    public void setDuration(double duration) { this.duration = duration; }
    public String getAudioFormat() { return audioFormat; }
    public void setAudioFormat(String audioFormat) { this.audioFormat = audioFormat; }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Duration: " + duration + " hours, Audio Format: " + audioFormat);
    }
}
