import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Marcus {
    public static void main(String[] args) {
        Task[] taskList = new Task[100];
        int taskListSize = 0;

        //Greeting Text
        System.out.println("Greetings, Marcus here!");
        System.out.println("Reading helps quiet the world around me, turning challenges into words on a page");
        System.out.println("I think of each task as a chapter, one step at a time, each with its own meaning, "
                            + "each shaping the story I live.");
        System.out.println("I hope this way of thinking will help you face your challenges more easily too!\n");


        String userInput = "";
        Scanner reader = new Scanner(System.in);

        while (true) {
            System.out.print("What can I do for you today?: ");
            userInput = reader.nextLine().trim(); //to remove whitespace if accidentally added by user

            //regex for markStatus, to identify if user input matches this style
            Pattern markStatusPattern = Pattern.compile("^(mark) (\\d+)$");
            Matcher markStatusMatcher = markStatusPattern.matcher(userInput);

            //regex for unmarkStatus, to identify if user input matches this style
            Pattern unmarkStatusPattern = Pattern.compile("^(unmark) (\\d+)$");
            Matcher unmarkStatusMatcher = unmarkStatusPattern.matcher(userInput);



            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Here are the current chapters:");
                for (int i = 0; i < taskListSize; i++) {
                    System.out.println((i + 1) + ". " + taskList[i]);
                }
            } else if (markStatusMatcher.matches()) {
                int taskIndex = Integer.parseInt(markStatusMatcher.group(2));
                if (taskIndex <= taskListSize & taskIndex > 0) {
                    System.out.println(taskList[taskIndex - 1].markComplete());
                } else {
                    System.out.println("That chapter does not exist in my story");
                }
            } else if (unmarkStatusMatcher.matches()) {
                int taskIndex = Integer.parseInt(unmarkStatusMatcher.group(2));
                if (taskIndex <= taskListSize & taskIndex > 0) {
                    System.out.println(taskList[taskIndex - 1].unmarkComplete());
                } else {
                    System.out.println("That chapter does not exist in my story");
                }
            } else {
                taskList[taskListSize] = new Task(userInput);
                taskListSize++;
                System.out.println("A new chapter in my story!");
                System.out.println("added: " + taskList[taskListSize - 1]);
            }
            System.out.print("\n");
        }

        System.out.println("Mission complete! Was I helpful today?");
    }
}
