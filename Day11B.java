import java.io.File;
import java.util.*;

public class Day11B {

    static final int ROWS = 98;
    static final int COLUMNS = 98;
    static final char OCC = '#';
    static final char FLOOR = '.';
    static final char VACANT = 'L';
    static final int LIMIT = 4; //max seats occupied adjacent
    static final int CRITERIA = 0;


    public static void main(String Args[]) {
        Scanner input;
        String line = "";

        try {
            input = new Scanner(new File("seats.txt"));
        } catch (Exception e) {
            return;
        }

        char[][] seatMap = new char[ROWS][COLUMNS];

        for ( int i = 0; input.hasNext() ;i++) {
            line = input.nextLine();
            seatMap[i] = line.toCharArray();
        }
        printMap(seatMap);
        while (seatsChanged(seatMap)){} //keep looping until no seats changed
        System.out.println("Occupied seats: " + countOccupied(seatMap));
    }

    static boolean seatsChanged(char[][] seatMap) {
        boolean changed = false;
        //for doing operations simultaneously
        char[][] updatedSeatMap = new char[ROWS][COLUMNS]; //basically a "temp" array
        copyArrays(seatMap,updatedSeatMap);
        for (int y = 0; y < ROWS;y++) {
            for (int x = 0; x < COLUMNS;x++) {
                //System.out.println("y,x = " + y + "," + x + " val: " + seatMap[y][x] + " occAdj: " + countAdjacent(seatMap,y,x));
                if (seatMap[y][x] == OCC && countAdjacent(seatMap,y,x) > LIMIT) {
                    updatedSeatMap[y][x] = VACANT;
                    changed = true;
                } else if (seatMap[y][x] == VACANT && countAdjacent(seatMap,y,x) <=CRITERIA ) {
                    updatedSeatMap[y][x] = OCC;
                    changed = true;
                }
            }
        }
        copyArrays(updatedSeatMap,seatMap);
        printMap(seatMap);
        return changed;
    }
    //now counts visible seats or whatever dumb thing
    static int countAdjacent(char[][] seatMap,int row, int col) {

        int occupied = 0;
        for (int y = -1; y < 2; y++) {
            for (int x = -1; x < 2; x++) {
                occupied += checkOccupied(seatMap, row, col, y, x);
            }
        }
        return occupied;
    }

    static int checkOccupied(char[][] seatMap, int y, int x, int dirY, int dirX) {
        int xPos = x, yPos = y; //location of seat checking
        x+=dirX;
        y+=dirY;
        for ( ; x > -1 && y > -1 && y < ROWS && x < COLUMNS; x+=dirX,y+=dirY) {
            //System.out.println("Checking x"+x + " Checking y " + y);
            if (x != xPos || y != yPos) {
                if (seatMap[y][x] == OCC) {
                    return 1;
                } else if (seatMap[y][x] == VACANT) {
                    //System.out.println("For x " + x + " y " + y + " hit vacant ");
                    return 0;
                }
            } else {
                //System.out.println("For x " + x + " y " + y + " 0,0 ");
                return 0;
            }
            //System.out.println("For x " + x + " y " + y + " hit wall  ");
        } return 0;
    }
    static int countOccupied(char[][] seatMap) {
        int count = 0;
        for (char[] y : seatMap) {
            for (char x : y) {
                if (x==OCC) {
                    count++;
                }
            }
        }
        return count;
    }

    static void copyArrays(char[][] arr1, char[][] arr2) {
        for (int y = 0; y < ROWS;y++) {
            for (int x = 0; x < COLUMNS;x++) {
                arr2[y][x] = arr1[y][x];
            }
        }
    }

    static void printMap(char[][] map) {
        for (char[] y : map) {
            for (char x : y) {
                System.out.print(x);
            }
            System.out.println();
        }
        System.out.println();
    }

}