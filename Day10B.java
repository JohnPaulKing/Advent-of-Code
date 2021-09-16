import java.io.File;
import java.util.*;

public class Day10B {
    public static int JOLT_DIFF = 3;
    public static int LAST_JOLT_DIFF = 3;

    public static void main(String Args[]) {
        Scanner input;
        String line = "";

        try {
            input = new Scanner(new File("jolts.txt"));
        } catch (Exception e) {
            return;
        }

        ArrayList<Integer> jolts = new ArrayList<>();
        ArrayList<Long> cache = new ArrayList<>();
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

        for (int i = 0; i <= jolts.size();i++) {
            cache.add((long) 0); //initialize cache
        }

        paths = connect(jolts, cache,0,"");
        System.out.println("part 2: "+ paths);

    }


    static long connect(ArrayList<Integer> jolts,ArrayList<Long> cache, int index,String path){
        long count = 0;
        int options = 0;
        //check base case (end of array)


        for (int i = 1; index+i < jolts.size() && jolts.get(i+index)-jolts.get(index) <= JOLT_DIFF ; i++) {
            //System.out.println("index " + index+ " has cache " + cache.get(index));
            options++;
            if (cache.get(index+i) == 0) {
                //System.out.println("Cache empty at index " + (index+i) + " val " + jolts.get(index+i));
                count += (connect(jolts, cache,index + i,path) );
                cache.set(index+i,count); //shouldn't be reallocated
            } else {
                //System.out.println("Cache value " + cache.get(index+i) + " at index " + (index+i)+ " val " + jolts.get(index+i));
                count+= cache.get(index+i);
            }

        }

        //base case check
        //when connecting to end of array
        if (options == 0) {
            cache.set(index,(long)1);
            return 1;
        }
        return count;
    }
}

