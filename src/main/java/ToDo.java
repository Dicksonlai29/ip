public class ToDo extends Task {
    public ToDo(String task){
        super(task);
    }

    public String toString() {
        return "[T]" + (this.getIsCompleted() ? "[X] " : "[ ] ") +
                this.getTitle();
    }
}
