package packages.system;

import java.util.Scanner;

public class UpdateStudent {
    static Scanner scanner = new Scanner(System.in);
    static StudentSystem system = new StudentSystem();
    
	private static void printMenu() {
        System.out.println("Choose an option to update:");
        System.out.println(" 1. Student's Name.");
        System.out.println(" 2. Student's GPA.");
        System.out.println(" 3. Student's Year.");
        System.out.println(" 4. Student's Department.");
        System.out.println(" 0. Exit Update.");
	}
	
	public static void updateStudent() {
        int updateID = InputValidator.inputValidID(scanner);

        while (true) {
        	printMenu();

            int updateChoice = InputValidator.inputValidChoice(scanner);
            if (updateChoice == 0) {     
                System.out.println();
                break;
            }

            switch (updateChoice) {
                case 1:             
                    String newName = InputValidator.addUniqueName(scanner, system);
                    system.updateStudentByID(updateID, updateChoice, newName);
                    break;

                case 2:            
                    double newGPA = InputValidator.inputValidGPA(scanner);
                    scanner.nextLine();
                    system.updateStudentByID(updateID, updateChoice, newGPA);
                    break;

                case 3:            
                    String newYear = InputValidator.inputValidYear(scanner);
                    scanner.nextLine();
                    system.updateStudentByID(updateID, updateChoice, newYear);
                    break;

                case 4:             
                    String newDepartment = InputValidator.inputValidDepartment(scanner);
                    scanner.nextLine();
                    system.updateStudentByID(updateID, updateChoice, newDepartment);
                    break;

                default:            
                    System.out.println("Invalid choice, please try again.");
            }
        }
	}
}
