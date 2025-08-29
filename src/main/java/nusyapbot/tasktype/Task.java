package nusyapbot.tasktype;

public class Task {

    private String title;
    protected boolean isCompleted;
    private char type;


    public Task(String title) {
        this.title = title;
        this.isCompleted = false;

    }

    public void setIsCompleted(boolean status) {
        this.isCompleted = status;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getTitle() {
        return this.title;
    }
    public boolean getIsCompleted() {
        return this.isCompleted;
    }
    public char getType() {
        return this.type;
    }
    
}
