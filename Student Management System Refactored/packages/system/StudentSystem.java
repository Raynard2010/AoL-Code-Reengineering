package packages.system;
import java.util.ArrayList;
import java.util.Scanner;

import packages.fileHandler.CsvFileHandler;

    // ---------------------------------------------- STUDENT SYSTEM CLASS ----------------------------------------------

public class StudentSystem {
    private final ArrayList<Student> studentList;
    static Scanner scanner = new Scanner(System.in);

    public StudentSystem() {this.studentList = new ArrayList<>();}
    public ArrayList<Student> getStudentList() {return studentList;}
    
    public void mergeStudentSystem(StudentSystem otherSystem) {
        int nonUniqueID = 0, nonUniqueName = 0, oldSize = this.studentList.size();
        for (Student student : otherSystem.getStudentList()) {
            boolean isFound = false;
            for (Student student1 : this.studentList) {
                if (student1.getID() == student.getID()) {
                    nonUniqueID++;
                    isFound = true;
                    break;
                }
                if (student1.getName().equals(student.getName())) {
                    nonUniqueName++;
                    isFound = true;
                    break;
                }
            }
            if (!isFound) this.studentList.add(student);
        }

        if (nonUniqueID > 0) System.out.println(nonUniqueID + " students with non-unique ID(s) were not added to the system.");
        if (nonUniqueName > 0) System.out.println(nonUniqueName + " students with non-unique name(s) were not added to the system.");

        if (oldSize == 0) System.out.println("Student system Added successfully!");
        else if (this.studentList.size() > oldSize) System.out.println("Student system merged successfully!");
        else System.out.println("No new students were added to the system.");
        System.out.println();
    }

    private static void printMenuAddStudent() {
        System.out.println("Choose an option to add student:");
        System.out.println(" 1. Add Student from CSV File.");
        System.out.println(" 2. Add Student Manually.");
        System.out.println(" 0. Exit Add Student.");
    }
    
    private void addStudentManually() {
    	Student newStudent = new Student();
    	newStudent.setName(InputValidator.addUniqueName(scanner, this));
    	newStudent.setID(InputValidator.addUniqueID(scanner, this));
    	newStudent.setGPA(InputValidator.inputValidGPA(scanner));
    	newStudent.setYear(InputValidator.inputValidYear(scanner));
    	newStudent.setDepartment(InputValidator.inputValidDepartment(scanner));

        if (newStudent.getYear().equals("First") || newStudent.getYear().equals("Second")) newStudent.setDepartment("General");

        this.addStudentToList(newStudent, false);
    }
    
	public void addStudent() {
        while (true) {
        	printMenuAddStudent();

            int addChoice = InputValidator.inputValidChoice(scanner);

            if (addChoice == 0) {       
                System.out.println();
                break;
            }

            switch (addChoice) {
                case 1:         
                    this.mergeStudentSystem(CsvFileHandler.readCsvFile());
                    break;

                case 2:         
                	this.addStudentManually();
                    break;

                default:        
                    System.out.println("Invalid choice, please try again.");
            }
        }
	}


    public void addStudentToList(Student newStudent, boolean byCSV) {
        this.studentList.add(newStudent);
        if (!byCSV) System.out.println("Student added successfully!\n");
    }

    public void removeStudentByID(int ID) {
        for (int i = 0; i < this.studentList.size(); i++) {
            if (this.studentList.get(i).getID() == ID) {
                this.studentList.remove(i);
                System.out.println("Student removed successfully!\n");
                return;
            }
        }
        System.out.println("Student not found!\n");
    }
    
    private <T> void updateStudentByID(int ID, int updateChoice, T newData) {
        for (Student student : this.studentList) {
            if (student.getID() == ID) {
                if (updateChoice == 1) student.setName((String) newData);
                if (updateChoice == 2) student.setGPA((double) newData);
                if (updateChoice == 3) student.setYear((String) newData);
                if (updateChoice == 4) student.setDepartment((String) newData);
                System.out.println("Student updated successfully!\n");
                return;
            }
        }

        System.out.println("Student not found!\n");
    }
    
	private static void printMenuUpdateStudent() {
        System.out.println("Choose an option to update:");
        System.out.println(" 1. Student's Name.");
        System.out.println(" 2. Student's GPA.");
        System.out.println(" 3. Student's Year.");
        System.out.println(" 4. Student's Department.");
        System.out.println(" 0. Exit Update.");
	}
	
	public void updateStudent() {
        int updateID = InputValidator.inputValidID(scanner);

        while (true) {
        	printMenuUpdateStudent();

            int updateChoice = InputValidator.inputValidChoice(scanner);
            if (updateChoice == 0) {     
                System.out.println();
                break;
            }

            switch (updateChoice) {
                case 1:             
                    String newName = InputValidator.addUniqueName(scanner, this);
                    this.updateStudentByID(updateID, updateChoice, newName);
                    break;

                case 2:            
                    double newGPA = InputValidator.inputValidGPA(scanner);
                    scanner.nextLine();
                    this.updateStudentByID(updateID, updateChoice, newGPA);
                    break;

                case 3:            
                    String newYear = InputValidator.inputValidYear(scanner);
                    scanner.nextLine();
                    this.updateStudentByID(updateID, updateChoice, newYear);
                    break;

                case 4:             
                    String newDepartment = InputValidator.inputValidDepartment(scanner);
                    scanner.nextLine();
                    this.updateStudentByID(updateID, updateChoice, newDepartment);
                    break;

                default:            
                    System.out.println("Invalid choice, please try again.");
            }
        }
	}
	
    public void printListHeader() {
        System.out.printf("%-20s  |  %-8s  |  %-4s  |  %-6s  |  %-5s%n",
                "Name", "ID", "GPA", "Year", "Dept");
        System.out.println("------------------------------------------------------------------");
    }
    
    public void printStudentData(Student student) {
        System.out.printf("%-20s  |  %-8s  |  %-4.2f  |  %-6s  |  %-5s%n",
                student.getName(), student.getID(), student.getGPA(), student.getYear(), student.getDepartment());
    }

    public void searchByID(int ID) {
        for (Student student : this.studentList) {
            if (student.getID() == ID) {
                System.out.println("Student found!");
                printListHeader();
                printStudentData(student);
                System.out.println();
                return;
            }
        }
        System.out.println("Student not found!\n");
    }
}
