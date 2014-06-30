package myatm;

class NotEnoughMoneyInATM extends Exception {} 

public class NotEnoughMoneyInATMDemo {
	
  public void f() throws NotEnoughMoneyInATM {
    System.out.println(
      "Throwing NotEnoughMoneyInATM");
    throw new NotEnoughMoneyInATM ();
  }
  public static void main(String[] args) {
	  NotEnoughMoneyInATMDemo sed = 
      new NotEnoughMoneyInATMDemo();
    try {
      sed.f();
    } catch(NotEnoughMoneyInATM e) {
      System.err.println("Caught it!");
    }
  }
} 