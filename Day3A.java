import java.io.File;
import java.util.Scanner;



public class Day3A {
    static final char TREE = '#';
    static final char EMPTY = '.';
    static final int ROWS = 323; //number of rows of input
    static final int COL = 31; //number of columns. repeat after 31

    static final int RIGHT = 1;
    static final int DOWN = 2;

    public static void main(String Args[]) {
        Scanner input;
        String line;
        boolean[][] map = new boolean[ROWS][];
        int treeCounter = 0;
        try {
            input = new Scanner(new File("trees.txt"));
        } catch (Exception e) {
            return;
        }

        //fill boolean map
        for (int i = 0; input.hasNext(); i++) {
            line = input.nextLine();
            map[i] = lineToBoolArray(line);
        }

        //traverse map
        int xPos = 0, yPos = 0; //coords
        while (yPos < ROWS) {
            if (map[yPos][xPos]) {
                treeCounter++;
            }
            xPos = (xPos + RIGHT)%COL; //map repeats so set remainder rather than actual number
            yPos += DOWN;
        }

        System.out.println(treeCounter);
    }


    public static boolean[] lineToBoolArray(String line) {
        boolean[] r = new boolean[line.length()];
        for (int i = 0; i < line.length(); i++) {
            r[i] = (line.charAt(i) == TREE);
        } return r;
    }
}