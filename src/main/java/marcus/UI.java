package marcus;

import marcus.task.Task;

import java.util.Scanner;

public class UI {
    private Scanner reader;

    public UI() {
        this.reader = new Scanner(System.in);
    }

    // Display methods
    /**
     * Shows the welcome message from the chatbot, Marcus.
     */
    public void showWelcome() {
        //Greeting Text
        System.out.println("Greetings, Marcus here!");
        System.out.println("Reading helps quiet the world around me, turning challenges into words on a page");
        System.out.println("I think of each task as a chapter, one step at a time, each with its own meaning, "
                + "each shaping the story I live.");
        System.out.println("I hope this way of thinking will help you face your challenges more easily too!");
    }

    /**
     * Shows filler text when requesting for user input.
     */
    public void requestAction() {
        System.out.print("\nWhat can I do for you today?:\n");
    }

    /**
     * Displays the task added, and the current size of task list.
     */
    public void showTaskAdded(TaskList tL) {
        System.out.println("A new chapter in your story!");
        System.out.println("added: " + tL.getTaskList().get(tL.getTaskListSize() - 1));
        System.out.println("Now you have " + tL.getTaskListSize() + " chapters in your story");
    }

    /**
     * Displays the task deleted, and the current size of task list.
     */
    public void showTaskDeleted(TaskList tL, Task deletedTask) {
        System.out.println("The following chapter has been removed from your story:");
        System.out.println(deletedTask);
        System.out.println("Now you have " + tL.getTaskListSize() + " chapters in your story");
    }

    /**
     * Displays the farewell message from the chatbot, Marcus.
     */
    public void showGoodbye() {
        System.out.println("Mission complete! Was I helpful today?");
    }

    /**
     * Displays the entire task list in a format easily understandable by users.
     */
    public void showTaskList(TaskList tL) {
        if (tL.getTaskListSize() > 0) {
            System.out.println("Here are the current chapters:");
            for (int i = 0; i < tL.getTaskListSize(); i++) {
                System.out.println((i + 1) + ". " + tL.getTaskList().get(i));
            }
        } else {
            System.out.println("Your story has no chapters currently");
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    // Input methods
    /**
     * Takes in user input.
     * Additional whitespace is trimmed.
     */
    public String getUserInput() {
        return this.reader.nextLine().trim();
    }

}
