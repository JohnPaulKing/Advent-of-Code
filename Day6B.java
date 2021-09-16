import java.io.File;
import java.util.*;



public class Day6B {
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
            groupAns = new boolean[26];
            Arrays.fill(groupAns,true); //starts as true
            //until reached a blank line, or eof
            while (input.hasNext() && !(line = input.nextLine()).equals("") ){
                boolean[] tempAns = new boolean[26]; //starts as false
                for (int i = 0; i < line.length(); i++) {
                    tempAns[line.charAt(i) - OFFSET] = true;
                }
                //now update group ans
                for (int i = 0; i < tempAns.length; i++) {
                    groupAns[i] &= tempAns[i];
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