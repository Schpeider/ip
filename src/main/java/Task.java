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
            return "This chapter has already been read before";
        } else {
            this.isCompleted = true;
            return "A brand new chapter complete!\n" + this;
        }

    }

    //switches status from true to false
    //returns a string to inform updated status
    public String unmarkComplete() {
        if (!this.isCompleted) {
            return "I haven't started on this chapter yet";
        } else {
            this.isCompleted = false;
            return "I-I can't remember this chapter anymore...\n" + this;
        }

    }

    public String getStatusIcon() {
        return isCompleted ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
