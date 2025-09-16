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
    public String showWelcome() {
        String message = "";
        //Greeting Text
        message += "Greetings, Marcus here! ";
        message += "I think of each task as a chapter, one step at a time, each with its own meaning, "
                + "each shaping the story I live. ";
        message += "I hope this way of thinking will help you face your challenges more easily too!";
        return message;
    }

    public String showHelp() {
        return "Here are the commands you can use:\n"
                + "  • list\n"
                + "  • bye\n"
                + "  • todo <description>\n"
                + "  • deadline <description> /by <yyyy-mm-dd>\n"
                + "  • event <description> /from <start> /to <end>\n"
                + "  • mark <task number>\n"
                + "  • unmark <task number>\n"
                + "  • delete <task number>\n"
                + "  • find <keyword>\n"
                + "  • help";
    }


    /**
     * Shows filler text when requesting for user input.
     */
    public String requestAction() {
        return ("What can I do for you today?");
    }

    /**
     * Displays the task added, and the current size of task list.
     */
    public String showTaskAdded(TaskList tL) {
        String message = "";
        message += "A new chapter in your story!\n";
        message += "added: " + tL.getTaskList().get(tL.getTaskListSize() - 1) + "\n";
        message += "Now you have " + tL.getTaskListSize() + " chapters in your story";
        return message;
    }

    /**
     * Displays the task deleted, and the current size of task list.
     */
    public String showTaskDeleted(TaskList tL, Task deletedTask) {
        String message = "";
        message += "The following chapter has been removed from your story:\n";
        message += deletedTask + "\n";
        message += "Now you have " + tL.getTaskListSize() + " chapters in your story";
        return message;
    }

    /**
     * Displays the farewell message from the chatbot, Marcus.
     */
    public String showGoodbye() {
        return "Mission complete! Was I helpful today?";
    }

    /**
     * Displays the entire task list in a format easily understandable by users.
     */
    public String showTaskList(TaskList tL) {
        String message = "";

        if (tL.getTaskListSize() == 0) {
            message += "Your story has no chapters currently";
            return message;
        }

        message += "Here are the current chapters:\n";
        for (int i = 0; i < tL.getTaskListSize(); i++) {
            message += (i + 1) + ". " + tL.getTaskList().get(i) + "\n";
        }
        return message;
    }

    public String findTask(TaskList tL, String keyword) {
        String message = "";

        if (tL.getTaskListSize() == 0) {
            message += "Your story has no chapters currently";
            return message;
        }

        message += "Here are the matching chapters:\n";
        boolean taskFound = false;
        for (int i = 0; i < tL.getTaskListSize(); i++) {
            if (tL.getTaskList().get(i).getDescription().contains(keyword)) {
                message += (i + 1) + ". " + tL.getTaskList().get(i) + "\n";
                taskFound = true;
            }
        }

        if (!taskFound) {
            message += "There are no matching chapters.";
        }

        return message;
    }

}
