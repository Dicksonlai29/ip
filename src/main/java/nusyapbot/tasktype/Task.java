package nusyapbot.tasktype;

import java.time.format.DateTimeFormatter;


/**
 * Represents a general task to be recorded.
 * It stores details of a task such as title, isCompleted and type.
 */
public class Task {
    private String title;
    protected boolean isCompleted;
    private char type;
    
    // specify a standard way to display date & time
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yy HH:mm");


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
