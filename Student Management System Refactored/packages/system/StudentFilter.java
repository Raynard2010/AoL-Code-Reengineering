package packages.system;

import java.util.Scanner;

public class StudentFilter {
    static Scanner scanner = new Scanner(System.in);
	
    private StudentSystem system;
	
	public StudentFilter(StudentSystem system) {
		this.system = system;
	}
	
    private void filterByGPA(double GPA) {
        System.out.println("Students with GPA of " + GPA + ":");
        system.printListHeader();

        boolean isFound = false;
        for (Student student : system.getStudentList()) {
            if (student.getGPA() == GPA) {
                isFound = true;
                system.printStudentData(student);
            }
        }

        if (!isFound) System.out.println("No students found with GPA of " + GPA + "!\n");
        else System.out.println();
    }

    private void filterByYear(String year) {
        System.out.println("Students in " + year + " year:");
        system.printListHeader();

        boolean isFound = false;
        for (Student student : system.getStudentList()) {
            if (student.getYear().equals(year)) {
                isFound = true;
                system.printStudentData(student);
            }
        }

        if (!isFound) System.out.println("No students found in " + year + " year!\n");
        else System.out.println();
    }

    private void filterByDepartment(String department) {
        System.out.println("Students in " + department + " department:");
        system.printListHeader();

        boolean isFound = false;
        for (Student student : system.getStudentList()) {
            if (student.getDepartment().equals(department)) {
                isFound = true;
                system.printStudentData(student);
            }
        }

        if (!isFound) System.out.println("No students found in " + department + " department!\n");
        else System.out.println();
    }
    
    private static String scanFilterChoice() {
        System.out.print("Choose an option to filter (GPA - Year - Department): ");
        return scanner.nextLine();
    }
	
	public void filterStudents() {
        String filterChoice = scanFilterChoice();

        while (!filterChoice.matches("GPA|Year|Department")) {
            System.out.println("Invalid input. Please enter a valid year.");
            filterChoice = scanFilterChoice();
        }

        switch (filterChoice) {
            case "GPA":
                double filterGPA = InputValidator.inputValidGPA(scanner);
                System.out.println();
                this.filterByGPA(filterGPA);
                break;

            case "Year":
                String filterYear = InputValidator.inputValidYear(scanner);
                System.out.println();
                this.filterByYear(filterYear);
                break;

            case "Department":
                String filterDepartment = InputValidator.inputValidDepartment(scanner);
                System.out.println();
                this.filterByDepartment(filterDepartment);
                break;

            default:
                System.out.println("Invalid choice, please try again.\n");
        }
	}
}
