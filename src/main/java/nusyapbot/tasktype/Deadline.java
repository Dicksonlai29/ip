package nusyapbot.tasktype;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
        this.setType('D');
       
    }

    public Deadline(String task, LocalDateTime deadline, boolean isCompleted) {
        super(task);
        this.deadline = deadline;
        this.setType('D');
        this.setIsCompleted(isCompleted);
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public void setDeadline(LocalDateTime deadline){
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + (this.getIsCompleted() ? "[X] " : "[ ] ") +
                this.getTitle() +
                " (by: " + this.deadline + ")";
    }
}
