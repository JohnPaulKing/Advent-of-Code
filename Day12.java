import java.io.File;
import java.util.*;

public class Day12 {

    static final int NORTH = 0;
    static final int EAST = 1;
    static final int SOUTH = 2;
    static final int WEST = 3;

    public static void main(String Args[]) {
        Scanner input;
        String line = "";

        try {
            input = new Scanner(new File("directions.txt"));
        } catch (Exception e) {
            return;
        }
        //coordinates of the ship
        int[] pos = new int[2]; //[NS],[EW]
        int[] wayPointPos = new int[] {1,10}; //1 unit east and 10 units north
        int facing = EAST; //starting direction
        int distance; //manhattan distance

        for (int i = 0; input.hasNext(); i++) {
            //read in line
            line = input.nextLine();
            //find the movement / turn character
            char instruction = line.charAt(0);
            //find the number (movement or turn amount, can be multiple chars long)
            int val = Integer.parseInt(line.substring(1));
            System.out.println(line); //for debug
            switch (instruction) {
                case 'F':
                    //move to waypoint
                    moveShipToWayPoint(val,pos,wayPointPos);
                    break;
                case 'N' :
                    move(NORTH,val,wayPointPos);
                    break;
                case 'S' :
                    move(SOUTH,val,wayPointPos);
                    break;
                case 'E' :
                    move(EAST,val,wayPointPos);
                    break;
                case 'W' :
                    move(WEST,val,wayPointPos);
                    break;
                case 'L' :
                    for (int x = 0; x < val / 90; x++) {
                        //get the old values
                        int oldNS = wayPointPos[0];
                        int oldEW = wayPointPos[1];
                        //rotate them
                        wayPointPos[0] = oldEW; //first coord = inverted second cord
                        wayPointPos[1] = -oldNS; //second coord = first coord
                    }
                    break;
                case 'R' :
                    for (int x = 0; x < val / 90; x++) {
                        //get the old values
                        int oldNS = wayPointPos[0];
                        int oldEW = wayPointPos[1];
                        //rotate them
                        wayPointPos[0] = -oldEW; //first coord = inverted second cord
                        wayPointPos[1] = oldNS; //second coord = first coord
                    }
                    break;
            }
            System.out.println("ship coords: "+pos[0] + ","+pos[1]);
            System.out.println("WP coords: "+wayPointPos[0] + ","+wayPointPos[1]);
            System.out.println("facing: " + facing);
        }

        distance = Math.abs(pos[0]) + Math.abs(pos[1]);
        System.out.println(distance);
    }

    static int turn(int dir, int val) {
        int turnAmount = val / 90; //90 degrees per turn, always this amount
        dir += turnAmount;
        dir = dir%4;
        if (dir < 0) {
            dir = 4 - (Math.abs(dir));
        }

        return dir;
    }

    static void moveShipToWayPoint(int val, int[] ship, int[] wayPoint) {
        //move ship and waypoint val times
        for (int i = 0; i < val; i++) {
            //first move ship
            // NS axis
            ship[0] += wayPoint[0];
            ship[1] += wayPoint[1];
            //waypoint stays a relative distance away
        }
    }

    static void move(int dir, int val, int[] pos) {
        switch (dir) {
            case 0:
                pos[0]+=val;
                break;
            case 1:
                pos[1]+=val;
                break;
            case 2:
                pos[0]-=val;
                break;
            case 3:
                pos[1]-=val;
                break;
        }
    }
}