package myatm;

	class NotEnoughMoneyInAccount extends Exception {} 

	public class NotEnoughMoneyInAccountDemo {
		
	  public void f() throws NotEnoughMoneyInAccount {
	    System.out.println(
	      "Throwing NotEnoughMoneyInAccount");
	    throw new NotEnoughMoneyInAccount ();
	  }
	  public static void main(String[] args) {
		  NotEnoughMoneyInAccountDemo sed = 
	      new NotEnoughMoneyInAccountDemo();
	    try {
	      sed.f();
	    } catch(NotEnoughMoneyInAccount e) {
	      System.err.println("Caught it!");
	    }
	  }
	} 