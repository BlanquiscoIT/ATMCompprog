package JavaChiz;
import java.util.Scanner;
public class ModularActivity {

	public static void main(String[] args) {
		
		displayMenu();
		
		System.out.println();
		
		double subtotal = takeorders();
		
		if (subtotal == 0) {
			System.out.println("No order placed");
		}
		
		double discount = computeDiscount(subtotal);
		double total = computeTotal (subtotal, discount);
		displayReceipt (subtotal, discount, total);

	}
	
	static void displayMenu(){
		System.out.println("=========MENU==========");
		System.out.println("(1) Rice Meal: P60.00");
		System.out.println("(2) Burger   : P45.00");
		System.out.println("(3) Iced Tea : P25.00");
		System.out.println("========================");
		}
	
	static double takeorders() {
		Scanner s = new Scanner (System.in);
		double finalSubtotal = 0;
		
		while (true) {
			System.out.print("Enter Item Code (0 to finish): ");
			int code = s.nextInt();
			
			if (code == 0) {
				break;
			}
			
			double price = getPrice(code);
			
			if (price < 0) {
				System.out.println("Invalid Choice");
				continue;
			}
			
			System.out.print("Enter Quantity: ");
			int qty = s.nextInt();
			double item = computeSubtotal (price, qty);
			finalSubtotal += item;
			}
			return finalSubtotal;
		}
		
	static double getPrice(int code) {
		switch (code) {
			case 1: return 60.00;
			case 2: return 45.00;
			case 3: return 25.00;
			default: return -1;
		}
			
			}
	static double computeSubtotal (double price, int qty) {
				return price * qty;
			}
			
	static double computeDiscount (double subtotal) {
		if (subtotal >= 500.00) {
		return subtotal * 0.10;
		}
		return 0;
		}
			
	static double computeTotal (double subtotal, double discount) {
		return subtotal - discount;
			}
			
			static void displayReceipt (double subtotal, double discount, double total) {
			System.out.println("======Receipt======");
			System.out.printf("Subtotal is: P%.2f\n", subtotal);
			System.out.printf("Discount is: P%.2f\n", discount);
			System.out.printf("Total is   : P%.2f\n", total);
			System.out.println("===================");
			}

}
