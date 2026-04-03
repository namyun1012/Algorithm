import java.io.IOException;
import java.util.Scanner;

public class Q1259 {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input;
            input = scanner.nextLine();

            if(input.equals("0")) {
                break;
            }

            int len = input.length();
            boolean check = true;



            for(int i = 0; i < (len / 2); i++) {
                if (input.charAt(i) != input.charAt(len - 1 - i)) {
                    check = false;
                    break;
                }
            }

            if(check) {
                System.out.println("yes");
            }
            else {
                System.out.println("no");
            }
        }
    }
}
