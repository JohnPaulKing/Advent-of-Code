import java.io.File;
import java.util.Scanner;

public class Day2A {
    public static void main(String Args[]) {
        Scanner input;
        try {
            input = new Scanner(new File("passwords"));
        } catch(Exception e) { return; }

        int min,max,boarder1,boarder2;
        int counter;
        int correctCounter = 0;
        char c;
        String line, password;

        while (input.hasNext()){
            counter = 0;
            line = input.nextLine();
            boarder1 = line.indexOf('-');
            min = Integer.parseInt(line.substring(0,boarder1)); //nunmber until dash
            boarder2 = line.indexOf(' ');
            max = Integer.parseInt(line.substring(boarder1+1,boarder2));
            c = line.substring(boarder2+1).charAt(0);

            password = line.substring(boarder2+4); //beginning of actual password

            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) == c) {
                    counter++;
                }
            }

            if (counter >= min && counter <= max) {
                correctCounter++;
            }
        }
        System.out.println(correctCounter);
    }
}
