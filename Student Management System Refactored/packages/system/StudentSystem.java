package packages.system;
import java.util.ArrayList;
import java.util.Comparator;

    // ---------------------------------------------- STUDENT SYSTEM CLASS ----------------------------------------------

public class StudentSystem {
    private final ArrayList<Student> studentList;

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

    public void sortStudentsBy(String sortBy) {
        switch (sortBy) {
            case "GPA" ->
                    this.studentList.sort((firstStudent, secondStudent) -> Double.compare(secondStudent.getGPA(), firstStudent.getGPA()));
            case "ID" ->
                    this.studentList.sort(Comparator.comparingInt(firstStudent -> firstStudent.getID()));
            case "Name" ->
                    this.studentList.sort(Comparator.comparing(firstStudent -> firstStudent.getName()));
            case "Year" ->
                    this.studentList.sort(Comparator.comparingInt(student -> switch (student.getYear()) {
                        case "First" -> 1;
                        case "Second" -> 2;
                        case "Third" -> 3;
                        case "Fourth" -> 4;
                        default -> 0;
                    }));
        }
    }

    public void addStudent(Student newStudent, boolean byCSV) {
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
    
    public <T> void updateStudentByID(int ID, int updateChoice, T newData) {
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
    
    private void printListHeader() {
        System.out.printf("%-20s  |  %-8s  |  %-4s  |  %-6s  |  %-5s%n",
                "Name", "ID", "GPA", "Year", "Dept");
        System.out.println("------------------------------------------------------------------");
    }
    
    private void printStudentData(Student student) {
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
    
    private boolean isEmptyStudentList() {
        if (this.studentList.isEmpty()) {
            System.out.println("No students found!\n");
            return true;
        }
        else return false;
    }

    public void listAndSortAllStudents(String sortBy) {
    	if(isEmptyStudentList()) return;
        
        sortStudentsBy(sortBy);

        System.out.println("\nList of Students:");
        printListHeader();

        for (Student student : this.studentList) {
            printStudentData(student);
        }
        System.out.println();
    }
   
    public void filterByGPA(double GPA) {
        System.out.println("Students with GPA of " + GPA + ":");
        printListHeader();

        boolean isFound = false;
        for (Student student : this.studentList) {
            if (student.getGPA() == GPA) {
                isFound = true;
                printStudentData(student);
            }
        }

        if (!isFound) System.out.println("No students found with GPA of " + GPA + "!\n");
        else System.out.println();
    }

    public void filterByYear(String year) {
        System.out.println("Students in " + year + " year:");
        printListHeader();

        boolean isFound = false;
        for (Student student : this.studentList) {
            if (student.getYear().equals(year)) {
                isFound = true;
                printStudentData(student);
            }
        }

        if (!isFound) System.out.println("No students found in " + year + " year!\n");
        else System.out.println();
    }

    public void filterByDepartment(String department) {
        System.out.println("Students in " + department + " department:");
        printListHeader();

        boolean isFound = false;
        for (Student student : this.studentList) {
            if (student.getDepartment().equals(department)) {
                isFound = true;
                printStudentData(student);
            }
        }

        if (!isFound) System.out.println("No students found in " + department + " department!\n");
        else System.out.println();
    }

    public void countTotalStudents() {
        System.out.println("The Total number of students: " + this.studentList.size());
        System.out.println();
    }

    public void calculateAverageGPA() {
        double sum = 0;
        for (Student student : this.studentList) {
            sum += student.getGPA();
        }
        System.out.println("The Average GPA: " + String.format("%.2f" ,sum / this.studentList.size()));
        System.out.println();
    }

    public void displayTop5() {
        sortStudentsBy("GPA");
        System.out.println("------------------------------ TOP 5 PERFORMING STUDENTS ------------------------------\n");
        int numberOfYears = 0;
        String [] years = {"First", "Second", "Third", "Fourth"};
        while (numberOfYears < 4) {    
            System.out.println(years[numberOfYears] + " Year:");
            int count = 1;
            boolean isFound = false;
            printListHeader();

            for (Student student : this.studentList) {
                if (student.getYear().equals(years[numberOfYears]) && count <= 5 && student.getGPA() >= 2.0) {
                    isFound = true;
                    System.out.print(" " + (count++) + ". ");
                    printStudentData(student);
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
            printListHeader();

            boolean isFailing = false;
            for (Student student : this.studentList) {
                if (student.getYear().equals(years[numberOfYears]) && student.getGPA() < 2.0) {
                    isFailing = true;
                    printStudentData(student);
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
    
    public void countStudentsByYear() {
        int totalFirst = 0, totalSecond = 0, totalThird = 0, totalFourth = 0;
        int successfulFirst = 0, successfulSecond = 0, successfulThird = 0, successfulFourth = 0;

        for (Student student : studentList) {
            switch (student.getYear()) {
                case "First" -> {
                    totalFirst++;
                    if (student.getGPA() >= 2.0) successfulFirst++;
                }
                case "Second" -> {
                    totalSecond++;
                    if (student.getGPA() >= 2.0) successfulSecond++;
                }
                case "Third" -> {
                    totalThird++;
                    if (student.getGPA() >= 2.0) successfulThird++;
                }
                case "Fourth" -> {
                    totalFourth++;
                    if (student.getGPA() >= 2.0) successfulFourth++;
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

    public void generateSummary() {
    	if(isEmptyStudentList()) return;

        System.out.println("\n------------------------------ CLASS PERFORMANCE ------------------------------\n");
        calculateAverageGPA();
        countTotalStudents();
        countStudentsByYear();
        displayTop5();
        displayFailingStudents();
    }
}
