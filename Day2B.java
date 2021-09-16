import java.io.File;
import java.util.Scanner;

public class Day2B {
    public static void main(String Args[]) {
        Scanner input;
        try {
            input = new Scanner(new File("passwords"));
        } catch(Exception e) { return; }

        int index1,index2,boarder1,boarder2;
        int correctCounter = 0;
        char c;
        String line, password;

        while (input.hasNext()){
            line = input.nextLine();
            boarder1 = line.indexOf('-');
            //+1 offsets the positions by 1
            index1 = Integer.parseInt(line.substring(0,boarder1)) - 1; //nunmber until dash
            boarder2 = line.indexOf(' ');
            index2 = Integer.parseInt(line.substring(boarder1+1,boarder2)) - 1;
            c = line.substring(boarder2+1).charAt(0);

            password = line.substring(boarder2+4); //beginning of actual password



            if ( (password.charAt(index1) == c ) ^ (password.charAt(index2) == c) ) {

                correctCounter++;
            }
        }
        System.out.println(correctCounter);
    }
}