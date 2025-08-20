import java.util.Scanner;

public class Marcus {
    public static void main(String[] args) {
        String[] taskList = new String[100];
        int taskListSize = 0;

        //Greeting Text
        System.out.println("Greetings, Marcus here!\n");

        String userInput = "";
        Scanner reader = new Scanner(System.in);

        while (true) {
            System.out.print("What can I do for you today?: ");
            userInput = reader.nextLine();

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < taskListSize; i++) {
                    System.out.println((i + 1) + ": " + taskList[i]);
                }
                System.out.print("\n");
            } else {
                taskList[taskListSize] = userInput;
                taskListSize++;
                System.out.println("added: " + userInput + "\n");
            }
        }

        System.out.println("Mission complete! Was I helpful today?");
    }
}
