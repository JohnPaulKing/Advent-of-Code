import java.io.File;
import java.util.*;



public class Day6A {
    static final char OFFSET = 'a'; //beginning


    public static void main(String Args[]) {
        Scanner input;
        String line = "";

        try {
            input = new Scanner(new File("customs.txt"));
        } catch (Exception e) {
            return;
        }

        int groupCount, total = 0;
        boolean[] groupAns; //default to false

        while (input.hasNext()) {
            //read all persons for group
            groupCount = 0;
            groupAns = new boolean[26]; //default to false
            //until reached a blank line, or eof
            while (input.hasNext() && !(line = input.nextLine()).equals("") ){
                for (int i = 0; i < line.length(); i++) {
                    groupAns[line.charAt(i) - OFFSET] = true;
                }
            }
            for (boolean a : groupAns) {
                //if true, increase count
                groupCount += (a) ? 1:0;
            }
            total += groupCount;
        }

        System.out.println("Total: " + total);
    }
}