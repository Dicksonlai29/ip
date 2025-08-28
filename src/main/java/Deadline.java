public class Deadline extends Task {
    private String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
        this.setType('D');
       
    }

    public Deadline(String task, String deadline, boolean isCompleted) {
        super(task);
        this.deadline = deadline;
        this.setType('D');
        this.setIsCompleted(isCompleted);
    }

    public String getDeadline() {
        return this.deadline;
    }

    public void setDeadline(){
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + (this.getIsCompleted() ? "[X] " : "[ ] ") +
                this.getTitle() +
                " (by: " + this.deadline + ")";
    }
}
