package packages.system;

import java.util.Scanner;

import packages.fileHandler.CsvFileHandler;

public class ListAndSortStudents {
    static Scanner scanner = new Scanner(System.in);
    static StudentSystem system = new StudentSystem();
        
    private static void printMenu() {
        System.out.println("Output all students in:");
        System.out.println(" 1. The Console.");
        System.out.println(" 2. A File.");
        System.out.println(" 0. Exit List and Sort.");
    }
    
    private static String scanSortingCriteria() {
        System.out.print("Enter sorting criteria (GPA, ID, Name, Year): ");
        return scanner.nextLine();
    }
    
	public static void listAndSortStudents() {
        while (true) {
        	printMenu();

            int outputChoice = InputValidator.inputValidChoice(scanner);

            if (outputChoice == 0) {           
                System.out.println();
                break;
            }

            else if (outputChoice == 1) {      
                String sortBy = scanSortingCriteria();

                while (!sortBy.matches("GPA|ID|Name|Year")) {
                    System.out.println("Invalid input. Please enter a valid sorting criteria.");
                    sortBy = scanSortingCriteria();
                }

                system.listAndSortAllStudents(sortBy);
                break;
            }

            else if (outputChoice == 2) {        
                CsvFileHandler.writeCsvFile(system);
                break;
            }

            else System.out.println("Invalid choice, please try again.\n");    
        }
	}
}
