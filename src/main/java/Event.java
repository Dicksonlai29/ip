public class Event extends Task {
    private String startDate;
    private String endDate;

    public Event(String task, String start, String end) {
        super(task);
        this.startDate = start;
        this.endDate = end;
    }

    public String getStartDate() {
        return this.startDate;
    }
    public String getEndDate() {
        return this.endDate;
    }

    public String toString() {
        return "[E]" + (this.getIsCompleted() ? "[X] " : "[ ] ") +
                this.getTitle() +
                " (from: " + this.startDate + "to: " + this.endDate + ")";
    }
}
