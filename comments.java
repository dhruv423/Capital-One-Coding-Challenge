package assignment1;

import java.util.Iterator;

public class comments implements Iterable<Marble> {
    private int counterIndex = 0; // Hold the last index in the array
    private final int MAXSIZE = 100; // Max size of the array
    private Marble [] array;

    public static void main (String args []) {
        Marble m1 = new Marble(Colour.blue, 24.4);
        Marble m2 = new Marble(Colour.red, 2.4);
        Marble m3 = new Marble(Colour.black, 1.3);
        Marble m4 = new Marble(Colour.pink, 2.1);

        MarblesBagArray marblesBag = new MarblesBagArray();
        marblesBag.add(m1);
        marblesBag.add(m2);
        marblesBag.add(m3);
        marblesBag.add(m4);

        marblesBag.del(m1); // This will delete the marble m1
        System.out.println(marblesBag.isEmpty()); // return false as it is not empty
        System.out.println(marblesBag.size()); // print out the size of list which is 4 since i dont delete i make it null
        System.out.println(marblesBag.isEmptyC(Colour.black)); // prints false as there is black marble in the bag
        System.out.println(marblesBag.maxw()); // prints 2.4 as it is the max weight because we delete m1 from the bag
        System.out.println(marblesBag.minw()); // prints 1.3 as it is the smallest
    }

    public MarblesBagArray(){
        this.array = new Marble[MAXSIZE];
    }

    /**
     * Add a Marble in the array, if exists then show message and return or if at capacity then show message
     * @param itemName Marble to add
     */
    public void add(Marble itemName) {
        if (counterIndex == MAXSIZE - 1) {
            System.out.println("Bag is full, can not add more marbles");
            return;
        }

        if (counterIndex == 0) {
            this.array[0] = itemName;
            counterIndex++;
            return;
        }

        for(int i = 0; i < counterIndex; i ++) {
            if (this.array[i] != null) {
                if (this.array[i].getColour() == itemName.getColour() && this.array[i].getWeight() == itemName.getWeight()) {
                    System.out.println("The marble is already in the bag");
                    return;
                }
            }
            else
                continue;
        }
        this.array[counterIndex] = itemName;
        counterIndex++;
    }

    /**
     * Delete the specified Marble from the array by making it null
     * @param itemname Specified Marble to delete
     */
    public void del(Marble itemname) {
        if (counterIndex == 0) {
            System.out.println("Marble is not in the bag");
            return;
        }

        for (int i = 0; i < MAXSIZE; i++) {
            if (this.array[i].getWeight() == itemname.getWeight() && this.array[i].getColour() == itemname.getColour()) {
                this.array[i] = null;
                return;
            }
        }
        System.out.println("Marble is not in the bag");
    }

    /**
     * Delete the first marble that matches the inputted colour
     * @param colour colour to match
     */
    public void delC(Colour colour) {
        if (counterIndex == 0) {
            System.out.println("Marble of " + colour + " colour is not in the bag");
            return;
        }

        for (int i = 0; i < counterIndex; i++) {
            if (this.array[i] != null) {
                if (this.array[i].getColour() == colour) {
                    this.array[i] = null;
                    return;
                }
            }
            else
                continue;
        }
        System.out.println("Marble of " + colour + " colour is not in the bag");
    }

    /**
     * Check if the array is empty
     * @return true if empty else false
     */
    public boolean isEmpty() {
        if (counterIndex != 0) {
            return false;
        }
        return true;
    }

    /**
     * Check if the array contains no marbles of specified colour
     * @param colour colour to check against
     * @return
     */
    public boolean isEmptyC(Colour colour) {
        if (counterIndex == 0) {
            return true;
        }

        for(int i = 0; i < counterIndex; i ++) {
            if (this.array[i] != null) {
                if (this.array[i].getColour() == colour) {
                    return false;
                }
            } else
                continue;
        }
        return true;
    }

    /**
     * The size of the array
     * @return size of array
     */
    public int size() {
        return counterIndex;
    }

    /**
     * The size of specified colours in the array
     * @param colour specified colour
     * @return
     */
    public int sizeC(Colour colour) {
        int counter = 0;
        if (counterIndex == 0){
            return counter;
        }
        for(int i = 0; i < counterIndex; i ++) {
            if (this.array[i] != null) {
                if (this.array[i].getColour() == colour) {
                    counter++;
                }
            } else
                continue;
        }
        return counter;
    }

    /**
     * Find the maximum weight marble in the array
     * @return maximum weight of the marble
     */
    public double maxw() {
        if (counterIndex == 0){
            return 0.0;
        }
        double maxWeight = 0;
        for(int i = 0; i < counterIndex; i ++) {
            if (this.array[i] != null) {
                if (this.array[i].getWeight() > maxWeight) {
                    maxWeight = this.array[i].getWeight();
                }
            } else
                continue;
        }
        return maxWeight;
    }

    /**
     * Find the minimum weight marble in the array
     * @return minimum weight of the marble
     */
    public double minw() {
        if (counterIndex == 0){
            return 0.0;
        }
        double minWeight = Double.MAX_VALUE;
        for(int i = 0; i < counterIndex; i ++) {
            if (this.array[i] != null) {
                if (this.array[i].getWeight() < minWeight) {
                    minWeight = this.array[i].getWeight();
                }
            } else
                continue;
        }
        return minWeight;
    }

    // Iterator
    @Override
    public Iterator<Marble> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Marble> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            while(index < 100) {
                if(array[index] != null) {
                    return true;
                }
                index++;
            }
            return false;
        }
        
        @Override
        public Marble next() {
            Marble marble = array[index];
            index++;
            return marble;
        }
    }
}
