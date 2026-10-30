package JavaChiz;
import java.util.Scanner;
public class ATMComputerProgramming {
	
public static final Scanner s = new Scanner (System.in);
	
	public static void main(String[] args) {
		

	}
	

	static void login() {
		int tries = 3;
		final int passcode = 1234;
		
		while (tries > 0) {
		System.out.print("Enter PIN: ");
		int pin = s.nextInt();
			
			if (pin < 1000 || pin > 9999){
			    if (tries > 0){
			        System.out.println("PIN must be 4 digits");
			    }
			    tries--;
			    if (tries == 0){
			        break;
			    }
			    continue;
				
			}else if (pin == passcode){
				System.out.println("Access Granted");
				break;
			}else{
			    tries--;
			    if (tries > 0){
			        System.out.println("Try Again");
			    }
			}
		}
		
		if (tries == 0){
		    System.out.println("Maximum amount of tries reached");
	    }
  	}


		
	}
	

	static double deposit() {

	}
	

	static double withdraw() {

	}
	

	static void checkBalance() {

	}

}
