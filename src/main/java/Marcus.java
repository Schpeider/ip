import java.util.Scanner;

public class Marcus {
    public static void main(String[] args) {
        System.out.println("Greetings, Marcus here!\n");

        String userInput = "";
        Scanner reader = new Scanner(System.in);

        while (true) {
            System.out.print("What can I do for you today?: ");
            userInput = reader.nextLine();

            if (userInput.equals("bye")) {
                break;
            }

            System.out.println(userInput + "\n");
        }

        System.out.println("Mission complete! Was I helpful today?");
    }
}
