import java.util.ArrayList;

public class Bag {
    private String name; //color-type of bag

    private ArrayList<Bag> containedBy;
    private ArrayList<Bag> contains;
    private ArrayList<Integer> numbers;


    public Bag(String name) {
        this.name = name;

        containedBy = new ArrayList<>();
        contains = new ArrayList<>();
        numbers = new ArrayList<>();
    }

    public void addToBag(Bag bag, int number) {
        containedBy.add(bag);
        bag.addContents(this, number);
    }

    public void addContents(Bag bag, int number) {
        this.contains.add(bag);
        this.numbers.add(number);
    }

    public String toString(){
        return this.name;
    }

    public int countColors(ArrayList<Bag> colors){
        boolean base = false;
        if (colors == null) {
            base = true;
            colors = new ArrayList<>();
        }
        int counter = 0; //only relevant for base
        //for each upper bag
        for (Bag b : containedBy) {
            //if it is not yet in the colors list, add it
            if (!colors.contains(b)) {
                colors.add(b);
            }
            //run this alg for all upper
            b.countColors(colors);
        }

        if (base) {
            return colors.size();
        }

        return counter;
    }

    public long countBagsRequired() {
        long counter = 0;

        for (int i = 0; i < contains.size(); i++) {
            long localCounter = 0;
            System.out.println(this.toString() + " contains " + numbers.get(i) + " of " + contains.get(i));
            counter += (numbers.get(i)); //increment by number require
            localCounter = numbers.get(i);
            counter += localCounter * contains.get(i).countBagsRequired(); //+increment for each subbag

        }
        return counter;
    }
}
