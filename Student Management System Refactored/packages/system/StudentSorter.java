package packages.system;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Scanner;

import packages.fileHandler.CsvFileHandler;

public class StudentSorter {
    static Scanner scanner = new Scanner(System.in);
	
    private StudentSystem system;
	
	public StudentSorter(StudentSystem system) {
		this.system = system;
	}
	
    private void listAndSortAllStudents(String sortBy) {
    	if(system.getStudentList().isEmpty()) return;
        
        sortStudentsBy(sortBy, system.getStudentList());

        System.out.println("\nList of Students:");
        system.printListHeader();

        for (Student student : system.getStudentList()) {
        	system.printStudentData(student);
        }
        System.out.println();
    }
    
    private static void printMenuListAndSortStudents() {
        System.out.println("Output all students in:");
        System.out.println(" 1. The Console.");
        System.out.println(" 2. A File.");
        System.out.println(" 0. Exit List and Sort.");
    }
    
    private static String scanSortingCriteria() {
        System.out.print("Enter sorting criteria (GPA, ID, Name, Year): ");
        return scanner.nextLine();
    }
    
	public void listAndSortStudents() {
        while (true) {
        	printMenuListAndSortStudents();

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

                this.listAndSortAllStudents(sortBy);
                break;
            }

            else if (outputChoice == 2) {
            	ArrayList<Student> sortedStudent = (ArrayList) system.getStudentList().clone();
            	
            	this.sortStudentsBy("ID", sortedStudent);
            	
                CsvFileHandler.writeCsvFile(sortedStudent);
                break;
            }

            else System.out.println("Invalid choice, please try again.\n");    
        }
	}
	
    public void sortStudentsBy(String sortBy, ArrayList<Student> studentList) {
        switch (sortBy) {
            case "GPA" ->
            studentList.sort((firstStudent, secondStudent) -> Double.compare(secondStudent.getGPA(), firstStudent.getGPA()));
            case "ID" ->
            studentList.sort(Comparator.comparingInt(firstStudent -> firstStudent.getID()));
            case "Name" ->
            studentList.sort(Comparator.comparing(firstStudent -> firstStudent.getName()));
            case "Year" ->
            studentList.sort(Comparator.comparingInt(student -> switch (student.getYear()) {
                        case "First" -> 1;
                        case "Second" -> 2;
                        case "Third" -> 3;
                        case "Fourth" -> 4;
                        default -> 0;
                    }));
        }
    }
}
