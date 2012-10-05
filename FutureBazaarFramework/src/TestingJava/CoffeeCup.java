package TestingJava;
public class CoffeeCup {

    /**
    * The amount of coffee contained in the cup.
    * Units are in milliliters of coffee.
    */
    private int innerCoffee = 0;        // a field

    /**
    * Adds coffee to the current inner amount.
    */
    public void addCoffee(int amount) {	// a method
        innerCoffee += amount;
    }

    /**
    * Releases one sip of coffee to the caller.
    * If current inner amount (innerCoffee) is less than a sip,
    * then returns entire remaining amount of coffee.
    * Always decrements innerCoffee by amount returned.
    */
    
    public int releaseOneSip(int sipSize) {
        int sip = sipSize;
        if (innerCoffee < sipSize) {
            sip = innerCoffee;
        }
        innerCoffee -= sip;
        return sip;
    }
    
    /**
    * Releases entire store of coffee to the caller.
    * Sets innerCoffee to zero.
    */
    public int spillEntireContents() {
        int all = innerCoffee;
        innerCoffee = 0;
        return all;
    }
    
    public static void main(String[] args)
    {
    	CoffeeCup cup = new CoffeeCup();
    	cup.addCoffee(150); // 150 ml of coffee
    	System.out.println("in coffee"+cup.innerCoffee);
    	System.out.println(cup.releaseOneSip(20)); // 20 ml sip
    	System.out.println("in coffee"+cup.innerCoffee);
    	int nn =cup.spillEntireContents();
    	System.out.println(nn);
    }
}