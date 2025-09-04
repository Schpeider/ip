public class DeadlineTask extends Task{
    String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getSaveFileString() {
        return String.format("D|%d|%s|%s", this.isCompleted ? 1 : 0, this.description, this.deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
