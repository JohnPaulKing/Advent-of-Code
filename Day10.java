import java.io.File;
import java.util.*;

public class Day10 {
    public static int JOLT_DIFF = 3;
    public static int LAST_JOLT_DIFF = 3;

    public static void main(String Args[]) {
        Scanner input;
        String line = "";

        try {
            input = new Scanner(new File("smallTest"));
        } catch (Exception e) {
            return;
        }

        ArrayList<Integer> jolts = new ArrayList<>();
        int one= 0, two = 0, three =0; //keeping track of jolt differences
        int answer;
        long paths;

        jolts.add(0); //initial output

        //get input, add it to array
        while (input.hasNext()) {
            line = input.nextLine();
            jolts.add(Integer.parseInt(line));
        }
        //sort it ascending
        jolts.sort(null);
        //add devices adapter (highest + 3)
        jolts.add(jolts.get(jolts.size()-1) + LAST_JOLT_DIFF);

        //go through the list, count differences
        //don't hit last element
        for (int i = 0; i < jolts.size()-1; i++) {
            int dif = jolts.get(i+1)-jolts.get(i);
            //System.out.println(jolts.get(i) + " to " + jolts.get(i+1) + " diff: " + dif);
            switch (dif) {
                case 1:
                    one++;
                    break;
                case 2:
                    two++;
                    break;
                case 3:
                    three++;
                    break;
            }
        }
        //System.out.println("Diff of one:" + one);
        //System.out.println("Diff of two:" + two);
        //System.out.println("Diff of three:" + three);

        answer = one * three;
        System.out.println("part 1:" + answer);

        paths = connect(jolts,0,"");
        System.out.println("part 2: "+ paths);

    }

    static long connect(ArrayList<Integer> jolts,int index,String path){
        long count = 0;
        int options = 0;
        //check base case (end of array)
        path = path + " -> " +jolts.get(index);

        for (int i = 1; index+i < jolts.size() && jolts.get(i+index)-jolts.get(index) <= JOLT_DIFF ; i++) {
            //System.out.println(jolts.get(index) + " can connect to " + jolts.get(index+i));
            options++;
            count += (connect(jolts, index + i,path) );
        }

        //base case check
        //when connecting to end of array
        if (options == 0) {
            //System.out.println(path);
            return 1;
        }
        return count;
    }
}