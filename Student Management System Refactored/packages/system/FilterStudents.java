package packages.system;

import java.util.Scanner;

public class FilterStudents {
    static Scanner scanner = new Scanner(System.in);
    static StudentSystem system = new StudentSystem();
    
    private static String scanFilterChoice() {
        System.out.print("Choose an option to filter (GPA - Year - Department): ");
        return scanner.nextLine();
    }
	
	public static void filterStudents() {
        String filterChoice = scanFilterChoice();

        while (!filterChoice.matches("GPA|Year|Department")) {
            System.out.println("Invalid input. Please enter a valid year.");
            filterChoice = scanFilterChoice();
        }

        switch (filterChoice) {
            case "GPA":
                double filterGPA = InputValidator.inputValidGPA(scanner);
                System.out.println();
                system.filterByGPA(filterGPA);
                break;

            case "Year":
                String filterYear = InputValidator.inputValidYear(scanner);
                System.out.println();
                system.filterByYear(filterYear);
                break;

            case "Department":
                String filterDepartment = InputValidator.inputValidDepartment(scanner);
                System.out.println();
                system.filterByDepartment(filterDepartment);
                break;

            default:
                System.out.println("Invalid choice, please try again.\n");
        }
	}
}
