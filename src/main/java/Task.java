public class Task {
    String description;
    boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    //switches status from false to true
    //returns a string to inform updated status
    public String markComplete() {
        if (this.isCompleted) {
            return "This chapter has already been completed before";
        } else {
            this.isCompleted = true;
            return "A brand new chapter complete!\n" + this;
        }

    }

    //switches status from true to false
    //returns a string to inform updated status
    public String unmarkComplete() {
        if (!this.isCompleted) {
            return "You haven't started on this chapter yet";
        } else {
            this.isCompleted = false;
            return "You forgot about this chapter...\n" + this;
        }

    }

    public String getStatusIcon() {
        return isCompleted ? "[X]" : "[ ]";
    }

    public String getSaveFileString() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
