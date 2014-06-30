package myatm;


	class NoCardInserted extends Exception {} 

	public class NoCardInsertedDemo {
		
	  public void f() throws NoCardInserted {
	    System.out.println(
	      "Throwing NoCardInserted");
	    throw new NoCardInserted ();
	  }
	  public static void main(String[] args) {
		  NoCardInsertedDemo sed = 
	      new NoCardInsertedDemo();
	    try {
	      sed.f();
	    } catch(NoCardInserted e) {
	      System.err.println("Caught it!");
	    }
	  }
	} 

