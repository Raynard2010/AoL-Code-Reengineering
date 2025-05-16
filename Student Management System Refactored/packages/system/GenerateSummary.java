package packages.system;

import java.util.Scanner;

import packages.fileHandler.TxtFileHandler;

public class GenerateSummary {
    static Scanner scanner = new Scanner(System.in);
    static StudentSystem system = new StudentSystem();
    
    private static void printMenu() {
        System.out.println("Choose an option to output generated summary:");
        System.out.println(" 1. Output the Summary in the Console.");
        System.out.println(" 2. Output the Summary in the Report File.");
        System.out.println(" 0. Exit Summary.");
    }
	
	public static void generateSummary() {
		printMenu();

        int summaryChoice = InputValidator.inputValidChoice(scanner);
        if (summaryChoice == 0) {            
            System.out.println();
            return;
        }

        switch (summaryChoice) {
            case 1:            
                system.generateSummary();
                break;

            case 2:             
                TxtFileHandler.writeTxtFile(system);
                break;

            default:          
                System.out.println("Invalid choice, please try again.");
        }
	}
}
