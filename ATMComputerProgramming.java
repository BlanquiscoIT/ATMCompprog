package JavaChiz;
import java.util.Scanner;
public class ATMComputerProgramming {
	
public static final Scanner s = new Scanner (System.in);
	
	public static void main(String[] args) {

		login();

	}

}
	

	static void login() {
		int tries = 3;
		final int passcode = 1234;

		System.out.println("Welcome, Teacher!");
		
		while (tries > 0) {
		System.out.print("Enter your PIN: ");
		int pin = s.nextInt();
			
			if (pin < 1000 || pin > 9999){
			    if (tries > 0){
			        System.out.println("PIN must be 4 digits");
			    }
			    tries--;
				
				System.out.println("Invalid PIN!" + " Attempts remaining: " + tries);
				
			    if (tries == 0){
			        break;
			    }
			    continue;
				
			}else if (pin == passcode){
				System.out.println("PIN accepted. Login successful!");
				break;
			}else{
			    tries--;
			    if (tries > 0){
			        System.out.println("Invalid PIN!" + " Attempts remaining: " + tries);
			    }
			}
		}
		
		if (tries == 0){
		    System.out.println("Too many failed attempts. Access denied. Program stopped!");
	    }
  	}


		
	
	

	static double allActivities() {

		

	}
	

	static double withdraw() {

	}
	

	static void checkBalance() {

	}

}
