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

    public String toString() {
        return "[T]" + (this.getIsCompleted() ? "[X] " : "[ ] ") +
                this.getTitle();
    }
}
