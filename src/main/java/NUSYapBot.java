import java.util.Scanner;

public class NUSYapBot {
    public static void main(String[] args) {
        String welcome = """
                         ________________________________
                         Hello! I'm NUSYapBot!
                         What can I do for you?
                        _________________________________
                        
                         """;

        String end = """
                        Goodbye. See you!
                        _________________________________
                     """;
        System.out.println(welcome);
        boolean flag = true;
        while (flag) {
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();
            if (!answer.equals("bye")) {
                System.out.println("> " + answer);
            } else {
                flag = false;
            }
        }

        System.out.println(end);
    }
}
