import java.io.File;
import java.util.*;

public class Day11 {

    static final int ROWS = 98;
    static final int COLUMNS = 98;
    static final char OCC = '#';
    static final char FLOOR = '.';
    static final char VACANT = 'L';
    static final int LIMIT = 3; //max seats occupied adjacent
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
                    //System.out.println("Changing from occ to vacant");
                    updatedSeatMap[y][x] = VACANT;
                    changed = true;
                } else if (seatMap[y][x] == VACANT && countAdjacent(seatMap,y,x) <=CRITERIA ) {
                    //System.out.println("Changing from vacant to occ");
                    updatedSeatMap[y][x] = OCC;
                    changed = true;
                }
            }
        }
        copyArrays(updatedSeatMap,seatMap);
        printMap(seatMap);
        return changed;
    }

    static int countAdjacent(char[][] seatMap,int row, int col) {
        int occupied = 0;
        int left = col-1,right=col+1,up=row-1,down=row+1; //mark boundaries
        left = (col == 0) ? col : col-1;
        right = (col == COLUMNS-1) ? col : col+1;
        up = (row == 0) ? row : row-1;
        down = (row == ROWS-1) ? row : row+1;

        //check 3x3 space (or smaller if on boundary)
        for (int y = up; y <= down; y++) {
            for (int x = left; x <= right; x++) {
                //skip middle element
                if (x != col || y != row){
                    if (seatMap[y][x] == OCC) {
                        occupied++;
                    }
                }
            }
        }
        return occupied;
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