import java.io.File;
import java.util.*;

import static java.lang.Math.log;


public class Day5A {
    static final int ROWS = 128;
    static final int COL = 8;
    static final int ROW_MULTIPLIER = 8;


    public static void main(String Args[]) {
        Scanner input;
        String line;

        int treeCounter = 0;
        try {
            input = new Scanner(new File("seats.txt"));
        } catch (Exception e) {
            return;
        }

        int highestID = 0;
        int currentID;
        ArrayList<Integer> IDs = new ArrayList<Integer>();
        //add all IDs
        while (input.hasNext()) {
            boolean added = false;
            line = input.nextLine();
            currentID = getSeatID(line);
            IDs.add(currentID);
        }
        //sort array by number
        IDs.sort(null);


        //print highest ID
        System.out.println("highest: " + IDs.get(IDs.size()-1));

        //find missing seat
        int seat = 0;
        for (int i = 0; i < IDs.size()-1; i++) {
            //if the direct next is 2 above
            if (IDs.get(i + 1) == (IDs.get(i) + 2)) {
                seat = IDs.get(i) + 1;
            }
        }

        System.out.println("seat " + seat);


    }

    public static int getSeatID(String line) {
        //to make this more "flexible"
        int rowBits = (int) (log(ROWS) / log(2));
        int colBits = (int) (log(COL) / log(2));
        int row = 0, column = 0, rowID, low, high ;

        low = 0;
        high = ROWS-1;
        for (int i = 0; i < rowBits; i++) {
            if (line.charAt(i) == 'F') {
                high = ((high-low) / 2) + low;
            } else if (line.charAt(i) == 'B') {
                low = low + (high-low)/2 + 1;
            }
            //System.out.print("Low: " + low + " High: " + high);
        }
        //System.out.println();
        row = low;

        low = 0;
        high = COL-1;
        for (int i = rowBits; i < rowBits+colBits; i++) {
            if (line.charAt(i) == 'L') {
                high = ((high-low) / 2) + low;
            } else if (line.charAt(i) == 'R') {
                low = low + (high-low)/2 + 1;
            }
        }
        column = low;
        //multiply row# by row multiplier, add column#
        rowID = (row*ROW_MULTIPLIER) + column;
        //System.out.println("    Row: " + row + " Column: " + column + " ID: " + rowID);
        return rowID;
    }
}