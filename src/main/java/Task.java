public class Task {

    private String title;
    private boolean isCompleted;


    public Task(String title) {
        this.title = title;
        this.isCompleted = false;

    }

    public void setIsCompleted(boolean status) {
        this.isCompleted = status;
    }

    public String getTitle() {
        return this.title;
    }
    public boolean getIsCompleted() {
        return this.isCompleted;
    }
}
