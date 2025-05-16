package packages.fileHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import packages.system.*;

    // --------------------------------- TXT FILE HANDLER CLASS ---------------------------------

public class TxtFileHandler {
    private static final String OUTPUT_TXT_FILE = "Final_Report.txt";
    
    private static void writeTotalNumberOfStudent(FileWriter writer, ArrayList<Student> studentList) throws IOException {
        writer.write("\nThe Total number of students: " + studentList.size());
        writer.write("\n\n");
    }
    
    private static void writeAverageGPA(FileWriter writer, ArrayList<Student> studentList) throws IOException {
        double sum = 0;
        for (Student student : studentList) {
            sum += student.getGPA();
        }
        writer.write("The Average GPA: " + String.format("%.2f" ,sum / studentList.size()));
        writer.write("\n\n");
    }
    
    private static void writeCountStudentsByYear(FileWriter writer, String year, int total, int successful) throws IOException {
    	writer.write("\n - " + year + " Year: " + total + ", With Success rate of " + String.format("%.3f" , successful * 100.0 / total) + "%");
    }
    
    private static void writeStudentCountAndSuccessRate(FileWriter writer, ArrayList<Student> studentList) throws IOException{
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
        
        writer.write("Year-wise Student Count:");
        writeCountStudentsByYear(writer, "First", totalFirst, successfulFirst);
        writeCountStudentsByYear(writer, "Second", totalSecond, successfulSecond);
        writeCountStudentsByYear(writer, "Third", totalThird, successfulThird);
        writeCountStudentsByYear(writer, "Fourth", totalFourth, successfulFourth);
        writer.write("\n\n");
    }
    
    private static void writeListHeader(FileWriter writer, String year) throws IOException {
        writer.write(year + " Year:\n");
        writer.write(String.format(" %-23s  |  %-8s  |  %-4s  |  %-6s  |  %-5s%n",
                "Name", "ID", "GPA", "Year", "Department"));
        writer.write("-------------------------------------------------------------------------\n");
    }
    
    private static void writeListData(FileWriter writer, Student student) throws IOException {
        writer.write(String.format("%-20s  |  %-8s  |  %-4.2f  |  %-6s  |  %-5s%n",
                student.getName(), student.getID(), student.getGPA(), student.getYear(), student.getDepartment()));
    }
    
    private static void writeTopPerformingStudents(FileWriter writer, ArrayList<Student> studentList) throws IOException {
        writer.write("------------------------------ TOP 5 PERFORMING STUDENTS ------------------------------\n\n");
        int numberOfYears = 0;
        String [] years = {"First", "Second", "Third", "Fourth"};

        while (numberOfYears < 4) {         
        	writeListHeader(writer, years[numberOfYears]);

            int count = 1;
            for (Student student : studentList) {
                if (student.getYear().equals(years[numberOfYears]) && count <= 5 && student.getGPA() >= 2.0) {
                    writer.write(" " + (count++) + ". ");
                    writeListData(writer, student);
                }
            }
            numberOfYears++;
            writer.write("\n");
        }
    }
    
    private static void writeFailingStudents(FileWriter writer, ArrayList<Student> studentList) throws IOException {
        writer.write("-------------------- FAILING STUDENTS (who have GPA less than 2.0) --------------------\n\n");
        int numberOfYears = 0;
        String [] years = {"First", "Second", "Third", "Fourth"};

        while (numberOfYears < 4) {     
        	writeListHeader(writer, years[numberOfYears]);

            boolean isFailing = false;
            for (Student student : studentList) {
                if (student.getYear().equals(years[numberOfYears]) && student.getGPA() < 2.0) {
                    isFailing = true;
                    writeListData(writer, student);
                }
            }

            if (!isFailing) writer.write(" No failing students found!\n");       
            else writer.write("\n");
            numberOfYears++;
        }
    }

    public static void writeTxtFile(StudentSystem students) {
        try (FileWriter writer = new FileWriter(OUTPUT_TXT_FILE)) {
            if (students.getStudentList().isEmpty()) {
                System.out.println("\nNo students to write to txt file.\n");
                return;
            }

            writer.write("------------------------------ CLASS PERFORMANCE ------------------------------\n");
            students.sortStudentsBy("GPA");
            ArrayList<Student> studentList = students.getStudentList();
            
            writeTotalNumberOfStudent(writer, studentList);

            writeAverageGPA(writer, studentList);

            writeStudentCountAndSuccessRate(writer, studentList);
            
            writeTopPerformingStudents(writer, studentList);
            
            writeFailingStudents(writer, studentList);

            writer.flush();
            System.out.println("\nTxt file written successfully.");
            System.out.println("File Path: " + OUTPUT_TXT_FILE);
        }

        catch (IOException e) {
            System.err.println("Error writing Txt file: " + e.getMessage());
        }
        System.out.println();
    }
}
