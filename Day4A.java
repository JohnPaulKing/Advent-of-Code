import java.io.File;
import java.util.Scanner;



public class Day4A {

    public static void main(String Args[]) {
        String datos,line;
        Scanner input;
        Passport test;
        int validCounter = 0;
        try {
            input = new Scanner(new File("passport.txt"));
        } catch (Exception e) {
            return;
        }

        datos = "";
        while (input.hasNext()) {
            line = input.nextLine();
            //if line is empty, start a new string
            if (line.equals("")) {
                System.out.print(datos + " ");
                test = new Passport(datos);
                if (test.isValid()) {
                    validCounter++;
                }
                datos = "";
            } else {
                datos = datos + " " + line;
            }
        }
        //last line of file seems messed up. this fixes it
        if (!datos.equals("")) {
            test = new Passport(datos);
            if (test.isValid()) {
                validCounter++;
            }
        }
        System.out.println(validCounter);


    }

}