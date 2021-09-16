import java.io.File;
import java.util.*;



public class Day7A {

    public static ArrayList<Bag> bags = new ArrayList<>();
    public static String searchTerm = "shinygold";

    public static void main(String Args[]) {
        Scanner input;
        String line = "";

        try {
            input = new Scanner(new File("luggage.txt"));
        } catch (Exception e) {
            return;
        }

        String bagName;
        int bagNum;
        int colorCount;
        long bagsRequired;
        String[] words;
        Bag container;
        Bag contained;
        while (input.hasNext()) {
            line = input.nextLine();
            //split by space
            words = line.split(" ");
            bagName = words[0] + words[1];

            //if doesn't exist, create it
            if ((container = findBag(bagName)) == null) {
                container = new Bag(bagName);
                bags.add(container);
            }

            //go through rest of the string
            for (int i = 4; i+2 < words.length; i+=4) {
                try {
                    bagNum = Integer.parseInt(words[i]);
                } catch (NumberFormatException e) {
                    bagNum = 0; //weird input?
                }
                bagName = words[i+1] + words[i+2];
                if ( (contained = findBag(bagName)) == null) {
                    contained = new Bag(bagName);
                    bags.add(contained);
                }
                //add the container to the bags comments
                contained.addToBag(container,bagNum);
            }
        }

        colorCount = findBag(searchTerm).countColors(null);
        bagsRequired = findBag(searchTerm).countBagsRequired();
        System.out.println("Colors: " + colorCount);
        System.out.println("Bags required: " + bagsRequired);
    }

    public static Bag findBag(String name) {
        for (Bag b: bags) {
            if (b.toString().equals(name)) {
                return b;
            }
        } return null;
    }
}