package packages.system;

import java.util.Scanner;

import packages.fileHandler.CsvFileHandler;

public class AddStudent {
    static Scanner scanner = new Scanner(System.in);
    static StudentSystem system = new StudentSystem();
    
    private static void printMenu() {
        System.out.println("Choose an option to add student:");
        System.out.println(" 1. Add Student from CSV File.");
        System.out.println(" 2. Add Student Manually.");
        System.out.println(" 0. Exit Add Student.");
    }
    
    private static void addStudentManually() {
    	Student newStudent = new Student();
    	newStudent.setName(InputValidator.addUniqueName(scanner, system));
    	newStudent.setID(InputValidator.addUniqueID(scanner, system));
    	newStudent.setGPA(InputValidator.inputValidGPA(scanner));
    	newStudent.setYear(InputValidator.inputValidYear(scanner));
    	newStudent.setDepartment(InputValidator.inputValidDepartment(scanner));

        if (newStudent.getYear().equals("First") || newStudent.getYear().equals("Second")) newStudent.setDepartment("General");

        system.addStudent(newStudent, false);
    }
    
	public static void addStudent() {
        while (true) {
        	printMenu();

            int addChoice = InputValidator.inputValidChoice(scanner);

            if (addChoice == 0) {       
                System.out.println();
                break;
            }

            switch (addChoice) {
                case 1:         
                    system.mergeStudentSystem(CsvFileHandler.readCsvFile());
                    break;

                case 2:         
                	addStudentManually();
                    break;

                default:        
                    System.out.println("Invalid choice, please try again.");
            }
        }
	}
}
