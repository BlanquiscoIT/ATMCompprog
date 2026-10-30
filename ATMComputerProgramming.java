package JavaChiz;
import java.util.Scanner;
public class ATMComputerProgramming {
	
public static final Scanner s = new Scanner (System.in);
	
	public static void main(String[] args) {
		

	}
	

	static double login() {
		int tries = 3;
		final int passcode = 1234;
		
		while (tries > 0) {
		System.out.print("Enter PIN: ");
		int pin = s.nextInt();
			
			if (pin.equals(passcode)){
				System.out.println("Access Granted");
				break;
			}else if (pin != passcode) {
				System.out.println("Try Again");
				tries--;
				continue;
			}else (tries == 0) {
				System.out.println("Maximum Amount of Tries Reached");
				break;
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
