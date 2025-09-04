package marcus.task;

public class ToDoTask extends Task{
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String getSaveFileString() {
        return String.format("T|%d|%s", this.isCompleted ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
