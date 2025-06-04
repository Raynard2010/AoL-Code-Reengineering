package packages.system;

import java.util.Scanner;

import packages.fileHandler.TxtFileHandler;

public class SummaryGenerator {
    static Scanner scanner = new Scanner(System.in);
    
    private StudentSystem system;
    private StudentSorter studentSorter = new StudentSorter(system);
	
	public SummaryGenerator(StudentSystem system) {
		this.system = system;
	}
	
	   public void countTotalStudents() {
	        System.out.println("The Total number of students: " + system.getStudentList().size());
	        System.out.println();
	    }

	    public void calculateAverageGPA() {
	        double sum = 0;
	        for (Student student : system.getStudentList()) {
	            sum += student.getGPA();
	        }
	        System.out.println("The Average GPA: " + String.format("%.2f" ,sum / system.getStudentList().size()));
	        System.out.println();
	    }

	    public void displayTop5() {
	    	studentSorter.sortStudentsBy("GPA", system.getStudentList());
	        System.out.println("------------------------------ TOP 5 PERFORMING STUDENTS ------------------------------\n");
	        int numberOfYears = 0;
	        String [] years = {"First", "Second", "Third", "Fourth"};
	        while (numberOfYears < 4) {    
	            System.out.println(years[numberOfYears] + " Year:");
	            int count = 1;
	            boolean isFound = false;
	            system.printListHeader();

	            for (Student student : system.getStudentList()) {
	                if (student.getYear().equals(years[numberOfYears]) && count <= 5 && !student.isFailing()) {
	                    isFound = true;
	                    System.out.print(" " + (count++) + ". ");
	                    system.printStudentData(student);
	                }
	            }
	            numberOfYears++;
	            if (!isFound) System.out.println(" No students found!\n");    
	            else System.out.println();
	        }
	    }

	    public void displayFailingStudents() {
	        System.out.println("-------------------- FAILING STUDENTS (who have GPA less than 2.0) --------------------\n");
	        int numberOfYears = 0;
	        String [] years = {"First", "Second", "Third", "Fourth"};
	        while (numberOfYears < 4) {     

	            System.out.println(years[numberOfYears] + " Year:");
	            system.printListHeader();

	            boolean isFailing = false;
	            for (Student student : system.getStudentList()) {
	                if (student.getYear().equals(years[numberOfYears]) && student.isFailing()) {
	                    isFailing = true;
	                    system.printStudentData(student);
	                }
	            }

	            numberOfYears++;
	            if (!isFailing) System.out.println(" No failing students found!\n");    
	            else System.out.println();
	        }
	        System.out.println();
	    }

	    private void printCountStudentsByYear(String year, int total, int successful) {
	    	System.out.println(" - " + year + " Year: " + total + ", With Success rate of " + String.format("%.3f" , successful * 100.0 / total) + "%");
	    }
	    
	    private void countStudentsByYear() {
	        int totalFirst = 0, totalSecond = 0, totalThird = 0, totalFourth = 0;
	        int successfulFirst = 0, successfulSecond = 0, successfulThird = 0, successfulFourth = 0;

	        for (Student student : system.getStudentList()) {
	            switch (student.getYear()) {
	                case "First" -> {
	                    totalFirst++;
	                    if (!student.isFailing()) successfulFirst++;
	                }
	                case "Second" -> {
	                    totalSecond++;
	                    if (!student.isFailing()) successfulSecond++;
	                }
	                case "Third" -> {
	                    totalThird++;
	                    if (!student.isFailing()) successfulThird++;
	                }
	                case "Fourth" -> {
	                    totalFourth++;
	                    if (!student.isFailing()) successfulFourth++;
	                }
	            }
	        }

	        System.out.println("Year-wise Student Count:");
	        printCountStudentsByYear("First", totalFirst, successfulFirst);
	        printCountStudentsByYear("Second", totalSecond, successfulSecond);
	        printCountStudentsByYear("Third", totalThird, successfulThird);
	        printCountStudentsByYear("Fourth", totalFourth, successfulFourth);
	        System.out.println();
	    }

	    private void generateSummaryInConsole() {
	    	if(system.getStudentList().isEmpty()) return;

	        System.out.println("\n------------------------------ CLASS PERFORMANCE ------------------------------\n");
	        calculateAverageGPA();
	        countTotalStudents();
	        countStudentsByYear();
	        displayTop5();
	        displayFailingStudents();
	    }
	    
	    private static void printMenuGenerateSummary() {
	        System.out.println("Choose an option to output generated summary:");
	        System.out.println(" 1. Output the Summary in the Console.");
	        System.out.println(" 2. Output the Summary in the Report File.");
	        System.out.println(" 0. Exit Summary.");
	    }
		
		public void generateSummary() {
			printMenuGenerateSummary();

	        int summaryChoice = InputValidator.inputValidChoice(scanner);
	        if (summaryChoice == 0) {            
	            System.out.println();
	            return;
	        }

	        switch (summaryChoice) {
	            case 1:            
	                this.generateSummaryInConsole();
	                break;

	            case 2:             
	                TxtFileHandler.writeTxtFile(system, studentSorter);
	                break;

	            default:          
	                System.out.println("Invalid choice, please try again.");
	        }
		}

}
