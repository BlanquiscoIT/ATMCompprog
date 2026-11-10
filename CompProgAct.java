package JavaChiz;

import java.util.Scanner;
public class CompProgAct {
	
	public static final Scanner s = new Scanner(System.in);
	
	static int numStudents, numQuizzes, numLabs, classesHeld;
    static int[] quizTotals, labTotals;
    static int midtermTotal, finalTotal;
    static int quizGrandTotal, labGrandTotal; // total points ng lahat ng quizzes at labs (walang array na kailangan)
    
    // para sa class summary (nire-reset every new class)
    static int studentsEncoded, passedCount, failedCount, droppedCount, deansListCount;
    static double sumAverages, highestAvg, lowestAvg;
    static String highestName, lowestName;
    
	public static void main(String[] args) {
		
		
		// 1. Title at login (login = may 3 attempts lang)
		showTitle();
		if (!login()) {
			return; // 3 wrong PINs, titigil na yung program
		}
		
		// 2. Repetition: uulit hanggang "N" ang sagot ng teacher sa "Encode another class?"
		boolean another;
		do {
			runClass(); // isang buong class: setup -> students -> summary
			another = askAnotherClass();
		} while (another);
		
		// 3. Exit message
		System.out.println("Thank you for using the Student Grade Computation System. Goodbye!");

	}
	
	static void showTitle() {
		System.out.println("------------------------- Student Grade Computation System ---------------------");
	}
	
	// ito yung nagpapatakbo ng isang buong class (tinatawag ng main para hindi magulo yung main)
	static void runClass() {
		setupClass();    // class setup (isang beses lang per class)
		resetSummary();  // fresh counters para sa bagong class
		
		for (int i = 1; i <= numStudents; i++) {
			processStudent(i); // score entry + computation + result ng bawat student
		}
		
		displaySummary(); // class summary pagkatapos ng lahat ng students
	}
	
	static boolean login() {

	    int tries = 3;
	    final int passcode = 1234;

	    System.out.println("Welcome, Teacher!");

	    while (tries > 0) {
	        System.out.print("Enter your PIN: ");
	        
	       
	        if (!s.hasNextInt()) {
	            s.nextLine();
	            tries--;
	            System.out.println("PIN must be numbers only!");
	            System.out.println("Invalid PIN! Attempts remaining: " + tries);
	            continue;
	        }
	        
	        int pin = s.nextInt();
	        s.nextLine();

	        if (pin < 1000 || pin > 9999) {
	            System.out.println("PIN must be 4 digits");
	            tries--;
	            System.out.println("Invalid PIN! Attempts remaining: " + tries);
	        } else if (pin == passcode) {
	            System.out.println("PIN accepted. Login successful!");
	            return true;
	        } else {
	            tries--;
	            System.out.println("Invalid PIN! Attempts remaining: " + tries);
	        }
	    }

	    System.out.println("Too many failed attempts. Access denied. Program stopped!");
	    return false;
	}
	
	static void setupClass() {
		System.out.println("---- CLASS SETUP ----");
		numStudents = getValidInt("Enter number of students: ", 1, Integer.MAX_VALUE);
		numQuizzes = getValidInt("Enter number of quizzes (1-5): ", 1, 5);
			quizTotals = new int[numQuizzes];
			quizGrandTotal = 0;
			for (int i=0;i<numQuizzes;i++) {
			quizTotals[i] = getValidInt("Total points of Quiz " + (i + 1) + ": ", 1, Integer.MAX_VALUE);
			quizGrandTotal += quizTotals[i];
			}
			numLabs = getValidInt("Enter number of laboratory activities (1-5): ", 1, 5);
			labTotals = new int[numLabs];  
			labGrandTotal = 0;
			for (int x=0;x<numLabs;x++) {
			labTotals[x] = getValidInt("Total points of lab activity " + (x + 1) + ": ", 1, Integer.MAX_VALUE);
			labGrandTotal += labTotals[x];
			}
		midtermTotal = getValidInt("Total points of Midterms Exams: ", 1, Integer.MAX_VALUE);
		finalTotal = getValidInt("Total points of Finals Exams: ", 1, Integer.MAX_VALUE);
		classesHeld = getValidInt("Enter number of classes held: ", 1, Integer.MAX_VALUE);
		
	}
	
	static int getValidInt(String input, int min, int max) {
		while (true) {
			System.out.print(input);
		if (s.hasNextInt()) {
			int value = s.nextInt();
            s.nextLine();
            if (value >= min&&value <=max) {
            	return value;
            	}
            	System.out.println("Error. Out of range.");
			}else { 
			s.nextLine(); // FIX: tinatanggal yung maling input, kung wala to infinite loop
			System.out.println("ERROR. USER DID NOT INPUT A NUMBER.");
			}
		}
	}
	
	// NEW: kapareho ng getValidInt pero para sa scores (pwedeng may decimal), 0 hanggang maxPoints lang
	static double getValidScore(String prompt, int maxPoints) {
		while (true) {
			System.out.print(prompt);
			if (s.hasNextDouble()) {
				double value = s.nextDouble();
				s.nextLine();
				if (value < 0) {
					System.out.println("Score cannot be negative! Please try again.");
				} else if (value > maxPoints) {
					System.out.println("Score cannot be higher than the total points (" + maxPoints + ")! Please try again.");
				} else {
					return value;
				}
			} else {
				s.nextLine();
				System.out.println("ERROR. USER DID NOT INPUT A NUMBER.");
			}
		}
	}
	
	
	static boolean attendanceCheck(double attendance) { 
		if (attendance < 80) {
			return true; 
			}
		else return false;
	}
	
	
	static String studentRemarks(double grade, boolean dropped) { //chinecheck neto yung conditions for dropping, passing, and failing
		if (dropped) {
			return "Dropped (absences exceed 20%)";
		}
		else if (grade <= 3.00) {
			return "Passed";
		}
		else {
			return "Failed";
		}
	}
	
	
    static String getStudentName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String studentName = s.nextLine().trim();

            if (studentName.isEmpty()) {
                System.out.println("Name cannot be an empty field, please try again.");
                continue;
            }

            if (studentName.matches(".*\\d.*")) {
                System.out.println("Name cannot contain numbers, please try again.");
                continue;
            }

        return studentName;
        }
    }
	
	
	
    static double getGradeEquI(double average) {
    	double percent = Math.round(average * 100) / 100.0;
    	
    	if (percent >= 96.50) {
    		return 1.00;
    	}else if (percent >= 93.50) { 
    		return 1.25;
    	}else if (percent >= 90.50) {
    		return 1.50;
    	}else if (percent >= 87.50) {
    		return 1.75;
    	}else if (percent >= 84.50) {
    		return 2.00;
    	}else if (percent >= 81.50) {
    		return 2.25;
    	}else if (percent >= 78.50) {
    		return 2.50;
        }else if (percent >= 75.50) {
    		return 2.75;
    	}else if (percent >= 75.00) {
    		return 3.00;
    	}else {
    		return 5.00; 
    	}
    }
    
    
    // ito yung buong process para sa 1 student (input, compute, display)
    static void processStudent(int number) {
    	System.out.println("\n===== Student " + number + " =====");
    	String name = getStudentName("Enter student name: ");
    	
    	double quizRaw = 0;
    	for (int i = 0; i < numQuizzes; i++) {
    		quizRaw += getValidScore("Quiz " + (i + 1) + " score (out of " + quizTotals[i] + "): ", quizTotals[i]);
    	}
    	
    	double labRaw = 0;
    	for (int x = 0; x < numLabs; x++) {
    		labRaw += getValidScore("Lab Activity " + (x + 1) + " score (out of " + labTotals[x] + "): ", labTotals[x]);
    	}
    	
    	double midterm = getValidScore("Midterm Exam score (out of " + midtermTotal + "): ", midtermTotal);
    	double finals = getValidScore("Final Exam score (out of " + finalTotal + "): ", finalTotal);
    	double attended = getValidScore("Classes attended (out of " + classesHeld + "): ", classesHeld);
    	
    	// percentage ng bawat component
    	double quizPct = computePercentage(quizRaw, quizGrandTotal);
    	double labPct = computePercentage(labRaw, labGrandTotal);
    	double examPct = computePercentage(midterm + finals, midtermTotal + finalTotal);
    	double attendPct = computePercentage(attended, classesHeld);
    	
    	double average = computeFinalAverage(quizPct, labPct, examPct, attendPct);
    	boolean dropped = attendanceCheck(attendPct);
    	double grade = getGradeEquI(average);
    	String remarks = studentRemarks(grade, dropped);
    	
    	String gradeText;
    	if (dropped) {
    		gradeText = "DRP";
    	} else {
    		gradeText = String.format("%.2f", grade);
    	}
    	
    	System.out.printf("Quizzes: %.2f | Laboratory: %.2f | Exams: %.2f | Attendance: %.2f%n", quizPct, labPct, examPct, attendPct);
    	System.out.printf("Final Average   : %.2f%n", average);
    	System.out.println("Grade Equivalent: " + gradeText);
    	System.out.println("Remarks         : " + remarks);
    	
    	updateSummary(name, average, grade, dropped);
    }
    
    static double computePercentage(double raw, double total) {
    	return (raw / total) * 100;
    }
    
    // Exams 30%, Laboratory 40%, Quizzes 20%, Attendance 10%
    static double computeFinalAverage(double quizPct, double labPct, double examPct, double attendPct) {
    	return (examPct * 0.30) + (labPct * 0.40) + (quizPct * 0.20) + (attendPct * 0.10);
    }

	// ni-rreset yung values kapag nag ulit ng isa pang class
    static void resetSummary() {
    	studentsEncoded = 0;
    	passedCount = 0;
    	failedCount = 0;
    	droppedCount = 0;
    	deansListCount = 0;
    	sumAverages = 0;
    	highestAvg = 0;
    	lowestAvg = 0;
    	highestName = "";
    	lowestName = "";
    }
    
    static void updateSummary(String name, double average, double grade, boolean dropped) {
    	studentsEncoded++;
    	sumAverages += average;
    	
    	if (studentsEncoded == 1 || average > highestAvg) {
    		highestAvg = average;
    		highestName = name;
    	}
    	if (studentsEncoded == 1 || average < lowestAvg) {
    		lowestAvg = average;
    		lowestName = name;
    	}
    	
    	if (dropped) {
    		droppedCount++;
    	} else if (grade <= 3.00) {
    		passedCount++;
    		if (grade <= 1.75) {
    			deansListCount++;
    		}
    	} else {
    		failedCount++;
    	}
    }

	//display ng results for the whole class
    static void displaySummary() {
    	double classAverage = sumAverages / studentsEncoded;
    	
    	System.out.println("\n========== CLASS SUMMARY ==========");
    	System.out.println("Students encoded        : " + studentsEncoded);
    	System.out.printf("Class average           : %.2f%n", classAverage);
    	System.out.printf("Highest average         : %.2f (%s)%n", highestAvg, highestName);
    	System.out.printf("Lowest average          : %.2f (%s)%n", lowestAvg, lowestName);
    	System.out.println("Passed                  : " + passedCount);
    	System.out.println("Failed                  : " + failedCount);
    	System.out.println("Dropped                 : " + droppedCount);
    	System.out.println("Grade of 1.75 or better : " + deansListCount);
    }

	//loop kung mag another class pa o hindi
    static boolean askAnotherClass() {
    	while (true) {
    		System.out.print("\nEncode another class? (Y/N): ");
    		String answer = s.nextLine().trim();
    		
    		if (answer.equalsIgnoreCase("Y")) {
    			return true;
    		} else if (answer.equalsIgnoreCase("N")) {
    			return false;
    		}
    		System.out.println("Invalid choice! Please enter Y or N only.");
    	}
    }
    
}
