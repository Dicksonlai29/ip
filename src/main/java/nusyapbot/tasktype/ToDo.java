package nusyapbot.tasktype;

/**
 * Represents a task of To Do type.
 * The type of this task is set to 'T'.
 */
public class ToDo extends Task {
    public ToDo(String task){
        super(task);
        this.setType('T');
    }
    public ToDo(String task, boolean isCompleted){
        super(task);
        this.setType('T');
        this.setIsCompleted(isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + (this.getIsCompleted() ? "[X] " : "[ ] ") +
                this.getTitle();
    }
}
