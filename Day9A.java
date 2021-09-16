import java.io.File;
import java.util.*;

public class Day9A {
    public static int PREAMBLE = 25;
    public static int GROUP = 25;
    public static int LINES = 1000;


    public static void main(String Args[]) {
        Scanner input;
        String line = "";

        try {
            input = new Scanner(new File("numbers.txt"));
        } catch (Exception e) {
            return;
        }

        long[] nums = new long[LINES];
        long invalid = 0;
        long weakness,low,high;
        int range[];

        for (int i = 0; input.hasNext(); i++){
            line = input.nextLine();
            nums[i] = Long.parseLong(line);
        }

        for (int i = PREAMBLE; i < LINES; i++)
        {
            if (!checkProperty(nums,i)) {
                invalid = nums[i];
            }
        }
        range = findWeakness(nums,invalid);
        low = high = nums[range[0]];
        for (int i = range[0]; i <=range[1];i++) {
            if (nums[i] > high) {
                high = nums[i];
            } else if (nums[i] < low) {
                low = nums[i];
            }
        }
        weakness = low+high;

        System.out.println("invalid: " + invalid);
        System.out.println("weakness: " + weakness);

    }

    static boolean checkProperty(long[] nums, int index) {
        //for each number 0-23
        for (int i = index-GROUP; i < index-1; i++) {
            //for each number after i until 24
            for (int j = i; j < index; j++) {
                if (nums[index] == nums[i]+nums[j]) {
                    return true;
                }
            }
        } return false;
    }

    static int[] findWeakness(long[] nums, long invalid) {
        int[] range;
        long sum;
        //for each number
        for (int i = 0; i < LINES; i++) {
            //while the sum is less than invalid num
            sum = nums[i];
            int j; //declare out of scope
            for (j = i+1; sum < invalid; j++) {
                sum += nums[j];
            }

            if (sum == invalid) {
                return new int[] {i,j-1};
            }
        }  return null;
    }
}