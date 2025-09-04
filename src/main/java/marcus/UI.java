package marcus;

import marcus.task.Task;

import java.util.Scanner;

public class UI {
    private Scanner reader;

    public UI() {
        this.reader = new Scanner(System.in);
    }

    // Display methods
    public void showWelcome() {
        //Greeting Text
        System.out.println("Greetings, Marcus here!");
        System.out.println("Reading helps quiet the world around me, turning challenges into words on a page");
        System.out.println("I think of each task as a chapter, one step at a time, each with its own meaning, "
                + "each shaping the story I live.");
        System.out.println("I hope this way of thinking will help you face your challenges more easily too!");
    }

    public void requestAction() {
        System.out.print("\nWhat can I do for you today?:\n");
    }

    public void showTaskAdded(TaskList tL) {
        System.out.println("A new chapter in your story!");
        System.out.println("added: " + tL.getTaskList().get(tL.getTaskListSize() - 1));
        System.out.println("Now you have " + tL.getTaskListSize() + " chapters in your story");
    }

    public void showTaskDeleted(TaskList tL, Task deletedTask) {
        System.out.println("The following chapter has been removed from your story:");
        System.out.println(deletedTask);
        System.out.println("Now you have " + tL.getTaskListSize() + " chapters in your story");
    }

    public void showGoodbye() {
        System.out.println("Mission complete! Was I helpful today?");
    }

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
    public String getUserInput() {
        return this.reader.nextLine().trim();
    }

}
