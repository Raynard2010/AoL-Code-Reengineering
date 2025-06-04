import java.util.*;
import packages.system.*;

    // ---------------------------------------------- MAIN FUNCTION ----------------------------------------------

public class App {
    static Scanner scanner = new Scanner(System.in);
    static StudentSystem system = new StudentSystem();
    static SummaryGenerator summaryGenerator = new SummaryGenerator(system);
    static StudentFilter studentFilter = new StudentFilter(system);
    static StudentSorter studentSorter = new StudentSorter(system);

	private static void printMenu() {
        System.out.println("Choose an option:");
        System.out.println(" 1. Add Student.");
        System.out.println(" 2. Remove Student.");
        System.out.println(" 3. Update Student.");
        System.out.println(" 4. Search Student by ID.");
        System.out.println(" 5. List and Sort Students.");
        System.out.println(" 6. Filter Students.");
        System.out.println(" 7. Count Total Students.");
        System.out.println(" 8. Calculate Average GPA.");
        System.out.println(" 9. Display Top 5 Students.");
        System.out.println(" 10. Display Failing Students.");
        System.out.println(" 11. Generate Summary.");
        System.out.println(" 0. Exit Application.");
	}
	
	private static void printExitMessage() {
        System.out.println("--- Thank you for using Student Management System Application!");
        System.out.println("\t--- Exiting system...");
	}
		
    public static void main(String[] args) {
        System.out.println("\n\t------------ WELCOME TO STUDENT MANAGEMENT SYSTEM ------------\n");

        while (true) {
        	printMenu();

            int choice = InputValidator.inputValidChoice(scanner);
            System.out.println();

            switch (choice) {
                case 1:     
                	system.addStudent();
                    break;

                case 2:             
                    int removeID = InputValidator.inputValidID(scanner);
                    system.removeStudentByID(removeID);
                    break;

                case 3:             
                	system.updateStudent();
                    break;

                case 4:             
                    int searchID = InputValidator.inputValidID(scanner);
                    system.searchByID(searchID);
                    break;

                case 5:             
                	studentSorter.listAndSortStudents();
                    break;

                case 6:             
                	studentFilter.filterStudents();
                    break;

                case 7:             
                	summaryGenerator.countTotalStudents();
                    break;

                case 8:             
                	summaryGenerator.calculateAverageGPA();
                    break;

                case 9:             
                	summaryGenerator.displayTop5();
                    break;

                case 10:            
                	summaryGenerator.displayFailingStudents();
                    break;

                case 11:            
                	summaryGenerator.generateSummary();
                    break;

                case 0:             
                	printExitMessage();
                    scanner.close();
                    return;

                default:            
                    System.out.println("Invalid choice, please try again.\n");
            }
        }
    }
}