package nusyapbot.tasktype;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String task, LocalDateTime start, LocalDateTime end) {
        super(task);
        this.startDate = start;
        this.endDate = end;
        this.setType('E');
        
    }
    public Event(String task, LocalDateTime start, LocalDateTime end, boolean isCompleted) {
        super(task);
        this.startDate = start;
        this.endDate = end;
        this.setType('E');
        this.setIsCompleted(isCompleted);
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }
    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public String toString() {
        String formattedStartDate = this.startDate.format(formatter);
        String formattedEndDate = this.endDate.format(formatter);
        return "[E]" + (this.getIsCompleted() ? "[X] " : "[ ] ") +
                this.getTitle() +
                " (from: " + formattedStartDate + "to: " + formattedEndDate + ")";
    }
}
